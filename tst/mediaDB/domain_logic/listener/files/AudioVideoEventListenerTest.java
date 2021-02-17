package mediaDB.domain_logic.listener.files;

import mediaDB.EventFactoryForTesting;
import mediaDB.domain_logic.MediaFileFactory;
import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.producer.Producer;
import mediaDB.domain_logic.producer.ProducerRepository;
import mediaDB.routing.events.files.AudioEvent;
import mediaDB.routing.events.files.AudioVideoEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class AudioVideoEventListenerTest {
    ProducerRepository producerRepository;
    MediaFileFactory mediaFileFactory;
    MediaFileRepository mediaFileRepository;

    EventFactoryForTesting eventFactoryForTesting;
    AudioVideoEventListener listener;
    AudioVideoEvent event;

    @BeforeEach
    void setUp() {
        producerRepository =  mock(ProducerRepository.class);
        mediaFileFactory = mock(MediaFileFactory.class);
        mediaFileRepository = mock(MediaFileRepository.class);
        listener = new AudioVideoEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
        eventFactoryForTesting = new EventFactoryForTesting();
        event = eventFactoryForTesting.audioVideoEvent();
    }

    @Test
    void onMediaEventCapacity() throws IOException {
        listener.onMediaEvent(event);
        verify(mediaFileRepository).capacityAvailable(any(BigDecimal.class));
    }

    @Test
    void onMediaEventProducer() throws IOException {
        listener.onMediaEvent(event);
        verify(producerRepository).contains(any(Producer.class));
    }

    @Test
    void onMediaEventFactory() throws IOException {
        listener.onMediaEvent(event);
        verify(mediaFileFactory).createAudioVideoFile(any(), any(), any(), any(), any(), any(), any(), any(), any(), any());
    }

    @Test
    void supports() {
        assertTrue(listener.supports(AudioVideoEvent.class));
    }
}