package mediaDB.ui.cli;

import mediaDB.domain_logic.Administration;
import mediaDB.domain_logic.MediaFileRepository;

import java.io.IOException;

public class ConfigMode {
    EventHandlers eventHandlers;
    String[] splitInput = null;
    String input = null;
//    TODO: extern initialisieren
    SizeObserver sizeObserver;
    TagObserver tagObserver;
    MediaFileRepository mediaFileRepository;

    public ConfigMode(EventHandlers eventHandlers, MediaFileRepository mediaFileRepository) {
        this.eventHandlers = eventHandlers;
        this.mediaFileRepository = mediaFileRepository;
    }

    protected void start() throws IOException {
        System.out.println("Classes: SizeObserver, TagObserver");
        System.out.println("add [class name]");
        System.out.println("remove [class name]");
        System.out.println("Zurück: 0");
        do {
            getAndVerifyInput();
        } while (!input.equals("0"));
    }

    private void getAndVerifyInput() throws IOException {
        input = Console.prompt("Config mode ");
        splitInput = input.split(" ");
        switch (splitInput[0]){
            case "0":
                return;
            case "add":
                if (splitInput[1].equals("SizeObserver"))
                    addSizeObserver();
                else if (splitInput[1].equals("TagObserver"))
                    addTagObserver();
                break;
            case "remove":
                if (splitInput[1].equals("SizeObserver"))
                    removeSizeObserver();
                else if (splitInput[1].equals("TagObserver"))
                    removeTagObserver();
                break;
            default:
                break;
        }
    }

    private void addSizeObserver() {
        sizeObserver = new SizeObserver(mediaFileRepository.getSizeObservable());
        mediaFileRepository.getSizeObservable().register(sizeObserver);
    }

    private void addTagObserver() {
        tagObserver = new TagObserver(mediaFileRepository.getTagObservable());
        mediaFileRepository.getTagObservable().register(tagObserver);
    }

    private void removeSizeObserver() {
        mediaFileRepository.getSizeObservable().deregister(sizeObserver);
    }

    private void removeTagObserver() {
        mediaFileRepository.getTagObservable().deregister(tagObserver);
    }
}