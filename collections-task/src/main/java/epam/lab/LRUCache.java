package epam.lab;
import java.util.*;
import java.util.stream.Collectors;

public class LRUCache<T> {
    private final Deque<T> cache = new ArrayDeque<>();
    private static final int MAX_SIZE = 3;

    public Deque<T> getCache() {
        return cache;
    }

    public void lruCaching(T element) {
        T oldElement = null;

        if (!cache.isEmpty()) {
            if (cache.size() < MAX_SIZE) {
                List<T> list = cache
                        .stream()
                        .filter(x -> x == element)
                        .collect(Collectors.toList());

                if (!list.isEmpty()) {
                    oldElement = list.get(0);
                }

                if (oldElement != null) {
                    cache.remove(oldElement);
                }
            } else {
                List<T> list = cache
                        .stream()
                        .filter(x -> x == element)
                        .collect(Collectors.toList());

                if (!list.isEmpty()) {
                    oldElement = list.get(0);
                }

                if (oldElement != null) {
                    cache.remove(oldElement);
                } else {
                    cache.removeFirst();
                }
            }
        }
        cache.addLast(element);
    }
}
