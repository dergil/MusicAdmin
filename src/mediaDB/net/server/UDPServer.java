package mediaDB.net.server;

/*
*   Sources:
*   https://www.baeldung.com/udp-in-java
*   https://stackoverflow.com/questions/4252294/sending-objects-across-network-using-udp-in-java
*/

import mediaDB.domain_logic.*;
import mediaDB.domain_logic.listener.display.DisplayEventListener;
import mediaDB.domain_logic.listener.display.DisplayModeProcessing;
import mediaDB.domain_logic.listener.display.GenerateDisplayContent;
import mediaDB.domain_logic.listener.files.*;
import mediaDB.domain_logic.observables.SizeObservable;
import mediaDB.domain_logic.observables.TagObservable;
import mediaDB.domain_logic.listener.*;
import mediaDB.domain_logic.producer.ProducerRepository;
import mediaDB.net.EventBus;
import mediaDB.routing.NetworkEvent;
import mediaDB.routing.events.misc.ServerResponseEvent;

import java.io.*;
import java.net.*;

//TODO: testen bezüglich buffer size, wenn z.B. lange liste zurückgegeben werden soll; Testbeispiele sind in src von Lohmann
public class UDPServer {
    private DatagramSocket socket;
    private byte[] buf = new byte[20048];
    EventBus eventBus;
    private int clientPort;
    private InetAddress address;

    public UDPServer(EventBus eventBus) throws SocketException {
        this.eventBus = eventBus;
        socket = new DatagramSocket(4445);
    }

    public int getClientPort() {
        return clientPort;
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public InetAddress getAddress() {
        return address;
    }

    public NetworkEvent receiveEvent() throws IOException, ClassNotFoundException {
        byte[] myBuf = new byte[2048];
        DatagramPacket packet = new DatagramPacket(myBuf, myBuf.length);
        socket.receive(packet);
        byte[] recBytes = packet.getData();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(recBytes, 0, recBytes.length);
        ObjectInputStream iStream = new ObjectInputStream(byteArrayInputStream);
        NetworkEvent recEvent = (NetworkEvent) iStream.readObject();
        System.out.println(recEvent.getEventName());
        clientPort = packet.getPort();
        System.out.println(clientPort);
        address = packet.getAddress();
        return recEvent;
    }

//    public void start() throws IOException, ClassNotFoundException {
//        while (true){
//            DatagramPacket packet = new DatagramPacket(buf, buf.length);
//            socket.receive(packet);
//            byte[] recBytes = packet.getData();
//            ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(recBytes));
//            NetworkEvent recEvent = (NetworkEvent) iStream.readObject();
//            serverEventBus.onMediaEvent(recEvent);
//            System.out.println(recEvent.getEventName());
//
//            InetAddress address = packet.getAddress();
//            System.out.println(address);
//            int port = packet.getPort();
//            System.out.println(port);
//
//            sendResponse("client");
//        }
//    }

    private void sendResponse(String response, String sender) throws IOException {
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        ObjectOutput oo = new ObjectOutputStream(bStream);
        ServerResponseEvent serverResponseEvent = new ServerResponseEvent(this, "none", response, sender);
        oo.writeObject(serverResponseEvent);
        oo.close();

        buf = bStream.toByteArray();
        DatagramPacket responsePacket = new DatagramPacket(buf, buf.length, address, clientPort);
        socket.send(responsePacket);
    }

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

//        udpServer.start();

    }
}
