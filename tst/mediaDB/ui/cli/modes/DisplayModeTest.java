package mediaDB.ui.cli.modes;

import mediaDB.routing.EventHandler;
import mediaDB.routing.events.misc.DisplayEvent;
import mediaDB.ui.cli.Console;
import mediaDB.ui.cli.EventFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

//TODO: generifizieren
class DisplayModeTest {
    EventHandler eventHandler;
    EventFactory eventFactoryMock;
    EventFactory eventFactory;
    DisplayMode displayMode;

    @BeforeEach
    void setUp() {
        eventHandler = mock(EventHandler.class);
        eventFactoryMock = mock(EventFactory.class);
        eventFactory = new EventFactory();
    }

    @Test
    void inputZero() throws IOException {
        displayMode = new DisplayMode(eventHandler, eventFactoryMock);
        try (MockedStatic<Console> console = mockStatic(mediaDB.ui.cli.Console.class)) {
            console.when(() -> Console.prompt(anyString())).thenReturn("0");
            displayMode.start();
            verifyNoInteractions(eventFactoryMock);
        }
    }

    @Test
    void contentEventCreated() {
        displayMode = new DisplayMode(eventHandler, eventFactoryMock);
        try (MockedStatic<mediaDB.ui.cli.Console> console = mockStatic(mediaDB.ui.cli.Console.class)) {
            console.when(() -> Console.prompt(anyString())).thenReturn("content audio");
            displayMode.start();
            verify(eventFactoryMock).displayEvent("content", "audio");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void contentEventSent() {
        displayMode = new DisplayMode(eventHandler, eventFactory);
        try (MockedStatic<mediaDB.ui.cli.Console> console = mockStatic(mediaDB.ui.cli.Console.class)) {
            console.when(() -> Console.prompt(anyString())).thenReturn("content audio");
            displayMode.start();
            verify(eventHandler).handle(any(DisplayEvent.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void upoloderEventCreated() {
        displayMode = new DisplayMode(eventHandler, eventFactoryMock);
        try (MockedStatic<mediaDB.ui.cli.Console> console = mockStatic(mediaDB.ui.cli.Console.class)) {
            console.when(() -> Console.prompt(anyString())).thenReturn("uploader");
            displayMode.start();
            verify(eventFactoryMock).displayEvent("uploader", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void uploaderEventSent() {
        displayMode = new DisplayMode(eventHandler, eventFactory);
        try (MockedStatic<mediaDB.ui.cli.Console> console = mockStatic(mediaDB.ui.cli.Console.class)) {
            console.when(() -> Console.prompt(anyString())).thenReturn("uploader");
            displayMode.start();
            verify(eventHandler).handle(any(DisplayEvent.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void tagEventCreated() {
        displayMode = new DisplayMode(eventHandler, eventFactoryMock);
        try (MockedStatic<mediaDB.ui.cli.Console> console = mockStatic(mediaDB.ui.cli.Console.class)) {
            console.when(() -> Console.prompt(anyString())).thenReturn("tag e");
            displayMode.start();
            verify(eventFactoryMock).displayEvent("tag", "e");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void tagEventSent() {
        displayMode = new DisplayMode(eventHandler, eventFactory);
        try (MockedStatic<mediaDB.ui.cli.Console> console = mockStatic(mediaDB.ui.cli.Console.class)) {
            console.when(() -> Console.prompt(anyString())).thenReturn("tag e");
            displayMode.start();
            verify(eventHandler).handle(any(DisplayEvent.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}