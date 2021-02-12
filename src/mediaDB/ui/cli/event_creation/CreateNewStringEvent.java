package mediaDB.ui.cli.event_creation;

import mediaDB.routing.StringEvent;

public class CreateNewStringEvent {
    public StringEvent process(String mode, String command, String option){
        return new StringEvent(this, mode, command, option);
    }
}
