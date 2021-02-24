package mediaDB.domain_logic.producer;

import mediaDB.domain_logic.producer.Producer;
import mediaDB.domain_logic.producer.ProducerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProducerRepositoryTest {
    ProducerRepository producerRepository;
    Producer gilbert = new Producer("Gilbert");

    @BeforeEach
    void setUp() {
        producerRepository = new ProducerRepository();

    }

    @Test
    void addProducerPositive() {
        producerRepository.addProducer(gilbert);
        assertEquals("Gilbert", producerRepository.getProducers().get(0).getName());
    }

    @Test
    void addProducerDuplicateDifferentObject() {
        producerRepository.addProducer(new Producer("Gilbert"));
        producerRepository.addProducer(new Producer("Gilbert"));
        assertEquals(1, producerRepository.getSize());
    }

    @Test
    void addProducerDuplicateSameObject() {
        producerRepository.addProducer(gilbert);
        producerRepository.addProducer(gilbert);
        assertEquals(1, producerRepository.getSize());
    }

    @Test
    void removeProducerString() {
        producerRepository.addProducer(gilbert);
        producerRepository.removeProducer("Gilbert");
        assertEquals(0, producerRepository.getSize());
    }

    @Test
    void testRemoveProducerInstance() {
        producerRepository.addProducer(gilbert);
        producerRepository.removeProducer(gilbert);
        assertEquals(0, producerRepository.getSize());
    }

    @Test
    void containsString() {
        producerRepository.addProducer(gilbert);
        assertTrue(producerRepository.contains("Gilbert"));
    }

    @Test
    void containsInstance() {
        producerRepository.addProducer(gilbert);
        assertTrue(producerRepository.contains(gilbert));
    }
}