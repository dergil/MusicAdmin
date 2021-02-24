package mediaDB.domain_logic.listener.display;

import mediaDB.domain_logic.AddressRepository;
import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.enums.MediaTypes;
import mediaDB.domain_logic.file_interfaces.Uploadable;
import mediaDB.domain_logic.files.InteractiveVideoFile;
import mediaDB.simulation.RandomMediadfileInstances;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class GenerateDisplayContentTest {
    GenerateDisplayContent generateDisplayContent;
    RandomMediadfileInstances randomMediadfileInstances;
    InteractiveVideoFile interactiveVideoFile;
    ArrayList<Uploadable> list;
    @BeforeEach
    void setUp(){
        generateDisplayContent = new GenerateDisplayContent();
        randomMediadfileInstances = new RandomMediadfileInstances();
        interactiveVideoFile = randomMediadfileInstances.randomInteractiveVideoFile();
        list = new ArrayList<>();
        list.add(interactiveVideoFile);
    }

    @Test
    void generate() {
        String result = generateDisplayContent.generate(list);
        assertTrue(result.contains(interactiveVideoFile.getUploadDate().toString()));
    }

    @Test
    void generateWithTypeRightType() {
        String result = generateDisplayContent.generate(list, MediaTypes.INTERACTIVEVIDEO.toString());
        assertTrue(result.contains(interactiveVideoFile.getUploadDate().toString()));
    }

    @Test
    void generateWithTypeWrongType() {
        String result = generateDisplayContent.generate(list, MediaTypes.LICENSEDVIDEO.toString());
        assertFalse(result.contains(interactiveVideoFile.getUploadDate().toString()));
    }
}