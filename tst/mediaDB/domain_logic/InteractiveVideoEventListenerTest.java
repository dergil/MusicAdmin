//package mediaDB.domain_logic;
//
//import mediaDB.EventFactory;
//import mediaDB.MediaTypesTest;
//import mediaDB.routing.events.files.InteractiveVideoEvent;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class InteractiveVideoEventListenerTest {
//    Administration administration = new Administration();
//    AdminListeners adminListeners = administration.getAdminListeners();
//    InteractiveVideoEventListener interactiveVideoEventListener;
//    EventFactory eventFactory = new EventFactory();
//    InteractiveVideoEvent interactiveVideoEvent;
//
//    @BeforeEach
//    void setUp() throws IOException {
//        interactiveVideoEvent = eventFactory.interactiveVideoEvent();
//        interactiveVideoEventListener = new InteractiveVideoEventListener(adminListeners);
//        administration.addProducer("Gilbert");
//    }
//
//    @Test
//    void onMediaEvent() throws IOException {
//        interactiveVideoEventListener.onMediaEvent(interactiveVideoEvent);
//        assertEquals(MediaTypesTest.INTERACTIVEVIDEO.toString(), administration.getMediaFileRepository().read().get(0).getFileType());
//    }
//
//    @Test
//    void onMediaEventNoCapacity() throws IOException {
//        administration.getMediaFileRepository().setMAX_CAPACITY(new BigDecimal(10000));
//        interactiveVideoEventListener.onMediaEvent(interactiveVideoEvent);
//        assertEquals(0, administration.getMediaFileRepository().read().size());
//    }
//}