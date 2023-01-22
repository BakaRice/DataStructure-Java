package com.ricemarch.datastructure.chapter05;

public class hashFunction {
//    public static int hash(String key, int tableSize) {
//        int hasVal = 0;
//        for (int i = 0; i < key.length(); i++) {
//            hasVal += key.charAt(i);
//        }
//        return hasVal % tableSize;
//    }

    /**
     * A hash routine for String objects.
     *
     * @param key       key the string to hash
     * @param tableSize tableSize the size of the hash table.
     * @return the hash value
     */
    public static int hash(String key, int tableSize) {
        int hashVal = 0;
        for (int i = 0; i < key.length(); i++) {
            hashVal = 38 * hashVal + key.charAt(i);
        }
        hashVal %= tableSize;
        if (hashVal < 0) {
            hashVal += tableSize;
        }
        return hashVal;
    }
}
