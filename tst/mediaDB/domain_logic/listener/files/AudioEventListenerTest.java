package mediaDB.domain_logic.listener.files;

import mediaDB.EventFactoryForTesting;
import mediaDB.domain_logic.AddressRepository;
import mediaDB.domain_logic.MediaFileFactory;
import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.observables.SizeObservable;
import mediaDB.domain_logic.observables.TagObservable;
import mediaDB.domain_logic.producer.Producer;
import mediaDB.domain_logic.producer.ProducerRepository;
import mediaDB.net.server.ToClientMessenger;
import mediaDB.routing.events.files.AudioEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

//TODO: bei Zeit und Muße bei onMediaEventFactory die Werte des events einfügen
class AudioEventListenerTest {
    ProducerRepository producerRepository;
    MediaFileFactory mediaFileFactory;
    MediaFileFactory spyMediaFileFactory;
    MediaFileRepository mediaFileRepository;

    EventFactoryForTesting eventFactoryForTesting;
    AudioEventListener listener;
    AudioEvent event;

    @BeforeEach
    void setUp() {
        producerRepository =  mock(ProducerRepository.class);
//        mediaFileFactory = mock(MediaFileFactory.class);
        mediaFileRepository = mock(MediaFileRepository.class);
        producerRepository = new ProducerRepository();
        producerRepository.addProducer("Gilbert");
        mediaFileRepository = new MediaFileRepository(mock(ToClientMessenger.class), new SizeObservable(), new TagObservable());
        mediaFileFactory = new MediaFileFactory(mediaFileRepository, new AddressRepository());
        spyMediaFileFactory = spy(mediaFileFactory);
//        when(mediaFileRepository.capacityAvailable(any(BigDecimal.class))).thenReturn(true);
//        when(producerRepository.contains(any(Producer.class))).thenReturn(true);
        listener = new AudioEventListener(producerRepository, spyMediaFileFactory, mediaFileRepository);
        eventFactoryForTesting = new EventFactoryForTesting();
        event = eventFactoryForTesting.audioEvent();
    }

    @Test
    void onMediaEventCapacity() {
        listener.onMediaEvent(event);
        verify(mediaFileRepository).capacityAvailable(any(BigDecimal.class));
    }

    @Test
    void onMediaEventProducer() {
        listener.onMediaEvent(event);
        verify(producerRepository).contains(any(Producer.class));
    }

    @Test
    void onMediaEventFactory() {
        listener.onMediaEvent(event);
//        verify(mediaFileFactory).createAudioFile((any()), any(), any(), any(), any(), any(), any(), any());
        verify(spyMediaFileFactory).createAudioFile(any(), any(), any(), any(), any(), any(), any(), any());
    }

    @Test
    void supports() {
        assertTrue(listener.supports(AudioEvent.class));
    }
}