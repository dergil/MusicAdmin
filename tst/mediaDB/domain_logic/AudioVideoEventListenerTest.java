//package mediaDB.domain_logic;
//
//import mediaDB.EventFactory;
//import mediaDB.MediaTypesTest;
//import mediaDB.routing.AudioVideoEvent;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class AudioVideoEventListenerTest {
//    Administration administration = new Administration();
//    AdminListeners adminListeners = administration.getAdminListeners();
//    AudioVideoEventListener audioVideoEventListener;
//    EventFactory eventFactory = new EventFactory();
//    AudioVideoEvent audioVideoEvent;
//
//    @BeforeEach
//    void setUp() throws IOException {
//        audioVideoEvent = eventFactory.audioVideoEvent();
//        audioVideoEventListener = new AudioVideoEventListener(adminListeners);
//        administration.addProducer("Gilbert");
//    }
//
//    @Test
//    void onMediaEvent() throws IOException {
//        audioVideoEventListener.onMediaEvent(audioVideoEvent);
//        assertEquals(MediaTypesTest.AUDIOVIDEO.toString(), administration.getMediaFileRepository().read().get(0).getFileType());
//    }
//
//    @Test
//    void onMediaEventNoCapacity() throws IOException {
//        administration.getMediaFileRepository().setMAX_CAPACITY(new BigDecimal(10000));
//        audioVideoEventListener.onMediaEvent(audioVideoEvent);
//        assertEquals(0, administration.getMediaFileRepository().read().size());
//    }
//}