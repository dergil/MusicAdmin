package mediaDB.domain_logic.observables;

import mediaDB.domain_logic.enums.Tag;
import mediaDB.domain_logic.observables.TagObservable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class TagObservableTest {
    TagObservable tagObservable;
    TagObservable spyObervable;
    ArrayList<Tag> list = new ArrayList<>();

    @BeforeEach
    void setUp() {
        tagObservable = new TagObservable();
        spyObervable = spy(tagObservable);
        list.add(Tag.Animal);
        list.add(Tag.Lifestyle);
    }

    @Test
    void addListCheckAnimal() {
        tagObservable.add(list);
        assertEquals(1, tagObservable.getTagOccurences().get("Animal"));
    }

    @Test
    void addListCheckLifestyle() {
        tagObservable.add(list);
        assertEquals(1, tagObservable.getTagOccurences().get("Lifestyle"));
    }

    @Test
    void addSpyAdvertise() {
        spyObervable.add(list);
        verify(spyObervable).advertise();
    }

    @Test
    void removeAnimal() {
        tagObservable.add(list);
        list.remove(Tag.Lifestyle);
        tagObservable.remove(list);
        assertEquals(0, tagObservable.getTagOccurences().get("Animal"));
    }

    @Test
    void removeSpyAdvertise() {
        spyObervable.remove(list);
        verify(spyObervable).advertise();
    }
}