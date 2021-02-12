package mediaDB.simulation;



import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.file_interfaces.Uploadable;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class ThreadDeleteS1 extends Thread {
    private CountDownLatch doneSignal;
//    Administration mediaFileRepository;
    MediaFileRepository mediaFileRepository;
    int iterations = 0;
    List<Uploadable> uploadables;
    Random random = new Random();

    public ThreadDeleteS1(MediaFileRepository mediaFileRepository) {
        this.mediaFileRepository = mediaFileRepository;
    }

//    public ThreadDeleteS1(Administration admin, int iterations, CountDownLatch doneSignal) {
//        this.admin = admin;
//        this.iterations = iterations;
//        this.doneSignal = doneSignal;
//    }

    public void run() {
        if (iterations != 0 && doneSignal != null) {
            for (int i = 0; i < iterations; i++) {
                uploadables = mediaFileRepository.read();
                int size = uploadables.size();
                if (size > 0) {
                    int randomInt = random.nextInt(size);
                    mediaFileRepository.delete(uploadables.get(randomInt));
                    System.out.println("Deleted file. Current number of Files: " + mediaFileRepository.read().size());
                }
            }
            doneSignal.countDown();
        } else {
            while (true) {
                    uploadables = mediaFileRepository.read();
                int size = uploadables.size();
                if (size > 0) {
                        int randomInt = random.nextInt(size);
                        mediaFileRepository.delete(uploadables.get(randomInt));
                        System.out.println("Deleted file. Current number of files: " + mediaFileRepository.read().size());
                    }
//                try {
//                    sleep(0);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }
}
