package com.ricemarch.datastructure.chapter05;

import java.util.LinkedList;
import java.util.List;

public class SeparateChainingHashTable<AnyType> {
    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    /**
     * construct the hash table
     *
     * @param size approximate table size
     */
    public SeparateChainingHashTable(int size) {
        theLists = new LinkedList[nextPrime(size)];
        for (int i = 0; i < theLists.length; i++) {
            theLists[i] = new LinkedList<>();
        }
    }

    /**
     * insert into the hash table. if the name is already present,then do nothing.
     *
     * @param x the item to insert
     */
    public void insert(AnyType x) {
        List<AnyType> whichList = theLists[myHash(x)];
        if (!whichList.contains(x)) {
            whichList.add(x);

            //rehash ?
            if (++currentSize > theLists.length) {
                rehash();
            }
        }

    }

    /**
     * remove from the hash table
     *
     * @param x the item to remove
     */
    public void remove(AnyType x) {
        List<AnyType> whichList = theLists[myHash(x)];
        if (whichList.contains(x)) {
            whichList.remove(x);
            currentSize--;
        }
    }

    /**
     * find an item in the hash table
     *
     * @param x the item to search for.
     * @return true if x is not found
     */
    public boolean contains(AnyType x) {
        List<AnyType> whichList = theLists[myHash(x)];
        return whichList.contains(x);
    }

    /**
     * Make the hash table logically empty.
     */
    public void makeEmpty() {
        for (List<AnyType> theList : theLists) {
            theList.clear();
        }
        currentSize = 0;
    }

    private static final int DEFAULT_TABLE_SIZE = 10;

    private List<AnyType>[] theLists;
    private int currentSize;


    /**
     * rehashing for separate chaining hash table
     */
    private void rehash() {
        List<AnyType>[] oldLists = theLists;

        //create new double-sized empty table

        theLists = new List[nextPrime(2 * theLists.length)];
        for (List<AnyType> theList : theLists) {
            theList = new LinkedList<>();
        }
        currentSize = 0;
        for (int i = 0; i < oldLists.length; i++) {
            for (AnyType item : oldLists[i]) {
                insert(item);
            }
        }
    }

    private int myHash(AnyType x) {
        int hashVal = x.hashCode();

        hashVal %= this.theLists.length;
        if (hashVal < 0) {
            hashVal += theLists.length;
        }
        return hashVal;
    }

    private static int nextPrime(int n) {
        return 0;
    }

    private static boolean isPrime(int n) {
        return true;
    }
}
