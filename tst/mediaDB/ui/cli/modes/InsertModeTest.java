package mediaDB.ui.cli.modes;

import mediaDB.ExampleInput;
import mediaDB.routing.EventHandler;
import mediaDB.routing.events.files.InteractiveVideoEvent;
import mediaDB.routing.events.misc.ProducerEvent;
import mediaDB.ui.cli.Console;
import mediaDB.ui.cli.EventFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

//TODO: Console input nicht über statisches mocken, sondern über input normal geben;
// weiteres testen?
class InsertModeTest {
    EventHandler eventHandler;
    EventFactory eventFactoryMock;
    EventFactory eventFactory;
    InsertMode insertMode;

    @BeforeEach
    void setUp() {
        eventHandler = mock(EventHandler.class);
        eventFactoryMock = mock(EventFactory.class);
        eventFactory = new EventFactory();
    }

    @Test
    void inputZero() throws IOException {
        insertMode = new InsertMode(eventHandler, eventFactoryMock);
        try (MockedStatic<Console> console = mockStatic(mediaDB.ui.cli.Console.class)) {
            console.when(() -> Console.prompt(anyString())).thenReturn("0");
            insertMode.start();
            verifyNoInteractions(eventFactoryMock);
        }
    }

    @Test
    void startValidProducer() {
        insertMode = new InsertMode(eventHandler, eventFactory);
        try (MockedStatic<Console> console = mockStatic(mediaDB.ui.cli.Console.class)) {
            console.when(() -> Console.prompt(anyString())).thenReturn("Gilbert");
            insertMode.start();
            verify(eventHandler).handle(any(ProducerEvent.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void startInvalidProducer() {
        insertMode = new InsertMode(eventHandler, eventFactory);
        try (MockedStatic<Console> console = mockStatic(mediaDB.ui.cli.Console.class)) {
            console.when(() -> Console.prompt(anyString())).thenReturn("Audio Gilbert");
            insertMode.start();
            verify(eventHandler, never()).handle(any(ProducerEvent.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void startValidFileInput() {
        insertMode = new InsertMode(eventHandler, eventFactory);
        try (MockedStatic<Console> console = mockStatic(mediaDB.ui.cli.Console.class)) {
            console.when(() -> Console.prompt(anyString())).thenReturn(ExampleInput.INTERACTIVEVIDEO_0.toString());
            insertMode.start();
            verify(eventHandler).handle(any(InteractiveVideoEvent.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void startInvalidFileInput() {
        insertMode = new InsertMode(eventHandler, eventFactory);
        try (MockedStatic<Console> console = mockStatic(mediaDB.ui.cli.Console.class)) {
            console.when(() -> Console.prompt(anyString())).thenReturn("InteractiveVideo Produzent1 Lifestyle,News string 3600 DWT 640 480 Abstimmung");
            insertMode.start();
            verifyNoInteractions(eventHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}