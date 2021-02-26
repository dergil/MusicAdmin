package mediaDB.IO;

import mediaDB.domain_logic.AddressRepository;
import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.observables.SizeObservable;
import mediaDB.domain_logic.observables.TagObservable;
import mediaDB.domain_logic.producer.ProducerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigDecimal;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class DeserializeTest {
    SizeObservable sizeObservable;
    TagObservable tagObservable;
    MediaFileRepository mediaFileRepository;
    ProducerRepository producerRepository;
    AddressRepository addressRepository;
    DeserializeDomainContent deserializeDomainContent;
    Deserialize deserialize;
    ObjectInputStream ois;
    RandomAccess randomAccess;

    @BeforeEach
    void setUp() {
        sizeObservable = mock(SizeObservable.class);
        tagObservable = mock(TagObservable.class);
        mediaFileRepository = mock(MediaFileRepository.class);
        producerRepository = mock(ProducerRepository.class);
        addressRepository = mock(AddressRepository.class);
        deserializeDomainContent = mock(DeserializeDomainContent.class);
        randomAccess = mock(RandomAccess.class);
        deserialize = new Deserialize(sizeObservable, tagObservable, mediaFileRepository, producerRepository, addressRepository, deserializeDomainContent, randomAccess);
    }

    @Test
    void deserializeSize() throws IOException {
        deserialize.deserialize();
        ois=new ObjectInputStream(new FileInputStream(SerFilenames.CURRENTSIZE.toString()));
        verify(deserializeDomainContent).deserializeBigDecimal(ois);
    }

    @Test
    void deserializeSizeSet() throws IOException {
        deserialize.deserialize();
        verify(sizeObservable).setCurrentSize(any());
    }

    @Test
    void deserializeTags() throws IOException {
        deserialize.deserialize();
        verify(deserializeDomainContent).deserializeStringIntegerMap(ois);
    }

    @Test
    void deserializeTagsSet() throws IOException {
        deserialize.deserialize();
        verify(tagObservable).setTagOccurences(any());
    }

    @Test
    void deserializeUploadables() throws IOException {
        deserialize.deserialize();
        verify(deserializeDomainContent).deserializeUploadableList(ois);
    }

    @Test
    void deserializeUploader() throws IOException {
        deserialize.deserialize();
        verify(deserializeDomainContent).deserializeUploaderSet(ois);
    }

    @Test
    void deserializeAddresses() throws IOException {
        deserialize.deserialize();
        verify(deserializeDomainContent).deserializeIntegerSet(ois);
    }

    @Test
    void deserializeAddressesSet() throws IOException {
        deserialize.deserialize();
        verify(addressRepository).setAddresses(any());
    }

}