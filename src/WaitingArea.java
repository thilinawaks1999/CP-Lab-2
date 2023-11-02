public class WaitingArea {
    private static int ridersCount = 0;

    // Method to get the ridersCount
    public static int getRidersCount() {
        return ridersCount;
    }

    // Method to increment the ridersCount
    public static void riderCountPlus() {
        ridersCount++;
    }

    // Method to decrement the ridersCount
    public static void riderCountMinus() {
        ridersCount--;
    }
}