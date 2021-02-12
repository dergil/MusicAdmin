package mediaDB.domain_logic;

import mediaDB.EventFactory;
import mediaDB.domain_logic.listener.AudioEventListener;
import mediaDB.routing.AudioEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.mockito.Mockito.*;

class AudioEventListenerTest {
    ProducerRepository producerRepository;
    MediaFileRepository mediaFileRepository;
    MediaFileFactory mediaFileFactory;
    MediaFileFactory spyMediaFileFactory;

    AudioEventListener audioEventListener;
    EventFactory eventFactory = new EventFactory();
    AudioEvent event;

    @BeforeEach
    void setUp() {
        producerRepository = mock(ProducerRepository.class);
        mediaFileRepository = mock(MediaFileRepository.class);
        mediaFileFactory = new MediaFileFactory(mediaFileRepository, mock(AddressRepository.class));
        spyMediaFileFactory = spy(mediaFileFactory);

        event = eventFactory.audioEvent();
        audioEventListener = new AudioEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
        when(producerRepository.contains(any(Uploader.class))).thenReturn(true);
    }

    @Test
    void onMediaEvent() throws IOException {
        when(mediaFileRepository.capacityAvailable(any(BigDecimal.class))).thenReturn(true);
        audioEventListener.onMediaEvent(event);
        verify(spyMediaFileFactory).createAudioFile(event.getFileType(), event.getSamplingRate(),
                event.getEncoding(), event.getTags(), event.getBitrate(), event.getLength(), any(BigDecimal.class), any(Uploader.class));
//        assertEquals(MediaTypesTest.AUDIO.toString(), mediaFileRepository.read().get(0).getFileType());
    }

//    @Test
//    void onMediaEventNoCapacity() throws IOException {
//        when(mediaFileRepository.capacityAvailable(any(BigDecimal.class))).thenReturn(false);
//        audioEventListener.onMediaEvent(event);
//        assertEquals(0, mediaFileRepository.read().size());
//    }
}