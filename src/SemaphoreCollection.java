import java.util.concurrent.Semaphore;

public class SemaphoreCollection {
    // Semaphore used for riders to come the waiting area, allowing 50 riders to the waiting area
    private static final Semaphore waitingArea = new Semaphore(50);

    // Semaphore used for riders to board the bus
    private static final Semaphore riderBoard = new Semaphore(0);

    // Semaphore used for bus to arrive
    private static final Semaphore busArrival = new Semaphore(1);

    // Semaphore used for bus to depart after the riders are boarded
    private static final Semaphore busDeparture = new Semaphore(0);

    // Method to get the waitingAreaSemaphore
    public static Semaphore getWaitingAreaSemaphore() {
        return waitingArea;
    }

    // Method to get the riderBoardBusSemaphore
    public static Semaphore getRiderBoardBusSemaphore() {
        return riderBoard;
    }

    // Method to get the busArrivalSemaphore
    public static Semaphore getBusArrivalSemaphore() {
        return busArrival;
    }

    // Method to get the busDepartureSemaphore
    public static Semaphore getBusDepartureSemaphore() {
        return busDeparture;
    }
}
