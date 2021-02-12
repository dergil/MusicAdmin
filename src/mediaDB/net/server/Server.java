package mediaDB.net.server;

import mediaDB.domain_logic.*;
import mediaDB.domain_logic.listener.*;
import mediaDB.routing.AudioEvent;
import mediaDB.routing.AudioVideoEvent;
import mediaDB.routing.EventListener;
import mediaDB.routing.NetworkEvent;
import mediaDB.tempserver.ServerToClientMessenger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Server {

    Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    public void executeSession() {
        ServerToClientMessenger toClient= new ServerToClientMessenger();
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

        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
                serverEventBus.setOut(out);
                NetworkEvent receivedEvent;
                while (true) {
                    receivedEvent = (NetworkEvent) in.readObject();
                    serverEventBus.onMediaEvent(receivedEvent);
                    System.out.println(mediaFileRepository.read().toString());
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
