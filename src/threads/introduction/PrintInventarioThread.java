package threads.introduction;

// Herda a classe Thread e sobrescreve o run() da superclasse que será executado no start()
public class PrintInventarioThread extends Thread {

    @Override
    public void run() {
        System.out.println("Imprimindo inventário");
    }

    public static void main(String... args) {
        new PrintInventarioThread().start();
    }
}
