package mediaDB.IO;

import mediaDB.domain_logic.file_interfaces.Content;
import mediaDB.domain_logic.file_interfaces.Uploadable;

import java.io.*;
import java.util.ArrayList;

/* Sources:
    https://stackoverflow.com/questions/2836646/java-serializable-object-to-byte-array
    https://www.javatpoint.com/java-randomaccessfile-class
*/

public class RandomAccess {
//    liste von files mit fileType, adresse, größe, offset
    RandomAccessFile randomAccessFile;
    ArrayList<SavedMediaFile> savedMediaFiles;
    int currentOffset;

    public RandomAccess() throws FileNotFoundException {
        randomAccessFile = new RandomAccessFile("RandomAccessFile", "rw");
        savedMediaFiles = new ArrayList<>();
        currentOffset = 0;
    }

    public void save(Uploadable uploadable) throws IOException {
        String fileType = uploadable.getFileType();
        String address = ((Content) uploadable).getAddress();
        byte[] serializedUploadable = serialize(uploadable);
        int length = serializedUploadable.length;
        System.out.println(length);
        SavedMediaFile savedMediaFile = new SavedMediaFile(fileType, address, length , currentOffset);
        randomAccessFile.seek(currentOffset);
        randomAccessFile.write(serializedUploadable);
        currentOffset += length;
        savedMediaFiles.add(savedMediaFile);
    }

    private byte[] serialize (Uploadable uploadable) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(uploadable);
        out.flush();
        return bos.toByteArray();
    }

    public Uploadable load(String address) throws IOException, ClassNotFoundException {
        for (SavedMediaFile savedMediaFile : savedMediaFiles){
            if (savedMediaFile.getAddress().equals(address))
                return load(savedMediaFile);
        }
        System.out.println("File  with this address is not saved.");
        return null;
    }

    private Uploadable load(SavedMediaFile savedMediaFile) throws IOException, ClassNotFoundException {
        int size = savedMediaFile.getSize();
        byte[] serializedUploadable = new byte[size];
        int offset = savedMediaFile.getOffset();
        randomAccessFile.seek(offset);
        randomAccessFile.read(serializedUploadable);
        return deserialize(serializedUploadable);
    }

    private Uploadable deserialize (byte[] serializedUploadable) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(serializedUploadable);
        ObjectInput in = new ObjectInputStream(bis);
        return (Uploadable) in.readObject();
    }

    public boolean isEmpty(){
        return savedMediaFiles.isEmpty();
    }

    public RandomAccessFile getRandomAccessFile() {
        return randomAccessFile;
    }

    public ArrayList<SavedMediaFile> getSavedMediaFiles() {
        return savedMediaFiles;
    }
}
