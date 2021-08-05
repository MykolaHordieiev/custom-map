package com.map.custom.implementation;


import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class CustomMap<Long, V> implements Map<Long, V> {

    private ArrayList<CustomEntry<Long, V>>[] table;

    private final int DEFAULT_INITIAL_CAPACITY = 16;
    private int size = 0;

    public CustomMap() {
        table = new ArrayList[DEFAULT_INITIAL_CAPACITY];
        for (int i = 0; i < DEFAULT_INITIAL_CAPACITY; i++) {
            table[i] = new ArrayList<>();
        }
    }

    private Map<Long, V> l = new HashMap<Long, V>();

    private int indexFor(Object key) {
        return key.hashCode() % table.length;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        requireNonNull(key);

        int index = indexFor(key);

        return table[index].stream()
                .anyMatch((e) -> e.getKey().equals(key));
    }

    @Override
    public boolean containsValue(Object value) {
        requireNonNull(value);

        return Arrays.stream(table)
                .flatMap(Collection::stream)
                .anyMatch(e -> e.getValue().equals(value));
    }

    @Override
    public V get(Object key) {
        requireNonNull(key);
        int index = indexFor(key);
        return table[index].stream()
                .filter(entry -> entry.getKey().equals(key))
                .findFirst()
                .map(CustomEntry::getValue)
                .orElse(null);
    }

    @Override
    public V put(Long key, V value) {
        requireNonNull(key);
        requireNonNull(value);

        int index = indexFor(key);

        for (CustomEntry<Long, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }
        table[index].add(new CustomEntry<Long, V>(key, value));
        size++;
        return value;
    }

    @Override
    public V remove(Object key) {
        requireNonNull(key);

        int index = indexFor(key);

        for (CustomEntry<Long, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                table[index].remove(entry);
                return oldValue;
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends Long, ? extends V> m) {
        requireNonNull(m);
        if (!m.isEmpty()) {
            m.keySet().forEach((key) -> put(key, m.get(key)));
        }
    }

    @Override
    public void clear() {
        Arrays.stream(table)
                .forEach(ArrayList::clear);
        size = 0;
    }

    @Override
    public Set<Long> keySet() {
        return Arrays.stream(table)
                .flatMap(Collection::stream)
                .map(CustomEntry::getKey)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Collection<V> values() {
        return Arrays.stream(table)
                .flatMap(Collection::stream)
                .map(CustomEntry::getValue)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Set<Map.Entry<Long, V>> entrySet() {
        return Arrays.stream(table)
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public String toString() {
        Set<Map.Entry<Long, V>> set = entrySet();

        String result = "{";
        result += set.stream()
                .map((e) -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining(", "));
        result += "}";

        return result;
    }

    public static class CustomEntry<Long, V> implements Map.Entry<Long, V> {
        Long key;
        V value;

        public CustomEntry(Long key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public Long getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            V temp = this.value;
            this.value = value;
            return temp;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CustomEntry<?, ?> that = (CustomEntry<?, ?>) o;
            return getKey().equals(that.getKey()) && getValue().equals(that.getValue());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getKey(), getValue());
        }
    }
}
