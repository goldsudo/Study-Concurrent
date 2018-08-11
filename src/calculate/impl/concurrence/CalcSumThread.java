package calculate.impl.concurrence;

public class CalcSumThread extends Thread {

    //线程名称
    public String name;
    //计算范围开始值
    public long start;
    //计算范围结束值
    public long end;
    //计算结果
    public long result;

    public CalcSumThread() {
    }

    public CalcSumThread(String name, int start, int end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        result = 0;
        for (long i = start; i <= end; ++i) {
            result += i;
        }
//        System.out.println("\t" + name + "执行完毕，结果为：" + result);
    }

}
