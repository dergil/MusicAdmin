package mediaDB.ui.cli;

import mediaDB.domain_logic.files.InteractiveVideoFile;
import mediaDB.domain_logic.files.LicensedAudioVideoFile;
import mediaDB.domain_logic.file_interfaces.Uploadable;

import java.util.ArrayList;
import java.util.Scanner;

public class Console {
    public static void greeting(){
        System.out.println("Willkommen zu Ihrer Mediaverwaltung!");
        System.out.println(" ");
    }

    public static String prompt(String mode){
        Scanner scan = new Scanner(System.in);
        String input = null;
        do {
            System.out.print(mode + "$ ");
            input = scan.nextLine();
        } while (input == null || input.isEmpty());
        return input;
    }

    public static String  getMode(){
        System.out.println(" ");
        System.out.println("Pick a mode:");
        System.out.println(":c - Insertion mode");
        System.out.println(":d - Deletion mode");
        System.out.println(":r - Display mode");
        System.out.println(":u - Alteration mode");
        System.out.println(":p - Persistence mode");
        System.out.println(":config - Configuration mode");
        System.out.println("0 - Quit");
        return prompt("").split(" ")[0];
    }

//    public static void modes(String input){
//        switch (input){
//            case ":c":
//                Console.insertMode();
//                break;
//            case ":d":
//                Console.deleteMode();
//                break;
//            case ":r":
//                Console.displayMode();
//                break;
//            case ":u":
//                Console.changeMode();
//                break;
//            case ":p":
//                Console.persistenceMode();
//                break;
//            case ":config":
//                Console.configMode();
//                break;
//            default:
//                System.out.println("Unknown mode.");
//                break;
//        }
//    }

//    public static String insertMode(){
//        return prompt("Insertion mode");
//    }
//    public static String deleteMode(){}
//    public static String displayMode(){}
//    public static String changeMode(){}
//    public static String persistenceMode(){}
//    public static String configMode(){}

    public static int scanInt (String name) {
        Scanner scan = new Scanner(System.in);
        String test = "test";
        int input = -6543;
        while (input < 0) {
            try {
                System.out.println("Geben Sie eine positive Zahl fuer " + name + " ein:");
                input = scan.nextInt();
            } catch (Exception e) {
                System.out.println("Fehlerhafte Eingabe. Bitte nochmal versuchen: ");
                scan.next();
            }
        }
        return input;
    }

    public static String scanString (String name) {
        Scanner scan = new Scanner(System.in);
        String input = "-6543";
        while (input.equals("-6543")) {
            try {
                System.out.println("Enter " + name + ":");
                input = scan.nextLine();
            } catch (Exception e) {
                System.out.println("Fehlerhafte Eingabe. Bitte nochmal versuchen: ");
                scan.next();
            }
        }
        return input;
    }

    public static int getCommand(){
        Scanner scan = new Scanner(System.in);
        int input;
        System.out.println(" ");
        System.out.println("Wählen Sie eine Option, indem Sie die jeweilige Zahl (1-5) eingeben.");
        System.out.println("(1) Anlegen eines Produzenten");
        System.out.println("(2) Einfügen einer interactiver Videodatei");
        System.out.println("(3) Einfügen einer lizensierten Audio-Videodatei");
        System.out.println("(4) Auflisten aller Mediendateien");
        System.out.println("(5) Löschen einer Mediadatei");
        System.out.println("(0) Exit");
        System.out.println(" ");
        input = scanInt("ihre Auswahl");
        while (input < 0 || input > 5){
            System.out.println("Fehlerhafte Eingabe. Bitte nochmal versuchen: ");
            input = scanInt("hre Auswahl");
        }
        return input;
    }

    public static void printString(String string){
        System.out.println(string);
    }

    public static void printFiles (ArrayList<Uploadable> uploads) {
        for (int i = 0; i < uploads.size(); i++) {
            if (uploads.get(i) instanceof InteractiveVideoFile) {
                InteractiveVideoFile file = ((InteractiveVideoFile) uploads.get(i));
                System.out.println("InteractiveVideoFile: ");
                System.out.print("Produzent: " + file.getUploader().getName() + "\t");
                System.out.print("Tags: " + file.getTags().toString() + "\t");
                System.out.print("Bitrate: " + file.getBitrate() + "\t");
                System.out.println("Länge: " + file.getLength().getSeconds() + "\t");
                System.out.print("Größe: " + file.getSize().toString() + "\t");
                System.out.print("Adresse: " + file.getAddress() + "\t");
                System.out.print("Acces count: " + file.getAccessCount() + "\t");
                System.out.println("Upload date: " + file.getUploadDate().toString() + "\t");

                System.out.print("Encoding: " + file.getEncoding() + "\t");
                System.out.print("Höhe: " + file.getHeight() + "\t");
                System.out.print("Breite: " + file.getWidth() + "\t");
                System.out.println("Interaktionstyp: " + file.getType() + "\t");
                System.out.println(" ");
            } else if (uploads.get(i) instanceof LicensedAudioVideoFile) {
                LicensedAudioVideoFile file = ((LicensedAudioVideoFile) uploads.get(i));
                System.out.println("LicensedAudioVideoFile: ");
                System.out.print("Produzent: " + file.getUploader().getName() + "\t");
                System.out.print("Tags: " + file.getTags().toString() + "\t");
                System.out.print("Bitrate: " + file.getBitrate() + "\t");
                System.out.println("Länge: " + file.getLength().getSeconds() + "\t");
                System.out.print("Größe: " + file.getSize().toString() + "\t");
                System.out.print("Adresse: " + file.getAddress() + "\t");
                System.out.print("Acces count: " + file.getAccessCount() + "\t");
                System.out.println("Upload date: " + file.getUploadDate().toString() + "\t");

                System.out.print("Encoding: " + file.getEncoding() + "\t");
                System.out.print("Höhe: " + file.getHeight() + "\t");
                System.out.print("Breite: " + file.getWidth() + "\t");
                System.out.println("Samplingrate: " + file.getSamplingRate() + "\t");
                System.out.println("Holder: " + file.getHolder() + "\t");
                System.out.println(" ");
            }
        }
    }
    public static void producerNotListet(){
        System.out.println("Produzent der Datei nicht gelistet.");
    }

    public static void sizeTooBig() {
        System.out.println("Speicherkapazität reicht nicht aus.");
    }
}
