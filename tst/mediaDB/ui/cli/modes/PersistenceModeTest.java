package mediaDB.ui.cli.modes;

import mediaDB.routing.EventHandler;
import mediaDB.routing.events.misc.PersistenceEvent;
import mediaDB.ui.cli.Console;
import mediaDB.ui.cli.EventFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class PersistenceModeTest {
    EventHandler eventHandler;
    EventFactory eventFactoryMock;
    EventFactory eventFactory;
    PersistenceMode persistenceMode;

    @BeforeEach
    void setUp() {
        eventHandler = mock(EventHandler.class);
        eventFactoryMock = mock(EventFactory.class);
        eventFactory = new EventFactory("ipsum");
    }

    @Test
    void startValidInput() {
        persistenceMode = new PersistenceMode(eventHandler, eventFactory);
        try (MockedStatic<Console> console = mockStatic(mediaDB.ui.cli.Console.class)) {
            console.when(() -> Console.prompt(anyString())).thenReturn("loadJOS");
            persistenceMode.start();
            verify(eventHandler).handle(any(PersistenceEvent.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void startInvalidInput() {
        persistenceMode = new PersistenceMode(eventHandler, eventFactory);
        try (MockedStatic<Console> console = mockStatic(mediaDB.ui.cli.Console.class)) {
            console.when(() -> Console.prompt(anyString())).thenReturn("loadJOSGilbert");
            persistenceMode.start();
            verifyNoInteractions(eventHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}