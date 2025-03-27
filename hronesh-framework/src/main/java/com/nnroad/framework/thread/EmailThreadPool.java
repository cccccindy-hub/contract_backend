package com.nnroad.framework.thread;


import java.util.concurrent.*;

public class EmailThreadPool {

    /**
     * 任务处理失败，直接丢弃
     */
    private static final ThreadPoolExecutor EMAIL_THREAD_POOL = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors() * 2, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100000),
            new ThreadPoolExecutor.AbortPolicy());

    public static void execute(Runnable command) {
        EMAIL_THREAD_POOL.execute(command);
    }

    /**
     * submit 的异常不会被线程异常拦截器拦截，调用者需要自己调用get方法，以便于对异常进行捕获
     *
     * @param command 任务
     * @return future
     */
    public static <T> Future<T> submit(Callable<T> command) {
        return EMAIL_THREAD_POOL.submit(command);
    }
}