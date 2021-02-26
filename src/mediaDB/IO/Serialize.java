package mediaDB.IO;

import mediaDB.domain_logic.*;
import mediaDB.domain_logic.observables.SizeObservable;
import mediaDB.domain_logic.observables.TagObservable;
import mediaDB.domain_logic.file_interfaces.Uploadable;
import mediaDB.domain_logic.producer.ProducerRepository;
import mediaDB.domain_logic.producer.Uploader;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Serialize {
    SizeObservable sizeObservable;
    TagObservable tagObservable;
    MediaFileRepository mediaFileRepository;
    ProducerRepository producerRepository;
    AddressRepository addressRepository;
    RandomAccess randomAccess;

    public Serialize(SizeObservable sizeObservable, TagObservable tagObservable, MediaFileRepository mediaFileRepository,
                     ProducerRepository producerRepository, AddressRepository addressRepository, RandomAccess randomAccess) {
        this.sizeObservable = sizeObservable;
        this.tagObservable = tagObservable;
        this.mediaFileRepository = mediaFileRepository;
        this.producerRepository = producerRepository;
        this.addressRepository = addressRepository;
        this.randomAccess = randomAccess;
    }

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

        ArrayList<SavedMediaFile> savedMediaFiles = randomAccess.getSavedMediaFiles();
        serializeObject((Serializable) savedMediaFiles, SerFilenames.RANDOMACCESSSAVEDMEDIAFILES.toString());

        int currentOffset = randomAccess.getCurrentOffset();
        serializeObject((Serializable) currentOffset, SerFilenames.RANDOMACCESSCURRENTOFFSET.toString());
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
