package mediaDB.domain_logic.listener;

import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.producer.ProducerRepository;
import mediaDB.routing.EventListener;
import mediaDB.routing.events.misc.StringEvent;

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
                if (event.getCommand().equals("address")){
                    String address = event.getOption();
                    if (mediaFileRepository.findByAddress(address) != null)
                        mediaFileRepository.delete(address);
                }
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
