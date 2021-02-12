//package mediaDB.domain_logic;
//
//import mediaDB.EventFactory;
//import mediaDB.MediaTypesTest;
//import mediaDB.routing.LicensedVideoEvent;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class LicensedVideoEventListenerTest {
//    Administration administration = new Administration();
//    AdminListeners adminListeners = administration.getAdminListeners();
//    LicensedVideoEventListener licensedVideoEventListener;
//    EventFactory eventFactory = new EventFactory();
//    LicensedVideoEvent licensedVideoEvent;
//
//    @BeforeEach
//    void setUp() throws IOException {
//        licensedVideoEvent = eventFactory.licensedVideoEvent();
//        licensedVideoEventListener = new LicensedVideoEventListener(adminListeners);
//        administration.addProducer("Gilbert");
//    }
//
//    @Test
//    void onMediaEvent() throws IOException {
//        licensedVideoEventListener.onMediaEvent(licensedVideoEvent);
//        assertEquals(MediaTypesTest.LICENSEDVIDEO.toString(), administration.getMediaFileRepository().read().get(0).getFileType());
//    }
//
//    @Test
//    void onMediaEventNoCapacity() throws IOException {
//        administration.getMediaFileRepository().setMAX_CAPACITY(new BigDecimal(10000));
//        licensedVideoEventListener.onMediaEvent(licensedVideoEvent);
//        assertEquals(0, administration.getMediaFileRepository().read().size());
//    }
//}