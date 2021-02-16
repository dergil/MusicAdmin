//package mediaDB.IO;
//
//import mediaDB.UI.cli.SizeObserver;
//import mediaDB.domainLogic.*;
//import mediaDB.simulation.RandomMediadfileInstances;
//
//public class PopulateAdminForSerialization {
//
//    Administration administration;
//    RandomMediadfileInstances random = new RandomMediadfileInstances();
//    private int nextAddress = 0;
//
//    public PopulateAdminForSerialization(Administration administration) {
//        this.administration = administration;
//    }
//
//    public void populate(){
//        addProducers();
//        addFiles();
//        addObserver();
//    }
//
//    private void addProducers(){
//        administration.addProducer(new Producer("Mike"));
//        administration.addProducer(new Producer("Dave"));
//        administration.addProducer(new Producer("Hannes"));
//    }
//
//    private void addFiles(){
//        InteractiveVideoFile file = random.randomInteractiveVideoFile();
//        addAddressToInteractiveVideoFile(file);
//        administration.create(file);
//        LicensedAudioVideoFile file1 = random.randomLicensedAudioVideoFile();
//        addAddressToLicensedAudioVideoFile(file1);
//        administration.create(file1);
//        InteractiveVideoFile file2 = random.randomInteractiveVideoFile();
//        addAddressToInteractiveVideoFile(file2);
//        administration.create(file2);
//        LicensedAudioVideoFile file3 = random.randomLicensedAudioVideoFile();
//        addAddressToLicensedAudioVideoFile(file3);
//        administration.create(file3);
//
//        administration.setNextAddress(nextAddress);
//    }
//
//    private void addObserver(){
//        SizeObserver sizeObserver = new SizeObserver(administration);
//    }
//
//    private void addAddressToInteractiveVideoFile(InteractiveVideoFile file){
//        file.setAddress(String.valueOf(nextAddress+1));
//        nextAddress++;
//    }
//
//    private void addAddressToLicensedAudioVideoFile(LicensedAudioVideoFile file){
//        file.setAddress(String.valueOf(nextAddress+1));
//        nextAddress++;
//    }
//}
