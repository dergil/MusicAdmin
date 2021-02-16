
package mediaDB.ui.cli.modes;

import mediaDB.routing.*;
import mediaDB.routing.events.files.*;
import mediaDB.routing.events.misc.ProducerEvent;
import mediaDB.ui.cli.event_creation.*;

import java.io.IOException;

public class InsertModeInputProcessing {
    EventListener<NetworkEvent> serverEventBus;
    //    EventHandler<AudioEvent> audioEventHandler;
//    EventHandler<AudioVideoEvent> audioVideoEventHandler;
//    EventHandler<InteractiveVideoEvent> interactiveVideoEventHandler;
//    EventHandler<LicensedAudioEvent> licensedAudioEventHandler;
//    EventHandler<LicensedAudioVideoEvent> licensedAudioVideoEventHandler;
//    EventHandler<LicensedVideoEvent> licensedVideoEventHandler;
//
//    EventHandler<ProducerEvent> producerEventEventHandler;


    public InsertModeInputProcessing(EventListener<NetworkEvent> serverEventBus) {
        this.serverEventBus = serverEventBus;
    }

    public void mediaFile(String[] input) throws IOException {
        switch (input[0]) {
            case "Audio":
                CreateAudioEvent createAudioEvent = new CreateAudioEvent();
                AudioEvent audioEvent = createAudioEvent.process(input);
//                audioEventHandler.handle(audioEvent);
                serverEventBus.onMediaEvent(audioEvent);
                break;
            case "AudioVideo":
                CreateAudioVideoEvent createAudioVideoEvent = new CreateAudioVideoEvent();
                AudioVideoEvent audioVideoEvent = createAudioVideoEvent.process(input);
//                audioVideoEventHandler.handle(audioVideoEvent);
                serverEventBus.onMediaEvent(audioVideoEvent);
                break;
            case "InteractiveVideo":
                CreateInteractiveVideoEvent createInteractiveVideoEvent = new CreateInteractiveVideoEvent();
                InteractiveVideoEvent interactiveVideoEvent = createInteractiveVideoEvent.process(input);
//                interactiveVideoEventHandler.handle(interactiveVideoEvent);
                serverEventBus.onMediaEvent(interactiveVideoEvent);
                break;
            case "LicensedAudio":
                CreateLicensedAudioEvent createLicensedAudioEvent = new CreateLicensedAudioEvent();
                LicensedAudioEvent licensedAudioEvent = createLicensedAudioEvent.process(input);
//                licensedAudioEventHandler.handle(licensedAudioEvent);
                serverEventBus.onMediaEvent(licensedAudioEvent);
                break;
            case "LicensedAudioVideo":
                CreateLicensedAudioVideoEvent createLicensedAudioVideoEvent = new CreateLicensedAudioVideoEvent();
                LicensedAudioVideoEvent licensedAudioVideoEvent = createLicensedAudioVideoEvent.process(input);
//                licensedAudioVideoEventHandler.handle(licensedAudioVideoEvent);
                serverEventBus.onMediaEvent(licensedAudioVideoEvent);
                break;
            case "LicensedVideo":
                CreateLicensedVideoEvent createLicensedVideoEvent = new CreateLicensedVideoEvent();
                LicensedVideoEvent licensedVideoEvent = createLicensedVideoEvent.process(input);
//                licensedVideoEventHandler.handle(licensedVideoEvent);
                serverEventBus.onMediaEvent(licensedVideoEvent);
                break;
            default:
                throw new IllegalArgumentException("Unrecognized media file type");
        }
    }

    public void producer(String name) throws IOException {
        CreateProducerEvent createProducerEvent = new CreateProducerEvent();
        ProducerEvent producerEvent = createProducerEvent.process(name, "add");
//        producerEventEventHandler.handle(producerEvent);
        serverEventBus.onMediaEvent(producerEvent);
    }

//    public void setAudioEventHandler(EventHandler<AudioEvent> audioEventHandler) {
//        this.audioEventHandler = audioEventHandler;
//    }
//
//    public void setAudioVideoEventHandler(EventHandler<AudioVideoEvent> audioVideoEventHandler) {
//        this.audioVideoEventHandler = audioVideoEventHandler;
//    }
//
//    public void setInteractiveVideoEventHandler(EventHandler<InteractiveVideoEvent> interactiveVideoEventHandler) {
//        this.interactiveVideoEventHandler = interactiveVideoEventHandler;
//    }
//
//    public void setLicensedAudioEventHandler(EventHandler<LicensedAudioEvent> licensedAudioEventHandler) {
//        this.licensedAudioEventHandler = licensedAudioEventHandler;
//    }
//
//    public void setLicensedAudioVideoEventHandler(EventHandler<LicensedAudioVideoEvent> licensedAudioVideoEventHandler) {
//        this.licensedAudioVideoEventHandler = licensedAudioVideoEventHandler;
//    }
//
//    public void setLicensedVideoEventHandler(EventHandler<LicensedVideoEvent> licensedVideoEventHandler) {
//        this.licensedVideoEventHandler = licensedVideoEventHandler;
//    }
//
//    public void setProducerEventEventHandler(EventHandler<ProducerEvent> producerEventEventHandler) {
//        this.producerEventEventHandler = producerEventEventHandler;
//    }
}
