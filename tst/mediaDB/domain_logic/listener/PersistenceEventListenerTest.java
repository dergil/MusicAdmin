package mediaDB.domain_logic.listener;

import mediaDB.IO.Deserialize;
import mediaDB.IO.DeserializeDomainContent;
import mediaDB.IO.RandomAccess;
import mediaDB.IO.Serialize;
import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.file_interfaces.Uploadable;
import mediaDB.domain_logic.files.InteractiveVideoFile;
import mediaDB.net.server.ToClientMessenger;
import mediaDB.routing.events.misc.PersistenceEvent;
import mediaDB.simulation.RandomMediadfileInstances;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class PersistenceEventListenerTest {
    MediaFileRepository mediaFileRepository;
    Serialize serialize;
    Deserialize deserialize;
    DeserializeDomainContent deserializeDomainContent;
    RandomAccess randomAccess;
    PersistenceEventListener persistenceEventListener;
    PersistenceEvent event;
    ArrayList<Uploadable> uploadables;
    RandomMediadfileInstances randomMediadfileInstances;
    InteractiveVideoFile file;
    ToClientMessenger toClientMessenger;

    @BeforeEach
    void setUp() {
        mediaFileRepository = mock(MediaFileRepository.class);
        serialize = mock(Serialize.class);
        deserialize = mock(Deserialize.class);
        deserializeDomainContent = mock(DeserializeDomainContent.class);
        randomAccess = mock(RandomAccess.class);
        toClientMessenger = mock(ToClientMessenger.class);
        persistenceEventListener = new PersistenceEventListener(mediaFileRepository, serialize, deserialize, deserializeDomainContent, randomAccess, toClientMessenger);
        uploadables = new ArrayList<>();
        randomMediadfileInstances = new RandomMediadfileInstances();
        file = randomMediadfileInstances.randomInteractiveVideoFile();
        uploadables.add(file);
        uploadables.add(file);
    }

    @Test
    void onMediaEventSaveJOS() throws IOException {
        setEvent("saveJOS", null);
        persistenceEventListener.onMediaEvent(event);
        verify(serialize).serialize();
    }

    @Test
    void onMediaEventLoadJOS() throws IOException {
        setEvent("loadJOS", null);
        persistenceEventListener.onMediaEvent(event);
        verify(deserialize).deserialize();
    }

    @Test
    void onMediaEventSaveEmptyRAF() throws IOException {
        when(mediaFileRepository.read()).thenReturn(uploadables);
        when(randomAccess.isEmpty()).thenReturn(true);
        setEvent("save", "0");
        persistenceEventListener.onMediaEvent(event);
        verify(randomAccess, times(2)).save(any());
    }

    @Test
    void onMediaEventSaveNonEmptyRAF() throws IOException {
        when(mediaFileRepository.read()).thenReturn(uploadables);
        when(mediaFileRepository.findByAddress(anyString())).thenReturn(file);
        when(randomAccess.isEmpty()).thenReturn(false);
        setEvent("save", "1");
        persistenceEventListener.onMediaEvent(event);
        verify(randomAccess, times(1)).save(any());
    }

    @Test
    void onMediaEventLoadRAFAccessed() throws IOException, ClassNotFoundException {
        setEvent("load", "1");
        persistenceEventListener.onMediaEvent(event);
        verify(randomAccess).load(any());
    }

    @Test
    void onMediaEventLoadMediaRepoAccessed() throws IOException, ClassNotFoundException {
        setEvent("load", "1");
        when(randomAccess.load(any())).thenReturn(file);
        persistenceEventListener.onMediaEvent(event);
        verify(mediaFileRepository).create(any());
    }

    @Test
    void supports() {
        assertTrue(persistenceEventListener.supports(PersistenceEvent.class));
    }

    private void setEvent(String command, String option){
        event = new PersistenceEvent(this, command, option, "sender");
    }
}