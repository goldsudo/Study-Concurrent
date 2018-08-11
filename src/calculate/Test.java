package calculate;

import calculate.impl.CalcSum;
import calculate.impl.CalcSumWithCallable;
import calculate.impl.CalcSumWithThread;

public class Test {

    //用于计算的目标数字
    private static int TARGET_NUM = 1000000000;

    public static void test(AbstractCalcSum calc) throws Exception {
        System.out.println("目标数字：" + TARGET_NUM);
        System.out.println("测试类：" + calc.getClass().getName() + " 模式：" + (calc.isConcurrent() ? "多线程" + " 开启线程数：" + AbstractCalcSum.CPU_CORE : "单线程"));
        long startTime = System.currentTimeMillis();
        long sum = calc.doCalc(TARGET_NUM);
        long endTime = System.currentTimeMillis();
        System.out.println("耗时: " + (endTime - startTime) + " ms " + "结果: " + sum);
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        //测试单线程版本
        test(new CalcSum());
        //测试利用Thread实现的多线程版本
        test(new CalcSumWithThread());
        //测试利用Callable，Future以及线程池ExecutorService实现的多线程版本
        test(new CalcSumWithCallable());
    }
}
