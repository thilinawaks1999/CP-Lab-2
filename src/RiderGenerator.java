import java.util.Random;

public class RiderGenerator implements Runnable {
    private float interArrivalMeanTime;
    private static Random random;

    public RiderGenerator(float interArrivalMeanTime) {
        this.interArrivalMeanTime = interArrivalMeanTime;
        random = new Random();
    }

    @Override
    public void run() {
        int riderIndex = 1;

        // Spawning rider threads for the user specified value
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // Initializing and starting the rider threads
                Rider rider = new Rider(
                        riderIndex,
                        SemaphoreCollection.getWaitingAreaSemaphore(),
                        SemaphoreCollection.getRiderBoardBusSemaphore(),
                        SemaphoreCollection.getBusArrivalSemaphore(),
                        SemaphoreCollection.getBusDepartureSemaphore()
                );
                (new Thread(rider)).start();

                riderIndex++;

                // Sleeping the thread to obtain the inter arrival time between the threads
                Thread.sleep(getRiderInterArrivalTime());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to get the rider inter arrival time
    public long getRiderInterArrivalTime() {
        //distribution = 1 - e^(-lambda * x)
        float lambda = 1 / interArrivalMeanTime;
        return Math.round(Math.log(1 - random.nextFloat()) / (-lambda));
    }
}