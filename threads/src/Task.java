public class Task implements Runnable {
    @Override
    public void run() {
        Main.i++;
        System.out.println(Thread.currentThread().getName() + ":" + Main.i);
    }
}
