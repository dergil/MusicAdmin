package mediaDB.domain_logic.observables;

import mediaDB.observer.Observable;
import mediaDB.observer.Observer;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class UploadsObservable {//extends Observable implements Serializable {
//    private List<Observer> observerList = new LinkedList<>();
//    private ChangeInDomainLogic changeInDomainLogic = null;
//
//    public synchronized void change(String type, String address){
//        changeInDomainLogic = new ChangeInDomainLogic(type, address);
//        advertise();
//    }
//
//    @Override
//    public void register(Observer observer) {
//        this.observerList.add(observer);
//    }
//
//    @Override
//    public void deregister(Observer observer) {
//        this.observerList.remove(observer);
//    }
//
//    @Override
//    public synchronized void advertise() {
//        for (Observer observer : observerList)
//            observer.update();
//    }
//
//    public synchronized ChangeInDomainLogic getChangeInDomainLogic() {
//        return changeInDomainLogic;
//    }
}
