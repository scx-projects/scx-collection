package dev.scx.collection.count_map;

import dev.scx.function.Function2Void;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/// DefaultCountMap
///
/// @author scx567888
public final class DefaultCountMap<K> implements CountMap<K> {

    private final Map<K, Long> map;

    /// 默认内部 map 使用 HashMap
    public DefaultCountMap() {
        this(HashMap::new);
    }

    /// 指定内部的 map 实现
    public DefaultCountMap(Supplier<Map<K, Long>> mapSupplier) {
        this.map = mapSupplier.get();
    }

    @Override
    public long add(K key, long count) {
        return map.merge(key, count, Long::sum);
    }

    @Override
    public Long set(K key, long count) {
        return map.put(key, count);
    }

    @Override
    public Long get(K key) {
        return map.get(key);
    }

    @Override
    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    @Override
    public Long remove(K key) {
        return map.remove(key);
    }

    @Override
    public Set<K> keys() {
        return map.keySet();
    }

    @Override
    public long size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Map<K, Long> toMap() {
        return toMap(HashMap::new);
    }

    @Override
    public Map<K, Long> toMap(Supplier<Map<K, Long>> mapSupplier) {
        var result = mapSupplier.get();
        result.putAll(map);
        return result;
    }

    @Override
    public <X extends Throwable> void forEach(Function2Void<? super K, Long, X> action) throws X {
        for (var entry : map.entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();
            action.apply(key, value);
        }
    }

    @Override
    public Iterator<CountMapEntry<K>> iterator() {
        return new DefaultCountMapIterator<>(map.entrySet().iterator());
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof DefaultCountMap<?> o) {
            return map.equals(o.map);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }

    @Override
    public String toString() {
        return this.map.toString();
    }

    private record DefaultCountMapIterator<K>(
        Iterator<Map.Entry<K, Long>> iterator
    ) implements Iterator<CountMapEntry<K>> {

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public CountMapEntry<K> next() {
            var next = iterator.next();
            return new DefaultCountMapEntry<>(next.getKey(), next.getValue());
        }

    }

    private record DefaultCountMapEntry<K>(K key, long count) implements CountMapEntry<K> {

    }

}
