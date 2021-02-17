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

    public static void printString(String string){
        System.out.println(string);
    }

    public static void producerNotListet(){
        System.out.println("Produzent der Datei nicht gelistet.");
    }

    public static void sizeTooBig() {
        System.out.println("Speicherkapazität reicht nicht aus.");
    }

}
