package mediaDB.routing;

import java.io.IOException;

public interface EventListener<T> {
    void onMediaEvent(T event) throws IOException;
    boolean supports(Class<?> clazz);
}
