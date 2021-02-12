package mediaDB.domain_logic.listener;

import mediaDB.domain_logic.DisplayModeServer;
import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.routing.DisplayEvent;
import mediaDB.routing.EventListener;
import mediaDB.tempserver.ServerToClientMessenger;

import java.io.IOException;

public class DisplayEventListener implements EventListener<DisplayEvent> {
    DisplayModeServer displayModeServer;
    MediaFileRepository mediaFileRepository;
    ServerToClientMessenger toClient;

    public DisplayEventListener(DisplayModeServer displayModeServer, MediaFileRepository mediaFileRepository, ServerToClientMessenger toClient) {
        this.displayModeServer = displayModeServer;
        this.mediaFileRepository = mediaFileRepository;
        this.toClient = toClient;
    }

    @Override
    public void onMediaEvent(DisplayEvent event) throws IOException {
        switch (event.getTopic()){
            case "uploader":
                toClient.sendString(displayModeServer.uploader());
                break;
            case "content":
                if (event.getOption() == null){
                    toClient.sendString(mediaFileRepository.read().toString());
                }
                else toClient.sendString(displayModeServer.content(mediaFileRepository.read(), event.getOption()));
                break;
            case "tag":
                String assigned = event.getOption();
                if (!assigned.equals("i") && !assigned.equals("e")){
                    toClient.sendString("Argument must be i or e");
                    break;
                }
                toClient.sendString(displayModeServer.tag(assigned));
                break;
            default:
                throw new IllegalArgumentException("Unknown display option");

        }

    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(DisplayEvent.class);
    }
}
