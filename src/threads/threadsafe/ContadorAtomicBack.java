package threads.threadsafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ContadorAtomicBack {
    private AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        ExecutorService service = null;

        try {
            service = Executors.newFixedThreadPool(20);
            ContadorAtomicBack manager = new ContadorAtomicBack();

            for (int i = 0; i < 10; i++) {
                service.submit(() -> manager.incrementarAndImprimir());
            }
        } finally {
            if (service != null) service.shutdown();
        }
    }

    private void incrementarAndImprimir() {
        System.out.print((count.incrementAndGet()) + " ");
    }
}
