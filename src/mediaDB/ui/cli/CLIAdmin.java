package mediaDB.ui.cli;

import java.io.IOException;

public class CLIAdmin {
    InsertMode insertMode;
    DisplayMode displayMode;
    DeletionMode deleteMode;
    ChangeMode changeMode;
    ConfigMode configMode;

    public CLIAdmin(InsertMode insertMode, DisplayMode displayMode, DeletionMode deleteMode, ChangeMode changeMode, ConfigMode configMode) {
        this.insertMode = insertMode;
        this.displayMode = displayMode;
        this.deleteMode = deleteMode;
        this.changeMode = changeMode;
        this.configMode = configMode;
    }

    public void start() throws IOException {
        Console.greeting();
        String input;
        while (true){
            input = Console.getMode();
            String firstChar = String.valueOf(input.charAt(0));
            if (firstChar.equals(":"))
                modes(input);
            else if (firstChar.equals("0"))
                System.exit(0);
            else System.out.println("Unknown command");
        }
    }

    private void modes(String mode) throws IOException {
        switch (mode){
            case ":c":
                insertMode.start();
                break;
            case ":d":
                deleteMode.start();
                break;
            case ":r":
                displayMode.start();
                break;
            case ":u":
                changeMode.start();
                break;
//            case ":p":
//                Console.persistenceMode();
//                break;
            case ":config":
                configMode.start();
                break;
            default:
                System.out.println("Unknown mode.");
                break;
        }
    }

    public InsertMode getInsertMode() {
        return insertMode;
    }

//    public void setProducerEventEventHandler(EventHandler<ProducerEvent> handler){
//        insertMode.getInsertModeInputProcessing().setProducerEventEventHandler(handler);
//    }
//
//    public void setAudioEventHandler(EventHandler<AudioEvent> handler){
//        insertMode.getInsertModeInputProcessing().setAudioEventHandler(handler);
//    }
//
//    public void setAudioVideoEventHandler(EventHandler<AudioVideoEvent> audioVideoEventHandler) {
//        insertMode.getInsertModeInputProcessing().setAudioVideoEventHandler(audioVideoEventHandler);
//    }
//
//    public void setInteractiveVideoEventHandler(EventHandler<InteractiveVideoEvent> interactiveVideoEventHandler) {
//        insertMode.getInsertModeInputProcessing().setInteractiveVideoEventHandler(interactiveVideoEventHandler);
//    }
//
//    public void setLicensedAudioEventHandler(EventHandler<LicensedAudioEvent> licensedAudioEventHandler) {
//        insertMode.getInsertModeInputProcessing().setLicensedAudioEventHandler(licensedAudioEventHandler);
//    }
//
//    public void setLicensedAudioVideoEventHandler(EventHandler<LicensedAudioVideoEvent> licensedAudioVideoEventHandler) {
//        insertMode.getInsertModeInputProcessing().setLicensedAudioVideoEventHandler(licensedAudioVideoEventHandler);
//    }
//
//    public void setLicensedVideoEventHandler(EventHandler<LicensedVideoEvent> licensedVideoEventHandler) {
//        insertMode.getInsertModeInputProcessing().setLicensedVideoEventHandler(licensedVideoEventHandler);
//    }
//
//
//    public void setDisplayEventEventHandler(EventHandler<DisplayEvent> displayEventEventHandler){
//        displayMode.setDisplayEventEventHandler(displayEventEventHandler);
//    }






//    protected void createProducerEvent(String name){}
//
//    protected void createMediafileEvent(String[] input){
////        Class for interpreting file type and initializing Event creation
//
//    }
}

