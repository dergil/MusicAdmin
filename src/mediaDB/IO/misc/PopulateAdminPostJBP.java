//package mediaDB.IO;
//
//import mediaDB.UI.cli.SizeObserver;
//import mediaDB.domainLogic.*;
//
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class PopulateAdminPostJBP {
//    Administration administration;
//    DomainLogicStateAsStrings savedState;
//
//    public PopulateAdminPostJBP(Administration administration, DomainLogicStateAsStrings savedState) {
//        this.administration = administration;
//        this.savedState = savedState;
//    }
//
//    public void process () throws ParseException {
//        processProducers();
//        processObserverList();
//        processInteractiveVideoFiles();
//        processLicensedAudioVideo();
//    }
//
//    private void processProducers (){
//        ArrayList<String> savedProducers = savedState.getProducers();
//        for (String s : savedProducers){
//            Producer producer = new Producer(s);
//            administration.addProducer(producer);
//        }
//    }
//
//    private void processObserverList (){
//        ArrayList<String> savedObservers = savedState.getObserverList();
//        List<SizeObserver> resultingList = new ArrayList<>();
//        for (String observer : savedObservers){
//            if (observer.equals("SizeObserver"))
//                resultingList.add(new SizeObserver(administration));
//            else System.out.println("Unknown observer");
//        }
//    }
//
//    private void processInteractiveVideoFiles () throws ParseException {
//        ArrayList<InteractiveVideoFileAsStrings> savedInteractiveVideoFiles = savedState.getInteractiveVideos();
//        for (InteractiveVideoFileAsStrings i : savedInteractiveVideoFiles){
//            InteractiveVideoFile file = MediaFileAsStringsToMediaFile.interactiveVideoFile(i);
//            administration.create(file);
//        }
//    }
//
//    private void processLicensedAudioVideo() throws ParseException {
//        ArrayList<LicensedAudioVideoFileAsStrings> savedLicensedAudioVideoFiles = savedState.getLicensedAudioVideos();
//        for (LicensedAudioVideoFileAsStrings l : savedLicensedAudioVideoFiles){
//            LicensedAudioVideoFile file = MediaFileAsStringsToMediaFile.licensedAudioVideoFile(l);
//            administration.create(file);
//        }
//    }
//}
