package mediaDB.domain_logic.listener;

import mediaDB.domain_logic.Producer;
import mediaDB.domain_logic.ProducerRepository;
import mediaDB.routing.EventListener;
import mediaDB.routing.events.misc.ProducerEvent;

import java.io.IOException;

public class ProducerEventListener implements EventListener<ProducerEvent> {
    ProducerRepository producerRepository;

    public ProducerEventListener(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    public void onMediaEvent(ProducerEvent event) throws IOException {
        if (event.getCommand().equals("add"))
            producerRepository.addProducer(new Producer(event.getName()));
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(ProducerEvent.class);
    }
}
