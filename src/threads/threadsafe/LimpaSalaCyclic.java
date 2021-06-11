package threads.threadsafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LimpaSalaCyclic {
    public static void main(String... args) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(4);
            var manager = new LimpaSalaCyclic();
            for (int i = 0; i < 4; i++)
                service.submit(() -> manager.executarLimpeza());
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

    public void executarLimpeza() {
        removerColaboradores();
        limparSala();
        adicionarColaboradores();
    }
}