package epam.lab;

public class CacheEntry {
    private String data;
    private int frequency;

    // default constructor
    CacheEntry() {

    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public int getFrequency() {
        return frequency;
    }
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "CacheEntry{" +
                "data='" + data + '\'' +
                ", frequency=" + frequency +
                '}';
    }
}
