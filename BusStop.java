import java.util.concurrent.Semaphore;

public class BusStop {
    private final int busCapacity = 50;
    private int waitingRiders = 0;
    private Semaphore busSemaphore = new Semaphore(busCapacity);
    private Semaphore riderBoardedSemaphore = new Semaphore(0);
    private Semaphore busDepartureSemaphore = new Semaphore(0);
    private Object lock = new Object();

    public void boardBus() {
        try {
            busSemaphore.acquire();
            synchronized (lock) {
                waitingRiders--;
                if (waitingRiders == 0) {
                    busDepartureSemaphore.release();
                }
            }
            riderBoardedSemaphore.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void depart() {
        try {
            riderBoardedSemaphore.acquire(busCapacity);
            System.out.println("Bus departing with " + busCapacity + " riders.");
            busSemaphore.release(busCapacity);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void riderArrives() {
        synchronized (lock) {
            if (waitingRiders == busCapacity) {
                busDepartureSemaphore.release();
            }
            waitingRiders++;
        }
    }

    public void busArrives() throws InterruptedException {
        if (waitingRiders == 0) {
            System.out.println("Bus arriving with no riders. Departing immediately.");
            busSemaphore.release(busCapacity);
        } else {
            System.out.println("Bus arriving. Waiting for riders to board.");
            busDepartureSemaphore.acquire();
        }
    }
}
