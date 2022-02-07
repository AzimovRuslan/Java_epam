package epam.lab;

import java.util.*;

public class LFUCache<K> {
    private final Map<K, Integer> cache = new LinkedHashMap<>();
    private static final int NUMBER_OF_USES = 1;

    private static final int MAX_SIZE = 3;

    public Map<K, Integer> getCache() {
        return cache;
    }

    public void lfuCaching(K result) {
        synchronized (this) {
            if (!cache.isEmpty()) {
                if (cache.containsKey(result)) {
                    cache.entrySet().forEach(k -> {
                        if (k.getKey().equals(result)) {
                            k.setValue(cache.get(result) + 1);
                        }
                    });
                } else {
                    if (cache.size() >= MAX_SIZE) {
                        final int[] minCounters = {getValueFirstEntry(cache)};

                        cache.forEach((key, value) -> {
                            if (value < minCounters[0]) {
                                minCounters[0] = value;
                            }
                        });

                        cache.entrySet()
                                .stream()
                                .filter(x -> minCounters[0] == x.getValue())
                                .findFirst()
                                .ifPresent(cache.entrySet()::remove);
                    }
                    cache.put(result, NUMBER_OF_USES);
                }
            } else {
                cache.put(result, NUMBER_OF_USES);
            }
        }
    }

    private int getValueFirstEntry(Map<K, Integer> map) {
        return map.values().iterator().next();
    }
}