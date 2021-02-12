package mediaDB.simulation;

import mediaDB.domain_logic.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class ThreadCreateS2 extends Thread {
//    private final BlockingQueue queue;
//    private CountDownLatch doneSignal;
//    Administration admin;
//    int iterations = 0;
//    RandomMediadfileInstances randomFiles = new RandomMediadfileInstances();
//    Random random = new Random();
//    InteractiveVideoFile interactiveVideoFile;
//    LicensedAudioVideoFile licensedAudioVideoFile;
//    ArrayList<Producer> producers;
//
//
//    public ThreadCreateS2(Administration admin, BlockingQueue queue) {
//        this.admin = admin;
//        this.queue = queue;
//    }
//
//    public  void run(){
//        producers = randomFiles.getProducers();
//        for (Producer producer : producers) {
//            admin.addProducer(producer);
//        }
//        if (iterations != 0 && doneSignal != null){
//            for (int i = 0; i < iterations; i++) {
//                if (random.nextBoolean()) {
//                    interactiveVideoFile = randomFiles.randomInteractiveVideoFile();
//                    admin.create(interactiveVideoFile);
//                    System.out.println("Added " + interactiveVideoFile.toString());
//                } else {
//                    licensedAudioVideoFile = randomFiles.randomLicensedAudioVideoFile();
//                    admin.create(licensedAudioVideoFile);
//                    System.out.println("Added " + licensedAudioVideoFile.toString());
//                }
//            }
//            doneSignal.countDown();
//        }
//        else {
//                while (true) {
//                    if (random.nextBoolean()) {
//                        interactiveVideoFile = randomFiles.randomInteractiveVideoFile();
//                        try {
//                            queue.put(interactiveVideoFile);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println("Added " + interactiveVideoFile.toString());
//                    } else {
//                        licensedAudioVideoFile = randomFiles.randomLicensedAudioVideoFile();
//                        try {
//                            queue.put(licensedAudioVideoFile);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println("Added " + licensedAudioVideoFile.toString());
//                    }
//                }
//        }
//    }
}

