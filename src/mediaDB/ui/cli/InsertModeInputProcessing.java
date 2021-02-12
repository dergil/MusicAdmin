
package mediaDB.ui.cli;

import mediaDB.routing.*;
import mediaDB.ui.cli.event_creation.*;

import java.io.IOException;

public class InsertModeInputProcessing {
    EventHandlers eventHandlers;

//    EventHandler<AudioEvent> audioEventHandler;
//    EventHandler<AudioVideoEvent> audioVideoEventHandler;
//    EventHandler<InteractiveVideoEvent> interactiveVideoEventHandler;
//    EventHandler<LicensedAudioEvent> licensedAudioEventHandler;
//    EventHandler<LicensedAudioVideoEvent> licensedAudioVideoEventHandler;
//    EventHandler<LicensedVideoEvent> licensedVideoEventHandler;
//
//    EventHandler<ProducerEvent> producerEventEventHandler;

    public InsertModeInputProcessing(EventHandlers eventHandlers) {
        this.eventHandlers = eventHandlers;
    }

    protected void mediaFile(String[] input) throws IOException {
        switch (input[0]) {
            case "Audio":
                CreateAudioEvent createAudioEvent = new CreateAudioEvent();
                AudioEvent audioEvent = createAudioEvent.process(input);
//                audioEventHandler.handle(audioEvent);
                eventHandlers.getAudioEventHandler().handle(audioEvent);
                break;
            case "AudioVideo":
                CreateAudioVideoEvent createAudioVideoEvent = new CreateAudioVideoEvent();
                AudioVideoEvent audioVideoEvent = createAudioVideoEvent.process(input);
//                audioVideoEventHandler.handle(audioVideoEvent);
                eventHandlers.getAudioVideoEventHandler().handle(audioVideoEvent);
                break;
            case "InteractiveVideo":
                CreateInteractiveVideoEvent createInteractiveVideoEvent = new CreateInteractiveVideoEvent();
                InteractiveVideoEvent interactiveVideoEvent = createInteractiveVideoEvent.process(input);
//                interactiveVideoEventHandler.handle(interactiveVideoEvent);
                eventHandlers.getInteractiveVideoEventHandler().handle(interactiveVideoEvent);
                break;
            case "LicensedAudio":
                CreateLicensedAudioEvent createLicensedAudioEvent = new CreateLicensedAudioEvent();
                LicensedAudioEvent licensedAudioEvent = createLicensedAudioEvent.process(input);
//                licensedAudioEventHandler.handle(licensedAudioEvent);
                eventHandlers.getLicensedAudioEventHandler().handle(licensedAudioEvent);
                break;
            case "LicensedAudioVideo":
                CreateLicensedAudioVideoEvent createLicensedAudioVideoEvent = new CreateLicensedAudioVideoEvent();
                LicensedAudioVideoEvent licensedAudioVideoEvent = createLicensedAudioVideoEvent.process(input);
//                licensedAudioVideoEventHandler.handle(licensedAudioVideoEvent);
                eventHandlers.getLicensedAudioVideoEventHandler().handle(licensedAudioVideoEvent);
                break;
            case "LicensedVideo":
                CreateLicensedVideoEvent createLicensedVideoEvent = new CreateLicensedVideoEvent();
                LicensedVideoEvent licensedVideoEvent = createLicensedVideoEvent.process(input);
//                licensedVideoEventHandler.handle(licensedVideoEvent);
                eventHandlers.getLicensedVideoEventHandler().handle(licensedVideoEvent);
                break;
            default:
                throw new IllegalArgumentException("Unrecognized media file type");
        }
    }

    protected void producer(String name) throws IOException {
        CreateProducerEvent createProducerEvent = new CreateProducerEvent();
        ProducerEvent producerEvent = createProducerEvent.process(name, "add");
//        producerEventEventHandler.handle(producerEvent);
        eventHandlers.getProducerEventEventHandler().handle(producerEvent);
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
