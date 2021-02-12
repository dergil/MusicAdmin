//package mediaDB.domain_logic;
//
//import mediaDB.EventFactory;
//import mediaDB.MediaTypesTest;
//import mediaDB.routing.LicensedAudioVideoEvent;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class LicensedAudioVideoEventListenerTest {
//    Administration administration = new Administration();
//    AdminListeners adminListeners = administration.getAdminListeners();
//    LicensedAudioVideoEventListener licensedAudioVideoEventListener;
//    EventFactory eventFactory = new EventFactory();
//    LicensedAudioVideoEvent licensedAudioVideoEvent;
//
//    @BeforeEach
//    void setUp() throws IOException {
//        licensedAudioVideoEvent = eventFactory.licensedAudioVideoEvent();
//        licensedAudioVideoEventListener = new LicensedAudioVideoEventListener(adminListeners);
//        administration.addProducer("Gilbert");
//    }
//
//    @Test
//    void onMediaEvent() throws IOException {
//        licensedAudioVideoEventListener.onMediaEvent(licensedAudioVideoEvent);
//        assertEquals(MediaTypesTest.LICENSEDAUDIOVIDEO.toString(), administration.getMediaFileRepository().read().get(0).getFileType());
//    }
//
//    @Test
//    void onMediaEventNoCapacity() throws IOException {
//        administration.getMediaFileRepository().setMAX_CAPACITY(new BigDecimal(10000));
//        licensedAudioVideoEventListener.onMediaEvent(licensedAudioVideoEvent);
//        assertEquals(0, administration.getMediaFileRepository().read().size());
//    }
//}