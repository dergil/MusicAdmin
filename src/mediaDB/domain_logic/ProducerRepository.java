package mediaDB.domain_logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ProducerRepository implements Serializable {
    public Set<Uploader> producers = Collections.synchronizedSet(new HashSet<>());

    public  void addProducer(String uploader) {
        if (nameExistent(uploader))
            System.out.println("A producer with this name already exists in the database.");
        else
            producers.add(new Producer(uploader));
    }

    public  void addProducer(Uploader uploader) {
        if (nameExistent(uploader.getName()))
            System.out.println("A producer with this name already exists in the database.");
        else
            producers.add(uploader);
    }

    private boolean nameExistent(String name){
        for (Uploader uploader: producers) {
            if (uploader.getName().equals(name))
                return true;
        }
        return false;
    }

    public ArrayList<Uploader> getProducers() {
        return new ArrayList<>(producers);
    }

//    public void setProducers(HashSet<Uploader> producers) {
//        this.producers = producers;
//    }
//
//    public Uploader getProducer(String name){
//        for (Uploader uploader : producers) {
//            if (uploader.getName().equals(name))
//                return uploader;
//        }
//        return null;
//    }
//
//    public Uploader getProducer(int i){
//        int index = 0;
//        for (Uploader uploader : producers) {
//            if (index == i)
//                return uploader;
//            index++;
//        }
//        return null;
//    }
//
//    public void setProducer(int i, Uploader uploader){
//        int index = 0;
//        for (Uploader u : producers) {
//            if (index == i)
//                u = uploader;
//            index++;
//        }
//    }

    public void removeProducer(Uploader uploader){
        if (producers.contains(uploader))
            producers.remove(uploader);
        else System.out.println("Producer does not exist in the database.");
    }

    public void removeProducer(java.lang.String name){
        boolean producerFound = false;
        for (Uploader uploader : producers){
            if (uploader.getName().equals(name)){
                producers.remove(uploader);
                producerFound = true;
            }
        }
        if (!producerFound)
            System.out.println("Producer does not exist in the database.");
    }

    public  boolean contains(Uploader uploader){
        return contains(uploader.getName());
    }

    public boolean contains(String name){
        for (Uploader uploader : producers) {
            if (uploader.getName().equals(name))
                return true;
        }
        return false;
    }

    public int getSize() {
        return producers.size();
    }

    @Override
    public String toString() {
        return "ProducerDB{" +
                "producers=" + producers +
                '}';
    }
}