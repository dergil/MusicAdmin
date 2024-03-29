package mediaDB.domain_logic.listener.files;

import mediaDB.domain_logic.*;
import mediaDB.domain_logic.producer.Uploader;
import mediaDB.domain_logic.producer.Producer;
import mediaDB.domain_logic.producer.ProducerRepository;
import mediaDB.routing.events.files.AudioEvent;
import mediaDB.routing.EventListener;

import java.math.BigDecimal;

public class AudioEventListener implements EventListener<AudioEvent> {
    ProducerRepository producerRepository;
    MediaFileFactory mediaFileFactory;
    MediaFileRepository mediaFileRepository;

    public AudioEventListener(ProducerRepository producerRepository, MediaFileFactory mediaFileFactory, MediaFileRepository mediaFileRepository) {
        this.producerRepository = producerRepository;
        this.mediaFileFactory = mediaFileFactory;
        this.mediaFileRepository = mediaFileRepository;
    }

    @Override
    public void onMediaEvent(AudioEvent event) {
        Uploader uploader = new Producer(event.getUploader());
        BigDecimal size = CalcSize.size(event.getLength(), event.getBitrate());
        boolean capacity = mediaFileRepository.capacityAvailable(size);
        boolean producer = producerRepository.contains(uploader);
        if (capacity && producer)
            mediaFileFactory.createAudioFile(event.getFileType(), event.getSamplingRate(),
                    event.getEncoding(), event.getTags(), event.getBitrate(), event.getLength(), size, uploader);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(AudioEvent.class);
    }
}
