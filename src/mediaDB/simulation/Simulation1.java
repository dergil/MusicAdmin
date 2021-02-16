package mediaDB.simulation;


import mediaDB.domain_logic.*;
import mediaDB.net.server.ToClientMessenger;

public class Simulation1 {
    public static void main(String[] args){
        ToClientMessenger toClient= new ToClientMessenger();
        SizeObservable sizeObservable = new SizeObservable();
        TagObservable tagObservable = new TagObservable();
        MediaFileRepository mediaFileRepository  = new MediaFileRepository(toClient, sizeObservable, tagObservable);
        ProducerRepository producerRepository = new ProducerRepository();
        AddressRepository addressRepository = new AddressRepository();
        MediaFileFactory mediaFileFactory = new MediaFileFactory(mediaFileRepository, addressRepository);

        ThreadCreateS1 thread1 = new ThreadCreateS1(mediaFileRepository, producerRepository);
        ThreadDeleteS1 thread2 = new ThreadDeleteS1(mediaFileRepository);
        thread1.start();
        thread2.start();
    }
}
