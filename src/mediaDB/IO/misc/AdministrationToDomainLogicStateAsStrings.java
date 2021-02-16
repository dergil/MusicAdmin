//package mediaDB.IO;
//
//import mediaDB.UI.cli.SizeObserver;
//import mediaDB.domainLogic.*;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Observer;
//
//public class AdministrationToDomainLogicStateAsStrings {
//    DomainLogicStateAsStrings logicAsStrings = new DomainLogicStateAsStrings();
//    Administration administration = new Administration();
//
//    public AdministrationToDomainLogicStateAsStrings(DomainLogicStateAsStrings logicAsStrings, Administration administration) {
//        this.logicAsStrings = logicAsStrings;
//        this.administration = administration;
//    }
//
//    public void process(){
//        logicAsStrings.setProducers(convertProducers(administration.getProducers()));
//        logicAsStrings.setObserverList(convertObservers(administration.getObserverList()));
//        logicAsStrings.setInteractiveVideos(convertInteractiveVideoFiles(administration.read()));
//        logicAsStrings.setLicensedAudioVideos(convertLicensedAudioVideoFile(administration.read()));
//    }
//
//    private ArrayList<String> convertProducers(ProducerDB originalProducers){
//        ArrayList<String> convertedProducers = new ArrayList<>();
//        HashSet<Uploader> producerSet = originalProducers.getProducers();
//        for (Uploader u : producerSet){
//            convertedProducers.add(u.getName());
//        }
//        return convertedProducers;
//    }
//
//    private ArrayList<String> convertObservers(List<Observer> originalObservers){
//        ArrayList<String> convertedObservers = new ArrayList<>();
//        for (Observer o : originalObservers){
//            if (o instanceof SizeObserver)
//                convertedObservers.add("SizeObserver");
//            else System.out.println("Unknown observer");
//        }
//        return convertedObservers;
//    }
//
//    private ArrayList<InteractiveVideoFileAsStrings> convertInteractiveVideoFiles (ArrayList<Uploadable> uploadables){
//        ArrayList<InteractiveVideoFileAsStrings> convertedInteractiveVideoFiles = new ArrayList<>();
//        for (Uploadable u : uploadables){
//            if (u instanceof InteractiveVideoFile){
//                InteractiveVideoFileAsStrings convertedFile = MediaFileToMediaFileAsStrings.interactiveVideoFile((InteractiveVideoFile) u);
//                convertedInteractiveVideoFiles.add(convertedFile);
//            }
//        }
//        return convertedInteractiveVideoFiles;
//    }
//
//    private ArrayList<LicensedAudioVideoFileAsStrings> convertLicensedAudioVideoFile (ArrayList<Uploadable> uploadables){
//        ArrayList<LicensedAudioVideoFileAsStrings> convertedLicensedAudioVideoFiles = new ArrayList<>();
//        for (Uploadable u : uploadables){
//            if (u instanceof LicensedAudioVideoFile){
//                LicensedAudioVideoFileAsStrings convertedFile = MediaFileToMediaFileAsStrings.licensedAudioVideoFile((LicensedAudioVideoFile) u);
//                convertedLicensedAudioVideoFiles.add(convertedFile);
//            }
//        }
//        return convertedLicensedAudioVideoFiles;
//    }
//}
