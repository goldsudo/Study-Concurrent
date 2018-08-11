package calculate;

public abstract class AbstractCalcSum {

    //cpu核数，用于控制多线程实现对象中开启的线程个数
    public static int CPU_CORE = 8;

    //标志位，用于判断当前实现类是否为多线程模式，默认不是多线程
    private boolean isConcurrent = false;

    public boolean isConcurrent() {
        return isConcurrent;
    }

    //执行计算的抽象方法，需要其他实现类进行重载实现
    public abstract long doCalc(int targetNum) throws Exception;
}
