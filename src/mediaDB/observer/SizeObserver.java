package mediaDB.observer;

import mediaDB.domain_logic.observables.SizeObservable;

import java.math.BigDecimal;

//TODO: observation printed twice?
public class SizeObserver implements Observer {
    private SizeObservable sizeObservable;
    private BigDecimal MAX_CAPACITY;
    private BigDecimal NINETYPERCENT;


    public SizeObserver(SizeObservable sizeObservable) {
        this.sizeObservable = sizeObservable;
        setMAX_CAPACITY(sizeObservable.getMAX_CAPACITY());
        calculate90PERCENT();
    }

    @Override
    public void update() {
        BigDecimal currentSize = sizeObservable.getCurrentSize();
        if (currentSize.compareTo(NINETYPERCENT) > 0)
            System.out.println("90% of the capacity is reached.");
    }

    public void setMAX_CAPACITY(BigDecimal MAX_CAPACITY) {
        this.MAX_CAPACITY = MAX_CAPACITY;
    }

    public void calculate90PERCENT() {
        if (MAX_CAPACITY != null)
            NINETYPERCENT = MAX_CAPACITY.multiply(new BigDecimal("0.9"));
    }
}
