package Structure;

import java.util.Objects;

public class HashMap {
    ArrayList keys;
    ArrayList values;

    int capacity;

    public HashMap(int capacity) {
        this.capacity = capacity;
        this.keys = new ArrayList(capacity);
        this.values = new ArrayList(capacity);
    }

    public void insert(Object key, Object value) {
        this.keys.add(key);
        this.values.add(value);
    }
    public void remove(Object value) {
        int index = values.getIndex(value);
        this.values.remove(value);
        this.keys.remove(keys.get(index));
    }
    public boolean contains(Object key) {
        for (int i = 0; i < keys.getLast() + 1; i++) {
            if (Objects.equals(keys.get(i), key))
                return true;
        }
        return false;
    }
    public Object getValue(Object key) {
        int index = keys.getIndex(key);
        return values.get(index);
    }

    public ArrayList getKeys() {
        return keys;
    }
    public ArrayList getValues () {
        return values;
    }
    public int getCapacity() {
        return capacity;
    }
}
