package com.airtribe.meditrack.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class DataStore<T> {

    private final List<T> items;
    private final Map<String, T> indexedItems;

    public DataStore() {
        this.items = new ArrayList<>();
        this.indexedItems = new HashMap<>();
    }

    public void save(T item) {
        if (item != null) {
            items.add(item);
        }
    }

    public void save(String key, T item) {
        if (key != null && !key.isBlank() && item != null) {
            indexedItems.put(key, item);
            items.add(item);
        }
    }

    public Optional<T> findByKey(String key) {
        if (key == null || key.isBlank()) {
            return Optional.empty();
        }
        return Optional.ofNullable(indexedItems.get(key));
    }

    public List<T> findAll() {
        return new ArrayList<>(items);
    }

    public List<T> findAll(Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        if (predicate == null) {
            return result;
        }
        for (T item : items) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public boolean update(String key, T item) {
        if (key == null || key.isBlank() || item == null || !indexedItems.containsKey(key)) {
            return false;
        }

        T old = indexedItems.put(key, item);
        items.remove(old);
        items.add(item);
        return true;
    }

    public Optional<T> removeByKey(String key) {
        if (key == null || key.isBlank()) {
            return Optional.empty();
        }

        T removed = indexedItems.remove(key);
        if (removed != null) {
            items.remove(removed);
        }
        return Optional.ofNullable(removed);
    }

    public boolean remove(T item) {
        if (item == null) {
            return false;
        }
        boolean removed = items.remove(item);
        if (removed) {
            indexedItems.values().removeIf(value -> value == item);
        }
        return removed;
    }

    public void clear() {
        items.clear();
        indexedItems.clear();
    }

    public int size() {
        return indexedItems.size();
    }

    public boolean isEmpty() {
        return indexedItems.isEmpty();
    }
}
