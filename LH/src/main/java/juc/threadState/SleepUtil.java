package juc.threadState;

import java.util.concurrent.TimeUnit;

public class SleepUtil {
    public static void sleep(long sleepTime) throws InterruptedException {
        TimeUnit.SECONDS.sleep(sleepTime);
    }
}
