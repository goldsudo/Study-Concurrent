package calculate.impl;

import calculate.AbstractCalcSum;


public class CalcSum extends AbstractCalcSum {

    //模式为单线程
    private boolean isConcurrent = false;

    @Override
    public boolean isConcurrent() {
        return isConcurrent;
    }

    @Override
    public long doCalc(int targetNum) {
        long sum = 0;
        //简单的循环累加
        for (int i = 1; i <= targetNum; ++i) {
            sum += i;
        }
        return sum;
    }
}
