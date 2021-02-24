package mediaDB.domain_logic.listener.display;

import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.file_interfaces.Content;
import mediaDB.domain_logic.file_interfaces.Uploadable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenerateDisplayContent {
    public String generate(List<Uploadable> uploadables) {
        return buildFromList(uploadables);
    }

    public String generate(List<Uploadable> uploadables, String type) {
        return contentWithType(uploadables, type);
    }

    private String contentWithType(List<Uploadable> uploadables, String type) {
        if (type.contains("Content") || type.equals("Uploadable"))
            return buildFromList(uploadables);
        ArrayList<Uploadable> filesWithType = new ArrayList<>();
        for (Uploadable uploadable : uploadables) {
            if (uploadable.getFileType().equals(type))
                filesWithType.add(uploadable);
        }
        return buildFromList(filesWithType);
    }

    private String buildFromList(List<Uploadable> list) {
//        TODO: soll hier incremented werden? ich glaube nicht, siehe simulation 2
//        mediaFileRepository.incrementAccessCountForList(list);
//        Content, Uploadable
        StringBuilder builder = new StringBuilder();
        for (Uploadable uploadable: list) {
//            StringBuilder innerBuilder = new StringBuilder();
            String mediaType = uploadable.getFileType();
            String uploadDate = uploadable.getUploadDate().toString();
            String address = ((Content)uploadable).getAddress();
            String accessCount = String.valueOf(((Content)uploadable).getAccessCount());
//            innerBuilder.append(mediaType, address, uploadDate, accessCount);
            builder.append(mediaType).append("\t").append(address).append("\t").append(uploadDate).append("\t").append(accessCount).append("\n");
        }
        return builder.toString();
    }
}
