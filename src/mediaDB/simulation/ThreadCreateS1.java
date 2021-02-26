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
    MediaFileRepository mediaFileRepository;
    ProducerRepository producerRepository;
    RandomMediadfileInstances randomFiles = new RandomMediadfileInstances();
    Random random = new Random();
    InteractiveVideoFile interactiveVideoFile;
    LicensedAudioVideoFile licensedAudioVideoFile;
    ArrayList<Producer> producers;

    public ThreadCreateS1(MediaFileRepository mediaFileRepository, ProducerRepository producerRepository) {
        this.mediaFileRepository = mediaFileRepository;
        this.producerRepository = producerRepository;
    }

    public void run(){
        producers = randomFiles.getProducers();
        for (Producer producer : producers) {
            producerRepository.addProducer(producer);
        }
            while (true) {
                    if (random.nextBoolean()) {
                        interactiveVideoFile = randomFiles.randomInteractiveVideoFile();
                        mediaFileRepository.create(interactiveVideoFile);
                        System.out.println("Tried to add  " + interactiveVideoFile.toString());
                    } else {
                        licensedAudioVideoFile = randomFiles.randomLicensedAudioVideoFile();
                        mediaFileRepository.create(licensedAudioVideoFile);
                        System.out.println("Tried to add  " + licensedAudioVideoFile.toString());
                    }
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


//    }

