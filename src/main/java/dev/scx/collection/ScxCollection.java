package dev.scx.collection;

import dev.scx.collection.count_map.CountMap;
import dev.scx.collection.count_map.DefaultCountMap;
import dev.scx.collection.multi_map.DefaultMultiMap;
import dev.scx.collection.multi_map.MultiMap;

import java.util.function.Function;

/// ScxCollection
///
/// @author scx567888
public final class ScxCollection {

    public static <K, T> MultiMap<K, T> groupingBy(Iterable<T> list, Function<? super T, ? extends K> keyFn) {
        return groupingBy(list, keyFn, t -> t);
    }

    public static <K, V, T> MultiMap<K, V> groupingBy(Iterable<T> list, Function<? super T, ? extends K> keyFn, Function<? super T, ? extends V> valueFn) {
        var multiMap = new DefaultMultiMap<K, V>();
        for (var t : list) {
            var key = keyFn.apply(t);
            var value = valueFn.apply(t);
            multiMap.add(key, value);
        }
        return multiMap;
    }

    public static <K, T> MultiMap<K, T> groupingBy(T[] list, Function<? super T, ? extends K> keyFn) {
        return groupingBy(list, keyFn, t -> t);
    }

    public static <K, V, T> MultiMap<K, V> groupingBy(T[] list, Function<? super T, ? extends K> keyFn, Function<? super T, ? extends V> valueFn) {
        var multiMap = new DefaultMultiMap<K, V>();
        for (var t : list) {
            var key = keyFn.apply(t);
            var value = valueFn.apply(t);
            multiMap.add(key, value);
        }
        return multiMap;
    }

    public static <T> CountMap<T> countingBy(Iterable<T> list) {
        return countingBy(list, k -> k, t -> 1L);
    }

    public static <K, T> CountMap<K> countingBy(Iterable<T> list, Function<? super T, ? extends K> keyFn) {
        return countingBy(list, keyFn, t -> 1L);
    }

    public static <K, T> CountMap<K> countingBy(Iterable<T> list, Function<? super T, ? extends K> keyFn, Function<? super T, Long> countFn) {
        var countMap = new DefaultCountMap<K>();
        for (var t : list) {
            var key = keyFn.apply(t);
            var count = countFn.apply(t);
            if (count != null) {
                countMap.add(key, count);
            }
        }
        return countMap;
    }

    public static <T> CountMap<T> countingBy(T[] list) {
        return countingBy(list, k -> k, t -> 1L);
    }

    public static <K, T> CountMap<K> countingBy(T[] list, Function<? super T, ? extends K> keyFn) {
        return countingBy(list, keyFn, t -> 1L);
    }

    public static <K, T> CountMap<K> countingBy(T[] list, Function<? super T, ? extends K> keyFn, Function<? super T, Long> countFn) {
        var countMap = new DefaultCountMap<K>();
        for (var t : list) {
            var key = keyFn.apply(t);
            var count = countFn.apply(t);
            if (count != null) {
                countMap.add(key, count);
            }
        }
        return countMap;
    }

}
