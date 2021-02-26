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
        clientPort = packet.getPort();
        address = packet.getAddress();
        return recEvent;
    }

    public void sendResponse(String response, String sender) throws IOException {
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        ObjectOutput oo = new ObjectOutputStream(bStream);
        ServerResponseEvent serverResponseEvent = new ServerResponseEvent(this, response, sender, "none");
        oo.writeObject(serverResponseEvent);
        oo.close();

        buf = bStream.toByteArray();
        DatagramPacket responsePacket = new DatagramPacket(buf, buf.length, address, clientPort);
        socket.send(responsePacket);
    }
}
