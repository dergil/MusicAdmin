package mediaDB.ui.gui;

import mediaDB.domain_logic.file_interfaces.Content;
import mediaDB.domain_logic.files.InteractiveVideoFile;
import mediaDB.simulation.RandomMediadfileInstances;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExtractDataFromStringTest {
    ExtractDataFromString extractDataFromString = new ExtractDataFromString();
    RandomMediadfileInstances randomMediadfileInstances = new RandomMediadfileInstances();
    InteractiveVideoFile file = randomMediadfileInstances.randomInteractiveVideoFile();

    @BeforeEach
    void setUp() {
    }

    @Test
    void getProducerDisplayVersion() {
        String input = "Gilbert=12";
        assertEquals("Gilbert", extractDataFromString.getProducerDisplayVersion(input));
    }

    @Test
    void getProducer() {
        String producerName = file.getUploader().getName();
        assertEquals(producerName, extractDataFromString.getProducer(file.toString()));
    }

    @Test
    void getAccessCount() {
        long accessCount = 345;
        file.setAccessCount(accessCount);
        assertEquals(accessCount, extractDataFromString.getAccessCount(file.toString()));
    }

    @Test
    void getAddress() {
        String address = ((Content) file).getAddress();
        assertEquals(address, String.valueOf(extractDataFromString.getAddress(file.toString())));
    }
}