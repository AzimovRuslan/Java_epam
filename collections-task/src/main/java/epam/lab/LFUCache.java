package epam.lab;

import java.util.LinkedHashMap;
import java.util.Map;

public class LFUCache <K> {
    private static int initialCapacity = 10;

    private Map<K, CacheEntry> cacheMap = new LinkedHashMap<>();

    public Map<K, CacheEntry> getCacheMap() {
        return cacheMap;
    }

    public LFUCache(int initialCapacity)
    {
        this.initialCapacity = initialCapacity;
    }

    public void addCacheEntry(K key, String data)
    {
        if(!isFull())
        {
            CacheEntry temp = new CacheEntry();
            temp.setData(data);
            temp.setFrequency(0);

            cacheMap.put(key, temp);
        }
        else
        {
            K entryKeyToBeRemoved = getLFUKey();
            cacheMap.remove(entryKeyToBeRemoved);

            CacheEntry temp = new CacheEntry();
            temp.setData(data);
            temp.setFrequency(0);

            cacheMap.put(key, temp);
        }
    }

    public K getLFUKey()
    {
        K key = null;
        int minFreq = Integer.MAX_VALUE;

        for(Map.Entry<K, CacheEntry> entry : cacheMap.entrySet())
        {
            if(minFreq > entry.getValue().getFrequency())
            {
                key = entry.getKey();
                minFreq = entry.getValue().getFrequency();
            }
        }

        return key;
    }

    public String getCacheEntry(K key)
    {
        if(cacheMap.containsKey(key))  // cache hit
        {
            CacheEntry temp = cacheMap.get(key);
            temp.setFrequency(temp.getFrequency() + 1);
            cacheMap.put(key, temp);
            return temp.getData();
        }
        return null; // cache miss
    }

    public boolean isFull()
    {
        if(cacheMap.size() == initialCapacity)
            return true;

        return false;
    }
}
