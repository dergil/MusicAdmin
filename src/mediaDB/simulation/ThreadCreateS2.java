package mediaDB.simulation;

import mediaDB.domain_logic.*;
import mediaDB.domain_logic.files.InteractiveVideoFile;
import mediaDB.domain_logic.files.LicensedAudioVideoFile;
import mediaDB.domain_logic.producer.Producer;
import mediaDB.domain_logic.producer.ProducerRepository;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class ThreadCreateS2 extends Thread {
    ProducerRepository producerRepository;
    final MediaFileRepository mediaFileRepository;
    private final BlockingQueue queue;
    private CountDownLatch doneSignal;
    int iterations = 0;
    RandomMediadfileInstances randomFiles = new RandomMediadfileInstances();
    Random random = new Random();
    InteractiveVideoFile interactiveVideoFile;
    LicensedAudioVideoFile licensedAudioVideoFile;
    ArrayList<Producer> producers;


    public ThreadCreateS2 (ProducerRepository producerRepository, MediaFileRepository mediaFileRepository, BlockingQueue blockingQueue){
        this.producerRepository = producerRepository;
        this.mediaFileRepository = mediaFileRepository;
        this.queue = blockingQueue;
    }

    public void run(){
        producers = randomFiles.getProducers();
        for (Producer producer : producers) {
            producerRepository.addProducer(producer);
        }
        if (iterations != 0 && doneSignal != null){
            for (int i = 0; i < iterations; i++) {
                if (random.nextBoolean()) {
                    interactiveVideoFile = randomFiles.randomInteractiveVideoFile();
                    mediaFileRepository.create(interactiveVideoFile);
                    System.out.println("Tried to add  " + interactiveVideoFile.toString());
                } else {
                    licensedAudioVideoFile = randomFiles.randomLicensedAudioVideoFile();
                    mediaFileRepository.create(licensedAudioVideoFile);
                    System.out.println("Tried to add  " + licensedAudioVideoFile.toString());
                }
            }
            doneSignal.countDown();
        }
        else {
                while (true) {
                    if (random.nextBoolean()) {
                        interactiveVideoFile = randomFiles.randomInteractiveVideoFile();
                        try {
                            queue.put(interactiveVideoFile);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Tried to add  " + interactiveVideoFile.toString() + " " + interactiveVideoFile.getAddress());
                    } else {
                        licensedAudioVideoFile = randomFiles.randomLicensedAudioVideoFile();
                        try {
                            queue.put(licensedAudioVideoFile);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Tried to add  " + licensedAudioVideoFile.toString() + " " + licensedAudioVideoFile.getAddress());
                    }
//                    synchronized (mediaFileRepository){
//                        try {
//                            mediaFileRepository.wait(200);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }

                }
        }
    }
}

