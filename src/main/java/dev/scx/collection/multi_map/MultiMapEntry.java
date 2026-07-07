package dev.scx.collection.multi_map;

import java.util.List;

/// MultiMapEntry
///
/// @author scx567888
public interface MultiMapEntry<K, V> {

    K key();

    V value();

    List<V> values();

}
