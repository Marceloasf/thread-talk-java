package threads.threadsafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContadorSyncBack {
    private int count = 0;

    public static void main(String[] args) {
        ExecutorService service = null;

        try {
            service = Executors.newFixedThreadPool(20);
            ContadorSyncBack manager = new ContadorSyncBack();

            for (int i = 0; i < 10; i++) {
                service.submit(() -> manager.incrementarAndImprimir());
            }
        } finally {
            if (service != null) service.shutdown();
        }
    }

    private void incrementarAndImprimir() {
        synchronized (this) {
            System.out.print((++count) + " ");
        }
    }
}
