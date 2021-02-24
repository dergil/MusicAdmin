package mediaDB.domain_logic.listener;

import mediaDB.domain_logic.listener.ProducerEventListener;
import mediaDB.domain_logic.producer.Producer;
import mediaDB.domain_logic.producer.ProducerRepository;
import mediaDB.net.server.ToClientMessenger;
import mediaDB.routing.events.misc.ProducerEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ProducerEventListenerTest {
    ProducerEventListener producerEventListener;
    ProducerRepository producerRepository;
    ProducerRepository spyProducerRepository;

    @BeforeEach
    void setUp() {
        producerRepository = new ProducerRepository();
        spyProducerRepository = spy(producerRepository);
        producerEventListener = new ProducerEventListener(spyProducerRepository, new ToClientMessenger());
    }

    @Test
    void onMediaEventAdd() throws IOException {
        ProducerEvent event = mock(ProducerEvent.class);
        when(event.getName()).thenReturn("Gilbert");
        when(event.getCommand()).thenReturn("add");
        producerEventListener.onMediaEvent(event);
        verify(spyProducerRepository).addProducer(any(Producer.class));
    }

    @Test
    void onMediaEventRemove() throws IOException {
        ProducerEvent event = mock(ProducerEvent.class);
        when(event.getName()).thenReturn("Gilbert");
        when(event.getCommand()).thenReturn("remove");
        producerEventListener.onMediaEvent(event);
        verify(spyProducerRepository).removeProducer("Gilbert");
    }

    @Test
    void supports() {
        assertTrue(producerEventListener.supports(ProducerEvent.class));
    }
}