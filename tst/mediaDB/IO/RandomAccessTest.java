package mediaDB.IO;

import mediaDB.domain_logic.file_interfaces.Uploadable;
import mediaDB.domain_logic.files.InteractiveVideoFile;
import mediaDB.domain_logic.files.LicensedAudioVideoFile;
import mediaDB.simulation.RandomMediadfileInstances;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RandomAccessTest {
    RandomAccess randomAccess;
    RandomAccessFile randomAccessFile;
    RandomAccessFile spyRandomAccessFile;
    RandomMediadfileInstances randomMediadfileInstances = new RandomMediadfileInstances();
    ArrayList<SavedMediaFile> savedMediaFiles;
    InteractiveVideoFile interactiveVideoFile = randomMediadfileInstances.randomInteractiveVideoFile();

    @BeforeEach
    void setUp() throws FileNotFoundException {
        savedMediaFiles = new ArrayList<>();
        randomAccessFile = new RandomAccessFile("RandomAccessFile", "rw");
        spyRandomAccessFile = spy(randomAccessFile);
        randomAccess = new RandomAccess(spyRandomAccessFile, savedMediaFiles);
    }

    @Test
    void save() throws IOException {
        randomAccess.save(interactiveVideoFile);
        verify(spyRandomAccessFile).write(any());
    }

    @Test
    void load() throws IOException, ClassNotFoundException {
        randomAccess.save(interactiveVideoFile);
        randomAccess.load(interactiveVideoFile.getAddress());
        verify(spyRandomAccessFile).read(any());
    }

    @Test
    void isEmptyFalse() {
        assertTrue(randomAccess.isEmpty());

    }

    @Test
    void isEmptyTrue() throws IOException {
        randomAccess.save(interactiveVideoFile);
        assertFalse(randomAccess.isEmpty());
    }

}