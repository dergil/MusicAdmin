package mediaDB.domain_logic.observables;

import mediaDB.domain_logic.enums.Tag;
import mediaDB.observer.Observable;
import mediaDB.observer.Observer;

import java.io.Serializable;
import java.util.*;

public class TagObservable extends Observable  implements Serializable {
    private List<Observer> observerList = new LinkedList<>();
    private Map<String , Integer> tagOccurences = Collections.synchronizedMap(new HashMap<>());

    public TagObservable() {
        tagOccurences.put(Tag.Animal.toString(), 0);
        tagOccurences.put(Tag.Lifestyle.toString(), 0);
        tagOccurences.put(Tag.News.toString(), 0);
        tagOccurences.put(Tag.Tutorial.toString(), 0);
    }

    public void add (Collection<Tag> tags){
        for (Tag tag : tags){
            int oldQuantity = tagOccurences.get(tag.toString());
            tagOccurences.replace(tag.toString(), oldQuantity, ++oldQuantity);
        }
        advertise();
    }

    public void remove (Collection<Tag> tags){
        for (Tag tag : tags){
            int oldQuantity = tagOccurences.get(tag.toString());
            if (oldQuantity > 0)
                tagOccurences.replace(tag.toString(), oldQuantity, --oldQuantity);
        }
        advertise();
    }

    @Override
    public void register(Observer observer) {
        this.observerList.add(observer);
    }

    @Override
    public void deregister(Observer observer) {
        this.observerList.remove(observer);
    }

    @Override
    public void advertise() {
        for (Observer observer : observerList)
            observer.update();
    }

    public Map<String, Integer> getTagOccurences() {
        return tagOccurences;
    }

    public List<Observer> getObserverList() {
        return observerList;
    }

    public void setObserverList(List<Observer> observerList) {
        this.observerList = observerList;
    }

    public void setTagOccurences(Map<String, Integer> tagOccurences) {
        this.tagOccurences.clear();
        this.tagOccurences.putAll(tagOccurences);
    }
}
