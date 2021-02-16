package mediaDB.IO;

import mediaDB.domain_logic.*;
import mediaDB.domain_logic.file_interfaces.Uploadable;
import mediaDB.observer.Observer;

import java.math.BigDecimal;
import java.util.*;

public class Deserialize {
    SizeObservable sizeObservable;
    TagObservable tagObservable;
    MediaFileRepository mediaFileRepository;
    ProducerRepository producerRepository;
    AddressRepository addressRepository;
    DeserializeDomainContent deserializeDomainContent;

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

    public void deserialzie(){
        BigDecimal currentSize = deserializeDomainContent.deserializeBigDecimal(SerFilenames.CURRENTSIZE.toString());
        sizeObservable.setCurrentSize(currentSize);

        Map<String , Integer> stringIntegerMap = deserializeDomainContent.deserializeStringIntegerMap(SerFilenames.TAGOCCURENCES.toString());
        tagObservable.setTagOccurences(stringIntegerMap);

        List<Uploadable> uploadableList = deserializeDomainContent.deserializeUploadableList(SerFilenames.UPLOADABLELIST.toString());
        for (Uploadable uploadable : uploadableList){
            mediaFileRepository.create(uploadable);
        }

        ArrayList<Uploader> uploaderArrayList = deserializeDomainContent.deserializeUploaderSet(SerFilenames.PRODUCERSET.toString());
        for (Uploader uploader : uploaderArrayList){
            producerRepository.addProducer(uploader);
        }

        Set<Integer> integerSet = deserializeDomainContent.deserializeIntegerSet(SerFilenames.ADDRESSESSET.toString());
        addressRepository.setAddresses(integerSet);
    }
}
