package mediaDB.simulation;


//https://stackoverflow.com/questions/2536692/a-simple-scenario-using-wait-and-notify-in-java
public class BlockingQueue {

//    private MediaFileRepository admin;
//    private BigDecimal MAX_CAPACITY;
//    private BigDecimal EIGHTYPERCENT;
//    private BigDecimal TENPERCENT;
//
//    public BlockingQueue(MediaFileRepository admin) {
//        this.admin = admin;
//        this.MAX_CAPACITY = admin.getMAX_CAPACITY();
//        calculate90PERCENT();
//        calculate10PERCENT();
//    }
//
//    public synchronized void put(Uploadable element) throws InterruptedException {
//        while(admin.getCurrentSize().compareTo(EIGHTYPERCENT) > 0) {
//            wait();
//        }
//
//        admin.create(element);
//        notify(); // notifyAll() for multiple producer/consumer threads
//    }
//
//    public synchronized void take(Uploadable element) throws InterruptedException {
//        while(admin.getCurrentSize().compareTo(TENPERCENT) < 0) {
//            wait();
//        }
//
//        admin.delete(element);
//        notify(); // notifyAll() for multiple producer/consumer threads
//    }
//
//    private void calculate90PERCENT() {
//        if (MAX_CAPACITY != null)
//            EIGHTYPERCENT = MAX_CAPACITY.multiply(new BigDecimal("0.8"));
//    }
//
//    private void calculate10PERCENT() {
//        if (MAX_CAPACITY != null)
//            TENPERCENT = MAX_CAPACITY.multiply(new BigDecimal("0.1"));
//    }
}
