package mediaDB.domain_logic.listener;

import mediaDB.domain_logic.producer.Producer;
import mediaDB.domain_logic.producer.ProducerRepository;
import mediaDB.net.server.ToClientMessenger;
import mediaDB.routing.EventListener;
import mediaDB.routing.events.misc.ProducerEvent;


import java.io.IOException;

public class ProducerEventListener implements EventListener<ProducerEvent> {
    ProducerRepository producerRepository;
    ToClientMessenger toClientMessenger;

    public ProducerEventListener(ProducerRepository producerRepository, ToClientMessenger toClientMessenger) {
        this.producerRepository = producerRepository;
        this.toClientMessenger = toClientMessenger;
    }

    @Override
    public void onMediaEvent(ProducerEvent event) throws IOException {
        if (event.getCommand().equals("add")){
            producerRepository.addProducer(new Producer(event.getName()));
            toClientMessenger.dataChange();
        }
        else if (event.getCommand().equals("remove")){
            producerRepository.removeProducer(event.getName());
            toClientMessenger.dataChange();
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(ProducerEvent.class);
    }
}
