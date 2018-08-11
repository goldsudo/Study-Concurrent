/**
 * 测试sleep方法特性
 */
public class SleepTest implements Runnable {
    public String name;
    public long sleepTime;

    public SleepTest() {
    }

    public SleepTest(String name, long sleepTime) {
        this.name = name;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        System.out.println("线程[" + name + "]开始执行");
        System.out.println("线程[" + name + "]即将睡眠 " + sleepTime + " ms");
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程[" + name + "]睡眠了 " + sleepTime + " ms");
        System.out.println("线程[" + name + "]执行完毕");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new SleepTest("测试1", 3000));
        Thread t2 = new Thread(new SleepTest("测试2", 1000));
        t1.start();
        t2.start();
    }
}
