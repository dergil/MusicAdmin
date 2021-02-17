package mediaDB.domain_logic;

import mediaDB.EventFactoryForTesting;
import mediaDB.MediaTypesTest;
import mediaDB.domain_logic.observables.SizeObservable;
import mediaDB.domain_logic.observables.TagObservable;
import mediaDB.domain_logic.listener.files.LicensedAudioEventListener;
import mediaDB.domain_logic.producer.ProducerRepository;
import mediaDB.routing.events.files.LicensedAudioEvent;
import mediaDB.net.server.ToClientMessenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class LicensedAudioEventListenerTest {
    SizeObservable sizeObservable;
    MediaFileRepository mediaFileRepository;
    LicensedAudioEventListener licensedAudioEventListener;
    EventFactoryForTesting eventFactoryForTesting = new EventFactoryForTesting();
    LicensedAudioEvent licensedAudioEvent;

    @BeforeEach
    void setUp() throws IOException {
        ToClientMessenger toClient= new ToClientMessenger();
        sizeObservable = new SizeObservable();
        TagObservable tagObservable = new TagObservable();
        mediaFileRepository  = new MediaFileRepository(toClient, sizeObservable, tagObservable);
        ProducerRepository producerRepository = new ProducerRepository();
        AddressRepository addressRepository = new AddressRepository();
        MediaFileFactory mediaFileFactory = new MediaFileFactory(mediaFileRepository, addressRepository);
        licensedAudioEvent = eventFactoryForTesting.licensedAudioEvent();
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