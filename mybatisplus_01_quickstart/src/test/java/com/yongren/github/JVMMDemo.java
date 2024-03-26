package com.yongren.github;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

public class JVMMDemo {

    public static void main(String[] args) {
        orderEnumDemo();
//        orderDemo();
    }

    /**
     模拟4条语句执行情况, 罗列所有执行顺序的可能, 当且仅当 x=b&&y=a 优先时, x=0&&y=0, 这是多线程有序性问题的最直观的例子
    * */
    private static void orderEnumDemo() {

        // 定义语句及执行顺序
        char[] people = {'A', 'B', 'C', 'D'};
        int[] seats = {1, 2, 3, 4};

        HashMap<String, String> elm = new HashMap<>();
        elm.put("A", "a[0] = 1   ");
        elm.put("B", "x[0] = b[0]");
        elm.put("C", "b[0] = 1   ");
        elm.put("D", "y[0] = a[0]");

        // 开始递归打印所有组合
        printCombinations(people, seats, 0, elm);
    }

    static int sum = 0;
    private static void printCombinations(char[] people, int[] seats, int index, HashMap<String, String> arr) {
            if (index == people.length) {
                // 所有的人都分配了座位，打印当前组合
                for (int i = 0; i < people.length; i++) {
                    String kk = String.valueOf(people[i]);
                    System.out.print(arr.get(kk) + " 执行在" + seats[i] + "号, ");
                }
                System.out.println(" -- [" + (sum++) + "]");
            } else {
                for (int i = index; i < seats.length; i++) {
                    // 交换当前人与index位置的人，以便分配座位
                    char temp = people[index];
                    people[index] = people[i];
                    people[i] = temp;

                    // 递归调用，为下一个人分配座位
                    printCombinations(people, seats, index + 1, arr);

                    // 恢复原来的状态以便尝试其他组合
                    temp = people[index];
                    people[index] = people[i];
                    people[i] = temp;
                }
            }
        }


    static void orderDemo() {
        for (long i = 0; i < Long.MAX_VALUE; i++) {
            final long[] x = {0L};
            final long[] y = {0L};
            final long[] a = {0L};
            final long[] b = {0L};

            CountDownLatch latch = new CountDownLatch(2);

            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {
                    a[0] = 1;
                    x[0] = b[0];
                    latch.countDown();
                }
            });

            Thread other = new Thread(new Runnable() {
                @Override
                public void run() {
                    b[0] = 1;
                    y[0] = a[0];
                    latch.countDown();
                }
            });

            one.start();
            other.start();
            try {
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            String rr = "第" + i + "次 >>> x=" + x[0] + ";y=" + y[0];
            /*
            *   x = b 和 y = a 被同时预先执行的时候, 才可能出现 x == 0 && 于== 0
            * */
            if (x[0] == 0 && y[0] == 0) {
                System.out.println(rr);
                break;
            }

        }
        System.out.println(" === done! ===");
    }

    @Test
    void demo() {
        // 有点信号量的意思?
        final CountDownLatch latch = new CountDownLatch(2);

        new Thread( () -> {
            try {
                System.out.println("Thread 1 is doing some work...");
                Thread.sleep(3000); // 模拟耗时任务
                System.out.println("Thread 1 finished its work.");
                latch.countDown(); // 计数器减一
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread( () -> {
            try {
                System.out.println("Thread 2 is doing some work...");
                Thread.sleep(2000); // 模拟耗时任务
                System.out.println("Thread 2 finished its work.");
                latch.countDown(); // 计数器减一
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            System.out.println(" main thread await...");
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(" === done ===");
    }
}

class Singleton {
    static Singleton instance;

    static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}