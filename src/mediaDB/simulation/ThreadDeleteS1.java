package mediaDB.simulation;



import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.file_interfaces.Uploadable;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class ThreadDeleteS1 extends Thread {
    MediaFileRepository mediaFileRepository;
    List<Uploadable> uploadables;
    Random random = new Random();

    public ThreadDeleteS1(MediaFileRepository mediaFileRepository) {
        this.mediaFileRepository = mediaFileRepository;
    }


    public void run() {
            while (true) {
                    uploadables = mediaFileRepository.read();
                int size = uploadables.size();
                if (size > 0) {
                        int randomInt = random.nextInt(size);
                        mediaFileRepository.delete(uploadables.get(randomInt));
                        System.out.println("Deleted file. Current number of files: " + mediaFileRepository.read().size());
                    }
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//        }
    }
}
