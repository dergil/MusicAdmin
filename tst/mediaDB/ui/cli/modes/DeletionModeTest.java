package mediaDB.ui.cli.modes;

import mediaDB.routing.EventHandler;
import mediaDB.routing.events.misc.StringEvent;
import mediaDB.ui.cli.Console;
import mediaDB.ui.cli.EventFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class DeletionModeTest {
    EventHandler eventHandler;
    EventFactory eventFactoryMock;
    EventFactory eventFactory;
    DeletionMode deletionMode;


    @BeforeEach
    void setUp() {
        eventHandler = mock(EventHandler.class);
        eventFactoryMock = mock(EventFactory.class);
        eventFactory = new EventFactory();
        deletionMode = new DeletionMode(eventHandler, eventFactoryMock);
    }

    @Test
    void inputZero() throws IOException {
        try (MockedStatic<Console> console = mockStatic(mediaDB.ui.cli.Console.class)) {
            console.when(() -> Console.prompt(anyString())).thenReturn("0");
            deletionMode.start();
            verifyNoInteractions(eventFactoryMock);
        }
    }

    @Test
    void producerDeletionEventCreated() throws IOException {
        try (MockedStatic<mediaDB.ui.cli.Console> console = mockStatic(mediaDB.ui.cli.Console.class)) {
            console.when(() -> Console.prompt(anyString())).thenReturn("hro");
            deletionMode.start();
            verify(eventFactoryMock).stringEvent("Deletion", "producer", "hro");
        }
    }

    @Test
    void producerDeletionEventSent() throws IOException {
        deletionMode = new DeletionMode(eventHandler, eventFactory);
        try (MockedStatic<mediaDB.ui.cli.Console> console = mockStatic(mediaDB.ui.cli.Console.class)) {
            console.when(() -> Console.prompt(anyString())).thenReturn("hro");
            deletionMode.start();
            verify(eventHandler).handle(any(StringEvent.class));
        }
    }

    @Test
    void mediaFileDeletionEventCreated() throws IOException {
        try (MockedStatic<mediaDB.ui.cli.Console> console = mockStatic(mediaDB.ui.cli.Console.class)) {
            console.when(() -> Console.prompt(anyString())).thenReturn("123");
            deletionMode.start();
            verify(eventFactoryMock).stringEvent("Deletion", "address", "123");
        }
    }

    @Test
    void mediaFileDeletionEventSent() throws IOException {
        deletionMode = new DeletionMode(eventHandler, eventFactory);
        try (MockedStatic<mediaDB.ui.cli.Console> console = mockStatic(mediaDB.ui.cli.Console.class)) {
            console.when(() -> Console.prompt(anyString())).thenReturn("123");
            deletionMode.start();
            verify(eventHandler).handle(any(StringEvent.class));
        }
    }

//    TODO: Vill fragen
//    private void eventSent(CLIMode cliMode, NetworkEvent event, String input){
//        try (MockedStatic<mediaDB.ui.cli.Console> console = mockStatic(mediaDB.ui.cli.Console.class)) {
//            console.when(() -> Console.prompt(anyString())).thenReturn("123");
//            cliMode.start();
//            verify(eventBusMock).handle(event.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    }