//package mediaDB.ui.gui;
//
//import mediaDB.domainLogic.AdminListeners;
//import mediaDB.domainLogic.Administration;
//import mediaDB.domainLogic.Producer;
//import mediaDB.domainLogic.Uploadable;
//import mediaDB.routing.events.files.InteractiveVideoEvent;
//import mediaDB.routing.events.files.LicensedAudioVideoEvent;
//
//import java.util.ArrayList;
//
//public class AdminCommunication {
//    Administration admin;
//    AdminListeners adminListeners;
//
//    public AdminCommunication(Administration admin) {
//        this.admin = admin;
//        this.adminListeners = new AdminListeners(admin);
//    }
//
//    public void createInteraactiveVideoFile(InteractiveVideoEvent event){
//        adminListeners.onInteractiveVideoEvent(event);
//    }
//
//    public void createLicensedAudioVideoFile(LicensedAudioVideoEvent event){
//        adminListeners.onLicensedAudioVideoEvent(event);
//    }
//
//    public void deleteFile (int index){
//        admin.delete(index);
//    }
//
//    public void addProducer (String name){
//        admin.addProducer(new Producer(name));
//    }
//
//    public ArrayList<Uploadable> read(){
//        return admin.read();
//    }
//}
