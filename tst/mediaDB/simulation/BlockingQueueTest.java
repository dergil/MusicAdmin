package mediaDB.simulation;

import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.files.InteractiveVideoFile;
import mediaDB.domain_logic.observables.SizeObservable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;


class BlockingQueueTest {
    MediaFileRepository mediaFileRepository;
    SizeObservable sizeObservable;
    BlockingQueue blockingQueue;
    RandomMediadfileInstances randomMediadfileInstances;
    InteractiveVideoFile interactiveVideoFile;

    @BeforeEach
    void setUp() {
        mediaFileRepository = mock(MediaFileRepository.class);
        sizeObservable = mock(SizeObservable.class);
        when(sizeObservable.getMAX_CAPACITY()).thenReturn(new BigDecimal(1000000));
        blockingQueue = new BlockingQueue(mediaFileRepository, sizeObservable);
        randomMediadfileInstances = new RandomMediadfileInstances();
        interactiveVideoFile = randomMediadfileInstances.randomInteractiveVideoFile();
    }

    @Test
    void put() throws InterruptedException {
        when(sizeObservable.getCurrentSize()).thenReturn(new BigDecimal(200000));
        blockingQueue.put(interactiveVideoFile);
        verify(mediaFileRepository).create(interactiveVideoFile);
    }

    @Test
    void take() throws InterruptedException {
        when(sizeObservable.getCurrentSize()).thenReturn(new BigDecimal(800000));
        blockingQueue.take(interactiveVideoFile);
        verify(mediaFileRepository).delete(interactiveVideoFile);
    }
}