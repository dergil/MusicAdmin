package mediaDB.domain_logic.listener;

import mediaDB.domain_logic.*;
import mediaDB.domain_logic.Uploader;
import mediaDB.routing.EventListener;
import mediaDB.routing.LicensedAudioEvent;

import java.io.IOException;
import java.math.BigDecimal;

public class LicensedAudioEventListener implements EventListener<LicensedAudioEvent> {
    ProducerRepository producerRepository;
    MediaFileFactory mediaFileFactory;
    MediaFileRepository mediaFileRepository;

    public LicensedAudioEventListener(ProducerRepository producerRepository, MediaFileFactory mediaFileFactory, MediaFileRepository mediaFileRepository) {
        this.producerRepository = producerRepository;
        this.mediaFileFactory = mediaFileFactory;
        this.mediaFileRepository = mediaFileRepository;
    }

    @Override
    public void onMediaEvent(LicensedAudioEvent event) throws IOException {
        Uploader uploader = new Producer(event.getUploader());
        BigDecimal size = new BigDecimal(event.getLength().getSeconds() * event.getBitrate());
        boolean capacity = mediaFileRepository.capacityAvailable(size);
        boolean producer = producerRepository.contains(uploader);
        if (capacity && producer)
            mediaFileFactory.createLicensedAudioFile(event.getFileType(), event.getSamplingRate(),
                    event.getEncoding(), event.getTags(), event.getHolder(), event.getBitrate(), event.getLength(), size, uploader);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(LicensedAudioEvent.class);
    }
}
