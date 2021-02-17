package mediaDB.ui.cli.modes;

import mediaDB.net.client.ClientEventBus;
import mediaDB.routing.EventHandler;
import mediaDB.routing.EventListener;
import mediaDB.routing.NetworkEvent;
import mediaDB.routing.events.misc.StringEvent;
import mediaDB.ui.cli.Console;
import mediaDB.ui.cli.EventFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;
import java.io.ObjectOutputStream;

import static org.mockito.Mockito.*;

class ChangeModeTest {
//    EventListener<NetworkEvent> eventBus;
//    EventListener<NetworkEvent> spyEventBus;
    EventHandler eventHandler;
    EventHandler spyEventHandler;
    
    EventFactory eventFactory;
    EventFactory spyEventFactory;
    ChangeMode changeMode;


    @BeforeEach
    void setUp() {
        eventHandler = new EventHandler();
        spyEventHandler = spy(eventHandler);
        eventFactory = new EventFactory();
        spyEventFactory = spy(eventFactory);
        changeMode = new ChangeMode(spyEventHandler, spyEventFactory);
    }

    @Test
    void inputZero() throws IOException {
        try (MockedStatic<mediaDB.ui.cli.Console> console = mockStatic(mediaDB.ui.cli.Console.class)) {
            console.when(() -> Console.prompt(anyString())).thenReturn("0");
            changeMode.start();
            verifyNoInteractions(spyEventFactory);
        }
    }

    @Test
    void inputNotNumeric() throws IOException {
        try (MockedStatic<mediaDB.ui.cli.Console> console = mockStatic(mediaDB.ui.cli.Console.class)) {
            console.when(() -> Console.prompt(anyString())).thenReturn("12a");
            changeMode.start();
            verify(spyEventHandler, never()).handle(any());
        }
    }

    @Test
    void eventCreated() throws IOException {
        try (MockedStatic<mediaDB.ui.cli.Console> console = mockStatic(mediaDB.ui.cli.Console.class)) {
            console.when(() -> Console.prompt(anyString())).thenReturn("123");
            changeMode.start();
            verify(spyEventFactory).stringEvent(anyString(), anyString(), anyString());
        }
    }

    @Test
    void eventSent() throws IOException {
        try (MockedStatic<mediaDB.ui.cli.Console> console = mockStatic(mediaDB.ui.cli.Console.class)) {
            console.when(() -> Console.prompt(anyString())).thenReturn("123");
            changeMode.start();
            verify(spyEventHandler).handle(any(StringEvent.class));
        }
    }
}
