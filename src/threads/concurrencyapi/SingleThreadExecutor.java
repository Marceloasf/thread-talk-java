package threads.concurrencyapi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService service = null;

        Runnable task1 = () -> System.out.println("Imprimindo inventário");
        Runnable task2 = () -> {
            for (int i = 0; i < 3; i++) System.out.println("Imprimindo: " + i);
        };

        try {
            System.out.println("Começou");
            service = Executors.newSingleThreadExecutor();
            service.execute(task1);
            service.execute(task2);
            service.execute(task1);
            System.out.println("Fim");
        } finally {
            if (service != null) service.shutdown();
        }
    }
}