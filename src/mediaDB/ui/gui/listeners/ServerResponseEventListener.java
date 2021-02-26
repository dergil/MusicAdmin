package mediaDB.ui.gui.listeners;

import javafx.application.Platform;
import javafx.scene.control.ListView;
import mediaDB.routing.EventHandler;
import mediaDB.routing.EventListener;
import mediaDB.routing.events.misc.ServerResponseEvent;
import mediaDB.ui.cli.EventFactory;

import java.io.IOException;

/*Quelle:
* https://stackoverflow.com/questions/48290353/gui-update-with-threads-error
* */

public class ServerResponseEventListener implements EventListener<ServerResponseEvent> {
    ListView<String> uploadsListView;
    ListView<String> producerListView;
    ListView<String> displayListView;
    EventFactory eventFactory;
    mediaDB.routing.EventHandler eventHandler;


    public ServerResponseEventListener(ListView<String> uploadsListView, ListView<String> producerListView,
                                       ListView<String> displayListView, EventFactory eventFactory, EventHandler eventHandler) {
        this.uploadsListView = uploadsListView;
        this.producerListView = producerListView;
        this.displayListView = displayListView;
        this.eventFactory = eventFactory;
        this.eventHandler = eventHandler;
    }

    //    update von uploads und producern, außerdem spezielle displays (tags, typed uploads)
    @Override
    public void onMediaEvent(ServerResponseEvent event) throws IOException {
        if (event.getSender().equals("gui")){
            String[] input = event.getEventName().split("\n");
            switch (event.getType()){
                case "uploadables":
                case "content":
                case "tag":
                    updateList(displayListView, input);
                    break;
                case "producer":
                    String producerString = input[0].replace("{", "").replace("}", "");
                    updateList(producerListView, producerString.split(", "));
                    break;
                case "DataChange":
                    eventHandler.handle(eventFactory.displayEvent("uploader", null));
                    eventHandler.handle(eventFactory.displayEvent("wholeUploads", null));
                    break;
                case "wholeUploads":
                    updateList(uploadsListView, input);
                    break;
                default:
                    System.out.println("Unknown ServerResponseEvent type");
            }
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
            return clazz.equals(ServerResponseEvent.class);
    }

    private void updateList(ListView<String> listView, String[] input){
        Platform.runLater(
                () -> {
                    listView.getItems().clear();
                    for (String upload : input) {
                        listView.getItems().add(upload);
                    }
                }
        );
    }
}
