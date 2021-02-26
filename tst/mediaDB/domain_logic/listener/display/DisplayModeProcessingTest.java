package mediaDB.domain_logic.listener.display;

import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.enums.MediaTypes;
import mediaDB.domain_logic.enums.Tag;
import mediaDB.domain_logic.file_interfaces.Uploadable;
import mediaDB.domain_logic.files.InteractiveVideoFile;
import mediaDB.domain_logic.producer.Producer;
import mediaDB.domain_logic.producer.ProducerRepository;
import mediaDB.domain_logic.producer.Uploader;
import mediaDB.simulation.RandomMediadfileInstances;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DisplayModeProcessingTest {
    DisplayModeProcessing displayModeProcessing;
    MediaFileRepository mediaFileRepository;
    ProducerRepository producerRepository;
    GenerateDisplayContent generateDisplayContent;
    RandomMediadfileInstances randomMediadfileInstances;
    InteractiveVideoFile interactiveVideoFile;
    ArrayList<Uploadable> uploadables;
    ArrayList<Uploader> uploaders;
    @BeforeEach
    void setUp(){
        mediaFileRepository = mock(MediaFileRepository.class);
        producerRepository = mock(ProducerRepository.class);
        generateDisplayContent = mock(GenerateDisplayContent.class);
        randomMediadfileInstances = new RandomMediadfileInstances();
        interactiveVideoFile = randomMediadfileInstances.randomInteractiveVideoFile();
        uploadables = new ArrayList<>();
        uploadables.add(interactiveVideoFile);
        uploadables.add(interactiveVideoFile);
        displayModeProcessing = new DisplayModeProcessing(generateDisplayContent, producerRepository, mediaFileRepository);
        when(mediaFileRepository.read()).thenReturn(uploadables);
    }

    @Test
    void uploaderNoFilesForProducer() {
        uploaders = new ArrayList<>();
        uploaders.add(interactiveVideoFile.getUploader());
        uploaders.add(new Producer("Gilbert"));
        when(producerRepository.getProducers()).thenReturn(uploaders);
        String result = displayModeProcessing.uploader();
        assertTrue(result.contains("Gilbert=0"));
    }

    @Test
    void uploaderTwoFilesForProducer() {
        uploaders = new ArrayList<>();
        uploaders.add(interactiveVideoFile.getUploader());
        uploaders.add(new Producer("Gilbert"));
        when(producerRepository.getProducers()).thenReturn(uploaders);
        String result = displayModeProcessing.uploader();
        String producerName = interactiveVideoFile.getUploader().toString();
        producerName = producerName.replace("Producer{name='", "");
        producerName = producerName.replace("'}", "");
        assertTrue(result.contains(producerName));
    }

    @Test
    void contentWithoutType() {
        displayModeProcessing.content(uploadables);
        verify(generateDisplayContent).generate(uploadables);
    }

    @Test
    void contentWithType() {
        displayModeProcessing.content(uploadables, MediaTypes.INTERACTIVEVIDEO.toString());
        verify(generateDisplayContent).generate(uploadables, MediaTypes.INTERACTIVEVIDEO.toString());
    }

    @Test
    void assignedTags() {
        Collection<Tag> assigned = interactiveVideoFile.getTags();
        assertTrue(displayModeProcessing.tag("i").contains(assigned.iterator().next().toString()));
    }



    @Test
    void nonAssignedTags() {
        Collection<Tag> assigned = interactiveVideoFile.getTags();
        Iterator<Tag> iterator = assigned.iterator();
        String assignedTag = iterator.next().toString();
        assertFalse(displayModeProcessing.tag("e").contains(assignedTag));
    }
}