package dev.scx.collection.test;

import dev.scx.collection.ScxCollection;
import dev.scx.collection.count_map.CountMap;
import dev.scx.collection.count_map.DefaultCountMap;
import dev.scx.collection.multi_map.DefaultMultiMap;
import dev.scx.collection.multi_map.MultiMap;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ScxCollectionTest {

    public static void main(String[] args) {
        test1();
        test2();
    }

    @Test
    public static void test1() {
        List<String> list = List.of("apple", "banana", "cherry", "date", "apple", "banana");

        // 按首字母分组
        MultiMap<Character, String> groupedByFirstLetter = ScxCollection.groupingBy(list, s -> s.charAt(0));
        MultiMap<Character, String> expected = new DefaultMultiMap<>();
        expected.add('a', "apple");
        expected.add('a', "apple");
        expected.add('b', "banana");
        expected.add('b', "banana");
        expected.add('c', "cherry");
        expected.add('d', "date");
        Assert.assertEquals(groupedByFirstLetter, expected);
    }

    @Test
    public static void test2() {
        List<String> list = List.of("apple", "banana", "cherry", "date", "apple", "banana");

        // 计数每个字符串出现的次数
        CountMap<String> countMap = ScxCollection.countingBy(list);
        CountMap<String> expected = new DefaultCountMap<>();
        expected.add("apple", 2L);
        expected.add("banana", 2L);
        expected.add("cherry", 1L);
        expected.add("date", 1L);
        Assert.assertEquals(countMap, expected);
    }

}
