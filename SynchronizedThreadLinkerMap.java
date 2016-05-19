/* Copyright (c) 2016, Mikhail Kotlik and Sam Xu
 * Versa Checkers
 * APCS Spring Final Project
 * SynchronizedThreadLinkerMap
 */

import java.util.*;

public class SynchronizedThreadLinkerMap {
    //Instance variables
    private HashMap<String, ThreadLinker> threadMap;

    //Constructors
    public SynchronizedThreadLinkerMap() {
        threadMap = new HashMap<String, ThreadLinker>();
    }

    //Accessors
    //TODO - check if these accessors should be synchronized
    public boolean containsKey(String key) {
        return threadMap.containsKey(key);
    }

    public ThreadLinker get(String key) {
        return threadMap.get(key);
    }

    public boolean isEmpty() {
        return threadMap.isEmpty();
    }

    public int size() {
        return threadMap.size();
    }

    //Mutators
    public synchronized ThreadLinker put(String key, ThreadLinker newLink) {
        return threadMap.put(key, newLink);
    }

    public synchronized ThreadLinker remove(String key) {
        return threadMap.remove(key);
    }
}