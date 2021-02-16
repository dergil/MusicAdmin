//package mediaDB.IO;
//
//import mediaDB.domainLogic.*;
//
//import java.math.BigDecimal;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//
//public class MediaFileAsStringsToMediaFile {
//
//    public static InteractiveVideoFile interactiveVideoFile(InteractiveVideoFileAsStrings stringFile) throws ParseException {
//        String type = stringFile.getType();
//        int width = Integer.parseInt(stringFile.getWidth());
//        int height = Integer.parseInt(stringFile.getHeight());
//        String encoding = stringFile.getEncoding();
//        long bitrate = Long.parseLong(stringFile.getBitrate());
//        Duration lenght = Duration.parse(stringFile.getLenght());
//        BigDecimal size = new BigDecimal(stringFile.getSize());
//        String address = stringFile.getAddress();
//
//        String tagsString = stringFile.getTags();
//        String cleanedTagsString = tagsString.replace("[", "");
//        cleanedTagsString = cleanedTagsString.replace("]", "");
//        String[] singleTags = cleanedTagsString.split(", ");
//        Collection<Tag> tags = new ArrayList<>();
//        for (String tag : singleTags){
//            tags.add(Tag.valueOf(tag));
//        }
//
//        long accessCount = Long.parseLong(stringFile.getAccessCount());
//        Uploader uploader = new Producer(stringFile.getUploader());
//        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
//        Date uploadDate = formatter.parse(stringFile.getUploadDate());
//        return new InteractiveVideoFile(type, width, height, encoding, bitrate, lenght, size, address, tags,
//                accessCount, uploader, uploadDate);
//    }
//
//    public static LicensedAudioVideoFile licensedAudioVideoFile(LicensedAudioVideoFileAsStrings stringFile) throws ParseException {
//        int samplingRate = Integer.parseInt(stringFile.getSamplingRate());
//        int width = Integer.parseInt(stringFile.getWidth());
//        int height = Integer.parseInt(stringFile.getHeight());
//        String encoding = stringFile.getEncoding();
//        String holder = stringFile.getHolder();
//        long bitrate = Long.parseLong(stringFile.getBitrate());
//        Duration lenght = Duration.parse(stringFile.getLength());
//        BigDecimal size = new BigDecimal(stringFile.getSize());
//        String address = stringFile.getAddress();
//
//        String tagsString = stringFile.getTags();
//        String cleanedTagsString = tagsString.replace("[", "");
//        cleanedTagsString = cleanedTagsString.replace("]", "");
//        String[] singleTags = cleanedTagsString.split(", ");
//        Collection<Tag> tags = new ArrayList<>();
//        for (String tag : singleTags){
//            tags.add(Tag.valueOf(tag));
//        }
//
//        long accessCount = Long.parseLong(stringFile.getAccessCount());
//        Uploader uploader = new Producer(stringFile.getUploader());
//        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
//        Date uploadDate = formatter.parse(stringFile.getUploadDate());
//        return new LicensedAudioVideoFile(samplingRate, width, height, encoding, holder, bitrate, lenght, size,
//                address, tags, accessCount, uploader, uploadDate);
//    }
//}
