public class Constant {
    private static final float riderArrivalMeanTime = 30f * 1000; // 30 seconds
    private static final float busArrivalMeanTime = 20 * 60f * 1000; // 20 minutes
    private static final int busCapacity = 50;
    private static final int busCountCanArrive = 1;

    public static float getRiderArrivalMeanTime() {
        return riderArrivalMeanTime;
    }

    public static float getBusArrivalMeanTime() {
        return busArrivalMeanTime;
    }

    public static int getBusCapacity() {
        return busCapacity;
    }

    public static int getBusCountCanArrive() {
        return busCountCanArrive;
    }
}
