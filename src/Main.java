import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        float riderInterArrivalMeanTime = 30f * 10;
        float busInterArrivalMeanTime = 20 * 60f * 10;

        String userInput;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("\n*******  Press X or x to exit  *******\n" );

            RiderGenerator riderGenerator = new RiderGenerator(riderInterArrivalMeanTime);
            (new Thread(riderGenerator)).start();

            RandomBusCreator busGenerator = new RandomBusCreator(busInterArrivalMeanTime);
            (new Thread(busGenerator)).start();

            while (true) {
                userInput = scanner.nextLine();
                // Exit the program if the user presses x or X
                if (userInput.equalsIgnoreCase("x")) {
                    System.out.println("Exiting the program...");
                    System.exit(0);
                }
            }
        }
    }
}