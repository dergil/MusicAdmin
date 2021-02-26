package main_methods;

import mediaDB.IO.*;
import mediaDB.domain_logic.AddressRepository;
import mediaDB.domain_logic.MediaFileFactory;
import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.listener.PersistenceEventListener;
import mediaDB.domain_logic.listener.ProducerEventListener;
import mediaDB.domain_logic.listener.StringEventListener;
import mediaDB.domain_logic.listener.display.DisplayEventListener;
import mediaDB.domain_logic.listener.display.DisplayModeProcessing;
import mediaDB.domain_logic.listener.display.GenerateDisplayContent;
import mediaDB.domain_logic.listener.files.*;
import mediaDB.domain_logic.observables.SizeObservable;
import mediaDB.domain_logic.observables.TagObservable;
import mediaDB.domain_logic.producer.ProducerRepository;
import mediaDB.net.EventBus;
import mediaDB.net.server.Server;
import mediaDB.net.server.ToClientMessenger;
import mediaDB.net.server.UDPServer;
import mediaDB.routing.NetworkEvent;
import mediaDB.ui.Numerical;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPorUDPServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ToClientMessenger toClient= new ToClientMessenger();
        SizeObservable sizeObservable = new SizeObservable();
        TagObservable tagObservable = new TagObservable();
        MediaFileRepository mediaFileRepository  = new MediaFileRepository(toClient, sizeObservable, tagObservable);
        ProducerRepository producerRepository = new ProducerRepository();
        AddressRepository addressRepository = new AddressRepository();
        MediaFileFactory mediaFileFactory = new MediaFileFactory(mediaFileRepository, addressRepository, toClient);

        AudioEventListener audioEventListener = new AudioEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
        AudioVideoEventListener audioVideoEventListener = new AudioVideoEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
        InteractiveVideoEventListener interactiveVideoEventListener = new InteractiveVideoEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
        LicensedAudioEventListener licensedAudioEventListener = new LicensedAudioEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
        LicensedAudioVideoEventListener licensedAudioVideoEventListener = new LicensedAudioVideoEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
        LicensedVideoEventListener licensedVideoEventListener = new LicensedVideoEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
        ProducerEventListener producerEventListener = new ProducerEventListener(producerRepository, toClient);
        GenerateDisplayContent generateDisplayContent = new GenerateDisplayContent();
        DisplayModeProcessing displayModeProcessing = new DisplayModeProcessing(generateDisplayContent, producerRepository, mediaFileRepository);
        DisplayEventListener displayEventListener = new DisplayEventListener(displayModeProcessing, mediaFileRepository, toClient);
        StringEventListener stringEventListener = new StringEventListener(mediaFileRepository, producerRepository, toClient);
        ArrayList<SavedMediaFile> savedMediaFiles = new ArrayList<>();
        RandomAccessFile randomAccessFile = new RandomAccessFile("RandomAccessFile", "rw");
        RandomAccess randomAccess = new RandomAccess(randomAccessFile, savedMediaFiles);
        Serialize serialize = new Serialize(sizeObservable, tagObservable, mediaFileRepository, producerRepository, addressRepository, randomAccess);
        DeserializeDomainContent deserializeDomainContent = new DeserializeDomainContent();
        Deserialize deserialize = new Deserialize(sizeObservable, tagObservable, mediaFileRepository, producerRepository, addressRepository, deserializeDomainContent, randomAccess);
        PersistenceEventListener persistenceEventListener = new PersistenceEventListener(mediaFileRepository, serialize, deserialize, deserializeDomainContent, randomAccess, toClient);
        EventBus eventBus = new EventBus();
        eventBus.register(audioEventListener);
        eventBus.register(audioVideoEventListener);
        eventBus.register(interactiveVideoEventListener);
        eventBus.register(licensedAudioEventListener);
        eventBus.register(licensedAudioVideoEventListener);
        eventBus.register(licensedVideoEventListener);
        eventBus.register(producerEventListener);
        eventBus.register(displayEventListener);
        eventBus.register(stringEventListener);
        eventBus.register(persistenceEventListener);


        BigDecimal capacity = null;
        String protocol = null;
        if (args.length < 2) {
            System.out.println("Usage: Capacity and protocol (TCP / UDP) as arguments.");
            return;
        }

        if (Numerical.check(args[0])) {
            capacity = new BigDecimal(args[0]);
            protocol = checkProtocol(args[1]);
        }
        sizeObservable.setMAX_CAPACITY(capacity);

        assert protocol != null;
        if (protocol.equals("TCP")) {
            try (ServerSocket ss = new ServerSocket(7777)) {
                while (true){
                    Socket socket = ss.accept();
                    Server server = new Server(socket, eventBus, toClient);
                    System.out.println("new client@"+socket.getInetAddress()+":"+socket.getPort());
                    new Thread(server).start();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        else if (protocol.equals("UDP")){
            String sender = "server";
            UDPServer udpServer = new UDPServer(eventBus);
            NetworkEvent firstReceivedEvent = udpServer.receiveEvent();
            toClient.setSocket(udpServer.getSocket());
            toClient.setAddress(udpServer.getAddress());
            toClient.setClientPort(udpServer.getClientPort());
            eventBus.onMediaEvent(firstReceivedEvent);
            udpServer.sendResponse("client", sender);
            while (true){
                toClient.setSocket(udpServer.getSocket());
                toClient.setAddress(udpServer.getAddress());
                toClient.setClientPort(udpServer.getClientPort());
                eventBus.onMediaEvent(udpServer.receiveEvent());
                udpServer.sendResponse("client", sender);
            }
        }
        else {
        System.out.println("Unknown protocol");
        System.exit(0);
        }
    }

    public static String checkProtocol(String input){
        if (input.equals("TCP"))
            return input;
        else if (input.equals("UDP"))
            return input;
        else {
            System.out.println("Unknown protocol");
            System.exit(0);
            return null;
        }
    }

}
