//package mediaDB.IO;
//
//import mediaDB.domainLogic.Administration;
//import mediaDB.domainLogic.Producer;
//import mediaDB.domainLogic.ProducerDB;
//import mediaDB.domainLogic.Uploadable;
//import mediaDB.observerPattern.Observer;
//
//import java.math.BigDecimal;
//import java.util.Collection;
//import java.util.List;
//
//public class RefillAdministration {
//    Administration administration;
//
//    public RefillAdministration(Administration administration) {
//        this.administration = administration;
//    }
//
//    public void uploads(Collection<Uploadable> uploads){
//        for (Uploadable u : uploads){
//            administration.create(u);
//        }
//    }
//
//    public void currentSize(BigDecimal currentSize){
//        administration.setCurrentSize(currentSize);
//    }
//
//    public void observerList(List<Observer> list){
//        administration.setObserverList(list);
//    }
//
//    public void producerDB(ProducerDB db){
//        for (int i = 0; i < db.getSize(); i++) {
//            Producer add = (Producer) db.getProducer(i);
//            assert add != null;
//            administration.addProducer(add);
//        }
//    }
//
//    public void nextAddress(int nextAddress){
//        administration.setNextAddress(nextAddress);
//    }
//}
