package juc;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;


public class MultiThread {
    public static void main(String args[]){
        //获取线程管理
        ThreadMXBean threadMXBean =  ManagementFactory.getThreadMXBean();
        //不需要获取lockedMonitors和lockedSynchronizers信息
        //只需获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
        for (ThreadInfo threadInfo : threadInfos) {
            String threadName = threadInfo.getThreadName();
            System.out.println(threadName);
        }
    }
  
}
