package mediaDB.net.client;

import mediaDB.routing.EventListener;
import mediaDB.routing.NetworkEvent;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientEventBusUDP implements EventListener<NetworkEvent> {
    DatagramSocket socket;
    InetAddress address;
    int port;

    public ClientEventBusUDP(DatagramSocket socket, InetAddress address, int port) {
        this.socket = socket;
        this.address = address;
        this.port = port;
    }

    @Override
    public void onMediaEvent(NetworkEvent event) throws IOException {
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        ObjectOutput oo = new ObjectOutputStream(bStream);
        oo.writeObject(event);
        bStream.close();
        oo.close();

        byte[] buf = bStream.toByteArray();
        System.out.println(buf.length);
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
        socket.send(packet);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
