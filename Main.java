public class Main {
    public static void main(String[] args) {
        BusStop busStop = new BusStop();

        for (int i = 1; i <= 100; i++) {
            new Rider(busStop).start();
            try {
                Thread.sleep(100); // Simulate rider arrivals
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        for (int i = 1; i <= 3; i++) {
            new Bus(busStop).start();
            try {
                Thread.sleep(500); // Simulate bus arrivals
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
