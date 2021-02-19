package mediaDB;

import mediaDB.domain_logic.file_interfaces.Content;
import mediaDB.domain_logic.files.InteractiveVideoFile;
import mediaDB.simulation.RandomMediadfileInstances;
import mediaDB.ui.Numerical;
import mediaDB.ui.gui.SortUploads;

public class Test {
    public static void main(String[] args) {
        RandomMediadfileInstances randomMediadfileInstances = new RandomMediadfileInstances();
        InteractiveVideoFile interactiveVideoFile = randomMediadfileInstances.randomInteractiveVideoFile();
        SortUploads sortUploads = new SortUploads();
        System.out.println(sortUploads.getProducer(interactiveVideoFile.toString()));


    }


}
