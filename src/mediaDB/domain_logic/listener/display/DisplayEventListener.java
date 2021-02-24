package mediaDB.domain_logic.listener.display;

import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.file_interfaces.Uploadable;
import mediaDB.routing.events.misc.DisplayEvent;
import mediaDB.routing.EventListener;
import mediaDB.net.server.ToClientMessenger;

import java.io.IOException;
import java.util.List;

public class DisplayEventListener implements EventListener<DisplayEvent> {
    DisplayModeProcessing displayModeProcessing;
    MediaFileRepository mediaFileRepository;
    ToClientMessenger toClient;

    public DisplayEventListener(DisplayModeProcessing displayModeProcessing, MediaFileRepository mediaFileRepository, ToClientMessenger toClient) {
        this.displayModeProcessing = displayModeProcessing;
        this.mediaFileRepository = mediaFileRepository;
        this.toClient = toClient;
    }

    @Override
    public void onMediaEvent(DisplayEvent event) throws IOException {
        String sender = event.getSender();
        switch (event.getTopic()){
            case "uploader":
                sendToClient(displayModeProcessing.uploader(), sender, "producer");
                break;
            case "content":
                if (event.getOption() == null){
                    sendToClient(displayModeProcessing.content(mediaFileRepository.read()), sender, "uploadables");
                }
                else sendToClient(displayModeProcessing.content(mediaFileRepository.read(), event.getOption()), sender, "content");
                break;
            case "tag":
                String assigned = event.getOption();
                if (!assigned.equals("i") && !assigned.equals("e")){
                    sendToClient("Argument must be i or e", sender, "tag");
                    break;
                }
                sendToClient(displayModeProcessing.tag(assigned), sender, "tag");
                break;
            case "wholeUploads":
                sendToClient(formatList(mediaFileRepository.read()), sender, "wholeUploads");
                break;
            default:
                throw new IllegalArgumentException("Unknown display option");

        }
    }

    private void sendToClient(String message, String sender, String type) throws IOException {
        if (sender.equals("gui"))
            toClient.sendToGUI(message, sender, type);
        else toClient.sendTypedString(message, sender, type);
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
