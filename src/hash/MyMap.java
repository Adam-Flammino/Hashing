package hash;

/**
 * @Course: SDEV 350 ~ Java Programming II
 * @Author Name: Liang
 * @Assignment Name: testmyhashmap
 * @Date: Feb 6, 2016
 * @Description: Listing 27.1- HashMap interface
 */

/**
 * @param <K> key
 * @param <V> value
 *
 */
public interface MyMap<K, V> {

    /**
     * Remove all of the entries from this map
     */
    public void clear();

    /**
     * Return true if the specified key is in the map
     *
     * @param key
     * @return
     */
    public boolean containsKey(K key);

    /**
     * Return true if this map contains the specified value
     *
     * @param value
     * @return
     */
    public boolean containsValue(V value);

    /**
     * Return a set of entries in the map
     *
     * @return
     */
    public java.util.Set<Entry<K, V>> entrySet();

    /**
     * Return the first value that matches the specified key
     *
     * @param key
     * @return
     */
    public V get(K key);

    /**
     * Return true if this map contains no entries
     *
     * @return
     */
    public boolean isEmpty();

    /**
     * Return a set consisting of the keys in this map
     *
     * @return
     */
    public java.util.Set<K> keySet();

    /**
     * Add an entry (key, value) into the map
     *
     * @param key
     * @param value
     * @return
     */
    public V put(K key, V value);

    /**
     * Remove the entries for the specified key
     *
     * @param key
     */
    public void remove(K key);

    /**
     * Return the number of mappings in this map
     *
     * @return
     */
    public int size();

    /**
     * Return a set consisting of the values in this map
     *
     * @return
     */
    public java.util.Set<V> values();

    /**
     * Define inner class for Entry
     *
     * @param <K>
     * @param <V>
     */
    public static class Entry<K, V> {

        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "[" + key + ", " + value + "]";
        }
    }
}
