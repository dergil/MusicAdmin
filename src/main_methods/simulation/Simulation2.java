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
import mediaDB.simulation.ThreadAccessCountS2;
import mediaDB.simulation.ThreadCreateS2;
import mediaDB.simulation.ThreadDeleteS2;

public class Simulation2 {

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
        ThreadCreateS2 thread1 = new ThreadCreateS2(producerRepository, mediaFileRepository, queue);
        ThreadDeleteS2 thread2 = new ThreadDeleteS2(mediaFileRepository, queue);
        ThreadAccessCountS2 thread3 = new ThreadAccessCountS2(mediaFileRepository);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
