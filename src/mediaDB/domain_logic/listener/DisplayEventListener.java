package mediaDB.domain_logic.listener;

import mediaDB.domain_logic.DisplayModeServer;
import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.file_interfaces.Uploadable;
import mediaDB.routing.events.misc.DisplayEvent;
import mediaDB.routing.EventListener;
import mediaDB.net.server.ToClientMessenger;

import java.io.IOException;
import java.util.List;

public class DisplayEventListener implements EventListener<DisplayEvent> {
    DisplayModeServer displayModeServer;
    MediaFileRepository mediaFileRepository;
    ToClientMessenger toClient;

    public DisplayEventListener(DisplayModeServer displayModeServer, MediaFileRepository mediaFileRepository, ToClientMessenger toClient) {
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
                    toClient.sendString(formatList(mediaFileRepository.read()));
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

    private String  formatList(List<Uploadable> uploadableList){
        StringBuilder stringBuilder = new StringBuilder();
        for (Uploadable uploadable : uploadableList){
            stringBuilder.append(uploadable.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(DisplayEvent.class);
    }
}
