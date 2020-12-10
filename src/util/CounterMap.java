package util;

import java.util.HashMap;

public class CounterMap<K> extends HashMap<K, Long> {

    public void increment(K key) {
        super.put(key, super.containsKey(key) ? (super.get(key) + 1) : 1);
    }

    public void increment(K key, Long amount) {
        super.put(key, super.containsKey(key) ? (super.get(key) + amount) : amount);
    }
}
