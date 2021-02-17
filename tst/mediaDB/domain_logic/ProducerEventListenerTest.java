package mediaDB.domain_logic;

import mediaDB.domain_logic.listener.ProducerEventListener;
import mediaDB.domain_logic.producer.ProducerRepository;
import mediaDB.routing.events.misc.ProducerEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class ProducerEventListenerTest {
    ProducerEventListener producerEventListener;
    ProducerRepository producerRepository;
    ProducerRepository spyProducerRepository;

    @BeforeEach
    void setUp() {
        producerRepository = new ProducerRepository();
        spyProducerRepository = spy(producerRepository);
        producerEventListener = new ProducerEventListener(spyProducerRepository);
    }

    @Test
    void onMediaEvent() throws IOException {
        ProducerEvent event = mock(ProducerEvent.class);
        when(event.getName()).thenReturn("Gilbert");
        when(event.getCommand()).thenReturn("add");
        producerEventListener.onMediaEvent(event);
        verify(spyProducerRepository).addProducer("Gilbert");
    }
}