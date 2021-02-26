package mediaDB.ui.gui;

import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.enums.Tag;
import mediaDB.domain_logic.observables.SizeObservable;
import mediaDB.domain_logic.observables.TagObservable;
import mediaDB.net.EventBus;
import mediaDB.observer.SizeObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SeperateCLITest {
    EventBus serverEventBus;
    MediaFileRepository mediaFileRepository;
    SizeObservable sizeObservable;
    TagObservable tagObservable;
    SeperateCLI seperateCLI;

    @BeforeEach
    void setUp() {
//        serverEventBus = mock(EventBus.class);
//        mediaFileRepository = mock(MediaFileRepository.class);
//        sizeObservable = mock(SizeObservable.class);
//        tagObservable = mock(TagObservable.class);
//        seperateCLI = new SeperateCLI(serverEventBus, mediaFileRepository, sizeObservable, tagObservable);
    }
//todo: nur pro forma test
//    @Test
//    void run() {
//        seperateCLI.start();
//        verify(sizeObservable).getMAX_CAPACITY();
//    }
}