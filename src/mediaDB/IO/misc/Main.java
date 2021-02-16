//package mediaDB.IO;
//
//import mediaDB.domainLogic.*;
//import mediaDB.observerPattern.Observer;
//
//import java.math.BigDecimal;
//import java.util.Collection;
//import java.util.List;
//
//public class Main {
//
//    public static void main(String[] args) {
//        Administration administration = new Administration();
//        Administration newAdministration = new Administration();
//        PopulateAdminForSerialization populateDomainLogic = new PopulateAdminForSerialization(administration);
//        populateDomainLogic.populate();
//        System.out.println(administration.toString());
////
////        AdminSerialization.serializeAdmin(administration, "admin.ser");
////        Administration resAdmin = AdminSerialization.deserializeAdministration("admin.ser");
////        assert resAdmin != null;
////        ArrayList<Uploadable> list;
////        list = resAdmin.read();
////        assert list != null;
////        for (Uploadable u : list) {
////            if (u instanceof InteractiveVideo)
////                System.out.println(((InteractiveVideoFile) u).toString());
////            else if (u instanceof LicensedAudioVideo)
////                System.out.println(((LicensedAudioVideoFile) u).toString());
////        }
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
////        assert resUploads != null;
////        for (Uploadable u : resUploads){
////            if (u instanceof InteractiveVideo)
////                System.out.println(u.toString());
////            else if (u instanceof LicensedAudioVideo)
////                System.out.println(u.toString());
////        }
////        System.out.println(" ");
////        assert resCurrentSize != null;
////        System.out.println("Current size: " + resCurrentSize.toString());
////
////        System.out.println(" ");
////        assert resObserverList != null;
////        System.out.println("Observer list: " + resObserverList.toString());
////
////        System.out.println(" ");
////        assert resProducerDB != null;
////        System.out.println("ProducerDB: " + resProducerDB.toString());
////
////        System.out.println(" ");
////        System.out.println("Next address: " + resAddressCount);
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
//        System.out.println(newAdministration.toString());
//        }
//    }
//
//
//
//
//
//
//
////    ArrayList<SerializableItem> list = new ArrayList<>();
////        for (int i = 1; i < 5; i++) {
////            SerializableItem item  = new SerializableItem();
////            item.string = ("string" + i);
////            item.anInt = i;
////            item.aLong = i*i*i;
////            list.add(item);
////        }
////        for (int i = 0; i < list.size(); i++) {
////            System.out.println(list.get(i).toString());
////        }
////        SerializableItem.serialize("myItems.ser", list);
////        Collection<SerializableItem> loadedList = SerializableItem.deserialize("myItems.ser");
////        for (SerializableItem i : loadedList){
////            System.out.println(i.toString());
////        }