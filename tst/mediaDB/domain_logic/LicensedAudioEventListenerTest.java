package mediaDB.domain_logic;

import mediaDB.EventFactory;
import mediaDB.MediaTypesTest;
import mediaDB.domain_logic.listener.LicensedAudioEventListener;
import mediaDB.routing.LicensedAudioEvent;
import mediaDB.tempserver.ServerToClientMessenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class LicensedAudioEventListenerTest {
    SizeObservable sizeObservable;
    MediaFileRepository mediaFileRepository;
    LicensedAudioEventListener licensedAudioEventListener;
    EventFactory eventFactory = new EventFactory();
    LicensedAudioEvent licensedAudioEvent;

    @BeforeEach
    void setUp() throws IOException {
        ServerToClientMessenger toClient= new ServerToClientMessenger();
        sizeObservable = new SizeObservable();
        TagObservable tagObservable = new TagObservable();
        mediaFileRepository  = new MediaFileRepository(toClient, sizeObservable, tagObservable);
        ProducerRepository producerRepository = new ProducerRepository();
        AddressRepository addressRepository = new AddressRepository();
        MediaFileFactory mediaFileFactory = new MediaFileFactory(mediaFileRepository, addressRepository);
        licensedAudioEvent = eventFactory.licensedAudioEvent();
        licensedAudioEventListener = new LicensedAudioEventListener(producerRepository, mediaFileFactory, mediaFileRepository);
        producerRepository.addProducer("Gilbert");
    }

    @Test
    void onMediaEvent() throws IOException {
        licensedAudioEventListener.onMediaEvent(licensedAudioEvent);
        assertEquals(MediaTypesTest.LICENSEDAUDIO.toString(), mediaFileRepository.read().get(0).getFileType());
    }

    @Test
    void onMediaEventNoCapacity() throws IOException {
        sizeObservable.setMAX_CAPACITY(new BigDecimal(10000));
        licensedAudioEventListener.onMediaEvent(licensedAudioEvent);
        assertEquals(0, mediaFileRepository.read().size());
    }
}