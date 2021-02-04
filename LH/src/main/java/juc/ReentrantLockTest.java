package juc;

import java.util.Timer;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest
{
    int a = 0;
    ReentrantLock lock = new ReentrantLock();

    public void write() {
        lock.lock();
        try {
            a++;

        }finally {
            lock.unlock();
        }
    }
    public void read() {
        lock.lock();
        try {
            int i = a;
            System.out.println(i);
        }finally {
            lock.unlock();
        }
    }
    public static void main(String args[]){

        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();


                reentrantLockTest.write();
                reentrantLockTest.read();
        }

}
