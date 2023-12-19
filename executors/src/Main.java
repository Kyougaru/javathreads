import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static AtomicInteger resource = new AtomicInteger(0);

    public static void main(String[] args) {
        List<Runnable> tasks = new ArrayList<>();
        for(int i=1; i<=10; i++) {
            final int taskId = i;
            tasks.add(() ->
                System.out.println("Task " + taskId + " is running, and integer value is " + resource.incrementAndGet())
            );
        }

        ExecutorService fixed = Executors.newFixedThreadPool(3); //Número fixo de threads
        ExecutorService cached = Executors.newCachedThreadPool(); //Threads são criadas e reutilizadas conforme necessário
        ExecutorService single = Executors.newSingleThreadExecutor(); //Uma única thread

        executeServiceTasks(fixed, tasks);
        executeServiceTasks(cached, tasks);
        executeServiceTasks(single, tasks);

        //Testando Scheduled Executor, thread pool com agendamento
        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(3);
        scheduled.scheduleAtFixedRate(() -> {
                    if (resource.get() == 40) {
                        scheduled.shutdown();
                    } else {
                        System.out.println("Using scheduled executor, and integer value is " + resource.incrementAndGet());
                    };
                },0, 1, TimeUnit.SECONDS);
    }

    private static void executeServiceTasks(ExecutorService executorService, List<Runnable> tasks) {
        try {
            System.out.println("==================================================");
            for (Runnable task : tasks) {
                executorService.submit(task);
            }
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            executorService.shutdownNow();
        }
    }
}