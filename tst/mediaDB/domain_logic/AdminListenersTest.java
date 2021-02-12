package mediaDB.domain_logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//TODO: nicht fertig, warte auf Antwort bzgl. Wrapper Methoden ohne eigene Logik
class AdminListenersTest {
//    Administration administration;
//    Administration spyAdmininstration;
//    MediaFileRepository mediaFileRepository;
//    MediaFileRepository spyMediaFileRepository;
//    ProducerRepository producerRepository;
//    ProducerRepository spyProducerRepository;
//    AdminListeners adminListeners;
//
//
//
//
//    @BeforeEach
//    void setUp() {
//        administration = new Administration();
//        spyAdmininstration = spy(administration);
//        mediaFileRepository = new MediaFileRepository();
//        spyMediaFileRepository = spy(mediaFileRepository);
//        producerRepository = new ProducerRepository();
//        spyProducerRepository = spy(producerRepository);
//        when(spyAdmininstration.getMediaFileRepository()).thenReturn(spyMediaFileRepository);
//        when(spyAdmininstration.getProducerRepository()).thenReturn(spyProducerRepository);
//        adminListeners = new AdminListeners(spyAdmininstration);
//    }
//
//    @Test
//    void capacityAvailible() throws IOException {
//        BigDecimal size = new BigDecimal(100000);
//        adminListeners.capacityAvailible(size);
//        verify(spyMediaFileRepository).capacityAvailable(size);
//    }
//
//    @Test
//    void calculateSize() {
////        TODO: Tests sollen nicht tautologisch sein, ihn fragen
//    }
//
//    @Test
//    void existingProducer() throws IOException {
//        Producer gilbert = new Producer("Gilbert");
//        adminListeners.existingProducer(gilbert);
//        verify(spyProducerRepository).contains(gilbert.getName());
//    }
//
//    @Test
//    void producersWithNumberOfFiles() {
//        adminListeners.producersWithNumberOfFiles();
//
//    }
//
//    @Test
//    void contentForDisplay() {
//    }
//
//    @Test
//    void testContentForDisplay() {
//    }
//
//    @Test
//    void tagsForDisplay() {
//    }
//
//    @Test
//    void addProducer() {
//    }
//
//    @Test
//    void removeFileByAddress() {
//    }
//
//    @Test
//    void removeProducer() {
//    }
//
//    @Test
//    void increaseAccessCount() {
//    }
}