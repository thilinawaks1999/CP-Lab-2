import java.util.concurrent.Semaphore;

public class Bus implements Runnable {
    private final int id;
    private final Semaphore busArrivalSemaphore;
    private final Semaphore busDepartureSemaphore;
    private final Semaphore riderBoardSemaphore;
    private static int busId;

    public Bus(int id, Semaphore busArrivalSemaphore, Semaphore busDepartureSemaphore,
               Semaphore riderBoardSemaphore) {
        this.id = id;
        this.busArrivalSemaphore = busArrivalSemaphore;
        this.busDepartureSemaphore = busDepartureSemaphore;
        this.riderBoardSemaphore = riderBoardSemaphore;
    }

    @Override
    public void run() {
        try {
            // Acquiring busArrivalSemaphore to arrive
            busArrivalSemaphore.acquire();

            // Arrival of the bus
            arrive();

            // Checking if there are waiting riders
            System.out.println("Waiting riders count: " + WaitingArea.getRidersCount());

            if (WaitingArea.getRidersCount() > 0) {
                // Releasing the rider boarding semaphore allowing a rider to board the bus
                riderBoardSemaphore.release();
                // Acquiring the bus departure semaphore to wait the bus until the riders get boarded
                busDepartureSemaphore.acquire();
            }

            // Departure of the bus
            depart();

            // Releasing busArrivalSemaphore, allowing next bus or rider to come
            busArrivalSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method to get the index of the bus
    public static int getBusCount() {
        return busId;
    }

    // Method to indicate the arrival of the bus and set the busId
    private void arrive() {
        busId=id;
        System.out.println("\n>> Bus_" + id + " arrived the bus stop at " + new java.util.Date() + "\n");
    }

    // Method to indicate the departure of the bus
    private void depart() {
        System.out.println("\n>> Bus_" + id + " departed the bus stop with at " + new java.util.Date() + "\n");
    }
}