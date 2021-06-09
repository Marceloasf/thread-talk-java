package threads.concurrencyapi;

import java.util.concurrent.*;

// Using ExecutorService methods (submit()) to do polling on a class.
// Thus example is the essence of the Concurrency API: to do complex things with threads without having to manage them directly.
// Non thread-safe example, but its using a single-thread executor.
public class PollingSingleThread {

    private static int counter = 0;

    public static void main(String[] unused) throws Exception {
        ExecutorService service = null;

        try {
            service = Executors.newSingleThreadExecutor();
            Future<?> result = service.submit(() -> {
                for(int i = 0; i < 500; i++) {
                    PollingSingleThread.counter++;
                    System.out.println(PollingSingleThread.counter);
                }
            });
            result.get(10, TimeUnit.SECONDS); // Resultado async, se passar do tempo inserido, uma exception é lançada!
            System.out.println("Reached!");
        } catch (TimeoutException e) {
            System.out.println("Not reached in time");
        } finally {
            if(service != null) service.shutdown();
        }
    }
}