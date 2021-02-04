package oswork.bankers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BankerAlgorithm {

    private static int[][] Allocation;//分配资源
    private static int[][] Need;//需求资源
    private static int[][] Max;//最大需求
    private static int[] Available;//可利用资源
    private static Process[] processes;//进程数
    private static List<Process> sercurityProcesses;//安全性检测
    private static int[] request;//请求资源
    private static boolean[] Finish;//进程是否完成
    private static boolean requestAgain = true;

    public static void main(String args[]){
        int m;//进程数
        int n;//资源数
        sercurityProcesses= new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入进程数：");
        m = scanner.nextInt();
        processes = new Process[m];
        Finish = new boolean[m];
        for (int i = 0; i < processes.length; i++) {
            System.out.println("请输入Process" + i +"的名称：");
            String processName = String.valueOf(scanner.nextInt());
            processes[i] = new Process(processName);
        }
        System.out.println("请输入系统资源数：");
        n = scanner.nextInt();
        Available = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.println("资源" +i+ "资源量：");
            int avb = scanner.nextInt();
            Available[i] = avb;
        }

        Allocation = new int[m][n];
        Need = new int[m][n];
        Max = new int[m][n];

        System.out.println("请依次输入最大需求矩阵");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Max[i][j] = scanner.nextInt();
            }
        }

        System.out.println("请依次输入分配矩阵：");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Allocation[i][j] = scanner.nextInt();
            }
        }
        for (int i = 0; i < Allocation.length; i++) {
            for (int j = 0; j < Allocation[0].length; j++) {
                Available[j] -= Allocation[i][j]; //分配后可用资源数
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Need[i][j] = Max[i][j] - Allocation[i][j];
            }
        }

        request = new int[Available.length];



        BankerAlgorithm bankerAlgorithm = new BankerAlgorithm();
        System.out.println("==========T0时刻安全性状态检查==========");
        bankerAlgorithm.securityExamine();
        bankerAlgorithm.request(scanner);  //开始分配
        if (requestAgain == false) return;
        //bankerAlgorithm.securityExamine(); //安全性检测
        //bankerAlgorithm.printState();      //状态打印
        scanner.close();



    }

    /**
     * 安全性检查
     */
    public boolean securityExamine() {
        int[] Work = Arrays.copyOfRange(Available,0,Available.length); //work=Ava值
        for (int i = 0; i < processes.length; i++) {
            if(!Finish[i]) {
                boolean flag = true;
                for (int j = 0; j < Available.length; j++) {
                    if(Need[i][j] > Work[j]) {
                        flag = false;
                        break;
                    }
                }
                //如果flag = true说明此进程获取到全部的资源进行执行
                if(flag) {
                    String processName = null;
                    try{
                        processName = getProcessName(i);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    //将进程添加到完成队列
                    Finish[i] = true;
                    //释放资源  ①work-need ②need+allocation
                    for (int j = 0; j < Work.length; j++) {
                        Work[j] = Allocation[i][j] + Work[j];
                    }
                    //加入到安全序列
                    sercurityProcesses.add(processes[i]);
                    i = -1;   //重头开始继续查询判断其他进程
                    System.out.println(processName + "分配完成!");
                }

            }

        }
        //所有进程循环判断结束
        if(sercurityProcesses.size() == Finish.length) {
            //所有进程都能得到分配的资源
            System.out.println("状态安全");
            System.out.println("安全序列：" + sercurityProcesses);
            sercurityProcesses.clear();
            Finish = new boolean[Finish.length];
            return true;
        }
        System.out.println("状态不安全");
        return false;

    }


    /**
     * 请求资源
     *
     */
    private void request(Scanner scanner) {

        while(true) {
            System.out.println("请输入要分配资源的ProcessName：");
            String processName = String.valueOf(scanner.nextInt());
            //判断输入进程是否存在，并查询进程号
            Integer processId = getProcessId(processName);
            if(processId == null) {
                System.out.println("输入进程不存在！");
                break;
            }

            System.out.println("请输入进程需要请求的资源数：");
            for (int i = 0; i < request.length; i++) {
                request[i] = scanner.nextInt();//请求的资源量保存到request数组
            }
            //判断输入请求资源是否合法
            for (int i = 0; i < request.length; i++) {
                if(Need[processId][i] < request[i]) {
                    System.out.println("输入参数有误!");
                    break;
                }
            }
            //判断输入请求资源是否够分配
            for (int i = 0; i < request.length; i++) {
                if(Available[i] < request[i]) {
                    System.out.println("资源：" + i + "不足！");
                    break;
                }
            }
            //分配资源
            for (int i = 0; i < request.length; i++) {
                //更新已分配
                Allocation[processId][i] = Allocation[processId][i] + request[i];
                //更新进程需求
                Need[processId][i] -= request[i];
                Available[i] -= request[i];
            }
            //安全性检查
            boolean isSecurity = securityExamine();
            if(isSecurity) {
                //安全性检查通过，需求为0的进程执行完成Finish=true 加入安全序列
                checkNeedNull(Available);
                printState();
            }else {
                //安全性检查失败 回退
                //更新已分配
                for (int i = 0; i < request.length; i++) {
                    Allocation[processId][i] = Allocation[processId][i] - request[i];
                    //更新进程需求
                    Need[processId][i] += request[i];
                    Available[i] += request[i];
                }

            }
            System.out.println("是否继续分配？true/false");
            requestAgain= scanner.nextBoolean();

            if(requestAgain == false) {
                break;
            }

        }

    }

    /**
     * 检查无需求执行完的进程
     */
    private void checkNeedNull(int[] Available) {
        for (int i = 0; i < Finish.length; i++) {
            boolean isNeed = true;
            for (int j = 0; j < Available.length; j++) {
                if(Need[i][j] != 0) {
                    isNeed = false;
                    break;
                }
            }
            //满足条件，则i进程need全为0
            if(isNeed) {
                String processName = null;
                try {
                    processName = getProcessName(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //修改i进程的状态位
                Finish[i] = true;
                //回收i进程占有的资源
                for (int j = 0; j < Available.length; j++) {
                    Available[j] = Available[j] + Allocation[i][j];
                }
                //把该进程放入安全序列中
                sercurityProcesses.add(processes[i]);
                System.out.println("进程"+processName+" need为0，直接释放!     此时Work："+Arrays.toString(Available));
            }
        }
    }


    private String getProcessName(int processId) {
        Process process = processes[processId];
        return process.getProcessName();
    }
    private Integer getProcessId(String processName) {
        for (int i = 0; i < processes.length; i++) {
            if(processes[i].getProcessName().equals(processName)) {
                return i;
            }
        }
        return null;
    }






    public void printState() {
        System.out.println();
        System.out.println("-------------------------当前进程状态----------------------------");
        System.out.println();
        System.out.println("processes\t\tAllocation\t\tNeed\t\t");
        for (int i = 0; i < Finish.length; i++) {
            String processName = null;
            try {
                processName = getProcessName(i);
            } catch (Exception e) {
                System.out.println("非法的下标");
            }
            System.out.print(processName+"\t\t\t\t");
            for (int j = 0; j < Available.length; j++) {
                System.out.print(Allocation[i][j]+" ");
            }
            System.out.print("\t\t");
            for (int j = 0; j < Available.length; j++) {
                System.out.print(Need[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("Avalibale:"+Arrays.toString(Available));
    }




}
class Process {
    private String processName;

    public Process(String processName) {
        this.processName = processName;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    @Override
    public String toString() {
        return "Process{" +
                "processName='" + processName + '\'' + '}';
    }


}

