package mediaDB.ui.cli;

import mediaDB.routing.*;

//TODO: Idealerweise einen EventBus implementieren, der ein event entgegennimmt, und seine Listener durchgeht, welche das Event haben wollen; siehe cp-event von der vill
public class EventHandlers {
    EventHandler<AudioEvent> audioEventHandler;
    EventHandler<AudioVideoEvent> audioVideoEventHandler;
    EventHandler<InteractiveVideoEvent> interactiveVideoEventHandler;
    EventHandler<LicensedAudioEvent> licensedAudioEventHandler;
    EventHandler<LicensedAudioVideoEvent> licensedAudioVideoEventHandler;
    EventHandler<LicensedVideoEvent> licensedVideoEventHandler;

    EventHandler<ProducerEvent> producerEventEventHandler;
    EventHandler<DisplayEvent> displayEventEventHandler;

    EventHandler<StringEvent> stringEventEventHandler;

    public EventHandler<StringEvent> getStringEventEventHandler() {
        return stringEventEventHandler;
    }

    public void setStringEventEventHandler(EventHandler<StringEvent> stringEventEventHandler) {
        this.stringEventEventHandler = stringEventEventHandler;
    }

    public EventHandler<DisplayEvent> getDisplayEventEventHandler() {
        return displayEventEventHandler;
    }

    public void setDisplayEventEventHandler(EventHandler<DisplayEvent> displayEventEventHandler) {
        this.displayEventEventHandler = displayEventEventHandler;
    }

    public EventHandler<AudioEvent> getAudioEventHandler() {
        return audioEventHandler;
    }

    public void setAudioEventHandler(EventHandler<AudioEvent> audioEventHandler) {
        this.audioEventHandler = audioEventHandler;
    }

    public EventHandler<AudioVideoEvent> getAudioVideoEventHandler() {
        return audioVideoEventHandler;
    }

    public void setAudioVideoEventHandler(EventHandler<AudioVideoEvent> audioVideoEventHandler) {
        this.audioVideoEventHandler = audioVideoEventHandler;
    }

    public EventHandler<InteractiveVideoEvent> getInteractiveVideoEventHandler() {
        return interactiveVideoEventHandler;
    }

    public void setInteractiveVideoEventHandler(EventHandler<InteractiveVideoEvent> interactiveVideoEventHandler) {
        this.interactiveVideoEventHandler = interactiveVideoEventHandler;
    }

    public EventHandler<LicensedAudioEvent> getLicensedAudioEventHandler() {
        return licensedAudioEventHandler;
    }

    public void setLicensedAudioEventHandler(EventHandler<LicensedAudioEvent> licensedAudioEventHandler) {
        this.licensedAudioEventHandler = licensedAudioEventHandler;
    }

    public EventHandler<LicensedAudioVideoEvent> getLicensedAudioVideoEventHandler() {
        return licensedAudioVideoEventHandler;
    }

    public void setLicensedAudioVideoEventHandler(EventHandler<LicensedAudioVideoEvent> licensedAudioVideoEventHandler) {
        this.licensedAudioVideoEventHandler = licensedAudioVideoEventHandler;
    }

    public EventHandler<LicensedVideoEvent> getLicensedVideoEventHandler() {
        return licensedVideoEventHandler;
    }

    public void setLicensedVideoEventHandler(EventHandler<LicensedVideoEvent> licensedVideoEventHandler) {
        this.licensedVideoEventHandler = licensedVideoEventHandler;
    }

    public EventHandler<ProducerEvent> getProducerEventEventHandler() {
        return producerEventEventHandler;
    }

    public void setProducerEventEventHandler(EventHandler<ProducerEvent> producerEventEventHandler) {
        this.producerEventEventHandler = producerEventEventHandler;
    }
}
