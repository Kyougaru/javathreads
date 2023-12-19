public class TaskWithSynchronized implements Runnable {
    @Override
    public synchronized void run() {
        Main.i++;
        System.out.println("Synchronized" + Thread.currentThread().getName() + ":" + Main.i);
    }
}
