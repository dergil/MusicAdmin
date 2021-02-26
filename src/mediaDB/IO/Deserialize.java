package mediaDB.IO;

import mediaDB.domain_logic.*;
import mediaDB.domain_logic.file_interfaces.Content;
import mediaDB.domain_logic.observables.SizeObservable;
import mediaDB.domain_logic.observables.TagObservable;
import mediaDB.domain_logic.file_interfaces.Uploadable;
import mediaDB.domain_logic.producer.ProducerRepository;
import mediaDB.domain_logic.producer.Uploader;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class Deserialize {
    SizeObservable sizeObservable;
    TagObservable tagObservable;
    MediaFileRepository mediaFileRepository;
    ProducerRepository producerRepository;
    AddressRepository addressRepository;
    DeserializeDomainContent deserializeDomainContent;
    RandomAccess randomAccess;
    ObjectInputStream ois;

    public Deserialize(SizeObservable sizeObservable, TagObservable tagObservable, MediaFileRepository mediaFileRepository,
                       ProducerRepository producerRepository, AddressRepository addressRepository,
                       DeserializeDomainContent deserializeDomainContent, RandomAccess randomAccess) {
        this.sizeObservable = sizeObservable;
        this.tagObservable = tagObservable;
        this.mediaFileRepository = mediaFileRepository;
        this.producerRepository = producerRepository;
        this.addressRepository = addressRepository;
        this.deserializeDomainContent = deserializeDomainContent;
        this.randomAccess = randomAccess;
    }

    public void deserialize() throws IOException {
        ois=new ObjectInputStream(new FileInputStream(SerFilenames.CURRENTSIZE.toString()));
        BigDecimal currentSize = deserializeDomainContent.deserializeBigDecimal(ois);
        sizeObservable.setCurrentSize(currentSize);

        ois=new ObjectInputStream(new FileInputStream(SerFilenames.TAGOCCURENCES.toString()));
        Map<String , Integer> stringIntegerMap = deserializeDomainContent.deserializeStringIntegerMap(ois);
        tagObservable.setTagOccurences(stringIntegerMap);

        ois=new ObjectInputStream(new FileInputStream(SerFilenames.UPLOADABLELIST.toString()));
        List<Uploadable> uploadableList = deserializeDomainContent.deserializeUploadableList(ois);
        for (Uploadable uploadable : uploadableList){
            if (!fileExisting(((Content)uploadable).getAddress()))
                mediaFileRepository.create(uploadable);
        }

        ois=new ObjectInputStream(new FileInputStream(SerFilenames.PRODUCERSET.toString()));
        ArrayList<Uploader> uploaderArrayList = deserializeDomainContent.deserializeUploaderSet(ois);
        for (Uploader uploader : uploaderArrayList){
            producerRepository.addProducer(uploader);
        }

        ois=new ObjectInputStream(new FileInputStream(SerFilenames.ADDRESSESSET.toString()));
        Set<Integer> integerSet = deserializeDomainContent.deserializeIntegerSet(ois);
        addressRepository.setAddresses(integerSet);

        ois=new ObjectInputStream(new FileInputStream(SerFilenames.RANDOMACCESSSAVEDMEDIAFILES.toString()));
        ArrayList<SavedMediaFile> savedMediaFiles = deserializeDomainContent.deserializeSavedMediaFiles(ois);
        randomAccess.setSavedMediaFiles(savedMediaFiles);

        ois=new ObjectInputStream(new FileInputStream(SerFilenames.RANDOMACCESSCURRENTOFFSET.toString()));
        int currentOffset = deserializeDomainContent.deserializeCurrentOffset(ois);
        randomAccess.setCurrentOffset(currentOffset);
    }

    private boolean fileExisting(String address){
        return mediaFileRepository.findByAddress(address) != null;
    }
}
