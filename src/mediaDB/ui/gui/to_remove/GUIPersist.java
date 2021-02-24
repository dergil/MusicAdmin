package mediaDB.ui.gui.to_remove;

import javafx.scene.control.ListView;
import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.producer.ProducerRepository;
import mediaDB.routing.EventHandler;
import mediaDB.routing.events.misc.PersistenceEvent;
import mediaDB.ui.cli.EventFactory;

import java.io.IOException;

public class GUIPersist {
    EventFactory eventFactory;
    EventHandler eventHandler;
//TODO: uploads und producer müssen nach loadJOS und loadAddress geladen werden; geht idealerweise automatisch, da Änderung in GL

    public GUIPersist(EventFactory eventFactory, EventHandler eventHandler) {
        this.eventFactory = eventFactory;
        this.eventHandler = eventHandler;
    }

    public void saveJOS() {
        PersistenceEvent event = eventFactory.persistenceEvent("saveJOS", null);
        try {
            eventHandler.handle(event);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadJOS(){
        PersistenceEvent event = eventFactory.persistenceEvent("loadJOS", null);
        try {
            eventHandler.handle(event);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadAddress (String address){
        PersistenceEvent event = eventFactory.persistenceEvent("load", address);
        try {
            eventHandler.handle(event);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void saveAddress (String address){
        PersistenceEvent event = eventFactory.persistenceEvent("save", address);
        try {
            eventHandler.handle(event);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
