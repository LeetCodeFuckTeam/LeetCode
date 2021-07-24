package fwf;

import javafx.concurrent.Task;
import sun.util.resources.cldr.lag.CalendarData_lag_TZ;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Thread {


    public static void main(String args[]) throws InterruptedException, ExecutionException {
        isSuccess();
        }

        public static boolean isSuccess() throws InterruptedException, ExecutionException {
            ExecutorService executorService = Executors.newFixedThreadPool(3);

            List<Callable<Boolean>> tasks = Arrays.asList(()->{return true;},()->{return true;});

            List<Future<Boolean>> futures = executorService.invokeAll(tasks);
            boolean flag = true;
            for (int i = 0; i < futures.size(); i++) {
                if(futures.get(i).get() == false) {
                    flag = false;
                }
            }
            return flag;
        }


}
