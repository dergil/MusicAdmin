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
import mediaDB.routing.events.files.InteractiveVideoEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class InteractiveVideoEventListenerTest {
    ProducerRepository producerRepository;
    MediaFileFactory mediaFileFactory;
    MediaFileRepository mediaFileRepository;
    InteractiveVideoEventListener interactiveVideoEventListener;
    EventFactoryForTesting eventFactory = new EventFactoryForTesting();
    InteractiveVideoEvent event = eventFactory.interactiveVideoEvent();

    @BeforeEach
    void setUp() {
        producerRepository = mock(ProducerRepository.class);
        mediaFileFactory = mock(MediaFileFactory.class);
        mediaFileRepository = mock(MediaFileRepository.class);
        interactiveVideoEventListener = new InteractiveVideoEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
    }

    @Test
    void onMediaEventCapacity() throws IOException {
        interactiveVideoEventListener.onMediaEvent(event);
        verify(mediaFileRepository).capacityAvailable(any());
    }

    @Test
    void onMediaEventProducer() throws IOException {
        interactiveVideoEventListener.onMediaEvent(event);
        verify(producerRepository).contains(any(Uploader.class));
    }

    @Test
    void onMediaEventCreation() throws IOException {
        ToClientMessenger toClient= new ToClientMessenger();
        SizeObservable sizeObservable = new SizeObservable();
        TagObservable tagObservable = new TagObservable();
        MediaFileRepository localMediaFileRepository  = spy(new MediaFileRepository(toClient, sizeObservable, tagObservable));
        AddressRepository addressRepository = new AddressRepository();
        MediaFileFactory mediaFileFactory = new MediaFileFactory(localMediaFileRepository, addressRepository, toClient);
        InteractiveVideoEventListener localInteractiveVideoEventListener = new InteractiveVideoEventListener(producerRepository, mediaFileFactory, localMediaFileRepository);
        when(producerRepository.contains(any(Uploader.class))).thenReturn(true);
        localInteractiveVideoEventListener.onMediaEvent(event);
        verify(localMediaFileRepository).create(any(Uploadable.class));
    }

    @Test
    void supports() {
        assertTrue(interactiveVideoEventListener.supports(InteractiveVideoEvent.class));
    }

}