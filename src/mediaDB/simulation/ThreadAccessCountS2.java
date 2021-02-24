package mediaDB.simulation;


import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.file_interfaces.Content;
import mediaDB.domain_logic.file_interfaces.Uploadable;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadAccessCountS2 extends Thread {
    final MediaFileRepository mediaFileRepository;
    List<Uploadable> uploadables;
    int randomInt;
    Uploadable pickedFile;

    public ThreadAccessCountS2(MediaFileRepository mediaFileRepository){
        this.mediaFileRepository = mediaFileRepository;
    }

    public void run(){
        while (true) {
            uploadables = mediaFileRepository.read();
            int size = uploadables.size();
            if (size > 0)
                randomInt = ThreadLocalRandom.current().nextInt(size);
            else continue;
            if ((randomInt + 1) < size)
                pickedFile = uploadables.get(randomInt);
            else continue;
            long oldCount;
            if (pickedFile != null)
                oldCount= ((Content) pickedFile).getAccessCount();
            else continue;
            String address = ((Content) pickedFile).getAddress();
            mediaFileRepository.incrementAccessCount(address);
            System.out.println("Tried to update access count of " + address + " from " + oldCount + " to " + (oldCount + 1));
//            synchronized (mediaFileRepository){
//                try {
//                    mediaFileRepository.wait(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }

        }
    }
}
