package threads.introduction;

public class PollingThread {
    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            for(int i = 0; i < 500; i++) PollingThread.counter++;
        }).start();

        while(PollingThread.counter < 100) {
            System.out.println("Ainda não chegou");
//            Thread.sleep(1000);
        }

        System.out.println("Chegou!");
    }
}
