package epam.lab;

import java.util.*;

public class LFUCacheN <K> {
    private Map<K, Integer> cache = new LinkedHashMap<>();

    private static final int MAX_SIZE = 3;

    public Map<K, Integer> getCache() {
        return cache;
    }

    public void lfuCaching(K result, int counter) {
        if (cache.isEmpty()) {
            cache.put(result, counter);
        }

        if (!cache.isEmpty()) {
            if (cache.containsKey(result)) {
                counter = cache.get(result) + 1;
                cache.remove(result);
            }
        } else {
            int minCounters = getValueFirstEntry(cache);
            K keyForRemove = getKeyFirstEntry(cache);

            for (Map.Entry<K, Integer> entry : cache.entrySet()) {
                if (entry.getValue() < minCounters) {
                    minCounters = entry.getValue();
                    keyForRemove = entry.getKey();
                }
            }
            cache.remove(keyForRemove);
            counter = minCounters + 1;
        }
        cache.put(result, counter);


//        if (cache.size() < MAX_SIZE) {
//            if (cache.containsKey(result)) {
//                counter = cache.get(result) + 1;
//                cache.remove(result);
//            }
//        } else {
//            int minCounters = getValueFirstEntry(cache);
//            K keyForRemove = getKeyFirstEntry(cache);
//
//            for (Map.Entry<K, Integer> entry : cache.entrySet()) {
//                if (entry.getValue() < minCounters) {
//                    minCounters = entry.getValue();
//                    keyForRemove = entry.getKey();
//                }
//            }
//            cache.remove(keyForRemove);
//            counter = minCounters + 1;
//        }
//        cache.put(result, counter);
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