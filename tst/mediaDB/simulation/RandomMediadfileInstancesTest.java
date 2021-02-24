package mediaDB.simulation;

import mediaDB.domain_logic.enums.MediaTypes;
import mediaDB.domain_logic.files.InteractiveVideoFile;
import mediaDB.domain_logic.files.LicensedAudioVideoFile;
import mediaDB.domain_logic.producer.Producer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class RandomMediadfileInstancesTest {
    RandomMediadfileInstances randomMediadfileInstances;

    @BeforeEach
    void setUp() {
        randomMediadfileInstances = new RandomMediadfileInstances();
    }

    @Test
    void randomInteractiveVideoFile() {
        InteractiveVideoFile interactiveVideoFile = randomMediadfileInstances.randomInteractiveVideoFile();
        assertEquals(MediaTypes.INTERACTIVEVIDEO.toString(), interactiveVideoFile.getFileType());
    }

    @Test
    void randomLicensedAudioVideoFile() {
        LicensedAudioVideoFile licensedAudioVideoFile = randomMediadfileInstances.randomLicensedAudioVideoFile();
        assertEquals(MediaTypes.LICENSEDAUDIOVIDEO.toString(), licensedAudioVideoFile.getFileType());
    }

    @Test
    void getProducers() {
        ArrayList<Producer> producers = randomMediadfileInstances.getProducers();
        assertTrue(producers.toString().contains("Hannes"));
    }
}