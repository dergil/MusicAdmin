package mediaDB.domain_logic.listener;

import mediaDB.domain_logic.*;
import mediaDB.domain_logic.Uploader;
import mediaDB.routing.EventListener;
import mediaDB.routing.events.files.LicensedVideoEvent;

import java.io.IOException;
import java.math.BigDecimal;

public class LicensedVideoEventListener implements EventListener<LicensedVideoEvent> {
    ProducerRepository producerRepository;
    MediaFileFactory mediaFileFactory;
    MediaFileRepository mediaFileRepository;

    public LicensedVideoEventListener(ProducerRepository producerRepository, MediaFileFactory mediaFileFactory, MediaFileRepository mediaFileRepository) {
        this.producerRepository = producerRepository;
        this.mediaFileFactory = mediaFileFactory;
        this.mediaFileRepository = mediaFileRepository;
    }

    @Override
    public void onMediaEvent(LicensedVideoEvent event) throws IOException {
        Uploader uploader = new Producer(event.getUploader());
        BigDecimal size = new BigDecimal(event.getLength().getSeconds() * event.getBitrate());
        boolean capacity = mediaFileRepository.capacityAvailable(size);
        boolean producer = producerRepository.contains(uploader);
        if (capacity && producer)
            mediaFileFactory.createLicensedVideoFile(event.getFileType(), event.getWidth(), event.getHeight(),
                    event.getEncoding(), event.getTags(), event.getHolder(), event.getBitrate(), event.getLength(), size, uploader);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(LicensedVideoEvent.class);
    }
}
