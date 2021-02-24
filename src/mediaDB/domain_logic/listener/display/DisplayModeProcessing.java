package mediaDB.domain_logic.listener.display;

import mediaDB.domain_logic.*;
import mediaDB.domain_logic.enums.Tag;
import mediaDB.domain_logic.file_interfaces.Content;
import mediaDB.domain_logic.file_interfaces.Uploadable;
import mediaDB.domain_logic.producer.ProducerRepository;
import mediaDB.domain_logic.producer.Uploader;

import java.io.IOException;
import java.util.*;

public class DisplayModeProcessing {
    GenerateDisplayContent generateDisplayContent;
    ProducerRepository producerRepository;
    MediaFileRepository mediaFileRepository;

    public DisplayModeProcessing(GenerateDisplayContent generateDisplayContent, ProducerRepository producerRepository, MediaFileRepository mediaFileRepository) {
        this.generateDisplayContent = generateDisplayContent;
        this.producerRepository = producerRepository;
        this.mediaFileRepository = mediaFileRepository;
    }

    public String uploader(){
        ArrayList<Uploader> producers = producerRepository.getProducers();
        List<Uploadable> files = mediaFileRepository.read();
        HashMap<String , Integer> map = new HashMap<>();
        for (Uploader producer : producers) {
            String producerName = producer.getName();
            int numberOfFiles = 0;
            for (Uploadable file : files) {
                String fileUploaderName = file.getUploader().getName();
                if (fileUploaderName.equals(producerName))
                    numberOfFiles++;
            }
            map.put(producerName, numberOfFiles);
        }
        return map.toString();
    }

    public String content(List<Uploadable> uploadables) {
        return generateDisplayContent.generate(uploadables);
    }

    public String content(List<Uploadable> uploadables, String type) {
        return generateDisplayContent.generate(uploadables, type);
    }

    public String tag(String assigned){
        //    TODO: possibly implement EnumSet
        if (assigned.equals("i")){
            return assignedTags().toString();
        } else if (assigned.equals("e")){
            return nonAssignedTags().toString();
        }
        else throw new IllegalArgumentException("Argument must be i or e");
    }

    //    TODO: possibly implement EnumSet
    private Set<Tag> assignedTags(){
        List<Uploadable> files = mediaFileRepository.read();
        Set<Tag> tags = new HashSet<>();
        for (Uploadable file: files) {
            tags.addAll(((Content) file).getTags());
        }
        return tags;
    }

    private Set<Tag> nonAssignedTags(){
        Set<Tag> nonAssignedTags = new HashSet<>();
        Set<Tag> assignedTags = assignedTags();
//        Tag[] existingTags = Tag.class.getEnumConstants();
//        EnumSet<Tag> existingTags = EnumSet.allOf(Tag.class);
//        Set<Tag> existing = new HashSet<>(Collections.list(Tag.class));
        Tag[] existingTags = Tag.values();
        for (Tag tag: existingTags) {
            if (!assignedTags.contains(tag))
                nonAssignedTags.add(tag);
        }
        return nonAssignedTags;
    }


    }
