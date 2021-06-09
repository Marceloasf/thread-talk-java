package threads.introduction;

// Implementa a interface Runnable e é passada no construtor de Thread para que sua implementação de run() seja executada
public class PrintNumerosRunnable implements Runnable {

    @Override
    public void run() {
        for(int i = 0; i < 3; i++)
            System.out.println("Imprimindo: " + i);
    }

    public static void main(String[] args) {
        new Thread(new PrintNumerosRunnable()).start();
    }
}
