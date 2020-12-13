package oswork.ProcessSchedule;

import java.util.*;

public class ProcessSchedule
{

    //进程就绪队列
    public static Queue<Process> readyQueue = new LinkedList<>();
    //进程结束队列
    public static Queue<Process> endQueue = new LinkedList<>();
    //SLF算法TreeSet
    public static TreeSet<Process> readySet = new TreeSet<>();
    //SLF算法进程结束队列
    public static Queue<Process> sjfEndQueue = new LinkedList<>();

    public static void main(String args[]){

        //创建进程并加入到数组中
        System.out.println("请输入要添加的进程数：");
        Scanner scanner = new Scanner(System.in);
        int processNum = scanner.nextInt();

        Process[] processes = new Process[processNum];


        System.out.println("初始化进程信息：（进程名/进程到达时间/耗时）");
        for (int i = 0; i < processNum; i++) {

            String processName = scanner.next();
            int reachTime = scanner.nextInt();
            int processTime = scanner.nextInt();
            Process process = new Process(processName,reachTime,processTime);
            processes[i] = process;

        }
        System.out.println("请选择进程调度算法：1、FCFS   2、RR   3、SJF   4、HRN");
        int type = scanner.nextInt();
        switch (type) {
            case 1:{
                FCFS(processes);
                Print(readyQueue);
                break;
            }
            case 2:{
                try {
                    System.out.println("请输入时间片：");
                    int periodTime = scanner.nextInt();
                    RR(processes,periodTime);
                    Print(endQueue);
            }catch (Exception e){
                    e.printStackTrace();
                }

                break;
            }
            case 3:{
                SJF(processes);
                Print(sjfEndQueue);
                break;
            }
            case 4:{
                HRN(processes);
                Print(endQueue);
                break;
            }
            default:{
                System.out.println("输入有误，请输入序号！");
            }
        }
        scanner.close();


    }


    /**
     * FCFS算法
     */
    public static void FCFS(Process[] processes) {
        Arrays.sort(processes, new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                if(o1.getReachTime()<o2.getReachTime()) {
                    return -1;
                }else if(o1.getReachTime()>o2.getReachTime()) {
                    return 1;
                }else {
                    return 0;
                }
            }
        });

        for (Process process : processes) {
            readyQueue.add(process);
        }

        Iterator<Process> iterator = readyQueue.iterator();
        Process firstProcess = iterator.next();
        firstProcess.setStartTime(firstProcess.getReachTime());
        firstProcess.setFinishTime(firstProcess.getStartTime()+firstProcess.getProcessTime());
        firstProcess.setPeriodTime(firstProcess.getFinishTime()-firstProcess.getStartTime());
        firstProcess.setWeightedPeriodTime(firstProcess.getPeriodTime()/firstProcess.getProcessTime());
        int nextProcessStartTime = firstProcess.getFinishTime();
        while (iterator.hasNext()) {
            Process process = iterator.next();
            process.setStartTime(nextProcessStartTime);
            process.setFinishTime(process.getStartTime()+process.getProcessTime());
            process.setPeriodTime(process.getFinishTime()-process.getStartTime());
            process.setWeightedPeriodTime(process.getPeriodTime()/process.getProcessTime());
            nextProcessStartTime = process.getFinishTime();

        }
    }


    /**
     * 高响应比优先算法HRN
     */
    public static void HRN(Process[] processes) {
        /**
         * 每次从就绪队列中取出进程时, 所有进程都要根据当前时间来算一次响应比, 取出响应比最大值的进程来执行, 执行结束后放到 endQueue.
         * 响应比 = (服务时间 + 等待时间) / 服务时间, 这样即让短作业优先执行, 又保证长作业一定会被执行
         */
        int time = 0;
        checkReachProcess(processes,time);
        boolean flag = false;//标志位当前是否有高响应比进程还在执行
        //计算高响应比
        Process maxProcess = null;

        while(endQueue.size()<processes.length) {

           if(readyQueue.size() == 0 && maxProcess == null) {
               time++;
               checkReachProcess(processes,time);
               break;
           }

            if(!flag) {
                 maxProcess = executeHRN(time);
            }

           if(maxProcess == null) {
               time++;
               checkReachProcess(processes,time);
               break;
           }
           flag = true;
           int processTime = maxProcess.getProcessTime();
           if(maxProcess.getExeTime() == 0) {
               maxProcess.setStartTime(time);
           }
            int exeTime = maxProcess.getExeTime();
            maxProcess.setExeTime(++exeTime);

            if(exeTime == processTime) {
                maxProcess.setFinishTime(time + 1);
                maxProcess.setPeriodTime(maxProcess.getFinishTime()-maxProcess.getReachTime());
                maxProcess.setWeightedPeriodTime(maxProcess.getPeriodTime()/maxProcess.getProcessTime());
                //加入到endQueue
                endQueue.add(maxProcess);
                flag = false;
                maxProcess = null;


            }
            time++;
            checkReachProcess(processes,time);
        }

    }

    /**
     * 计算响应比返回最高响应比进程
     */
    public static Process executeHRN(int time) {
        if(readyQueue.size() == 0) {
            return null;
        }
        Process maxProcess = null;
        int maxHrnTime = 0;

        for (Process process : readyQueue) {
            int reachTime = process.getReachTime();
            int processTime = process.getProcessTime();
            int hrnTime = ((time + processTime) - reachTime)/processTime;
            if(hrnTime >= maxHrnTime) {
                maxHrnTime = hrnTime;
                maxProcess = process;
            }
        }
        readyQueue.remove(maxProcess);
        return maxProcess;
    }



    /**
     * SLF算法
     */
    public static void SJF(Process[] processes) {
        //初始化时间
        int time = 0;
        checkSJFReadyProcess(processes,time);
        while (sjfEndQueue.size()<processes.length) {
            if(readySet.isEmpty()) {
                //就绪队列为空时间增加并判断是否有进程到达
                time++;
                checkSJFReadyProcess(processes,time);
                break;
            }
            //取出服务时间最小的进程
            Process process = readySet.pollFirst();
            process.setStartTime(time);
            process.setFinishTime(process.getStartTime()+process.getProcessTime());
            process.setPeriodTime(process.getFinishTime()-process.getReachTime());
            process.setWeightedPeriodTime(process.getPeriodTime()/process.getProcessTime());
            //将执行后的进程加入到任务完成队列
            sjfEndQueue.add(process);
            //时间增加为短作业执行时间
            for (int i = 1; i <= process.getProcessTime(); i++) {
                time++;
                checkSJFReadyProcess(processes,time);
            }
        }

    }

    /**
     * 定时查询队列检测加入TreeSet
     *
     */
    public static void checkSJFReadyProcess(Process[] processes,int time) {
        for (Process process : processes) {
            if(process.getReachTime() == time) {
                readySet.add(process);
            }
        }
    }




    /**
     * RR算法
     */
    public static void RR(Process[] processes,int periodTime) throws Exception {
        if(periodTime<0) {
            throw new Exception("参数不正确");
        }
        int time = 0;
        boolean flag = false;
        checkReachProcess(processes,time);

        //如果还有没有执行完进程继续分配时间片
        while(endQueue.size()<processes.length) {
            for (int i = 0; i < periodTime; i++) {
                time++;
                flag = executeProcess(time);
                if(flag) {
                    //计算进程周转时间并将进程加入endQueue
                    Process poll = readyQueue.poll();
                    poll.setFinishTime(time);
                    poll.setPeriodTime(poll.getFinishTime()-poll.getReachTime());
                    poll.setWeightedPeriodTime(poll.getPeriodTime()/poll.getProcessTime());
                    endQueue.offer(poll);
                    checkReachProcess(processes,time);
                    break;
                }

                checkReachProcess(processes,time);
                // 将进程从队首加到就绪队列的对尾部
                // 进程在一个时间片内未执行完
                if(i == periodTime-1 && !flag && !readyQueue.isEmpty()) {
                    Process poll = readyQueue.poll();
                    readyQueue.offer(poll);
                }
            }
        }

    }
    /**
     * 时间片增加判断是否有进程到达
     */
    public static  void checkReachProcess(Process[] processes,int time) {

            for (Process process : processes) {
                if(process.getReachTime() == time) {
                    //加入到就绪队列尾部
                    readyQueue.offer(process);
                }
            }

    }

    /**
     * 进程执行
     * 当前进程执行完返回true否则false
     */
    public static boolean executeProcess(int time) {
        if(readyQueue.isEmpty()) {
            return false;
        }
        Process peek = readyQueue.peek();
        int exeTime = peek.getExeTime();
        if(peek.getExeTime()==0){
            peek.setStartTime(--time);
        }


        peek.setExeTime(++exeTime);
        if(peek.getExeTime() == peek.getProcessTime()) {
            return true;
        }
        return false;

    }

    public static void Print(Collection collection) {

        //打印
        System.out.println("            调度示意图");
        System.out.println("进程    到达时间    耗时      开始时间 完成时间  周转时间  带权周转时间");
        Iterator<Process> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Process next = iterator.next();
            next.Print();
        }
        System.out.printf("平均周转时间:%.4f",Avg_ProcessTime(collection));
        System.out.println();
        System.out.printf("平均带权周转时间:%.4f",Avg_WeightedProcessTime(collection));

    }




    public static double Avg_ProcessTime(Collection endQueue){
        //平均周转时间
        double avg = 0;
        Iterator<Process> it = endQueue.iterator();
        while( it.hasNext()){
            Process p = it.next();
            avg += p.getPeriodTime();
        }
        avg /= endQueue.size();
        return avg;
    }

    public static double Avg_WeightedProcessTime(Collection endQueue){
        //平均带权周转时间
        double avg = 0;
        Iterator<Process> it = endQueue.iterator();
        while( it.hasNext()){
            Process p = it.next();
            avg += p.getWeightedPeriodTime();
        }
        avg /= endQueue.size();
        return avg;
    }

}
