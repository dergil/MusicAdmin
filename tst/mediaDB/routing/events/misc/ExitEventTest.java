package mediaDB.routing.events.misc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExitEventTest {
    ExitEvent exitEvent;
    String sender = "sender";

    @BeforeEach
    void setUp() {
        exitEvent = new ExitEvent(this, sender);
    }

    @Test
    void getEventName() {
        assertEquals("ExitEvent", exitEvent.getEventName());
    }

    @Test
    void getSender() {
        assertEquals(sender, exitEvent.getSender());
    }
}