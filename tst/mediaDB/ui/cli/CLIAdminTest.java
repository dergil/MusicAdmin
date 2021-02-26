package mediaDB.ui.cli;

import javafx.collections.ListChangeListener;
import mediaDB.routing.events.misc.PersistenceEvent;
import mediaDB.ui.cli.modes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class CLIAdminTest {
    InsertMode insertMode;
    DisplayMode displayMode;
    DeletionMode deleteMode;
    ChangeMode changeMode;
    ConfigMode configMode;
    PersistenceMode persistenceMode;
    CLIAdmin cliAdmin;

//    @BeforeEach
//    void setUp() {
//        insertMode = mock(InsertMode.class);
//        displayMode = mock(DisplayMode.class);
//        deleteMode = mock(DeletionMode.class);
//        changeMode = mock(ChangeMode.class);
//        configMode = mock(ConfigMode.class);
//        persistenceMode = mock(PersistenceMode.class);
//        cliAdmin = new CLIAdmin(insertMode, displayMode, deleteMode, changeMode, configMode, persistenceMode);
//    }

    //    Cannot be tested, since system exits before verification
//    Possible solution: https://stefanbirkner.github.io/system-rules/
//    @Test
//    void startInputZero() {
//        try (MockedStatic<Console> console = mockStatic(mediaDB.ui.cli.Console.class)) {
//            console.when(Console::getMode).thenReturn("0");
////            cliAdmin.start();
////            verifyNoInteractions(insertMode, displayMode, deleteMode, changeMode, configMode, persistenceMode);
//        }
//    }
}