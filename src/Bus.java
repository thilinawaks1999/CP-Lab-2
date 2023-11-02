import java.util.concurrent.Semaphore;

public class Bus implements Runnable {
    private final int index;
    private final Semaphore busArrivalSemaphore;
    private final Semaphore busDepartureSemaphore;
    private final Semaphore riderBoardBusSemaphore;
    private static int busIndex;

    public Bus(int index, Semaphore busArrivalSemaphore, Semaphore busDepartureSemaphore,
               Semaphore riderBoardBusSemaphore) {
        this.index = index;
        this.busArrivalSemaphore = busArrivalSemaphore;
        this.busDepartureSemaphore = busDepartureSemaphore;
        this.riderBoardBusSemaphore = riderBoardBusSemaphore;
    }

    @Override
    public void run() {
        try {
            // Acquiring busArrivalSemaphore to arrive
            busArrivalSemaphore.acquire();

            // Arrival of the bus
            arrive();

            // Checking if there are waiting riders
            System.out.println("Waiting rider count: " + WaitingArea.getRidersCount());

            if (WaitingArea.getRidersCount() > 0) {
                // Releasing the rider boarding semaphore allowing a rider to board the bus
                riderBoardBusSemaphore.release();
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
        return busIndex;
    }

    // Method to indicate the arrival of the bus
    private void arrive() {
        busIndex=index;
        System.out.println(">> Bus " + index + " arrived the bus stop");
    }

    // Method to indicate the departure of the bus
    private void depart() {
        System.out.println(">> Bus " + index + " departed the bus stop");
    }
}