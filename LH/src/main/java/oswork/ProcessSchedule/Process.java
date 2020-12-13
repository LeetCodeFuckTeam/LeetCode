package oswork.ProcessSchedule;

public class Process implements Comparable<Process> {
    private String ProcessName;             //进程名
    private int ReachTime;                  //到达时间
    private int ProcessTime;                //处理时间
    private int FinishTime;                 //完成时间
    private int PeriodTime;                 //周转时间
    private int StartTime;                  //开始时间
    private double WeightedPeriodTime;      //带权周转时间
    private int Priority;                   //优先级
    private int exeTime;                    //已执行时间

    public Process(String processName, int reachTime, int processTime) {
        ProcessName = processName;
        ReachTime = reachTime;
        ProcessTime = processTime;
    }

    public Process(String processName, int reachTime, int processTime, int priority) {
        ProcessName = processName;
        ReachTime = reachTime;
        ProcessTime = processTime;
        Priority = priority;
    }



    public String getProcessName() {
        return ProcessName;
    }

    public void setProcessName(String processName) {
        ProcessName = processName;
    }

    public int getReachTime() {
        return ReachTime;
    }

    public void setReachTime(int reachTime) {
        ReachTime = reachTime;
    }

    public int getProcessTime() {
        return ProcessTime;
    }

    public void setProcessTime(int processTime) {
        ProcessTime = processTime;
    }

    public int getFinishTime() {
        return FinishTime;
    }

    public void setFinishTime(int finishTime) {
        FinishTime = finishTime;
    }

    public int getPeriodTime() {
        return PeriodTime;
    }

    public void setPeriodTime(int periodTime) {
        PeriodTime = periodTime;
    }

    public int getStartTime() {
        return StartTime;
    }

    public void setStartTime(int startTime) {
        StartTime = startTime;
    }

    public double getWeightedPeriodTime() {
        return WeightedPeriodTime;
    }

    public void setWeightedPeriodTime(double weightedPeriodTime) {
        WeightedPeriodTime = weightedPeriodTime;
    }

    public int getPriority() {
        return Priority;
    }

    public void setPriority(int priority) {
        Priority = priority;
    }

    public int getExeTime() {
        return exeTime;
    }

    public void setExeTime(int exeTime) {
        this.exeTime = exeTime;
    }

    @Override
    public int compareTo(Process o) {
        if(this.getProcessTime()>o.getProcessTime()) {
            return 1;
        }else if (this.getProcessTime()<o.getProcessTime()){
            return -1;
        }else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Process{" +
                "ProcessName='" + ProcessName + '\'' +
                ", ReachTime=" + ReachTime +
                ", ProcessTime=" + ProcessTime +
                ", FinishTime=" + FinishTime +
                ", PeriodTime=" + PeriodTime +
                ", StartTime=" + StartTime +
                ", WeightedPeriodTime=" + WeightedPeriodTime +
                ", Priority=" + Priority +
                '}';
    }

    public void Print(){
        System.out.print(this.ProcessName+"        "+this.ReachTime+"           "+this.ProcessTime+"      "+"    "+this.StartTime+"     "+this.FinishTime+"        "+this.PeriodTime+"         ");
        System.out.printf("%.4f",this.WeightedPeriodTime);
        System.out.println();
    }

}
