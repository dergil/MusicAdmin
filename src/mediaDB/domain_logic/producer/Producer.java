package mediaDB.domain_logic.producer;

import java.io.Serializable;

public class Producer implements Uploader, Serializable {
    public String name;

    public Producer(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Producer{" +
                "name='" + name + '\'' +
                '}';
    }
}
