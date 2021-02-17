package mediaDB.simulation;

import mediaDB.domain_logic.enums.Tag;
import mediaDB.domain_logic.files.InteractiveVideoFile;
import mediaDB.domain_logic.files.LicensedAudioVideoFile;
import mediaDB.domain_logic.producer.Producer;
import mediaDB.domain_logic.file_interfaces.Uploadable;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class RandomMediadfileInstances {
    int bitrate;
    Duration length;
    int lastAddress = 0;
    ArrayList<Producer> producers = new ArrayList<>();

    public RandomMediadfileInstances() {
        initialiseUploaders();
    }

    public InteractiveVideoFile randomInteractiveVideoFile() {
        length = randomDuration();
        bitrate = randomNumber4();
//        return new InteractiveVideoFile("InteractiveVideo", randomType(), randomNumber3(), randomNumber3(), randomEncoding(),
//                randomBitrate(), randomDuration(), new BigDecimal(length * bitrate), randomAddress(), randomTags(),
//                0, randomUploader(), new Date());
        return new InteractiveVideoFile("file", randomType(), randomNumber3(), randomNumber3(), randomEncoding(),
                randomAddress(), randomTags(), 0, bitrate, length, new BigDecimal(length.getSeconds() * bitrate),
                randomUploader(), new Date());
    }

    public LicensedAudioVideoFile randomLicensedAudioVideoFile() {
        length = randomDuration();
        bitrate = randomNumber4();
//        return new LicensedAudioVideoFile(randomNumber5(), randomNumber3(), randomNumber3(), randomEncoding(),
//                randomHolder(), randomBitrate(), randomDuration(), new BigDecimal(length * bitrate),
//                randomAddress(), randomTags(), 0, randomUploader(), new Date());
        return new LicensedAudioVideoFile("file", randomNumber5(), randomNumber3(), randomNumber3(), randomEncoding(),
                randomAddress(), randomTags(), 0, randomHolder(), bitrate, length,
                new BigDecimal(bitrate*length.getSeconds()), randomUploader(), new Date());
    }

    public Uploadable randomUploadable() {
        int number = randomNumber3();
        if (number < 550)
            return randomInteractiveVideoFile();
        else return randomLicensedAudioVideoFile();
    }

    private int randomNumber6() {
        int min = 100000;
        int max = 999999;
        return (int) (Math.random() * (max - min + 1) + min);
    }

    private int randomNumber3() {
        int min = 100;
        int max = 999;
        return (int) (Math.random() * (max - min + 1) + min);
    }

    private int randomNumber4() {
        int min = 1000;
        int max = 9999;
        return (int) (Math.random() * (max - min + 1) + min);
    }

    private int randomNumber5() {
        int min = 10000;
        int max = 99999;
        return (int) (Math.random() * (max - min + 1) + min);
    }

    private int randomBitrate() {
        bitrate = randomNumber4();
        return bitrate;
    }

    private String randomAddress() {
        return String.valueOf(++lastAddress);
    }

    private Collection<Tag> randomTags() {
        int number = randomNumber6();
        List<Tag> al = new ArrayList<Tag>();
        if (number < 333333) {
            al.add(Tag.Animal);
            al.add(Tag.Tutorial);
            return al;
        }
        else if (number > 333333 && number < 666666) {
            al.add(Tag.Animal);
            al.add(Tag.Lifestyle);
        } else {
            al.add(Tag.News);
            al.add(Tag.Tutorial);
        }
        return al;
    }

    private String randomType() {
        int number = randomNumber6();
        if (number < 333333) {
            return("Abstimmung");
        }
        else if (number > 333333 && number < 666666) {
            return("Story");
        } else {
            return("Lernvideo");
        }
    }

    private String randomEncoding() {
        int number = randomNumber6();
        if (number < 333333) {
            return("DWT");
        }
        else if (number > 333333 && number < 666666) {
            return("ABC");
        } else {
            return("DEF");
        }
    }

    private Producer randomUploader() {
        int number = randomNumber6();
        if (number < 333333) {
            return producers.get(0);
        }
        else if (number > 333333 && number < 666666) {
            return producers.get(1);
        } else {
            return producers.get(2);
        }
    }

    private void initialiseUploaders(){
        producers.add(new Producer("Mike"));
        producers.add(new Producer("Dave"));
        producers.add(new Producer("Hannes"));
    }

    private Duration randomDuration(){
        int lengthLocal;
        int number = randomNumber6();
        if (number < 333333) {
            lengthLocal = 3600;
            return Duration.ofSeconds(3600);
        }
        else if (number > 333333 && number < 666666) {
            lengthLocal = 5400;
            return Duration.ofSeconds(5400);
        } else {
            lengthLocal = 600;
            return Duration.ofSeconds(600);
        }
    }

    private String randomHolder() {
        int number = randomNumber6();
        if (number < 333333) {
            return("EdBangerRecords");
        }
        else if (number > 333333 && number < 666666) {
            return("Universal");
        } else {
            return("Sony");
        }
    }

    public ArrayList<Producer> getProducers() {
        return producers;
    }
}

