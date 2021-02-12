package mediaDB.routing;

import java.util.EventObject;

public class StringEvent extends EventObject implements NetworkEvent{
    String mode;
    String command;
    String option;

    public StringEvent(Object source, String mode, String command, String option) {
        super(source);
        this.mode = mode;
        this.command = command;
        this.option = option;
    }

    public String getMode() {
        return mode;
    }

    public String getCommand() {
        return command;
    }

    public String getOption() {
        return option;
    }

    @Override
    public String getEventName() {
        return "StringEvent";
    }

}
