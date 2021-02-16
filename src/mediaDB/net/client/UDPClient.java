package mediaDB.net.client;

//Quelle: https://www.baeldung.com/udp-in-java

import mediaDB.routing.NetworkEvent;
import mediaDB.routing.events.misc.ProducerEvent;
import mediaDB.routing.events.misc.ServerResponseEvent;
import mediaDB.ui.cli.CLIAdminForNet;
import mediaDB.ui.cli.event_creation.CreateProducerEvent;
import mediaDB.ui.cli.event_creation.CreateServerResponseEvent;
import mediaDB.ui.cli.modes.*;

import java.io.*;
import java.net.*;

//Source: https://stackoverflow.com/questions/4252294/sending-objects-across-network-using-udp-in-java

public class UDPClient {
    private DatagramSocket socket;
    private InetAddress address;
    private int port;

    private byte[] buf;

    public UDPClient() throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        address = InetAddress.getByName("localhost");
        port = 4445;
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public void sendEvent(NetworkEvent networkEvent) throws IOException {
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        ObjectOutput oo = new ObjectOutputStream(bStream);
        oo.writeObject(networkEvent);
        oo.close();

        buf = bStream.toByteArray();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
        socket.send(packet);
    }

    public void receiveEvent() throws IOException, ClassNotFoundException {


        try {
            while (true) {
                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                byte[] recBytes = packet.getData();
                ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(recBytes));
                NetworkEvent recEvent = (NetworkEvent) iStream.readObject();
                String response = recEvent.getEventName();
                System.out.println(recEvent.getEventName());
                if (response.equals("client")) {
                    break;
                }
            }
        }
        catch (EOFException | ClassNotFoundException e ) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        UDPClient udpClient = new UDPClient();
        ClientEventBusUDP clientEventBus = new ClientEventBusUDP(udpClient.getSocket(), udpClient.getAddress(), udpClient.getPort());
        InsertModeInputProcessing insertModeInputProcessing = new InsertModeInputProcessing(clientEventBus);
        InsertMode insertMode = new InsertMode(insertModeInputProcessing, clientEventBus);
        DisplayMode displayMode = new DisplayMode(clientEventBus);
        DeletionMode deletionMode = new DeletionMode(clientEventBus);
        ChangeMode changeMode = new ChangeMode(clientEventBus);
        CLIAdminForNet cliAdmin = new CLIAdminForNet(insertMode, displayMode, deletionMode, changeMode);

        while (true){
            cliAdmin.start();
            udpClient.receiveEvent();
        }

//        CreateServerResponseEvent createServerResponseEvent = new CreateServerResponseEvent();
//        NetworkEvent testEvent = createServerResponseEvent.process("Test me");
//        udpClient.sendEvent(testEvent);
    }

}
