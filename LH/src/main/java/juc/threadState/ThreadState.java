package juc.threadState;

import java.util.concurrent.TimeUnit;

public class ThreadState {

    public static void main(String args[]){

        new Thread(new TimeWaiting(),"timeWaitingThread").start();

        new Thread(new Waiting(),"waitingThread").start();

        new Thread(new Blocked(),"blockThread1").start();

        new Thread(new Blocked(),"blockedThread2").start();

        }





    /**
     * 该线程在Waiting实例上持续等待
     */
    static class Waiting implements Runnable {

        @Override
        public void run() {
            synchronized (Waiting.class) {
                try {
                    Waiting.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 该线程持续睡眠
     */
    static class TimeWaiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    SleepUtil.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 该线程在Blocked上加锁后不会释放
     */
    static class Blocked implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    SleepUtil.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
