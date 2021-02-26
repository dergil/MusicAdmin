package mediaDB.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import mediaDB.ui.Numerical;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SortUploads {
    ExtractDataFromString extractDataFromString;

    public SortUploads(ExtractDataFromString extractDataFromString) {
        this.extractDataFromString = extractDataFromString;
    }

    public void producer(ListView<String> uploadsListView){
        ObservableList<String> current = uploadsListView.getItems();
        ObservableList<String> sorted = FXCollections.observableArrayList();
        int size = current.size();
        for (int i = 0; i < size; i++) {
            String uploadWithSmallesProducer = current.get(0);
            for (String string : current){
                String currentSmallestProducer = extractDataFromString.getProducer(uploadWithSmallesProducer);
                if (extractDataFromString.getProducer(string).compareTo(currentSmallestProducer) < 0)
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
                int currentSmallestCount = extractDataFromString.getAccessCount(uploadWithSmallestAccessCount);
                if (extractDataFromString.getAccessCount(string) < currentSmallestCount)
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
                int  currentSmallestAddress = extractDataFromString.getAddress(uploadWithSmallestAddress);
                if (extractDataFromString.getAddress(string) < currentSmallestAddress)
                    uploadWithSmallestAddress = string;
            }
            current.remove(uploadWithSmallestAddress);
            sorted.add(uploadWithSmallestAddress);
        }
        uploadsListView.setItems(sorted);
    }

//    public String getAddressDisplayVersion(String upload){
//        String[] uploads = upload.split("\t");
//        System.out.println(uploads[1]);
//        return uploads[1];
//    }




//    public int getAccessCountDisplayVersion(String upload){
//        String count = upload.split("\t")[3];
//        System.out.println(count);
//        return Integer.parseInt(count);
//    }




//    public String getProducerFromUploadString(String upload){
//        String producer = upload.split("\t")[2];
//        System.out.println(producer);
//        return producer;
//    }



}
