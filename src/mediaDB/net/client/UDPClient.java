package mediaDB.net.client;

//Quelle: https://www.baeldung.com/udp-in-java

import mediaDB.routing.EventHandler;
import mediaDB.routing.NetworkEvent;
import mediaDB.ui.cli.CLIAdminForNet;
import mediaDB.ui.cli.EventFactory;
import mediaDB.ui.cli.modes.*;

import java.io.*;
import java.net.*;

//Source: https://stackoverflow.com/questions/4252294/sending-objects-across-network-using-udp-in-java

public class UDPClient {
    private DatagramSocket socket;
    private InetAddress address;
    private int port;

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

    public void receiveEvent() throws IOException {
        try {
            while (true) {
//                receive packets until "client" (done) packet arrives
                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                byte[] recBytes = packet.getData();
                ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(recBytes));
                NetworkEvent recEvent = (NetworkEvent) in.readObject();
                String eventName = recEvent.getEventName();
                if (eventName.equals("client")) {
                    break;
                }
            }
        }
        catch (EOFException | ClassNotFoundException e ) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        UDPClient udpClient = new UDPClient();
        ClientEventBusUDP clientEventBus = new ClientEventBusUDP(udpClient.getSocket(), udpClient.getAddress(), udpClient.getPort());
        EventHandler eventHandler = new EventHandler();
        eventHandler.add(clientEventBus);
        String clientName = "clientName";
        EventFactory eventFactory = new EventFactory(clientName);
        InsertMode insertMode = new InsertMode(eventHandler, eventFactory);
        DisplayMode displayMode = new DisplayMode(eventHandler, eventFactory);
        DeletionMode deletionMode = new DeletionMode(eventHandler, eventFactory);
        ChangeMode changeMode = new ChangeMode(eventHandler, eventFactory);
        PersistenceMode persistenceMode = new PersistenceMode(eventHandler, eventFactory);
        CLIAdminForNet cliAdmin = new CLIAdminForNet(insertMode, displayMode, deletionMode, changeMode,
                persistenceMode, eventHandler, eventFactory);

        while (true){
            cliAdmin.start();
            udpClient.receiveEvent();
        }
    }
}
