package mediaDB.domain_logic.listener.files;

import for_testing.EventFactoryForTesting;
import mediaDB.domain_logic.AddressRepository;
import mediaDB.domain_logic.MediaFileFactory;
import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.file_interfaces.Uploadable;
import mediaDB.domain_logic.observables.SizeObservable;
import mediaDB.domain_logic.observables.TagObservable;
import mediaDB.domain_logic.producer.ProducerRepository;
import mediaDB.domain_logic.producer.Uploader;
import mediaDB.net.server.ToClientMessenger;
import mediaDB.routing.events.files.AudioEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.Mockito.*;

class AudioEventListenerTest {
    ProducerRepository producerRepository;
    MediaFileFactory mediaFileFactory;
    MediaFileRepository mediaFileRepository;
    AudioEventListener audioEventListener;
    EventFactoryForTesting eventFactory = new EventFactoryForTesting();
    AudioEvent event = eventFactory.audioEvent();

    @BeforeEach
    void setUp() {
        producerRepository = mock(ProducerRepository.class);
        mediaFileFactory = mock(MediaFileFactory.class);
        mediaFileRepository = mock(MediaFileRepository.class);
        audioEventListener = new AudioEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
    }

    @Test
    void onMediaEventCapacity() {
        audioEventListener.onMediaEvent(event);
        verify(mediaFileRepository).capacityAvailable(any());
    }

    @Test
    void onMediaEventProducer() {
        audioEventListener.onMediaEvent(event);
        verify(producerRepository).contains(any(Uploader.class));
    }

    @Test
    void onMediaEventCreation() {
        ToClientMessenger toClient= new ToClientMessenger();
        SizeObservable sizeObservable = new SizeObservable();
        TagObservable tagObservable = new TagObservable();
        MediaFileRepository localMediaFileRepository  = spy(new MediaFileRepository(toClient, sizeObservable, tagObservable));
        AddressRepository addressRepository = new AddressRepository();
        MediaFileFactory mediaFileFactory = new MediaFileFactory(localMediaFileRepository, addressRepository, toClient);
        AudioEventListener localAudioEventListener = new AudioEventListener(producerRepository, mediaFileFactory, localMediaFileRepository);
        when(producerRepository.contains(any(Uploader.class))).thenReturn(true);
        localAudioEventListener.onMediaEvent(event);
        verify(localMediaFileRepository).create(any(Uploadable.class));
    }

    @Test
    void supports() {
        assertTrue(audioEventListener.supports(AudioEvent.class));
    }
}