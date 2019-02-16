package com.uw.tcss558.clientserver.datastore;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryDataStore implements DataStore {
    //Using hashmap for storing the data
    private Map<String, String> dataStore = new ConcurrentHashMap();
    private static InMemoryDataStore obj;
    
    private InMemoryDataStore()
    {
        
    }
    public static InMemoryDataStore getInstance(){
        if(obj == null) {
            obj = new InMemoryDataStore();
        }
        return obj;
    }

    @Override
    public String get(String key) {
        String value = dataStore.getOrDefault(key, null);
        return String.format("get key=%s get value=%s", key, value);
    }

    @Override
    public String put(String key, String value) {

        dataStore.put(key, value);
        return String.format("put key=%s", key);
    }

    @Override
    public String store() {
        StringBuilder mapout = new StringBuilder();
        mapout.append("\n");
        for(Map.Entry<String, String> entry : dataStore.entrySet()) {
            mapout.append(String.format("key:%s:value:%s", entry.getKey(), entry.getValue()));
            mapout.append("\n");
        }

        return mapout.toString();
    }

    @Override
    public String delete(String key) {
        if(dataStore.containsKey(key))
        {
            dataStore.remove(key);
        }
        return String.format("delete key=%s", key);
    }
}
