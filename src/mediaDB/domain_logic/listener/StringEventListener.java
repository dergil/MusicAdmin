package mediaDB.domain_logic.listener;

import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.producer.ProducerRepository;
import mediaDB.net.server.ToClientMessenger;
import mediaDB.routing.EventListener;
import mediaDB.routing.events.misc.StringEvent;

import java.io.IOException;

public class StringEventListener implements EventListener<StringEvent> {
    MediaFileRepository mediaFileRepository;
    ProducerRepository producerRepository;
    ToClientMessenger toClientMessenger;

    public StringEventListener(MediaFileRepository mediaFileRepository, ProducerRepository producerRepository, ToClientMessenger toClientMessenger) {
        this.mediaFileRepository = mediaFileRepository;
        this.producerRepository = producerRepository;
        this.toClientMessenger = toClientMessenger;
    }

    @Override
    public void onMediaEvent(StringEvent event) throws IOException {
        switch (event.getMode()){
            case "Deletion":
                if (event.getCommand().equals("address")){
                    String address = event.getOption();
                    if (mediaFileRepository.findByAddress(address) != null){
                        mediaFileRepository.delete(address);
                        toClientMessenger.dataChange();
                    }
                }
                else if (event.getCommand().equals("producer")){
                    producerRepository.removeProducer(event.getOption());
                    toClientMessenger.dataChange();
                }
                break;
            case "Change":
                mediaFileRepository.incrementAccessCount(event.getOption());
                toClientMessenger.dataChange();
                break;

        }

    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(StringEvent.class);
    }
}
