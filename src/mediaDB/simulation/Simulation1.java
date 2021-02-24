package mediaDB.simulation;


import mediaDB.domain_logic.*;
import mediaDB.domain_logic.observables.SizeObservable;
import mediaDB.domain_logic.observables.TagObservable;
import mediaDB.domain_logic.observables.UploadsObservable;
import mediaDB.domain_logic.producer.ProducerRepository;
import mediaDB.net.server.ToClientMessenger;
import mediaDB.observer.UploadsObserver;

public class Simulation1 {
    public static void main(String[] args){
        ToClientMessenger toClient= new ToClientMessenger();
        SizeObservable sizeObservable = new SizeObservable();
        TagObservable tagObservable = new TagObservable();
        UploadsObservable uploadsObservable = new UploadsObservable();
        MediaFileRepository mediaFileRepository  = new MediaFileRepository(toClient, sizeObservable, tagObservable);
        mediaFileRepository.setUploadsObservable(uploadsObservable);
        ProducerRepository producerRepository = new ProducerRepository();

//        Observer for uploads
//        UploadsObserver uploadsObserver = new UploadsObserver(uploadsObservable, mediaFileRepository);
//        uploadsObservable.register(uploadsObserver);

        ThreadCreateS1 thread1 = new ThreadCreateS1(mediaFileRepository, producerRepository);
        ThreadDeleteS1 thread2 = new ThreadDeleteS1(mediaFileRepository);
        thread1.start();
        thread2.start();
    }
}
