package mediaDB.ui.cli;

import mediaDB.domain_logic.enums.Tag;

import java.util.ArrayList;
import java.util.Collection;

public class ExtractTags {
    public static Collection<Tag> extract(String input){
        String[] splitTags = input.split(",");
        Collection<Tag> tags = new ArrayList<>();

        for (String splitTag : splitTags) {
            if (existingTag(splitTag))
                tags.add(Tag.valueOf(splitTag));
            else System.out.println("Invalid Tag");
        }
        return tags;
    }

    private static boolean existingTag(String input){
        boolean animal = Tag.Animal.toString().equals(input);
        boolean lifestyle = Tag.Lifestyle.toString().equals(input);
        boolean news = Tag.News.toString().equals(input);
        boolean tutorial = Tag.Tutorial.toString().equals(input);
        return animal || lifestyle || news || tutorial;
    }
}
