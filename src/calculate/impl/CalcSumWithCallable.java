package calculate.impl;

import calculate.AbstractCalcSum;
import calculate.impl.concurrence.CalcSumCallable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CalcSumWithCallable extends AbstractCalcSum {

    //初始化线程池，默认线程数为配置的CPU核数
    private static ExecutorService POOL = Executors.newFixedThreadPool(CPU_CORE);

    //模式为多线程
    private boolean isConcurrent = true;

    @Override
    public boolean isConcurrent() {
        return isConcurrent;
    }

    @Override
    public long doCalc(int targetNum) throws ExecutionException, InterruptedException {
        return doCalcWithPool(targetNum);
    }

    public static long doCalcWithPool(int targetNum) throws InterruptedException, ExecutionException {
        //划分线程子任务
        CalcSumCallable[] tasks = splitTask(targetNum);
        //使用线程池执行所有callable线程
        List<Future<Long>> results = POOL.invokeAll(Arrays.asList(tasks));
        //遍历Future集合，获取所有子线程的计算结果
        long total = 0;
        for (Future<Long> result : results) {
            //执行get方法时，如果子线程还未执行完，那么会阻塞主线程等待结果
            total += result.get();
        }
        return total;
    }

    //根据目标数target与任务划分指标splitTo对计算任务进行多线程子任务划分
    private static CalcSumCallable[] splitTask(int targetNum) {
        CalcSumCallable[] tasks = new CalcSumCallable[CPU_CORE];
        int splitNum = targetNum / CPU_CORE;
        for (int i = 0; i < CPU_CORE; ++i) {
            int start = i * splitNum + 1;
            int end = splitNum * (i + 1);
            //由于target不一定能被splitTo除尽，此时最后一个区间直接取target的值
            if (i == CPU_CORE - 1 && end < targetNum) {
                end = targetNum;
            }
            String name = start + "~" + end + "计算任务";
            tasks[i] = new CalcSumCallable(name, start, end);
        }
        return tasks;
    }
}
