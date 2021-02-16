package mediaDB.IO;

import mediaDB.domain_logic.*;
import mediaDB.domain_logic.file_interfaces.Uploadable;
import mediaDB.observer.Observer;

import java.io.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Serialize {
    SizeObservable sizeObservable;
    TagObservable tagObservable;
    MediaFileRepository mediaFileRepository;
    ProducerRepository producerRepository;
    AddressRepository addressRepository;

    public Serialize(SizeObservable sizeObservable, TagObservable tagObservable,
                     MediaFileRepository mediaFileRepository, ProducerRepository producerRepository,
                     AddressRepository addressRepository) {
        this.sizeObservable = sizeObservable;
        this.tagObservable = tagObservable;
        this.mediaFileRepository = mediaFileRepository;
        this.producerRepository = producerRepository;
        this.addressRepository = addressRepository;
    }

//    TODO: observerlisten werden nicht serialisiert, ist das richtig so?
    public void serialize(){

        BigDecimal currentSize = sizeObservable.getCurrentSize();
        serializeObject((Serializable) currentSize, SerFilenames.CURRENTSIZE.toString());

        Map<String , Integer> stringIntegerMap = tagObservable.getTagOccurences();
        serializeObject((Serializable) stringIntegerMap, SerFilenames.TAGOCCURENCES.toString());

        List<Uploadable> uploadableList = mediaFileRepository.read();
        serializeObject((Serializable) uploadableList, SerFilenames.UPLOADABLELIST.toString());

        Collection<Uploader> uploaderCollection = producerRepository.getProducers();
        serializeObject((Serializable) uploaderCollection, SerFilenames.PRODUCERSET.toString());

        Collection<Integer> integerCollection = addressRepository.getAddresses();
        serializeObject((Serializable) integerCollection, SerFilenames.ADDRESSESSET.toString());
    }

    private void serializeObject (Serializable object, String filename){
        try (ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(filename))){
            serialize(oos,object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void serialize(ObjectOutput objectOutput, Serializable object) throws IOException {
        objectOutput.writeObject(object);
    }



}