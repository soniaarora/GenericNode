package com.uw.tcss558.clientserver.datastore;

import java.util.HashMap;
import java.util.Map;

public interface DataStore {

    String get(String key);
    String put(String key, String value);
    String store();
    String delete(String key);
}
