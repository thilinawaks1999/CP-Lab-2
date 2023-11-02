public class Rider extends Thread {
    private BusStop busStop;

    public Rider(BusStop busStop) {
        this.busStop = busStop;
    }

    @Override
    public void run() {
        busStop.riderArrives();
        System.out.println("Rider arrived at the bus stop.");
        busStop.boardBus();
        System.out.println("Rider boarded the bus.");
    }
}
