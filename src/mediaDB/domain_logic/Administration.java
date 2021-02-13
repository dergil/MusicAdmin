package mediaDB.domain_logic;

//            case "AudioFile":
//
//                    case "AudioVideoFile":
//
//                    case "InteractiveVideoFile":
//
//                    case "LicensedAudioFile":
//
//                    case "LicensedAudioVideoFile":
//
//                    case "LicensedVideoFile":
//
//default:

//TODO: test possible nefarious/idiotic inputs
public class Administration {
//    private MediaFileRepository mediaFileRepository; // = new MediaFileRepository();
//    private ProducerRepository producerRepository; // = new ProducerRepository();
//    private MediaFileFactory mediaFileFactory; // = new MediaFileFactory(this);
////    private TempMessaging toClient = new TempMessaging();
//    ServerToClientMessenger toClient; // = new ServerToClientMessenger();
//    private DisplayModeServer displayModeServer; // = new DisplayMode(this);
//    private AdminListeners adminListeners; // = new AdminListeners(this);
//
//    public Administration(MediaFileRepository mediaFileRepository, ProducerRepository producerRepository,
//                          MediaFileFactory mediaFileFactory, ServerToClientMessenger toClient, DisplayModeServer displayModeServer,
//                          AdminListeners adminListeners) {
//        this.mediaFileRepository = mediaFileRepository;
//        this.producerRepository = producerRepository;
//        this.mediaFileFactory = mediaFileFactory;
//        this.toClient = toClient;
//        this.displayModeServer = displayModeServer;
//        this.adminListeners = adminListeners;
//    }
//
//    public String producersWithNumberOfFiles(){
//        return displayModeServer.uploader();
//    }
//
//    public String  contentForDisplay() throws IOException {
//        return displayModeServer.content(mediaFileRepository.read());
//    }
//
//    public String contentForDisplay(String type) throws IOException {
//        return displayModeServer.content(mediaFileRepository.read(), type);
//    }
//
//    protected String tagsForDisplay(String assigned){
//        if (!assigned.equals("i") && !assigned.equals("e")){
//            throw new IllegalArgumentException("Argument must be i or e");
//        }
//        return displayModeServer.tag(assigned);
//    }
//
//    public void addProducer(String name) throws IOException {
//        if (producerRepository.contains(name))
//            toClient.sendString("Producer already exists.");
//        else producerRepository.addProducer(new Producer(name));
//    }
//
//    public void removeProducer(Uploader uploader) throws IOException {
//        if (producerRepository.contains(uploader))
//            producerRepository.removeProducer(uploader);
//        else toClient.producerNotListet();
//    }
//
//    public void removeProducer(String name) throws IOException {
//        if (producerRepository.contains(name))
//            producerRepository.removeProducer(name);
//        else toClient.producerNotListet();
//    }
//
//    public void removeMediaFile(Uploadable uploadable) throws IOException {
//        if (mediaFileRepository.contains(uploadable))
//            mediaFileRepository.delete(uploadable);
//        else toClient.fileNotListet();
//    }
//
//    public void removeMediaFile(String address) throws IOException {
//        if (mediaFileRepository.contains(address))
//            mediaFileRepository.delete(address);
//        else toClient.fileNotListet();
//    }
//
//    public void incrementAccessCount(String address) throws IOException {
//        if (mediaFileRepository.contains(address)){
//            Uploadable file = mediaFileRepository.findByAddress(address);
//            Content content = ((Content)file);
//            content.setAccessCount(content.getAccessCount() + 1);
//        }
//        else toClient.fileNotListet();
//    }
//
//    public void incrementAccessCount(Uploadable file) throws IOException {
//        if (mediaFileRepository.contains(file)){
//            Content content = ((Content)file);
//            content.setAccessCount(content.getAccessCount() + 1);
//        }
//        else toClient.fileNotListet();
//    }
//
//    protected void incrementAccessCountForList(List<Uploadable> list) throws IOException {
//        for (Uploadable uploadable : list){
//            incrementAccessCount(uploadable);
//        }
//    }
//
//    public MediaFileRepository getMediaFileRepository() {
//        return mediaFileRepository;
//    }
//
//    public ProducerRepository getProducerRepository() {
//        return producerRepository;
//    }
//
//    public MediaFileFactory getMediaFileFactory() {
//        return mediaFileFactory;
//    }
//
//    public AdminListeners getAdminListeners() {
//        return adminListeners;
//    }
//
//    public ServerToClientMessenger getToClient() {
//        return toClient;
//    }
//
//    public void registerSizeObserver(Observer observer){
//        mediaFileRepository.getSizeObservable().register(observer);
//    }
}
