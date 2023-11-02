import java.util.Random;

public class RiderCreator implements Runnable {
    private float arrivalMeanTime;
    private static Random random;

    public RiderCreator(float arrivalMeanTime) {
        this.arrivalMeanTime = arrivalMeanTime;
        random = new Random();
    }

    @Override
    public void run() {
        int riderId = 1;

        // Spawning rider threads for the user specified value
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // Initializing and starting the rider threads
                Rider rider = new Rider(
                        riderId,
                        SemaphoreCollection.getWaitingAreaSemaphore(),
                        SemaphoreCollection.getRiderBoardBusSemaphore(),
                        SemaphoreCollection.getBusArrivalSemaphore(),
                        SemaphoreCollection.getBusDepartureSemaphore()
                );
                (new Thread(rider)).start();

                riderId++;

                // Sleeping the thread to obtain the inter arrival time between the threads
                Thread.sleep(getRiderArrivalTime());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to get the rider inter arrival time
    public long getRiderArrivalTime() {
        float lambda = 1 / arrivalMeanTime;
        return Math.round(Math.log(1 - random.nextFloat()) / (-lambda));
    }
}