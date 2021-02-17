package mediaDB.simulation;


import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.observables.SizeObservable;
import mediaDB.domain_logic.file_interfaces.Uploadable;

import java.math.BigDecimal;

//https://stackoverflow.com/questions/2536692/a-simple-scenario-using-wait-and-notify-in-java
public class BlockingQueue {

    private MediaFileRepository mediaFileRepository;
    private SizeObservable sizeObservable;
    private BigDecimal MAX_CAPACITY;
    private BigDecimal NINETYPERCENT;
    private BigDecimal TENPERCENT;

    public BlockingQueue(MediaFileRepository mediaFileRepository, SizeObservable sizeObservable) {
        this.mediaFileRepository = mediaFileRepository;
        this.sizeObservable = sizeObservable;
        this.MAX_CAPACITY = sizeObservable.getMAX_CAPACITY();
        calculate90PERCENT();
        calculate10PERCENT();
    }

    public synchronized void put(Uploadable element) throws InterruptedException {
        while(sizeObservable.getCurrentSize().compareTo(NINETYPERCENT) > 0) {
            wait();
        }

        mediaFileRepository.create(element);
        notify(); // notifyAll() for multiple producer/consumer threads
    }

    public synchronized void take(Uploadable element) throws InterruptedException {
        while(sizeObservable.getCurrentSize().compareTo(TENPERCENT) < 0) {
            wait();
        }

        mediaFileRepository.delete(element);
        notify(); // notifyAll() for multiple producer/consumer threads
    }

    private void calculate90PERCENT() {
        if (MAX_CAPACITY != null)
            NINETYPERCENT = MAX_CAPACITY.multiply(new BigDecimal("0.9"));
    }

    private void calculate10PERCENT() {
        if (MAX_CAPACITY != null)
            TENPERCENT = MAX_CAPACITY.multiply(new BigDecimal("0.1"));
    }
}
