package threads.threadsafe;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LimpaSalaCyclicBack {
    public static void main(String... args) {
        ExecutorService service = null;

        try {
            service = Executors.newFixedThreadPool(4);
            var manager = new LimpaSalaCyclicBack();

            CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
            CyclicBarrier cyclicBarrier2 = new CyclicBarrier(4, () -> System.out.println("Sala limpa!"));

            for (int i = 0; i < 4; i++) {
                service.submit(() -> manager.executarLimpeza(cyclicBarrier, cyclicBarrier2));
            }
        } finally {
            if (service != null) service.shutdown();
        }
    }

    private void removerColaboradores() {
        System.out.println("Removendo colaboradores");
    }

    private void limparSala() {
        System.out.println("Limpando a sala");
    }

    private void adicionarColaboradores() {
        System.out.println("Adicionando colaboradores");
    }

    public void executarLimpeza(CyclicBarrier cyclicBarrier, CyclicBarrier cyclicBarrier2) {
        try {
            removerColaboradores();
            cyclicBarrier.await();
            limparSala();
            cyclicBarrier2.await();
            adicionarColaboradores();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}