package mediaDB.ui.cli.modes;

import mediaDB.domain_logic.MediaTypes;
import mediaDB.net.server.ServerEventBus;
import mediaDB.routing.EventListener;
import mediaDB.routing.NetworkEvent;
import mediaDB.ui.cli.Console;

import java.io.IOException;

public class InsertMode {
    String mediaTypes = MediaTypes.ALL_TYPES.toString();
    InsertModeInputProcessing insertModeInputProcessing;
    String[] splitInput = null;
    String input = null;
    EventListener<NetworkEvent> serverEventBus;

    public InsertMode(InsertModeInputProcessing insertModeInputProcessing, EventListener<NetworkEvent> serverEventBus) {
        this.insertModeInputProcessing = insertModeInputProcessing;
        this.serverEventBus = serverEventBus;
    }

//    while loop, damit mode nicht jedes mal neu ausgewählt werden muss
//    input über console holen
//    hier verarbeiten


    public void start() throws IOException {
        System.out.println("Produzent: [Produzentenname]");
        System.out.println("Mediendatei: [Media-Typ] [Produzentenname] [kommaseparierte" +
                "Tags, einzelnes Komma für keine] [Bitrate] [Länge]\n" +
                "[[Encoding] [Höhe] [Breite] [Samplingrate]" +
                "[Interaktionstyp] [Lizenzsgeber]]");
        System.out.println("Zurück: 0");

//        do {
            getAndVerifyInput();
//        } while (!input.equals("0"));
    }

//    TODO: eigene Klasse, damit Methode nicht public sein muss
    public void getAndVerifyInput() throws IOException {
        input = Console.prompt("Insertion mode ");
        splitInput = input.split(" ");
        if (splitInput[0].equals("0"))
            return;
        if (checkForSpace())
            return;
//            continue;
        if (checkIfProducer()){
            insertModeInputProcessing.producer(splitInput[0]);
            return;
//            continue;
        }
        if (checkIfValidMediafile()){
            insertModeInputProcessing.mediaFile(splitInput);
        }
        else System.out.println("Syntax error. Bitrate, Länge, Höhe, Breite und Samplingrate müssen numerisch sein.");
    }

    private boolean checkIfValidMediafile() {
//        TODO: contains gibt auch bei z.B. "d" true zurück
        boolean mediaType = mediaTypes.contains(splitInput[0]);
        boolean generalStructure = checkIfGeneralMediafile();
//        check if syntax for different media file types fits
        return mediaType && generalStructure && validSpecificMediaFile();
    }

    private boolean validSpecificMediaFile(){
//        TODO: MediaTypes nehmen?
        switch (splitInput[0]){
            case "Audio":
                return validAudioFile();
            case "AudioVideo":
                return validAudioVideoFile();
            case "InteractiveVideo":
                return validInteractiveVideoFile();
            case "LicensedAudio":
                return validLicensedAudioFile();
            case "LicensedAudioVideo":
                return validLicensedAudioVideoFile();
            case "LicensedVideo":
                return validLicensedVideoFile();
            default:
                throw new IllegalArgumentException("Unrecognized media file type");
        }
    }
// general file fields: 5
    private boolean validAudioFile(){
//        samplingrate, encoding,
        boolean size = splitInput.length == 7;
        boolean samplingrate = checkIfNumeric(splitInput[5]);
        return size && samplingrate;
    }

    private boolean validAudioVideoFile(){
//        sampling, width, height, encoding,
        boolean size = splitInput.length == 9;
        boolean height = checkIfNumeric(splitInput[6]);
        boolean width = checkIfNumeric(splitInput[7]);
        boolean samplingrate = checkIfNumeric(splitInput[8]);
        return size&& height && width && samplingrate;
    }

    private boolean validInteractiveVideoFile(){
//        type, width, height, encoding,
        boolean size = splitInput.length == 9;
        boolean height = checkIfNumeric(splitInput[6]);
        boolean width = checkIfNumeric(splitInput[7]);
        return size && height && width;
    }

    private boolean validLicensedAudioFile(){
//        sampling, encoding, holder,
        boolean size = splitInput.length == 8;
        boolean samplingrate = checkIfNumeric(splitInput[6]);
        return size && samplingrate;
    }

    private boolean validLicensedAudioVideoFile(){
//        sampling, width, heigt, encoding, holder
        boolean size = splitInput.length == 10;
        boolean height = checkIfNumeric(splitInput[6]);
        boolean width = checkIfNumeric(splitInput[7]);
        boolean samplingrate = checkIfNumeric(splitInput[8]);
        return size && height && width && samplingrate;
    }

    private boolean validLicensedVideoFile(){
//        width, height, encoding, holder
        boolean size = splitInput.length == 9;
        boolean height = checkIfNumeric(splitInput[6]);
        boolean width = checkIfNumeric(splitInput[7]);
        return size && height && width;
    }

    private boolean checkIfGeneralMediafile() {
        if (splitInput.length >= 5){
            boolean tags = splitInput[2].contains(",");
            boolean bitrate = checkIfNumeric(splitInput[3]);
            boolean length = checkIfNumeric(splitInput[4]);
            return tags && bitrate && length;
        }
        else {
            System.out.println("Syntax error");
            return false;
        }
    }

//    TODO: testen
    private boolean checkIfNumeric(String input){
        return input.matches("[0-9]+");
    }

    private boolean checkIfProducer() {
        return splitInput.length == 1 && !mediaTypes.contains(splitInput[0]);
    }

    private boolean checkForSpace() {
        if (splitInput[0].equals(" ")) {
            System.out.println("Führende Leerzeichen sind nicht erlaubt");
            return true;
        }
        return false;
    }

    public InsertModeInputProcessing getInsertModeInputProcessing() {
        return insertModeInputProcessing;
    }
}
