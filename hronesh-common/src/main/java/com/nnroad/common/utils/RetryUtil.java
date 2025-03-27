package com.nnroad.common.utils;

import com.nnroad.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

/**
 * @author wangbi
 * @since 2022/4/21
 */
@Slf4j
public class RetryUtil {

    /**
     * 重试
     *
     * @param task     任务
     * @param retryNum 重试次数
     * @param <T>      返回值
     * @return T
     */
    public static <T> T doRetry(Supplier<T> task, int retryNum) {
        try {
            return task.get();
        } catch (Exception e) {
            retryNum--;
            if (retryNum >= 0) {
                log.info("任务执行失败，重试执行...");
                return doRetry(task, retryNum);
            } else {
                log.error("任务执行失败，原因是：" + e);
                throw new BusinessException(e.getMessage());
            }
        }
    }

    /**
     * 重试
     *
     * @param task     任务
     * @param retryNum 重试次数
     */
    public static void doRetry(Runnable task, int retryNum) {
        try {
            task.run();
        } catch (Exception e) {
            retryNum--;
            if (retryNum >= 0) {
                log.info("任务执行失败，重试执行...");
                doRetry(task, retryNum);
            } else {
                log.error("任务执行失败，原因是：" + e);
                throw new BusinessException(e.getMessage());
            }
        }
    }
}
