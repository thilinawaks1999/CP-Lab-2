import java.util.Random;

public class RandomBusCreator implements Runnable {
    private float arrivalMeanTime;
    private static Random random;

    public RandomBusCreator(float arrivalMeanTime) {
        this.arrivalMeanTime = arrivalMeanTime;
        random = new Random();
    }

    @Override
    public void run() {
        int busIndex = 1;

        // Spawning bus threads for the user specified value
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // Initializing and starting the bus threads
                Bus bus = new Bus(
                        busIndex,
                        SemaphoreCollection.getBusArrivalSemaphore(),
                        SemaphoreCollection.getBusDepartureSemaphore(),
                        SemaphoreCollection.getRiderBoardBusSemaphore()
                );
                (new Thread(bus)).start();

                // Incrementing busIndex
                busIndex++;

                // Sleeping the thread to obtain the inter arrival time between the bus threads
                Thread.sleep(getArrivalTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to get the exponentially distributed bus inter arrival time
    public long getArrivalTime() {
        float lambda = 1 / arrivalMeanTime;
        return Math.round(Math.log(1 - random.nextFloat()) / (-lambda));
    }
}