package mediaDB.simulation;


import mediaDB.domain_logic.*;
import mediaDB.domain_logic.files.InteractiveVideoFile;
import mediaDB.domain_logic.files.LicensedAudioVideoFile;
import mediaDB.domain_logic.producer.Producer;
import mediaDB.domain_logic.producer.ProducerRepository;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class ThreadCreateS1 extends Thread {
    private CountDownLatch doneSignal;
    MediaFileRepository mediaFileRepository;
    ProducerRepository producerRepository;

    int iterations = 0;
    RandomMediadfileInstances randomFiles = new RandomMediadfileInstances();
    Random random = new Random();
    InteractiveVideoFile interactiveVideoFile;
    LicensedAudioVideoFile licensedAudioVideoFile;
    ArrayList<Producer> producers;

    public ThreadCreateS1(MediaFileRepository mediaFileRepository, ProducerRepository producerRepository) {
        this.mediaFileRepository = mediaFileRepository;
        this.producerRepository = producerRepository;
    }

    public ThreadCreateS1(MediaFileRepository mediaFileRepository, int iterations, CountDownLatch doneSignal) {
        this.mediaFileRepository = mediaFileRepository;
        this.iterations = iterations;
        this.doneSignal = doneSignal;
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
                    System.out.println("Added " + interactiveVideoFile.toString());
                } else {
                    licensedAudioVideoFile = randomFiles.randomLicensedAudioVideoFile();
                    mediaFileRepository.create(licensedAudioVideoFile);
                    System.out.println("Added " + licensedAudioVideoFile.toString());
                }
            }
            doneSignal.countDown();
        }
        else {
            while (true) {
                    if (random.nextBoolean()) {
                        interactiveVideoFile = randomFiles.randomInteractiveVideoFile();
                        mediaFileRepository.create(interactiveVideoFile);
                        System.out.println("Added " + interactiveVideoFile.toString());
                    } else {
                        licensedAudioVideoFile = randomFiles.randomLicensedAudioVideoFile();
                        mediaFileRepository.create(licensedAudioVideoFile);
                        System.out.println("Added " + licensedAudioVideoFile.toString());
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

