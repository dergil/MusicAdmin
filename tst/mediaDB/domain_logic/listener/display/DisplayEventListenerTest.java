package mediaDB.domain_logic.listener.display;

import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.enums.MediaTypes;
import mediaDB.net.server.ToClientMessenger;
import mediaDB.routing.events.misc.DisplayEvent;
import mediaDB.routing.events.misc.PersistenceEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class DisplayEventListenerTest {
    DisplayModeProcessing displayModeProcessing;
    MediaFileRepository mediaFileRepository;
    ToClientMessenger toClient;
    DisplayEvent displayEvent;
    DisplayEventListener displayEventListener;

    @BeforeEach
    void setUp() {
        displayModeProcessing = mock(DisplayModeProcessing.class);
        mediaFileRepository = mock(MediaFileRepository.class);
        toClient = mock(ToClientMessenger.class);
        displayEventListener = new DisplayEventListener(displayModeProcessing, mediaFileRepository, toClient);
    }

    @Test
    void onMediaEventUploaderToClient() throws IOException {
        setEvent("uploader", null);
        displayEventListener.onMediaEvent(displayEvent);
        verify(toClient).sendString(any(), anyString());
    }

    @Test
    void onMediaEventUploaderDisplayModeProcessing() throws IOException {
        setEvent("uploader", null);
        displayEventListener.onMediaEvent(displayEvent);
        verify(displayModeProcessing).uploader();
    }

    @Test
    void onMediaEventContentToClient() throws IOException {
        setEvent("content", null);
        displayEventListener.onMediaEvent(displayEvent);
        verify(toClient).sendString(any(), anyString());
    }

    @Test
    void onMediaEventContent() throws IOException {
        setEvent("content", null);
        displayEventListener.onMediaEvent(displayEvent);
        verify(displayModeProcessing).content(anyList());
    }

    @Test
    void onMediaEventContentWithTypeToClient() throws IOException {
        setEvent("content", MediaTypes.AUDIO.toString());
        displayEventListener.onMediaEvent(displayEvent);
        verify(toClient).sendString(any(), anyString());
    }

    @Test
    void onMediaEventContentWithType() throws IOException {
        setEvent("content", MediaTypes.AUDIO.toString());
        displayEventListener.onMediaEvent(displayEvent);
        verify(displayModeProcessing).content(anyList(), anyString());
    }

    @Test
    void onMediaEventTagToClient() throws IOException {
        setEvent("tag", "i");
        displayEventListener.onMediaEvent(displayEvent);
        verify(toClient).sendString(any(), anyString());
    }

    @Test
    void onMediaEventTag() throws IOException {
        setEvent("tag", "i");
        displayEventListener.onMediaEvent(displayEvent);
        verify(displayModeProcessing).tag("i");
    }

    @Test
    void supportsPositive() {
        assertTrue(displayEventListener.supports(DisplayEvent.class));
    }

    @Test
    void supportsNegative() {
        assertFalse(displayEventListener.supports(PersistenceEvent.class));
    }

    private void setEvent(String topic, String option){
        displayEvent = new DisplayEvent(this, topic, option, "sender");

    }
}