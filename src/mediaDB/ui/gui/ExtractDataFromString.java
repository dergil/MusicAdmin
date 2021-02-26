package mediaDB.ui.gui;

import mediaDB.ui.Numerical;

public class ExtractDataFromString {
    public String getProducerDisplayVersion(String upload){
        int lastChar = upload.indexOf("=");
        return upload.substring(0, lastChar);
    }

    public String getProducer(String upload){
        int beginindex = upload.lastIndexOf("name='") + 6;
        String producer = "";
        String currentChar = "";
        do {
            currentChar = upload.substring(beginindex, beginindex + 1);
            producer = producer.concat(currentChar);
            beginindex++;
        } while (!currentChar.equals("'"));
        producer = producer.replaceAll("'", "");
        return producer;
    }

    public int getAccessCount(String upload){
        int beginindex = upload.lastIndexOf("accessCount=") + 12;
        String accessCount = "";
        String currentChar = "";
        do {
            currentChar = upload.substring(beginindex, beginindex + 1);
            accessCount = accessCount.concat(currentChar);
            beginindex++;
        } while (Numerical.check(currentChar));
        accessCount = accessCount.replaceAll(",", "");
        return Integer.parseInt(accessCount);
    }



    public int getAddress(String upload){
        int beginindex = upload.lastIndexOf("address='") + 9;
        String address = "";
        String currentChar = "";
        do {
            currentChar = upload.substring(beginindex, beginindex + 1);
            address = address.concat(currentChar);
            beginindex++;
        } while (Numerical.check(currentChar));
        address = address.replaceAll("'", "");
        return Integer.parseInt(address);
    }
}
