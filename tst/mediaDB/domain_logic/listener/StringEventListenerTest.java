package mediaDB.domain_logic.listener;

import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.producer.ProducerRepository;
import mediaDB.net.server.ToClientMessenger;
import mediaDB.routing.events.misc.StringEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class StringEventListenerTest {
    MediaFileRepository mediaFileRepository;
    ProducerRepository producerRepository;
    ToClientMessenger toClientMessenger;
    StringEventListener stringEventListener;
    StringEvent event;


    @BeforeEach
    void setUp() {
        mediaFileRepository = mock(MediaFileRepository.class);
        producerRepository = mock(ProducerRepository.class);
        toClientMessenger = mock(ToClientMessenger.class);
        stringEventListener = new StringEventListener(mediaFileRepository, producerRepository, toClientMessenger);
    }

    @Test
    void onMediaEventDeletion() throws IOException {
        setEvent("Deletion", "address", "1");
        stringEventListener.onMediaEvent(event);
        verify(mediaFileRepository).findByAddress("1");
    }

    @Test
    void onMediaEventChange() throws IOException {
        setEvent("Change", null, "1");
        stringEventListener.onMediaEvent(event);
        verify(mediaFileRepository).incrementAccessCount("1");
    }

    @Test
    void supports() {
        assertTrue(stringEventListener.supports(StringEvent.class));
    }

    private void setEvent(String mode, String command, String option){
        event = new StringEvent(this, mode, command, option, "sender");
    }
}