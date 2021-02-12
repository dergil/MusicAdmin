package mediaDB.domain_logic.listener;

import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.ProducerRepository;
import mediaDB.routing.EventListener;
import mediaDB.routing.StringEvent;

import java.io.IOException;

public class StringEventListener implements EventListener<StringEvent> {
    MediaFileRepository mediaFileRepository;
    ProducerRepository producerRepository;

    public StringEventListener(MediaFileRepository mediaFileRepository, ProducerRepository producerRepository) {
        this.mediaFileRepository = mediaFileRepository;
        this.producerRepository = producerRepository;
    }

    @Override
    public void onMediaEvent(StringEvent event) throws IOException {
        switch (event.getMode()){
            case "Deletion":
                if (event.getCommand().equals("address"))
                    mediaFileRepository.delete(event.getOption());
                else if (event.getCommand().equals("producer"))
                    producerRepository.removeProducer(event.getOption());
                break;
            case "Change":
//                TODO: right type?
                mediaFileRepository.incrementAccessCount(event.getOption());
                break;

        }

    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(StringEvent.class);
    }
}
