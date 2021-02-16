//package mediaDB.IO;
//
//import mediaDB.domainLogic.*;
//import mediaDB.domain_logic.file_interfaces.Uploadable;
//import mediaDB.observerPattern.Observer;
//
//import java.io.*;
//import java.math.BigDecimal;
//import java.util.Collection;
//import java.util.List;
//
//public class AdminSerialization {
//
//    public static void serializeMediaFiles(List<Uploadable> files, String filename){
//        try (ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(filename))){
//            serializeUploadableList(oos,files);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void serializeUploadableList(ObjectOutput objectOutput, Collection<Uploadable> items) throws IOException {
//        objectOutput.writeObject(items);
//    }
//
////    public static void serializeUploads (Administration admin, String filename){
////        Collection<Uploadable> items = admin.read();
////        try (ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(filename))){
////            serializeUploadList(oos,items);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//
////    }
//
//
////    public static void serializeCurrentSize (BigDecimal size, String filename){
////        try (ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(filename))){
////            serializeBigDecimal(oos,size);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
//
//    public static void serializeSize (BigDecimal size, String filename){
//        try (ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(filename))){
//            serializeBigDecimal(oos,size);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void serializeBigDecimal(ObjectOutput objectOutput, BigDecimal size) throws IOException {
//        objectOutput.writeObject(size);
//    }
//
//
//    public static void serializeOberverList (List<Observer> observers, String filename){
//        try (ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(filename))){
//            serializeOberverListObject(oos,observers);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void serializeOberverListObject(ObjectOutput objectOutput, List<Observer> size) throws IOException {
//        objectOutput.writeObject(size);
//    }
//
//
//
//    public static void serializeProducerDB (Administration admin, String filename){
//        ProducerDB producerDB = admin.getProducers();
//        try (ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(filename))){
//            serializeProducerDBFile(oos,producerDB);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void serializeProducerDBFile (ObjectOutput objectOutput, ProducerDB db) throws IOException {
//        objectOutput.writeObject(db);
//    }
//
//
//    public static void serializeAddressCount (Administration admin, String filename){
//        int nextAddress = admin.getNextAddress();
//        try (ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(filename))){
//            serializeAddressCountInt(oos,nextAddress);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void serializeAddressCountInt (ObjectOutput objectOutput, int nextAddress) throws IOException {
//        objectOutput.writeInt(nextAddress);
//    }
//
////    public static Administration deserializeAdministration(String filename){
////        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream(filename))){
////            return deserializeAdministration(ois);
////        } catch (FileNotFoundException e) {
////            e.printStackTrace();
////        } catch (IOException e) { e.printStackTrace();
////        } catch (ClassNotFoundException e) {
////            e.printStackTrace();
////        }
////        return null;
////}
////    public static Administration deserializeAdministration(ObjectInput objectInput) throws IOException, ClassNotFoundException {
////        return (Administration)objectInput.readObject();
////    }
//
//    public static Collection<Uploadable> deserializeUploads(String filename){
//        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream(filename))){
//            return deserializeUploads(ois);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) { e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    public static Collection<Uploadable> deserializeUploads(ObjectInput objectInput) throws IOException, ClassNotFoundException {
//        return (Collection<Uploadable>)objectInput.readObject();
//    }
//
//    public static BigDecimal deserializeCurrentSize(String filename){
//        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream(filename))){
//            return deserializeCurrentSize(ois);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) { e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    public static BigDecimal deserializeCurrentSize(ObjectInput objectInput) throws IOException, ClassNotFoundException {
//        return (BigDecimal)objectInput.readObject();
//    }
//
//    public static List<Observer> deserializeObserverList(String filename){
//        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream(filename))){
//            return deserializeObserverList(ois);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) { e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    public static List<Observer> deserializeObserverList(ObjectInput objectInput) throws IOException, ClassNotFoundException {
//        return (List<Observer>)objectInput.readObject();
//    }
//
//    public static ProducerDB deserializeProducerDB(String filename){
//        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream(filename))){
//            return deserializeProducerDB(ois);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) { e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    public static ProducerDB deserializeProducerDB(ObjectInput objectInput) throws IOException, ClassNotFoundException {
//        return (ProducerDB)objectInput.readObject();
//    }
//
//    public static int deserializeAddressCount(String filename){
//        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream(filename))){
//            return deserializeAddressCount(ois);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) { e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//    public static int deserializeAddressCount(ObjectInput objectInput) throws IOException, ClassNotFoundException {
//        return objectInput.readInt();
//    }
//}
