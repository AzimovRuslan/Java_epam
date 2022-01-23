package epam.lab;
import java.util.*;

public class LRUCache<T> {
    private Deque<T> cache = new ArrayDeque<>();
    private static final int MAX_SIZE = 3;

    public Deque<T> getCache() {
        return cache;
    }

    public void lruCaching(T element) {
        T oldElement = null;

        if (cache.size() < MAX_SIZE) {
            if (!cache.isEmpty()) {
                for (T cacheElement : cache) {
                    if (cacheElement == element) {
                        oldElement = cacheElement;
                    }
                }

                if (oldElement != null) {
                    cache.remove(oldElement);
                }
            }
        } else {
            for (T cacheElement : cache) {
                if (cacheElement == element) {
                    oldElement = cacheElement;
                }
            }

            if (oldElement != null) {
                cache.remove(oldElement);
            } else {
                cache.removeFirst();
            }
        }
        cache.addLast(element);
    }
}
