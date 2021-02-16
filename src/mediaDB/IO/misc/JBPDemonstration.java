//package mediaDB.IO;
//
//import mediaDB.UI.cli.SizeObserver;
//import mediaDB.domainLogic.Administration;
//import mediaDB.domainLogic.Producer;
//import mediaDB.simulation.RandomMediadfileInstances;
//
//import java.beans.XMLDecoder;
//import java.beans.XMLEncoder;
//import java.io.*;
//import java.text.ParseException;
//
//public class JBPDemonstration {
//    public void process() throws ParseException {
//        System.out.println("JBP demonstration: ");
//        System.out.println(" ");
//        System.out.println("Administration pre-JBP: ");
//        Administration administration = new Administration();
////        Filling Administration with data
//        SizeObserver sizeObserver = new SizeObserver(administration);
//        RandomMediadfileInstances random = new RandomMediadfileInstances();
//        administration.addProducer(new Producer("Mike"));
//        administration.addProducer(new Producer("Dave"));
//        administration.addProducer(new Producer("Hannes"));
//        administration.create(random.randomInteractiveVideoFile());
//        administration.create(random.randomInteractiveVideoFile());
//        administration.create(random.randomLicensedAudioVideoFile());
//        administration.create(random.randomLicensedAudioVideoFile());
//        DomainLogicStateAsStrings domainLogicState = new DomainLogicStateAsStrings();
//        DomainLogicStateAsStrings loadedState = null;
//        AdministrationToDomainLogicStateAsStrings conversion =
//                new AdministrationToDomainLogicStateAsStrings(domainLogicState, administration);
//        conversion.process();
//        System.out.println(administration.toString());
//        System.out.println(" ");
//        System.out.println(domainLogicState.toString());
//        System.out.println("---");
//
//        try(XMLEncoder encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream("JBPDomainLogic.xml")));){
//            encoder.writeObject(domainLogicState);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        try (XMLDecoder decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream("JBPDomainLogic.xml")));){
//            loadedState=(DomainLogicStateAsStrings)decoder.readObject();
//        }
//        catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        assert loadedState != null;
//        System.out.println(loadedState.toString());
//        Administration newAdministration = new Administration();
//        PopulateAdminPostJBP populateAdmin = new PopulateAdminPostJBP(newAdministration, loadedState);
//        populateAdmin.process();
//        System.out.println(" ");
//        System.out.println("Administration post-JBP: ");
//        System.out.println(newAdministration.toString());
//    }
//}
