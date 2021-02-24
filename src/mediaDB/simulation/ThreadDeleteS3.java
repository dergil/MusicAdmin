package mediaDB.simulation;

import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.file_interfaces.Content;
import mediaDB.domain_logic.file_interfaces.Uploadable;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadDeleteS3 extends Thread{
    final MediaFileRepository mediaFileRepository;
    BlockingQueue queue;
    List<Uploadable> uploadables;
    Uploadable pickedFile;
    int size;
    int randomInt;


    public ThreadDeleteS3(MediaFileRepository mediaFileRepository, BlockingQueue queue) {
        this.mediaFileRepository = mediaFileRepository;
        this.queue = queue;
    }

    public void run() {
        while (true) {
            for (int i = 0; i < ThreadLocalRandom.current().nextInt(10); i++) {
                uploadables = mediaFileRepository.read();
                size = uploadables.size();
                if (size > 0)
                    pickedFile = lowestAccessCount();
                else continue;
                if (pickedFile != null) {
                    try {
                        queue.take(pickedFile);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else continue;
                String address = ((Content) pickedFile).getAddress();
                System.out.println("Tried to delete file " + address);
//                synchronized (mediaFileRepository){
//                    try {
//                        mediaFileRepository.wait(200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        }
    }

    private Uploadable lowestAccessCount(){
        long lowestCount = 50000000000L;
        Uploadable lowestCountUploadable = null;
        for (int i = 0; i < size; i++) {
            Uploadable uploadable = uploadables.get(i);
            long accessCount = ((Content) uploadable).getAccessCount();
            if (accessCount <= lowestCount){
                lowestCountUploadable = uploadable;
                lowestCount = accessCount;
            }
        }
        return lowestCountUploadable;
    }
}
