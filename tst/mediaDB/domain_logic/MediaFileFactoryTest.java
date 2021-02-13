package mediaDB.domain_logic;

import mediaDB.MediaTypesTest;
import mediaDB.tempserver.ToClientMessenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

//TODO: isolieren, damit es keine integration tests sind
class MediaFileFactoryTest {
    MediaFileRepository mediaFileRepository;
    MediaFileFactory mediaFileFactory;
    AddressRepository addressRepository = mock(AddressRepository.class);
     int samplingRate = 300;
     int width = 400;
     int height = 500;
     String encoding = "DWT";
     List<Tag> al = new ArrayList<Tag>();
     Collection<Tag> tags = al;
     String holder = "Sony";
     long bitrate = 350;
     Duration length = Duration.ofSeconds(667);
     Uploader uploader = new Producer("Gilbert");
     String type = "Abstimmung";
     BigDecimal size = new BigDecimal(length.getSeconds()*bitrate);


    @BeforeEach
    void setUp() {
        mediaFileRepository = new MediaFileRepository(new ToClientMessenger(), new SizeObservable(), new TagObservable());
        mediaFileFactory = new MediaFileFactory(mediaFileRepository, addressRepository);
        when(addressRepository.nextAddress()).thenReturn("1");
        al.add(Tag.Animal);
    }

    @Test
    void createAudioFile() {
        mediaFileFactory.createAudioFile(MediaTypesTest.AUDIO.toString(), samplingRate, encoding, tags, bitrate, length, size, uploader);
        assertEquals(mediaFileRepository.read().get(0).getFileType(), MediaTypesTest.AUDIO.toString());
    }

    @Test
    void createAudioVideoFile() {
        mediaFileFactory.createAudioVideoFile(MediaTypesTest.AUDIOVIDEO.toString(), samplingRate, width, height, encoding, tags, bitrate, length, size, uploader);
        assertEquals(mediaFileRepository.read().get(0).getFileType(), MediaTypesTest.AUDIOVIDEO.toString());
    }

    @Test
    void createInteractiveVideoFile() {
        mediaFileFactory.createInteractiveVideoFile(MediaTypesTest.INTERACTIVEVIDEO.toString(), type, width, height, encoding, tags, bitrate, length, size, uploader);
        assertEquals(mediaFileRepository.read().get(0).getFileType(), MediaTypesTest.INTERACTIVEVIDEO.toString());
    }

    @Test
    void createLicensedAudioFile() {
        mediaFileFactory.createLicensedAudioFile(MediaTypesTest.LICENSEDAUDIO.toString(), samplingRate, encoding, tags, holder, bitrate, length, size, uploader);
        assertEquals(mediaFileRepository.read().get(0).getFileType(), MediaTypesTest.LICENSEDAUDIO.toString());
    }

    @Test
    void createLicensedAudioVideoFile() {
        mediaFileFactory.createLicensedAudioVideoFile(MediaTypesTest.LICENSEDAUDIOVIDEO.toString(), samplingRate, width, height, encoding, tags, holder, bitrate, length, size, uploader);
        assertEquals(mediaFileRepository.read().get(0).getFileType(), MediaTypesTest.LICENSEDAUDIOVIDEO.toString());
    }

    @Test
    void createLicensedVideoFile() {
        mediaFileFactory.createLicensedVideoFile(MediaTypesTest.LICENSEDVIDEO.toString(), width, height, encoding, tags, holder, bitrate, length, size, uploader);
        assertEquals(mediaFileRepository.read().get(0).getFileType(), MediaTypesTest.LICENSEDVIDEO.toString());
    }
}