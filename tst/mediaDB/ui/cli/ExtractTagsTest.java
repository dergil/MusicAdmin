package mediaDB.ui.cli;

import mediaDB.domain_logic.enums.Tag;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class ExtractTagsTest {
    String input = "Animal,Lifestyle";
    @Test
    void extractAnimal() {
        Collection<Tag> result = ExtractTags.extract(input);
        assertTrue(result.contains(Tag.Animal));
    }

    @Test
    void extractLifestyle() {
        Collection<Tag> result = ExtractTags.extract(input);
        assertTrue(result.contains(Tag.Lifestyle));
    }
}