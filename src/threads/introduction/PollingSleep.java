package threads.introduction;

public class PollingSleep {
    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            for(int i = 0; i < 500; i++) PollingSleep.counter++;
        }).start();

        while(PollingSleep.counter < 100) {
            System.out.println("Not reached yet");
//            Thread.sleep(1000);
        }

        System.out.println("Reached!");
    }
}
