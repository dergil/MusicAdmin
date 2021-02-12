package mediaDB.ui.cli;

import mediaDB.ExampleInput;
import mediaDB.PreliminaryEventSetup;
import mediaDB.domain_logic.Administration;
import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.SizeObservable;
import mediaDB.domain_logic.TagObservable;
import mediaDB.tempserver.ServerToClientMessenger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.mockito.Mockito.*;

class DisplayModeTest {
//    ByteArrayInputStream testIn;
//
//    MediaFileRepository mediaFileRepository = new MediaFileRepository(mock(ServerToClientMessenger.class), mock(SizeObservable.class), mock(TagObservable.class));
//    EventHandlers eventHandlers = new EventHandlers();
//    InsertMode insertMode = new InsertMode(eventHandlers);
//    DisplayMode displayMode = new DisplayMode(eventHandlers);
//    DeletionMode deletionMode = new DeletionMode(eventHandlers);
//    ChangeMode changeMode = new ChangeMode(eventHandlers);
//    ConfigMode configMode = new ConfigMode(eventHandlers, mediaFileRepository);
//    CLIAdmin cliAdmin = new CLIAdmin(insertMode, displayMode, deletionMode, changeMode, configMode);
//
//    @BeforeEach
//    void setUp() throws IOException {
////        preliminaryEventSetup.setup();
//
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void start() throws IOException {
//        administration.addProducer(ExampleInput.PRODUCER_0.toString());
//        setInput(ExampleInput.INTERACTIVEVIDEO_0.toString());
//        cliAdmin.getInsertMode().getAndVerifyInput();
//        assertEquals(1, administration.getMediaFileRepository().read().size());
//    }
//
//    @Test
//    void testStream() throws IOException {
//        String producer = ExampleInput.PRODUCER_0.toString();
//        setInput(producer);
//        cliAdmin.getInsertMode().getAndVerifyInput();
//        assertEquals(1, administration.getProducerRepository().getSize());
//    }
//
//    private void setInput(String input){
//        testIn = new ByteArrayInputStream(input.getBytes());
//        System.setIn(testIn);
//    }
}