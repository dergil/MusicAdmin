package mediaDB.IO;

import mediaDB.domain_logic.*;
import mediaDB.domain_logic.observables.SizeObservable;
import mediaDB.domain_logic.observables.TagObservable;
import mediaDB.domain_logic.file_interfaces.Uploadable;
import mediaDB.domain_logic.producer.ProducerRepository;
import mediaDB.domain_logic.producer.Uploader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.util.*;

public class Deserialize {
    SizeObservable sizeObservable;
    TagObservable tagObservable;
    MediaFileRepository mediaFileRepository;
    ProducerRepository producerRepository;
    AddressRepository addressRepository;
    DeserializeDomainContent deserializeDomainContent;
    ObjectInputStream ois;

    public Deserialize(SizeObservable sizeObservable, TagObservable tagObservable, MediaFileRepository mediaFileRepository,
                       ProducerRepository producerRepository, AddressRepository addressRepository,
                       DeserializeDomainContent deserializeDomainContent) {
        this.sizeObservable = sizeObservable;
        this.tagObservable = tagObservable;
        this.mediaFileRepository = mediaFileRepository;
        this.producerRepository = producerRepository;
        this.addressRepository = addressRepository;
        this.deserializeDomainContent = deserializeDomainContent;
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
    }
}
