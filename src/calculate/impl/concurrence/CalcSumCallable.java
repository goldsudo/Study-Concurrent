package calculate.impl.concurrence;

import java.util.concurrent.*;

public class CalcSumCallable implements Callable<Long> {

    //线程名称
    public String name;
    //计算范围开始值
    public long start;
    //计算范围结束值
    public long end;
    //计算结果
    public long result;

    public CalcSumCallable() {}

    public CalcSumCallable(String name, int start, int end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    @Override
    public Long call() {
        result = 0;
        for (long i = start; i <= end; ++i) {
            result += i;
        }
//        System.out.println("\t" + name + "执行完毕，结果为：" + result);
        return result;
    }
}
