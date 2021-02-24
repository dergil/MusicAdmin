package mediaDB.net.server;

//import mediaDB.net.server.ServerEventHandler;
//
//import java.io.IOException;

import mediaDB.routing.EventListener;
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
//    boolean gui = false;
    String type = "none";
    EventListener<ServerResponseEvent> eventEventListener;

//    public void producerNotListet(String sender) throws IOException {
//        sendMessage("Produzent der Datei nicht gelistet.", sender);
//    }
//
//    public void fileNotListet(String sender) throws IOException {
//        sendMessage("Datei nicht gelistet.", sender);
//    }


    public void list(String list, String sender) throws IOException {
        sendMessage(list, sender);
    }

    public void sendString(String response, String sender) throws IOException {
        sendMessage(response, sender);
    }

    public void sendTypedString(String response, String sender, String type) throws IOException {
        sendTypedMessage(response, sender, type);
    }

    private void sendMessage(String message, String sender) throws IOException {
        if (checkUDP()){
            byte[] buf;
            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            ObjectOutput oo = new ObjectOutputStream(bStream);
            ServerResponseEvent serverResponseEvent = new ServerResponseEvent(this, message, sender, type);
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
                objectOutputStream.writeObject(new ServerResponseEvent(this, message, sender, type));
            }
        }
    }

    private void sendTypedMessage(String message, String sender, String type) throws IOException {
        ServerResponseEvent serverResponseEvent = new ServerResponseEvent(this, message, sender, type);
        if (checkUDP()){
            byte[] buf;
            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            ObjectOutput oo = new ObjectOutputStream(bStream);
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
                objectOutputStream.writeObject(serverResponseEvent);
            }
        }
    }

    public void sendToGUI(String message, String sender, String type) throws IOException {
        ServerResponseEvent serverResponseEvent = new ServerResponseEvent(this, message, sender, type);
        if (eventEventListener != null) {
            eventEventListener.onMediaEvent(serverResponseEvent);
        }
    }

    public void dataChange() throws IOException {
        if (eventEventListener != null)
            sendToGUI("DataChange", "gui", "DataChange");
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

//    public void setGUI(){
//        gui = true;
//    }


    public void setEventEventListener(EventListener<ServerResponseEvent> eventEventListener) {
        this.eventEventListener = eventEventListener;
    }
}
