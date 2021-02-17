package mediaDB.domain_logic;

import mediaDB.domain_logic.observables.SizeObservable;
import mediaDB.domain_logic.observables.TagObservable;
import mediaDB.domain_logic.files.InteractiveVideoFile;
import mediaDB.simulation.RandomMediadfileInstances;
import mediaDB.net.server.ToClientMessenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class MediaFileRepositoryTest {
    SizeObservable sizeObservable;
    MediaFileRepository mediaFileRepository;
    RandomMediadfileInstances randomMediadfileInstances = new RandomMediadfileInstances();
    InteractiveVideoFile interactiveVideoFile = randomMediadfileInstances.randomInteractiveVideoFile();
    BigDecimal sizeInteractiveVideoFile = new BigDecimal(interactiveVideoFile.getLength().getSeconds()*interactiveVideoFile.getBitrate());

    @BeforeEach
    void setUp() {
        sizeObservable = new SizeObservable();
        mediaFileRepository = new MediaFileRepository(new ToClientMessenger(), sizeObservable, new TagObservable());
    }

    @Test
    void create() {
        mediaFileRepository.create(interactiveVideoFile);
        assertTrue(mediaFileRepository.contains(interactiveVideoFile));
    }

    @Test
    void createAddedSize() {
        mediaFileRepository.create(interactiveVideoFile);
        assertEquals(sizeInteractiveVideoFile, sizeObservable.getCurrentSize());
    }

    @Test
    void deleteByInstance() {
        mediaFileRepository.create(interactiveVideoFile);
        mediaFileRepository.delete(interactiveVideoFile);
        assertFalse(mediaFileRepository.contains(interactiveVideoFile));
    }

    @Test
    void deleteRemovedSize() {
        mediaFileRepository.create(interactiveVideoFile);
        mediaFileRepository.delete(interactiveVideoFile);
        assertEquals(new BigDecimal(0), sizeObservable.getCurrentSize());
    }

    @Test
    void deleteByAddress() {
        mediaFileRepository.create(interactiveVideoFile);
        mediaFileRepository.delete(interactiveVideoFile.getAddress());
        assertFalse(mediaFileRepository.contains(interactiveVideoFile));
    }

    @Test
    void findByAddress() {
        mediaFileRepository.create(interactiveVideoFile);
        assertEquals(mediaFileRepository.findByAddress(interactiveVideoFile.getAddress()), interactiveVideoFile);
    }

    @Test
    void capacityAvailableTrue() {
        sizeObservable.setMAX_CAPACITY(sizeInteractiveVideoFile.add(new BigDecimal(1)));
        assertTrue(mediaFileRepository.capacityAvailable(sizeInteractiveVideoFile));
    }

    @Test
    void capacityAvailableFals() {
        sizeObservable.setMAX_CAPACITY(sizeInteractiveVideoFile.subtract(new BigDecimal(1)));
        assertFalse(mediaFileRepository.capacityAvailable(sizeInteractiveVideoFile));
    }

    @Test
    void containsTrueByInstance() {
        mediaFileRepository.create(interactiveVideoFile);
        assertTrue(mediaFileRepository.contains(interactiveVideoFile));
    }

    @Test
    void containsFalseByInstance() {
        mediaFileRepository.create(interactiveVideoFile);
        mediaFileRepository.delete(interactiveVideoFile);
        assertFalse(mediaFileRepository.contains(interactiveVideoFile));
    }

    @Test
    void containsTrueByAddress() {
        mediaFileRepository.create(interactiveVideoFile);
        assertTrue(mediaFileRepository.contains(interactiveVideoFile.getAddress()));
    }

    @Test
    void containsFalseByAddress() {
        mediaFileRepository.create(interactiveVideoFile);
        mediaFileRepository.delete(interactiveVideoFile);
        assertFalse(mediaFileRepository.contains(interactiveVideoFile.getAddress()));
    }
}