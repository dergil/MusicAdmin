package mediaDB.IO;

import mediaDB.domain_logic.AddressRepository;
import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.observables.SizeObservable;
import mediaDB.domain_logic.observables.TagObservable;
import mediaDB.domain_logic.producer.ProducerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

//TODO: optional um Test der Streams ergänzen
class SerializeTest {
    SizeObservable sizeObservable;
    TagObservable tagObservable;
    MediaFileRepository mediaFileRepository;
    ProducerRepository producerRepository;
    AddressRepository addressRepository;
    Serialize serialize;

    @BeforeEach
    void setUp() {
        sizeObservable = mock(SizeObservable.class);
        tagObservable = mock(TagObservable.class);
        mediaFileRepository = mock(MediaFileRepository.class);
        producerRepository = mock(ProducerRepository.class);
        addressRepository = mock(AddressRepository.class);
        serialize = new Serialize(sizeObservable, tagObservable, mediaFileRepository, producerRepository, addressRepository);
    }

    @Test
    void serializeSize() {
        serialize.serialize();
        verify(sizeObservable).getCurrentSize();
    }

    @Test
    void serializeTags() {
        serialize.serialize();
        verify(tagObservable).getTagOccurences();
    }

    @Test
    void serializeUploadables() {
        serialize.serialize();
        verify(mediaFileRepository).read();
    }

    @Test
    void serializeProducer() {
        serialize.serialize();
        verify(producerRepository).getProducers();
    }

    @Test
    void serializeAddresses() {
        serialize.serialize();
        verify(addressRepository).getAddresses();
    }
}