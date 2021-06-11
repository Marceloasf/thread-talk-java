package threads.introduction;

public class RunningThreads {

    public static void main(String... args) {
        System.out.println("Começou");
        (new PrintInventarioThread()).run();
        (new Thread(new PrintNumerosRunnable())).run();
        (new PrintInventarioThread()).run();
        System.out.println("Fim");
    }
}
