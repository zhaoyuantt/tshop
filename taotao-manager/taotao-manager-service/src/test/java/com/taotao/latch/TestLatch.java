package com.taotao.latch;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch测试类
 * @author zhaoyuan
 * @date 2019，Dec 4 9:13 am
 */
public class TestLatch {
    public static void main(String[] args) {
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(2);

        for (int i = 0;i < 2;i++){
            Thread thread = new Thread(new player(begin,end), String.valueOf(i));
            thread.start();
        }

        System.out.println("$$$$$$$$$$the race begin$$$$$$$$$$");

        begin.countDown();
        try {
            //让主线程在这里暂停
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("$$$$$$$$$$.getCount$$$$$$$$$$"+end.getCount());
        System.out.println("$$$$$$$$$$the race end$$$$$$$$$$");

    }
}

class player implements Runnable{

    private CountDownLatch begin;
    private CountDownLatch end;

    public player() {
    }

    public player(CountDownLatch begin, CountDownLatch end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public void run() {
        System.out.println("**********start**********"+Thread.currentThread().getName());

        try {
            begin.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("**********arrived**********"+Thread.currentThread().getName());

        //当end.getCount=0时，线程自动唤醒
        end.countDown();

    }
}
