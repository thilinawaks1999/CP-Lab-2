import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        float riderArrivalMeanTime = Constant.getRiderArrivalMeanTime(); // 30 seconds
        float busArrivalMeanTime = Constant.getBusArrivalMeanTime(); // 20 minutes

        String userInput;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("\n*******  Press X or x to exit  *******\n" );

            System.out.println( "--------------- Start of the Senate Bus at Wellesley College ---------------\n" );
            System.out.println(new java.util.Date());

            RiderCreator riderCreator = new RiderCreator(riderArrivalMeanTime);
            (new Thread(riderCreator)).start();

            RandomBusCreator busCreator = new RandomBusCreator(busArrivalMeanTime);
            (new Thread(busCreator)).start();

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