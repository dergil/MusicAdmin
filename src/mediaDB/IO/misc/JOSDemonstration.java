//package mediaDB.IO;
//
//import mediaDB.domainLogic.Administration;
//import mediaDB.domainLogic.ProducerDB;
//import mediaDB.domainLogic.Uploadable;
//import mediaDB.observerPattern.Observer;
//
//import java.math.BigDecimal;
//import java.util.Collection;
//import java.util.List;
//
//public class JOSDemonstration {
//    public void execute(){
//        System.out.println("JOS demonstration: ");
//        System.out.println(" ");
//        Administration administration = new Administration();
//        Administration newAdministration = new Administration();
//        PopulateAdminForSerialization populateDomainLogic = new PopulateAdminForSerialization(administration);
//        populateDomainLogic.populate();
//        System.out.println("Administration pre-JOS: ");
//        System.out.println(administration.toString());
//
//        AdminSerialization.serializeUploads(administration, "uploads.ser");
//        AdminSerialization.serializeCurrentSize(administration, "currentSize.ser");
//        AdminSerialization.serializeOberverList(administration, "observerList.ser");
//        AdminSerialization.serializeProducerDB(administration, "producerDB.ser");
//        AdminSerialization.serializeAddressCount(administration, "nextAddress.ser");
//        Collection<Uploadable> resUploads = AdminSerialization.deserializeUploads("uploads.ser");
//        BigDecimal resCurrentSize = AdminSerialization.deserializeCurrentSize("currentSize.ser");
//        List<Observer> resObserverList = AdminSerialization.deserializeObserverList("observerList.ser");
//        ProducerDB resProducerDB = AdminSerialization.deserializeProducerDB("producerDB.ser");
//        int resAddressCount = AdminSerialization.deserializeAddressCount("nextAddress.ser");
//
//        System.out.println("--------------");
//
//        RefillAdministration refillAdministration = new RefillAdministration(newAdministration);
//
//        assert resProducerDB != null;
//        refillAdministration.producerDB(resProducerDB);
//        assert resUploads != null;
//        refillAdministration.uploads(resUploads);
//        refillAdministration.currentSize(resCurrentSize);
//        refillAdministration.observerList(resObserverList);
//        refillAdministration.nextAddress(resAddressCount);
//        System.out.println("Administration post-JOS: ");
//        System.out.println(newAdministration.toString());
//    }
//}
