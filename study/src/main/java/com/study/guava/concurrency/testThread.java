package com.study.guava.concurrency;

import com.google.common.util.concurrent.*;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * @Author Kervin
 * @ClassName com.study.guava.concurrency
 * @Description 测试线程
 * @Date 2019/11/30 20:25
 * @Version 1.0
 */
public class testThread {

    public static void main(String[] args) {
       new testThread().task();
    }
    public void task() {
        ListeningExecutorService service = ThreadPooBuild.getService();
        service.submit(new Runnable() {
            Set<Object> data = new HashSet<Object>();
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                for (int i = 0; i < 2000000; i++) {
                    data.add(createOrderCode(UUID.randomUUID().toString().replace("-", "")));
                }
                System.out.println(data.size());
                service.shutdown();
                System.out.println("線程執行的時間===== " + ((System.currentTimeMillis() - startTime) / 1000) + "/s");
            }
        });
    }

    /**
     * 订单号生成
     *
     * @param orderId
     * @return
     */
    public String createOrderCode(String orderId) {
        int currentTimeMillis_substr = 8;
        int nanoTime_start = 7;
        int nanoTime_end = 10;
        return Math.abs(orderId.hashCode())
                + Long.toString(System.currentTimeMillis()).substring(currentTimeMillis_substr)
                + Long.toString(System.nanoTime()).substring(nanoTime_start, nanoTime_end);
    }


    @Test
    public void test01(){
       /* ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture explosion = service.submit(new Callable() {
            @Override
            public Object call() {
                return null;
            }
        });

        Futures.addCallback(explosion, new FutureCallback() {
            // we want this handler to run immediately after we push the big red button!
            public void onSuccess(Object explosion) {
                //do something
            }
            public void onFailure(Throwable thrown) {
                //do something
            }});*/
    }
}
