package com.nnroad.payroll.rabbit.listener;

import com.nnroad.common.constant.RedisCacheKey;
import com.nnroad.common.core.redis.RedisCache;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author raven
 * @since 2024/4/26 16:32
 */
@Slf4j
public abstract class AbstractListener<T> {

    @Value("${queue.msg.retry.time:16}")
    private Integer retryTime;

    @Value("${queue.msg.retry.timeout:3}")
    private Integer timeout;

    @Autowired
    private RedisCache redisCache;

    public void consumerMsg(Message msg, Channel channel) {
        T data = resolverData(msg);
        String key = getUniqueKey(data);
        log.info("接收到消息：key-{}, message-{}", key, msg);

        MessageProperties properties = msg.getMessageProperties();
        long deliveryTag = properties.getDeliveryTag();
        try {
            //消息消费
            consumer(data);

            channel.basicAck(deliveryTag, true);
            log.info("消息消费成功：key-{}", key);
        } catch (Exception e) {
            log.error("消息消费异常: e", e);
            try {
                if (requeue(key)) {
                    //需要判断不在消费
                    channel.basicReject(deliveryTag, true);
                    log.info("消息消费失败重新发到队列：key-{}", key);
                } else {
                    channel.basicAck(deliveryTag, false);
                    log.info("消息消费失败判定不在重新发到队列：key-{}", key);
                }
            } catch (IOException ex) {
                log.error("消息回退失败：", e);
            }
        }
    }

    protected abstract T resolverData(Message msg);

    protected abstract String getUniqueKey(T data);

    protected abstract void consumer(T data);

    protected boolean requeue(String key) {
        String cacheKey = String.format(RedisCacheKey.QUEUE_RETRY_KEY, key);
        long retry = redisCache.incr(cacheKey, 1);
        if (retry == 1) {
            redisCache.expire(cacheKey, timeout, TimeUnit.DAYS);
        }
        // RETRY_KEY_TIMEOUT 天内的最大重试次数
        return retry <= retryTime;
    }
}
