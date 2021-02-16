package mediaDB.simulation;


import mediaDB.domain_logic.Administration;
import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.file_interfaces.Content;
import mediaDB.domain_logic.file_interfaces.Uploadable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
                randomInt = getRandomNumberInRange(size);
            else continue;
            if ((randomInt + 1) < size)
                pickedFile = uploadables.get(randomInt);
            else continue;
            long oldCount;
            if (pickedFile != null)
                oldCount= ((Content) pickedFile).getAccessCount();
            else continue;
            String address = ((Content) pickedFile).getAddress();
            try {
                mediaFileRepository.incrementAccessCount(address);
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    private static int getRandomNumberInRange(int max) {
        if (0 >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return (int) (Math.random() * ((max) + 1));
    }
}
