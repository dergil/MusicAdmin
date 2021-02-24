package mediaDB.domain_logic.producer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProducerTest {
    Producer producer;

    @BeforeEach
    void setUp() {
        producer = new Producer("Gilbert");

    }

    @Test
    void setName() {
        producer.setName("NotGilbert");
        assertEquals("NotGilbert", producer.getName());
    }

    @Test
    void getName() {
        assertEquals("Gilbert", producer.getName());
    }

    @Test
    void testToString() {
        assertTrue(producer.toString().contains("Gilbert"));
    }
}