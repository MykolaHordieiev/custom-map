package com.map.custom.implementation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class CustomMapTest {

    private Map<Long, String> map;
    private final String value = "value";
    private final Long key = 1L;

    @Before
    public void init() {
        map = new CustomMap<>();
    }

    @Test
    public void size() {
        Assert.assertEquals(0, map.size());
        map.put(key, value);
        assertEquals(1, map.size());
    }

    @Test
    public void isEmpty() {
        assertTrue(map.isEmpty());
        map.put(key, value);
        assertFalse(map.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void searchForNullKeyShouldBeThrewNullPointerException() {
        map.containsKey(null);
    }

    @Test
    public void containsKey() {
        assertFalse(map.containsKey(key));
        map.put(key, value);
        assertTrue(map.containsKey(key));
    }

    @Test(expected = NullPointerException.class)
    public void searchForNullValueShouldBeThrewNullPointerException() {
        map.containsValue(null);
    }

    @Test
    public void containsValue() {
        assertFalse(map.containsValue(value));
        map.put(key, value);
        assertTrue(map.containsValue(value));
    }

    @Test(expected = NullPointerException.class)
    public void getValueByKeyWhenKeyIsNullShouldBeThrewNullPointerException() {
        map.get(null);
    }

    @Test
    public void get() {
        String expected = "value";
        map.put(key, value);
        assertEquals(expected, map.get(key));
        assertNull(map.get(2L));
    }

    @Test(expected = NullPointerException.class)
    public void putNullKeyShouldBeThrewNullPointerException() throws Exception {
        map.put(null, value);
    }

    @Test(expected = NullPointerException.class)
    public void putNullValueShouldBeThrewNullPointerException() throws Exception {
        map.put(key, null);
    }

    @Test
    public void put() {
        String expected = "value";
        String resultWhenReturnedNewValue = map.put(key, value);
        assertEquals(expected, resultWhenReturnedNewValue);
        String resultWhenReturnedOldValue = map.put(key, "value2");
        assertEquals(expected, resultWhenReturnedOldValue);
    }

    @Test(expected = NullPointerException.class)
    public void removeWhenKeyIsNull() {
        map.remove(null);
    }

    @Test
    public void removeWhenMapHasNotKey() {
        map.put(key, value);
        String remove = map.remove(2L);
        assertNull(remove);
    }

    @Test
    public void remove() {
        String expected = "value";
        map.put(key, value);
        String result = map.remove(key);
        assertEquals(expected, result);
    }

    @Test(expected = NullPointerException.class)
    public void putAllWhenTryPutNull() {
        map.putAll(null);
    }

    @Test
    public void putAll() {
        Map<Long, String> putMap = new CustomMap<>();
        putMap.put(key, value);
        putMap.put(2L, "last");

        map.putAll(putMap);

        assertEquals(2, map.size());
    }

    @Test
    public void clear() {
        map.put(key, value);
        assertEquals(1, map.size());
        assertTrue(map.containsKey(key));
        assertTrue(map.containsValue(value));
        map.clear();
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }

    @Test
    public void keySet() {
        map.put(key, value);
        map.put(2L, "vall");

        Set<Long> expected = new HashSet<>();
        expected.add(key);
        expected.add(2L);

        assertEquals(expected, map.keySet());
    }

    @Test
    public void values() {
        map.put(key, "value1");
        map.put(2L, "value2");

        Collection<String> expected = new ArrayList<>();
        expected.add("value1");
        expected.add("value2");

        assertEquals(expected, map.values());

    }

    @Test
    public void entrySet() {
        map.put(key, value);
        map.put(2L, "value2");

        Set<Map.Entry<Long, String>> expected = new LinkedHashSet<>();
        expected.add(new CustomMap.CustomEntry<>(key, value));
        expected.add(new CustomMap.CustomEntry<>(2L, "value2"));

        Set<Map.Entry<Long, String>> result = map.entrySet();
        assertEquals(expected, result);
    }
}