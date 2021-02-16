package mediaDB.ui.cli.event_creation;

import mediaDB.routing.events.misc.ServerResponseEvent;

public class CreateServerResponseEvent {
    public ServerResponseEvent process(String message){
        return new ServerResponseEvent(this, message);
    }
}
