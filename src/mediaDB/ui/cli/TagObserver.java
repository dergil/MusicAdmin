package mediaDB.ui.cli;

import mediaDB.domain_logic.Tag;
import mediaDB.domain_logic.TagObservable;
import mediaDB.observer.Observer;

import java.util.ArrayList;
import java.util.Map;

public class TagObserver implements Observer {
    TagObservable tagObservable;
    ArrayList<String> currentTags = new ArrayList<>();
    ArrayList<String> previousTags = new ArrayList<>();

    public TagObserver(TagObservable tagObservable) {
        this.tagObservable = tagObservable;
    }

    @Override
    public void update() {
        currentTags.clear();
        Map<String , Integer> tagOccurences = tagObservable.getTagOccurences();
        for (Tag tag: Tag.values()){
            if (tagOccurences.get(tag.toString()) > 0)
                currentTags.add(tag.toString());
        }
        for (String tag : currentTags){
            if (!previousTags.contains(tag))
                System.out.println("File with tag " + tag + " added.");
        }
        for (String tag : previousTags) {
            if (!currentTags.contains(tag))
                System.out.println("Last file with tag " + tag + " deleted");
        }
        previousTags.clear();
        previousTags.addAll(currentTags);
    }
}
