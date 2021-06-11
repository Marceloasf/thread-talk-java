package threads.concurrencyapi;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFutureCollections {

    public static void main(String[] args) throws Exception {
        ExecutorService service = null;

        try {
            service = Executors.newSingleThreadExecutor();
            System.out.println("Começou");
            Callable<String> tarefa = () -> "Resultado 1";
            Callable<String> tarefa2 = () -> "Resultado 2";
            Callable<String> tarefa3 = () -> "Resultado 3";
            List<Future<String>> list = service.invokeAll(List.of(tarefa, tarefa2, tarefa3));
            for (Future<String> future : list) {
                System.out.println(future.get());
            }
            System.out.println("Fim");
        } finally {
            if (service != null) service.shutdown();
        }
    }
}
