package mediaDB.IO;

import mediaDB.domain_logic.producer.Uploader;
import mediaDB.domain_logic.file_interfaces.Uploadable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DeserializeDomainContent {
    public BigDecimal deserializeBigDecimal(ObjectInputStream ois){
//        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream(filename))){
        try {
            return (BigDecimal)ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Uploadable> deserializeUploadableList(ObjectInputStream ois){
        try {
            return (List<Uploadable>)ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Map<String , Integer> deserializeStringIntegerMap(ObjectInputStream ois){
        try {
            return (Map<String , Integer>)ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Uploader> deserializeUploaderSet(ObjectInputStream ois){
        try {
            return (ArrayList<Uploader>)ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Set<Integer> deserializeIntegerSet(ObjectInputStream ois) {
        try {
            return (Set<Integer>) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<SavedMediaFile> deserializeSavedMediaFiles(ObjectInputStream ois){
        try {
            return (ArrayList<SavedMediaFile>) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int deserializeCurrentOffset(ObjectInputStream ois){
        try {
            return (int) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
