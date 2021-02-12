package mediaDB.domain_logic;

import mediaDB.tempserver.ServerToClientMessenger;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;

public class AdminListeners {
//    Administration administration;
//    MediaFileRepository mediaFileRepository;
//    MediaFileFactory mediaFileFactory;
//    ProducerRepository producerRepository;
//    ServerToClientMessenger toClient;
//
//    AudioEventListener audioEventListener;
//    AudioVideoEventListener audioVideoEventListener;
//    InteractiveVideoEventListener interactiveVideoEventListener;
//    LicensedAudioEventListener licensedAudioEventListener;
//    LicensedAudioVideoEventListener licensedAudioVideoEventListener;
//    LicensedVideoEventListener licensedVideoEventListener;
//
//    ProducerEventListener producerEventListener;
//    DisplayEventListener displayEventListener;
//
//    StringEventListener stringEventListener;



//    public AdminListeners(Administration administration) {
//        this.administration = administration;
//        this.mediaFileRepository = administration.getMediaFileRepository();
//        this.mediaFileFactory = administration.getMediaFileFactory();
//        this.producerRepository = administration.getProducerRepository();
//        this.toClient = administration.getToClient();
//
//        this.audioEventListener = new AudioEventListener(this);
//        this.audioVideoEventListener = new AudioVideoEventListener(this);
//        this.interactiveVideoEventListener = new InteractiveVideoEventListener(this);
//        this.licensedAudioEventListener = new LicensedAudioEventListener(this);
//        this.licensedAudioVideoEventListener = new LicensedAudioVideoEventListener(this);
//        this.licensedVideoEventListener = new LicensedVideoEventListener(this);
//
//        this.producerEventListener = new ProducerEventListener(this);
//        this.displayEventListener = new DisplayEventListener(this);
//        this.stringEventListener = new StringEventListener(this);
//    }


//    public AdminListeners(MediaFileRepository mediaFileRepository, MediaFileFactory mediaFileFactory, ProducerRepository producerRepository,
//                          ServerToClientMessenger toClient, AudioEventListener audioEventListener,
//                          AudioVideoEventListener audioVideoEventListener, InteractiveVideoEventListener interactiveVideoEventListener,
//                          LicensedAudioEventListener licensedAudioEventListener, LicensedAudioVideoEventListener licensedAudioVideoEventListener,
//                          LicensedVideoEventListener licensedVideoEventListener, ProducerEventListener producerEventListener,
//                          DisplayEventListener displayEventListener, StringEventListener stringEventListener) {
//        this.mediaFileRepository = mediaFileRepository;
//        this.mediaFileFactory = mediaFileFactory;
//        this.producerRepository = producerRepository;
//        this.toClient = toClient;
//        this.audioEventListener = audioEventListener;
//        this.audioVideoEventListener = audioVideoEventListener;
//        this.interactiveVideoEventListener = interactiveVideoEventListener;
//        this.licensedAudioEventListener = licensedAudioEventListener;
//        this.licensedAudioVideoEventListener = licensedAudioVideoEventListener;
//        this.licensedVideoEventListener = licensedVideoEventListener;
//        this.producerEventListener = producerEventListener;
//        this.displayEventListener = displayEventListener;
//        this.stringEventListener = stringEventListener;
//    }
//
//    protected boolean capacityAvailible(BigDecimal size) throws IOException {
//        if (mediaFileRepository.capacityAvailable(size))
//            return true;
//        else toClient.sendString("File too large, capacity is reached.");
//        return false;
//    }
//
//    protected BigDecimal calculateSize(Duration length, long bitrate){
//        return new BigDecimal(length.getSeconds()*bitrate);
//    }
//
//    protected boolean existingProducer(Uploader uploader) throws IOException {
//        if (producerRepository.contains(uploader.getName()))
//            return true;
//        else toClient.producerNotListet();
//        return false;
//    }
//
//    public String  producersWithNumberOfFiles(){
//        return administration.producersWithNumberOfFiles();
//    }
//
//    protected String contentForDisplay() throws IOException {
//        return administration.contentForDisplay();
//    }
//
//    protected String contentForDisplay(String type) throws IOException {
//        return administration.contentForDisplay(type);
//    }
//
//    protected String tagsForDisplay(String assigned){
//        return administration.tagsForDisplay(assigned);
//    }
//
//    protected void addProducer(String name) throws IOException {
//        administration.addProducer(name);
//    }
//
//    protected void removeFileByAddress(String address) throws IOException {
//        administration.removeMediaFile(address);
//    }
//
//    protected void removeProducer(String name) throws IOException {
//        administration.removeProducer(name);
//    }
//
//    protected void increaseAccessCount(String address) throws IOException {
//        administration.incrementAccessCount(address);
//    }
//
//    public MediaFileFactory getMediaFileFactory() {
//        return mediaFileFactory;
//    }
//
//    public AudioEventListener getAudioEventListener() {
//        return audioEventListener;
//    }
//
//    public AudioVideoEventListener getAudioVideoEventListener() {
//        return audioVideoEventListener;
//    }
//
//    public InteractiveVideoEventListener getInteractiveVideoEventListener() {
//        return interactiveVideoEventListener;
//    }
//
//    public LicensedAudioEventListener getLicensedAudioEventListener() {
//        return licensedAudioEventListener;
//    }
//
//    public LicensedAudioVideoEventListener getLicensedAudioVideoEventListener() {
//        return licensedAudioVideoEventListener;
//    }
//
//    public LicensedVideoEventListener getLicensedVideoEventListener() {
//        return licensedVideoEventListener;
//    }
//
//    public ProducerEventListener getProducerEventListener() {
//        return producerEventListener;
//    }
//
//    public DisplayEventListener getDisplayEventListener() {
//        return displayEventListener;
//    }
//
//    public ServerToClientMessenger getToClient() {
//        return toClient;
//    }
//
//    public StringEventListener getStringEventListener() {
//        return stringEventListener;
//    }
}
