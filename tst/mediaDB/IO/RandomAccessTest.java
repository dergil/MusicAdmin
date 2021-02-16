package mediaDB.IO;

import mediaDB.domain_logic.file_interfaces.Uploadable;
import mediaDB.domain_logic.files.InteractiveVideoFile;
import mediaDB.domain_logic.files.LicensedAudioVideoFile;
import mediaDB.simulation.RandomMediadfileInstances;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RandomAccessTest {
    RandomAccess randomAccess;
    RandomMediadfileInstances randomMediadfileInstances = new RandomMediadfileInstances();
    InteractiveVideoFile interactiveVideoFile = randomMediadfileInstances.randomInteractiveVideoFile();
    LicensedAudioVideoFile licensedAudioVideoFile = randomMediadfileInstances.randomLicensedAudioVideoFile();

    @BeforeEach
    void setUp() throws FileNotFoundException {
        randomAccess = new RandomAccess();
    }

    @Test
    void save() throws IOException {
        randomAccess.save(interactiveVideoFile);
        SavedMediaFile savedMediaFile = randomAccess.getSavedMediaFiles().get(0);
        System.out.println(savedMediaFile.getSize());
        System.out.println(savedMediaFile.getOffset());
        System.out.println(randomAccess.getRandomAccessFile().length());
    }

    @Test
    void load() throws IOException, ClassNotFoundException {
        randomAccess.save(interactiveVideoFile);
        randomAccess.save(licensedAudioVideoFile);
        Uploadable uploadable = randomAccess.load(licensedAudioVideoFile.getAddress());
        LicensedAudioVideoFile file = (LicensedAudioVideoFile) uploadable;
        System.out.println(file);

    }

    @Test
    void isEmpty() {
    }
}