package epam.lab;

import java.util.*;
import java.util.stream.Collectors;

public class LFUCacheN <K> {
    private Map<K, Integer> cache = new LinkedHashMap<>();

    private static final int MAX_SIZE = 3;

    public Map<K, Integer> getCache() {
        return cache;
    }

    public void lfuCaching(K result, int counter) {
        if (cache.isEmpty()) {
            cache.put(result, counter);
        } else {
            if (cache.containsKey(result)) {
                for (Map.Entry<K, Integer> entry : cache.entrySet()) {
                    if (entry.getKey() == result) {
                        entry.setValue(cache.get(result) + 1);
                    }
                }
            } else {
                if (cache.size() >= MAX_SIZE) {
                    int minCounters = getValueFirstEntry(cache);

                    Map<K, Integer> entryForRemove = new LinkedHashMap<>();
//
//                    cache.forEach((key, value) -> {
//                        if (value < minCounters) {
//                            entryForRemove.put(key, value);
//                        }
//                    });

//
//
//                            cache.entrySet()
//                            .stream()
//                            .filter(x -> x.getValue() < minCounters)
//                            .forEach(k -> entryForRemove.put(k.getKey(), k.getValue()));
////                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

//                    K keyForRemove = entryForRemove.keySet().iterator().next();
                    K keyForRemove = getKeyFirstEntry(cache);




                    for (Map.Entry<K, Integer> entry : cache.entrySet()) {
                        if (entry.getValue() < minCounters) {
                            minCounters = entry.getValue();
                            keyForRemove = entry.getKey();
                        }
                    }
                    entryForRemove.put(keyForRemove, minCounters);


                    K q = entryForRemove.keySet().iterator().next();

                    cache.remove(q);
                }
                cache.put(result, counter);
            }

        }
    }

    private int getValueFirstEntry(Map<K, Integer> map) {
        List<Integer> counters = new ArrayList<>(map.values());
        return counters.get(0);
    }

    private K getKeyFirstEntry(Map<K, Integer> map) {
        List<K> counters = new ArrayList<>(map.keySet());
        return counters.get(0);
    }
}

//                    for (Map.Entry<K, Integer> entry : cache.entrySet()) {
//                        if (entry.getValue() < minCounters) {
//                            minCounters = entry.getValue();
//                            keyForRemove = entry.getKey();
//                        }
//                    }