package main_methods.simulation;

import mediaDB.domain_logic.*;
import mediaDB.domain_logic.observables.SizeObservable;
import mediaDB.domain_logic.observables.TagObservable;
import mediaDB.domain_logic.observables.UploadsObservable;
import mediaDB.domain_logic.producer.ProducerRepository;
import mediaDB.net.server.ToClientMessenger;
import mediaDB.observer.SizeObserver;
import mediaDB.observer.TagObserver;
import mediaDB.simulation.BlockingQueue;
import mediaDB.simulation.ThreadCreateS2;
import mediaDB.simulation.ThreadDeleteS3;

public class Simulation3 {
    public static void main(String[] args) {
        ToClientMessenger toClient= new ToClientMessenger();
        SizeObservable sizeObservable = new SizeObservable();
        TagObservable tagObservable = new TagObservable();
        MediaFileRepository mediaFileRepository  = new MediaFileRepository(toClient, sizeObservable, tagObservable);
        ProducerRepository producerRepository = new ProducerRepository();
        SizeObserver sizeObserver = new SizeObserver(sizeObservable);
        sizeObservable.register(sizeObserver);
        TagObserver tagObserver = new TagObserver(tagObservable);
        tagObservable.register(tagObserver);


        BlockingQueue queue = new BlockingQueue(mediaFileRepository, sizeObservable);
        ThreadCreateS2 create1 = new ThreadCreateS2(producerRepository, mediaFileRepository, queue);
        ThreadCreateS2 create2 = new ThreadCreateS2(producerRepository, mediaFileRepository, queue);
        ThreadDeleteS3 delete1 = new ThreadDeleteS3(mediaFileRepository, queue);
        ThreadDeleteS3 delete2 = new ThreadDeleteS3(mediaFileRepository, queue);
        create1.start();
        create2.start();
        delete1.start();
        delete2.start();
    }
}
