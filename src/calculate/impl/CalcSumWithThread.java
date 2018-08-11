package calculate.impl;

import calculate.AbstractCalcSum;
import calculate.impl.concurrence.CalcSumThread;

public class CalcSumWithThread extends AbstractCalcSum {

    //模式为多线程
    private boolean isConcurrent = true;

    @Override
    public boolean isConcurrent() {
        return isConcurrent;
    }

    @Override
    public long doCalc(int targetNum) throws InterruptedException {
        return doCalcWithThread(targetNum);
    }

    private static long doCalcWithThread(int targetNum) throws InterruptedException {
        //划分线程子任务
        CalcSumThread[] tasks = splitTask(targetNum);
        //开始所有线程
        for (CalcSumThread t : tasks) {
            t.start();
        }
        //调用线程的join方法，使主线程阻塞并等待所有子线程完成（注意：如果把下面的for循环注释掉，那么最后将得到未知的total值）
        for (CalcSumThread t : tasks) {
            t.join();
        }
        //此时所有子线程都执行完毕，可以直接获取每个子线程的计算结果
        long total = 0;
        for (CalcSumThread t : tasks) {
            total += t.result;
        }
        return total;
    }

    //根据目标数target与任务划分指标splitTo对计算任务进行多线程子任务划分
    public static CalcSumThread[] splitTask(int targetNum) {
        CalcSumThread[] tasks = new CalcSumThread[CPU_CORE];
        int splitNum = targetNum / CPU_CORE;
        for (int i = 0; i < CPU_CORE; ++i) {
            int start = i * splitNum + 1;
            int end = splitNum * (i + 1);
            //由于target不一定能被splitTo除尽，此时最后一个区间直接取target的值
            if (i == CPU_CORE - 1 && end < targetNum) {
                end = targetNum;
            }
            String name = start + "~" + end + "计算任务";
            tasks[i] = new CalcSumThread(name, start, end);
        }
        return tasks;
    }

}
