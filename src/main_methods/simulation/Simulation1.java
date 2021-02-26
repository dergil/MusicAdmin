package main_methods.simulation;


import mediaDB.domain_logic.*;
import mediaDB.domain_logic.observables.SizeObservable;
import mediaDB.domain_logic.observables.TagObservable;
import mediaDB.domain_logic.observables.UploadsObservable;
import mediaDB.domain_logic.producer.ProducerRepository;
import mediaDB.net.server.ToClientMessenger;
import mediaDB.simulation.ThreadCreateS1;
import mediaDB.simulation.ThreadDeleteS1;

public class Simulation1 {
    public static void main(String[] args){
        ToClientMessenger toClient= new ToClientMessenger();
        SizeObservable sizeObservable = new SizeObservable();
        TagObservable tagObservable = new TagObservable();
        MediaFileRepository mediaFileRepository  = new MediaFileRepository(toClient, sizeObservable, tagObservable);
        ProducerRepository producerRepository = new ProducerRepository();

        ThreadCreateS1 thread1 = new ThreadCreateS1(mediaFileRepository, producerRepository);
        ThreadDeleteS1 thread2 = new ThreadDeleteS1(mediaFileRepository);
        thread1.start();
        thread2.start();
    }
}
