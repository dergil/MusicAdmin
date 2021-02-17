package mediaDB.IO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.ObjectInputStream;

class DeserializeDomainContentTest {
    DeserializeDomainContent deserializeDomainContent;
    ObjectInputStream ois;

    @BeforeEach
    void setUp() {
        deserializeDomainContent = new DeserializeDomainContent();
        ois = mock(ObjectInputStream.class);
    }

    @Test
    void deserializeBigDecimal() throws IOException, ClassNotFoundException {
        deserializeDomainContent.deserializeBigDecimal(ois);
        verify(ois).readObject();
    }

    @Test
    void deserializeUploadableList() throws IOException, ClassNotFoundException {
        deserializeDomainContent.deserializeUploadableList(ois);
        verify(ois).readObject();
    }

    @Test
    void deserializeStringIntegerMap() throws IOException, ClassNotFoundException {
        deserializeDomainContent.deserializeStringIntegerMap(ois);
        verify(ois).readObject();
    }

    @Test
    void deserializeUploaderSet() throws IOException, ClassNotFoundException {
        deserializeDomainContent.deserializeUploaderSet(ois);
        verify(ois).readObject();
    }

    @Test
    void deserializeIntegerSet() throws IOException, ClassNotFoundException {
        deserializeDomainContent.deserializeIntegerSet(ois);
        verify(ois).readObject();
    }
}