package com.yongren.github;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class ThreadDemo {

    public static void main(String[] args) {
//        unSafeDemo1();
//        synchDemo();
    }

    // 使用lock锁完成: {10, 5, 20, 50, 100, 200, 800, 2, 80, 300, 700}有这样一个奖池
    // 同时在2个城市进行抽奖, 请随机从奖池中获取元素并输出:
    // 随机抽取,随机开奖:
    // 北京用户抽中了一个 xx
    // 北京用户抽中了一个 xx
    // 北京用户抽中了一个 xx
    // 上海用户抽中了一个 xx 元大奖
    // ...

    static void lockDemo() {

    }


    // demo: 使用synchronized锁, 使用2个线程同时在0-100之内的数字, 将所有的奇数输出
    // 刚开始并不建议这样的写法, 因为使用匿名内部类的方式, 虽然可以很好的组织代码, 但结果返回并不直接,老老实实创建类吧😂
    static int num = 100;
    static ArrayList<Integer> arr = new ArrayList<>();
    static void synchDemo() {
        Runnable call = new Runnable() {
            public void run() {
                while (true) {
                    synchronized (Runnable.class) {

                        if (num > 0) {
                            if (num % 2 == 0) {
                                arr.add(num);
                            }
                            num -= 1;
                            System.out.println(Thread.currentThread().getName() + " --> " + num);
                        } else {
                            System.out.println(Thread.currentThread().getName() + " result is  -> " + arr + arr.stream().count());
                            break;
                        }
                    }
                }
            }
        };

        Thread t1 = new Thread(call, "001");
        Thread t2 = new Thread(call, "002");
        Thread t3 = new Thread(call, "003");

        t1.start();
        t2.start();
        t3.start();
    }

    static void unSafeDemo1() {
        System.out.println(" --> test here ");

        SalerWindow s1 = new SalerWindow();
        SalerWindow s2 = new SalerWindow();

        s1.setName("001窗口");
        s2.setName("002窗口");

        s1.start();
        s2.start();
    }
}


// 抽奖线程
class RaffleThread extends Thread {

    static int[] array = {10, 5, 20, 50, 100, 200, 800, 2, 80, 300, 700};
    // 抽中idx
    static int index = 0;

    @Override
    public void run() {
    }
}



/**
java 多线程3中方式:
- 继承Thread
- 实现Runnable
- 实现Callable

线程的生命周期对操作系统来说很复杂,但基本可以粗略的看成
创建new 可运行Runnable 运行中Running 终止ternimal 阻塞Block等几大类, 其中进入block包含了常见的sleep/waite等
比如: 当我们start的时候,就是NEW转为Runnbale的,等待CPU随机调度


多线程引起的问题展示: (这里遇到一个问题, 测试框架test跑出的结果未必完全,线程相关复杂的demo最好在main中跑)
如下展示的,2个窗口都买了35号和58号票, 而且还多买了一张票
这就是典型的多线程共享数据造成的数据出错,分析一下结果:


SalerWindow{name='002窗口'} -> saled [34] ticket
SalerWindow{name='002窗口'} -> saled [35] ticket
SalerWindow{name='001窗口'} -> saled [35] ticket
...
SalerWindow{name='001窗口'} -> saled [58] ticket
SalerWindow{name='002窗口'} -> saled [58] ticket
...
SalerWindow{name='001窗口'} -> saled [100] ticket
SalerWindow{name='002窗口'} -> saled [101] ticket
 saled done
 saled done
 */
class SalerWindow extends Thread {

    // static保证所有的窗口都是同一个票仓
    static int saledTicketes = 0;

    @Override
    public String toString() {
        return "SalerWindow{" +
                "name='" + getName() + '\'' +
                '}';
    }

    @Override
    public void run() {
        sale();
    }

    void sale() {
        while (true) {

            // 99
            if (saledTicketes < 100) {
                try {
                    // 买卖个过程中的耗时及延迟模拟
                    Thread.sleep(1);
                    saledTicketes += 1;// s1 100 // s2 101
                    System.out.println(this + " -> saled [" + saledTicketes + "] ticket");

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println(" saled done ");
                break;
            }

        }
    }
}

class RunnableThreadDemo implements Runnable {

    @Override
    public void run() {
        // ... here do something in thread running...
    }
}

class CallableThreadDemo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        // .... do something in thread running, also return something...
        return 100;
    }
}
