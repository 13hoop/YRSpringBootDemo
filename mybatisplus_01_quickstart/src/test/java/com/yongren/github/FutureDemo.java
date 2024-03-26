package com.yongren.github;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.concurrent.*;

@SpringBootTest
public class FutureDemo {

    @Test
    void funtureDemo() {

        CompletableFuture<Integer> t = CompletableFuture.supplyAsync(() -> {
            return 10;
        });
        try {
            System.out.println(" --> " + t.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void handlerFutureDemo() {
        final CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync( ()-> {
            int i = 10/0;
            return i;
        }).handleAsync((in, throwable) -> {
            if (throwable != null) {
                System.out.println("throwanle" + throwable.getMessage());
                return -1;
            }
            return 0;
        });
        System.out.println(" === asyn ....");
    }

    @Test
    void demo1() {
        // cf实例
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync( () -> {

            return fetchPrice();
        });

        cf.thenAccept((result) -> {
            System.out.println(" -- price --> " + result);
        });

        cf.exceptionally((e) -> {
           e.printStackTrace();
           return null;
        });

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    Double fetchPrice() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (Math.random() < 0.3) {
            throw new RuntimeException("fetch price failed!");
        }
        return 5 + Math.random() * 20;
    }

    // 勋章service
    private String getMedalInfo(long userId) throws InterruptedException {
        // 模拟勋章数据查询耗时
        Thread.sleep(500);
        return new String("medal service query result");
    }

    // 用户service
    private String getUserInfo(long userId) throws InterruptedException {
        // 模拟用户数据查询耗时
        Thread.sleep(300);
        return "user service query result";
    }

    // 模拟我的页面接口请求, 包含用户信息以及勋章查询, 使用异步方式
    @Test
    void userPageAPIDemo() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        long userId = 666L;
        long start = System.currentTimeMillis();

        FutureTask<String> queryUsreTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return getUserInfo(userId);
            }
        });
        executorService.submit(queryUsreTask);

        // 模拟主线程其他操作
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        FutureTask<String> queryMedalTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return getMedalInfo(userId);
            }
        });
        executorService.submit(queryMedalTask);


        try {
            System.out.println(" -- result --> " + queryUsreTask.get() + " -AND- " + queryMedalTask.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println(" -- 总用时 --> " + (System.currentTimeMillis() - start) + "ms");
    }
}
