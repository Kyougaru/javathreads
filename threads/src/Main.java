public class Main {
    public static int i = 0;

    public static void main(String[] args) {
        //Runnables
        var task = new Task();
        var taskWithSynchronized = new TaskWithSynchronized();

        //Problema de concorrência
        var t0 = new Thread(task);
        var t1 = new Thread(task);
        var t2 = new Thread(task);

        t0.start();
        t1.start();
        t2.start();

        //Usando Synchronized
        var t3 = new Thread(taskWithSynchronized);
        var t4 = new Thread(taskWithSynchronized);
        var t5 = new Thread(taskWithSynchronized);

        t3.start();
        t4.start();
        t5.start();
    }
}