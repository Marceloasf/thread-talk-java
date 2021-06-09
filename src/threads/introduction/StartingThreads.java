package threads.introduction;

public class StartingThreads {

    public static void main(String... args) {
        System.out.println("begin");
        (new PrintInventarioThread()).start();
        (new Thread(new PrintNumerosRunnable())).start();
        (new PrintInventarioThread()).start();
        System.out.println("end");
    }
}
