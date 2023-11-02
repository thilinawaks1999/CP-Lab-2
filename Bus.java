public class Bus extends Thread {
    private BusStop busStop;

    public Bus(BusStop busStop) {
        this.busStop = busStop;
    }

    @Override
    public void run() {
        try {
            busStop.busArrives();
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        busStop.depart();
    }
}
