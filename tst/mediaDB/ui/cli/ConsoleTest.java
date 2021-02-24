package mediaDB.ui.cli;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

/*
Quellen:
https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
https://stackoverflow.com/questions/6415728/junit-testing-with-simulated-user-input
*/

class ConsoleTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    void greeting() {
        System.setOut(new PrintStream(outContent));
        Console.greeting();
        assertTrue(outContent.toString().contains("Willkommen zu Ihrer Mediaverwaltung!"));
        System.setOut(originalOut);
    }

    @Test
    void prompt() {
        String myString = "My string";
        ByteArrayInputStream in = new ByteArrayInputStream(myString.getBytes());
        System.setIn(in);
        assertEquals(myString, Console.prompt("mode"));
    }

    @Test
    void getMode() {
        String myString = ":c";
        ByteArrayInputStream in = new ByteArrayInputStream(myString.getBytes());
        System.setIn(in);
        String result = Console.getMode();
        assertEquals(":c", result);
    }
}