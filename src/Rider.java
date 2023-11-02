import java.util.concurrent.Semaphore;

class Rider implements Runnable {
    private int id;
    private final Semaphore waitingAreaSemaphore;
    private final Semaphore riderBoardSemaphore;
    private final Semaphore busArrivalSemaphore;
    private final Semaphore busDepartureSemaphore;

    public Rider(int id, Semaphore waitingAreaSemaphore, Semaphore riderBoardSemaphore,
                 Semaphore busArrivalSemaphore, Semaphore busDepartureSemaphore) {
        this.id = id;
        this.waitingAreaSemaphore = waitingAreaSemaphore;
        this.riderBoardSemaphore = riderBoardSemaphore;
        this.busArrivalSemaphore = busArrivalSemaphore;
        this.busDepartureSemaphore = busDepartureSemaphore;
    }

    @Override
    public void run() {
        try {
            // Acquiring the semaphore in trying to enter the rider waiting area
            waitingAreaSemaphore.acquire();

            // Acquiring busArrivalSemaphore allowing rider to enter the waiting area before a bus comes
            busArrivalSemaphore.acquire();

            // Enter waiting area and increment the ridersCount
            enterWaitingArea();
            WaitingArea.riderCountPlus();

            // Releasing busArrivalSemaphore allowing a bus to arrive
            busArrivalSemaphore.release();

            // Acquiring the semaphore to board the bus
            riderBoardSemaphore.acquire();

            // Board the bus
            boardBus();

            // Decrementing the ridersCount once boarded
            WaitingArea.riderCountMinus();

            if (WaitingArea.getRidersCount() == 0) {
                // When all the riders are boarded, allowing the bus to depart by releasing the bus departure semaphore
                busDepartureSemaphore.release();
            } else {
                // If there are more riders waiting, allowing them to get into the bus
                riderBoardSemaphore.release();
            }

            // Releasing the semaphore for enter waiting area
            waitingAreaSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void boardBus() {
        System.out.println("Rider_" + id + " boarded bus to the bus with index:" + Bus.getBusCount() + " at " + new java.util.Date());
    }

    public void enterWaitingArea() {
        System.out.println("Rider_" + id + " entered waiting area to board the bus at " + new java.util.Date());
    }
}