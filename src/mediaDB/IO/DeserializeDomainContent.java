package mediaDB.IO;

import mediaDB.domain_logic.Uploader;
import mediaDB.domain_logic.file_interfaces.Uploadable;
import mediaDB.observer.Observer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DeserializeDomainContent {
    public BigDecimal deserializeBigDecimal(String filename){
        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream(filename))){
            return (BigDecimal)ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Uploadable> deserializeUploadableList(String filename){
        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream(filename))){
            return (List<Uploadable>)ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public List<Observer> deserializeObserverList(String filename){
//        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream(filename))){
//            return (List<Observer>)ois.readObject();
//        } catch (ClassNotFoundException | IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


    public Map<String , Integer> deserializeStringIntegerMap(String filename){
        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream(filename))){
            return (Map<String , Integer>)ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Uploader> deserializeUploaderSet(String filename){
        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream(filename))){
            return (ArrayList<Uploader>)ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Set<Integer> deserializeIntegerSet(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Set<Integer>) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
