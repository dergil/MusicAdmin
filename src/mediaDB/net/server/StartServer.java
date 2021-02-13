package mediaDB.net.server;

import mediaDB.domain_logic.*;
import mediaDB.domain_logic.listener.*;
import mediaDB.net.server.Server;
import mediaDB.net.server.ServerEventBus;
import mediaDB.tempserver.ToClientMessenger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StartServer {
//TODO: ToClientMessenger only works for most recently added server (not fixed yet, bc not a requirement)
//    I could set ToClient in while loop
    public static void main(String[] args) {
        ToClientMessenger toClient= new ToClientMessenger();
        SizeObservable sizeObservable = new SizeObservable();
        TagObservable tagObservable = new TagObservable();
        MediaFileRepository mediaFileRepository  = new MediaFileRepository(toClient, sizeObservable, tagObservable);
        ProducerRepository producerRepository = new ProducerRepository();
        AddressRepository addressRepository = new AddressRepository();
        MediaFileFactory mediaFileFactory = new MediaFileFactory(mediaFileRepository, addressRepository);

        AudioEventListener audioEventListener = new AudioEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
        AudioVideoEventListener audioVideoEventListener = new AudioVideoEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
        InteractiveVideoEventListener interactiveVideoEventListener = new InteractiveVideoEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
        LicensedAudioEventListener licensedAudioEventListener = new LicensedAudioEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
        LicensedAudioVideoEventListener licensedAudioVideoEventListener = new LicensedAudioVideoEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
        LicensedVideoEventListener licensedVideoEventListener = new LicensedVideoEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
        ProducerEventListener producerEventListener = new ProducerEventListener(producerRepository);
        GenerateDisplayContent generateDisplayContent = new GenerateDisplayContent(mediaFileRepository);
        DisplayModeServer displayModeServer = new DisplayModeServer(generateDisplayContent, producerRepository, mediaFileRepository);
        DisplayEventListener displayEventListener = new DisplayEventListener(displayModeServer, mediaFileRepository, toClient);
        StringEventListener stringEventListener = new StringEventListener(mediaFileRepository, producerRepository);

        ServerEventBus serverEventBus = new ServerEventBus();
        serverEventBus.register(audioEventListener);
        serverEventBus.register(audioVideoEventListener);
        serverEventBus.register(interactiveVideoEventListener);
        serverEventBus.register(licensedAudioEventListener);
        serverEventBus.register(licensedAudioVideoEventListener);
        serverEventBus.register(licensedVideoEventListener);
        serverEventBus.register(producerEventListener);
        serverEventBus.register(displayEventListener);
        serverEventBus.register(stringEventListener);

        try (ServerSocket ss = new ServerSocket(7777);) {
//            server.executeSession(); blocks; unblocks after Excpetion in Server class, which exits while loop there
//            reason: everything has to run on a single thread
            while (true){
                Socket socket = ss.accept();
                Server server = new Server(socket, serverEventBus, toClient);
                server.executeSession();
                System.out.println("Instruction after server.executeSession();");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}