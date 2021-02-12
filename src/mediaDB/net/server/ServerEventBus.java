package mediaDB.net.server;

import mediaDB.routing.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

public class ServerEventBus implements EventListener<NetworkEvent>{
//    MediaRepository mediaRepository;
//    MediaFileFactory fileCreator;
    List<EventListener<NetworkEvent>> listeners = new LinkedList<>();
    ObjectOutputStream out;
    ServerResponseEvent endEvent = new ServerResponseEvent(this, "End");

//    public void onMediaEvent(NetworkEvent event) throws IOException {
//        for (EventListener listener : listeners) {
//            if(listener.supports(event.getClass())){
//                listener.onMediaEvent(event);
//            }
//        }
//    }

    @Override
    public boolean supports(Class clazz) {
        return true;
    }

//    @Override
//    public void onMediaEvent(Object event) throws IOException {
//        for (EventListener listener : listeners) {
//            if(listener.supports(event.getClass())){
//                listener.onMediaEvent(event);
//            }
//        }
//    }

    public void register (EventListener eventListener){
        listeners.add(eventListener);
    }

    //    public ServerEventHandler(MediaRepository mediaRepository, MediaFileFactory mediaFileFactory) {
//        this.mediaRepository = mediaRepository;
//        this.fileCreator = mediaFileFactory;
//    }

//    enum EventType{
//        AudioEvent,
//        AudioVideoEvent,
//        InteractiveVideoEvent,
//        LicensedAudioEvent,
//        LicensedAudioVideoEvent,
//        LicensedVideoEvent,
//
////        DeleteMediaEvent, substituted by StringEvent
//        DisplayEvent,
//        ProducerEvent,
//        StringEvent,
//        CloseConnectionEvent
//    }
//
//    public void   handle(NetworkEvent event) throws IOException, InterruptedException {
//        EventType type = EventType.valueOf(event.getEventName());
//        switch (type){
//            case DeleteMediaEvent:
//                mediaRepository.onDeleteMediaEvent((DeleteMediaEvent) event);
//                break;
//            case InteractiveVideoEvent:
//                fileCreator.onInteractiveVideoEvent((InteractiveVideoEvent) event);
//                break;
//            case LicensedAudioVideoEvent:
//                fileCreator.onLicensedAudioVideoEvent((LicensedAudioVideoEvent) event);
//                break;
//            case ListEvent:
//                mediaRepository.onListEvent((ListEvent) event);
//                break;
//            case ProducerEvent:
//                mediaRepository.onProducerEvent((ProducerEvent) event);
//                break;
//            case CloseConnectionEvent:
//                sendResponse("End");
//                System.exit(0);
//                break;
//        }
//    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public ServerResponseEvent getEndEvent() {
        return endEvent;
    }

    public void sendResponse(String response) throws IOException {
        out.writeObject(new ServerResponseEvent(this, response));
    }


    @Override
    public void onMediaEvent(NetworkEvent event) throws IOException {
        for (EventListener<NetworkEvent> listener : listeners) {
            if(listener.supports(event.getClass())){
                listener.onMediaEvent(event);
            }
        }
    }
}
