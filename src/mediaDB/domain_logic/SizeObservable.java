package mediaDB.domain_logic;

import mediaDB.observer.Observable;
import mediaDB.observer.Observer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

//TODO: calculate size here?
public class SizeObservable extends Observable implements Serializable {
    private List<Observer> observerList = new LinkedList<>();
    BigDecimal MAX_CAPACITY = new BigDecimal(170000000L);
    BigDecimal currentSize = new BigDecimal(0);

    void addSize(BigDecimal size){
        currentSize = currentSize.add(size);
    }

    void subtractSize(BigDecimal size){
        currentSize = currentSize.subtract(size);
    }

    public boolean capacityAvailable(BigDecimal size){
        BigDecimal newSize = currentSize.add(size);
        return newSize.compareTo(MAX_CAPACITY) < 0;
    }

    @Override
    public void register(Observer observer) {
        this.observerList.add(observer);
    }

    @Override
    public void deregister(Observer observer) {
        this.observerList.remove(observer);
    }

    @Override
    public void advertise() {
        for (Observer observer : observerList)
            observer.update();
    }

    public BigDecimal getMAX_CAPACITY() {
        return MAX_CAPACITY;
    }

    public BigDecimal getCurrentSize() {
        return currentSize;
    }

    public void setMAX_CAPACITY(BigDecimal MAX_CAPACITY) {
        this.MAX_CAPACITY = MAX_CAPACITY;
    }

    public List<Observer> getObserverList() {
        return observerList;
    }

    public void setObserverList(List<Observer> observerList) {
        this.observerList = observerList;
    }

    public void setCurrentSize(BigDecimal currentSize) {
        this.currentSize = currentSize;
    }
}
