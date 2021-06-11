package threads.threadsafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ContadorLockBack {
    private int count = 0;

    public static void main(String[] args) {
        ExecutorService service = null;

        try {
            service = Executors.newFixedThreadPool(20);
            ContadorLockBack manager = new ContadorLockBack();

            Lock lock = new ReentrantLock();
            for (int i = 0; i < 10; i++) {
                service.submit(() -> {
                    try {
                        manager.incrementarAndImprimir(lock);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        } finally {
            if (service != null) service.shutdown();
        }
    }

    private void incrementarAndImprimir(Lock lock) throws InterruptedException {
        if (lock.tryLock(10, TimeUnit.SECONDS)) {
            try {
                System.out.print((++count) + " ");
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Não conseguiu a lock");
        }
    }
}
