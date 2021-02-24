
package mediaDB.domain_logic;

import mediaDB.domain_logic.enums.Tag;
import mediaDB.domain_logic.files.*;
import mediaDB.domain_logic.producer.Uploader;
import mediaDB.net.server.ToClientMessenger;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

//Source : https://www.javacodeexamples.com/get-first-or-last-element-from-linkedhashset-in-java-example/3041

public class MediaFileFactory {
    MediaFileRepository mediaFileRepository;
    AddressRepository addressRepository;
    ToClientMessenger toClientMessenger;

    long defaultAccessCount = 0;

    public MediaFileFactory(MediaFileRepository mediaFileRepository, AddressRepository addressRepository, ToClientMessenger toClientMessenger) {
        this.mediaFileRepository = mediaFileRepository;
        this.addressRepository = addressRepository;
        this.toClientMessenger = toClientMessenger;
    }

    public void createAudioFile(String fileType, int samplingRate, String encoding, Collection<Tag> tags, long bitrate,
                                Duration length, BigDecimal size, Uploader uploader) {
        String address = addressRepository.nextAddress();
        Date newDate = new Date();
        AudioFile file = new AudioFile(fileType, samplingRate, encoding, address, tags, defaultAccessCount, bitrate,
                length, size, uploader, newDate);
        mediaFileRepository.create(file);
        messageGUI();
    }

    public void createAudioVideoFile(String fileType, int samplingRate, int width, int height, String encoding,
                                     Collection<Tag> tags, long bitrate, Duration length,
                                     BigDecimal size, Uploader uploader) {
        String address = addressRepository.nextAddress();
        Date newDate = new Date();
        AudioVideoFile file = new AudioVideoFile(fileType, samplingRate, width, height, encoding, address, tags,
               defaultAccessCount, bitrate, length, size, uploader, newDate);
        mediaFileRepository.create(file);
        messageGUI();
    }

    public void createInteractiveVideoFile(String fileType, String type, int width, int height, String encoding,
                                           Collection<Tag> tags, long bitrate, Duration length,
                                           BigDecimal size, Uploader uploader) {
        String address = addressRepository.nextAddress();
        Date newDate = new Date();
        InteractiveVideoFile file = new InteractiveVideoFile(fileType, type, width, height, encoding, address, tags,
               defaultAccessCount, bitrate, length, size, uploader, newDate);
        mediaFileRepository.create(file);
        messageGUI();
    }

    public void createLicensedAudioFile(String fileType, int samplingRate, String encoding, Collection<Tag> tags,
                                        String holder, long bitrate, Duration length, BigDecimal size,
                                        Uploader uploader) {
        String address = addressRepository.nextAddress();
        Date newDate = new Date();
        LicensedAudioFile file = new LicensedAudioFile(fileType, samplingRate, encoding, address, tags,defaultAccessCount,
                holder, bitrate, length, size, uploader, newDate);
        mediaFileRepository.create(file);
        messageGUI();
    }

    public void createLicensedAudioVideoFile(String fileType, int samplingRate, int width, int height, String encoding,
                                             Collection<Tag> tags, String holder, long bitrate,
                                             Duration length, BigDecimal size, Uploader uploader) {
        String address = addressRepository.nextAddress();
        Date newDate = new Date();
        LicensedAudioVideoFile file = new LicensedAudioVideoFile(fileType, samplingRate, width, height, encoding,
                address, tags,defaultAccessCount, holder, bitrate, length, size, uploader, newDate);
        mediaFileRepository.create(file);
        messageGUI();
    }

    public void createLicensedVideoFile(String fileType, int width, int height, String encoding,
                                        Collection<Tag> tags, String holder, long bitrate, Duration length,
                                        BigDecimal size, Uploader uploader) {
        String address = addressRepository.nextAddress();
        Date newDate = new Date();
        LicensedVideoFile file = new LicensedVideoFile(fileType, width, height, encoding, address, tags,defaultAccessCount,
                holder, bitrate, length, size, uploader, newDate);
        mediaFileRepository.create(file);
        messageGUI();
    }

    private void messageGUI()  {
        try {
            toClientMessenger.dataChange();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
