package com.nnroad.payroll.rabbit.listener;

import com.nnroad.payroll.config.RabbitConfig;
import com.nnroad.payroll.rabbit.consumer.GzgPayrollConsumer;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
@RequiredArgsConstructor
public class PayrollListener extends AbstractListener<String> {

    private final static String KEY_PREFIX = "payroll_report:confirm:";

    private final GzgPayrollConsumer gzgPayrollConsumer;

    @RabbitListener(queues = "#{rabbitConfig.payrollQueueName}")
    public void payrollListener(Message msg, Channel channel) {
        log.info("接收到确认工资单消息，payrollId:" + new String(msg.getBody(), StandardCharsets.UTF_8));
        super.consumerMsg(msg, channel);
    }

    @Override
    protected String resolverData(Message msg) {
        return new String(msg.getBody(), StandardCharsets.UTF_8);
    }

    @Override
    protected String getUniqueKey(String data) {
        return KEY_PREFIX + data;
    }

    @Override
    protected void consumer(String payrollId) {

        gzgPayrollConsumer.processMessage(payrollId);
    }
}
