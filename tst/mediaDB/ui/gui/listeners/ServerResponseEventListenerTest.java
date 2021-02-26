package mediaDB.ui.gui.listeners;

import for_testing.EventFactoryForTesting;
import javafx.scene.control.ListView;
import mediaDB.routing.EventHandler;
import mediaDB.routing.events.misc.DisplayEvent;
import mediaDB.routing.events.misc.ServerResponseEvent;
import mediaDB.ui.cli.EventFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ServerResponseEventListenerTest {
//    ListView<String> uploadsListView;
//    ListView<String> producerListView;
//    ListView<String> displayListView;
//    EventFactory eventFactory;
//    mediaDB.routing.EventHandler eventHandler;
//    ServerResponseEventListener serverResponseEventListener;
//
//    @BeforeEach
//    void setUp() {
//        uploadsListView = spy(ListView.class);
//        producerListView = spy(ListView.class);
//        displayListView = spy(ListView.class);
//        eventFactory = mock(EventFactory.class);
//        eventHandler = mock(EventHandler.class);
//        serverResponseEventListener = new ServerResponseEventListener(uploadsListView, producerListView, displayListView, eventFactory, eventHandler);
//    }
//
////    Not all types can be tested, since mockito does not support mocking ListView
//    @Test
//    void onMediaEvent() throws IOException {
//        ServerResponseEvent event = new ServerResponseEvent(this, "Test", "gui", "DataChange");
//        serverResponseEventListener.onMediaEvent(event);
//        verify(eventHandler).handle(any(DisplayEvent.class));
//    }
//
//    @Test
//    void supports() {
//    }
}