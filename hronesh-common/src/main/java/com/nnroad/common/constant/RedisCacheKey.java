package com.nnroad.common.constant;

public interface RedisCacheKey {

    String BASE = "hronesh:";

    String QUEUE_RETRY_KEY = BASE + "queue:retry:%s";
}
