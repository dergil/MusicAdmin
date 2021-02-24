package mediaDB.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import mediaDB.ui.Numerical;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SortUploads {
    public void producer(ListView<String> uploadsListView){
        ObservableList<String> current = uploadsListView.getItems();
        ObservableList<String> sorted = FXCollections.observableArrayList();
        int size = current.size();
        for (int i = 0; i < size; i++) {
            String uploadWithSmallesProducer = current.get(0);
            for (String string : current){
                String currentSmallestProducer = getProducer(uploadWithSmallesProducer);
                if (getProducer(string).compareTo(currentSmallestProducer) < 0)
                    uploadWithSmallesProducer = string;
            }
            current.remove(uploadWithSmallesProducer);
            sorted.add(uploadWithSmallesProducer);
        }
        uploadsListView.setItems(sorted);
    }

    public void accessCount(ListView<String> uploadsListView){
        ObservableList<String> current = uploadsListView.getItems();
        ObservableList<String> sorted = FXCollections.observableArrayList();
        int size = current.size();
        for (int i = 0; i < size; i++) {
            String uploadWithSmallestAccessCount = current.get(0);
            for (String string : current){
                int currentSmallestCount = getAccessCount(uploadWithSmallestAccessCount);
                if (getAccessCount(string) < currentSmallestCount)
                    uploadWithSmallestAccessCount = string;
            }
            current.remove(uploadWithSmallestAccessCount);
            sorted.add(uploadWithSmallestAccessCount);
        }
        uploadsListView.setItems(sorted);
    }

    public void address(ListView<String> uploadsListView){
        ObservableList<String> current = uploadsListView.getItems();
        ObservableList<String> sorted = FXCollections.observableArrayList();
        int size = current.size();
        for (int i = 0; i < size; i++) {
            String uploadWithSmallestAddress = current.get(0);
            for (String string : current){
                int  currentSmallestAddress = getAddress(uploadWithSmallestAddress);
                if (getAddress(string) < currentSmallestAddress)
                    uploadWithSmallestAddress = string;
            }
            current.remove(uploadWithSmallestAddress);
            sorted.add(uploadWithSmallestAddress);
        }
        uploadsListView.setItems(sorted);
    }

    public int getAddress(String upload){
        int beginindex = upload.lastIndexOf("address='") + 9;
        String address = "";
        String currentChar = "";
        do {
            currentChar = upload.substring(beginindex, beginindex + 1);
            address = address.concat(currentChar);
            beginindex++;
        } while (Numerical.isNumerical(currentChar));
        address = address.replaceAll("'", "");
        return Integer.parseInt(address);
    }

    public String getAddressDisplayVersion(String upload){
        String[] uploads = upload.split("\t");
        System.out.println(uploads[1]);
        return uploads[1];
    }


    public int getAccessCount(String upload){
    int beginindex = upload.lastIndexOf("accessCount=") + 12;
    String accessCount = "";
    String currentChar = "";
    do {
        currentChar = upload.substring(beginindex, beginindex + 1);
        accessCount = accessCount.concat(currentChar);
        beginindex++;
    } while (Numerical.isNumerical(currentChar));
    accessCount = accessCount.replaceAll(",", "");
    return Integer.parseInt(accessCount);
    }

    public int getAccessCountDisplayVersion(String upload){
        String count = upload.split("\t")[3];
        System.out.println(count);
        return Integer.parseInt(count);
    }


    public String getProducer(String upload){
    int beginindex = upload.lastIndexOf("name='") + 6;
    String producer = "";
    String currentChar = "";
    do {
        currentChar = upload.substring(beginindex, beginindex + 1);
        producer = producer.concat(currentChar);
        beginindex++;
    } while (!currentChar.equals("'"));
    producer = producer.replaceAll("'", "");
    return producer;
    }

    public String getProducerFromUploadString(String upload){
        String producer = upload.split("\t")[2];
        System.out.println(producer);
        return producer;
    }


        public String getProducerDisplayVersion(String upload){
        int lastChar = upload.indexOf("=");
        return upload.substring(0, lastChar);
    }
}
