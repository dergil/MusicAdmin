package mediaDB.net.server;

//import mediaDB.net.server.ServerEventHandler;
//
//import java.io.IOException;

import mediaDB.routing.events.misc.ServerResponseEvent;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

//TODO: multiple outputstreams and setOut() for multiple clients?
public class ToClientMessenger {
    List<ObjectOutputStream> outputStreamList = new ArrayList<>();
    int clientPort = Integer.MAX_VALUE;
    private DatagramSocket socket;
    InetAddress address;

    public void producerNotListet() throws IOException {
        sendMessage("Produzent der Datei nicht gelistet.");
    }

    public void fileNotListet() throws IOException {
        sendMessage("Datei nicht gelistet.");
    }


    public void list(String list) throws IOException {
        sendMessage(list);
    }

    public void sendString(String response) throws IOException {
        sendMessage(response);
    }

    private void sendMessage(String message) throws IOException {
        if (checkUDP()){
            byte[] buf;
            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            ObjectOutput oo = new ObjectOutputStream(bStream);
            ServerResponseEvent serverResponseEvent = new ServerResponseEvent(this, message);
            oo.writeObject(serverResponseEvent);
            oo.close();

            buf = bStream.toByteArray();
            DatagramPacket responsePacket = new DatagramPacket(buf, buf.length, address, clientPort);
            socket.send(responsePacket);
        }
        else if (outputStreamList.isEmpty()){
            System.out.println(message);
        }
        else {
            for (ObjectOutputStream objectOutputStream: outputStreamList){
                objectOutputStream.writeObject(new ServerResponseEvent(this, message));
            }
        }
    }

    private boolean checkUDP(){
        return socket != null && address != null && clientPort != Integer.MAX_VALUE;
    }

    public void setOut(ObjectOutputStream out) {
        outputStreamList.add(out);
    }

    public void setClientPort(int clientPort) {
        this.clientPort = clientPort;
    }

    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }
}
