package com.study.guava.concurrency;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @Author Kervin
 * @ClassName com.study.guava.concurrency
 * @Description sd
 * @Date 2019/11/30 20:32
 * @Version 1.0
 */
public final class ThreadPooBuild {
    private static ThreadLocal<ThreadPooBuild> instance = new ThreadLocal<ThreadPooBuild>();
    protected static ListeningExecutorService service = null;
    protected static ScheduledThreadPoolExecutor scheduledThreadPool = null;

    public static ListeningExecutorService getService() {
        return service;
    }

    public static ScheduledThreadPoolExecutor getScheduledThreadPool() {
        return scheduledThreadPool;
    }

    static {
        if ((instance.get() == null) || (service == null)) {
            syncInit();
        }
    }

    public static void shutdown() {
        service.shutdown();
    }

    private static void syncInit() {
        service = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        scheduledThreadPool = new ScheduledThreadPoolExecutor(16);
        scheduledThreadPool.setRemoveOnCancelPolicy(true);
    }
}
