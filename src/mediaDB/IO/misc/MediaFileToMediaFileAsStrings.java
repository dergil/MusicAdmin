//package mediaDB.IO;
//
//import mediaDB.domainLogic.InteractiveVideoFile;
//import mediaDB.domainLogic.LicensedAudioVideoFile;
//
//public class MediaFileToMediaFileAsStrings {
//
//    public static InteractiveVideoFileAsStrings interactiveVideoFile(InteractiveVideoFile file){
//        InteractiveVideoFileAsStrings stringFile = new InteractiveVideoFileAsStrings();
//        stringFile.setType(file.getType().toString());
//        stringFile.setWidth(Integer.toString(file.getWidth()));
//        stringFile.setHeight(Integer.toString(file.getHeight()));
//        stringFile.setEncoding(file.getEncoding());
//        stringFile.setBitrate(Long.toString(file.getBitrate()));
//        stringFile.setLenght(file.getLength().toString());
//        stringFile.setSize(file.getSize().toString());
//        stringFile.setAddress(file.getAddress());
//        stringFile.setTags(file.getTags().toString());
//        stringFile.setAccessCount(Long.toString(file.getAccessCount()));
//        stringFile.setUploader(file.getUploader().getName());
//        stringFile.setUploadDate(file.getUploadDate().toString());
//        return stringFile;
//    }
//
//    public static LicensedAudioVideoFileAsStrings licensedAudioVideoFile(LicensedAudioVideoFile file){
//        LicensedAudioVideoFileAsStrings stringFile = new LicensedAudioVideoFileAsStrings();
//        stringFile.setSamplingRate(Integer.toString(file.getSamplingRate()));
//        stringFile.setWidth(Integer.toString(file.getWidth()));
//        stringFile.setHeight(Integer.toString(file.getHeight()));
//        stringFile.setEncoding(file.getEncoding());
//        stringFile.setHolder(file.getHolder());
//        stringFile.setBitrate(Long.toString(file.getBitrate()));
//        stringFile.setLength(file.getLength().toString());
//        stringFile.setSize(file.getSize().toString());
//        stringFile.setAddress(file.getAddress());
//        stringFile.setTags(file.getTags().toString());
//        stringFile.setAccessCount(Long.toString(file.getAccessCount()));
//        stringFile.setUploader(file.getUploader().getName());
//        stringFile.setUploadDate(file.getUploadDate().toString());
//        return stringFile;
//    }
//}
