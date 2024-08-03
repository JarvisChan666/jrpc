package com.jvc.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocalRegistry {

    /**
     * Storage
     */

    private static final Map<String, Class<?>> map = new ConcurrentHashMap<>();

    /**
     * Register service
     */

    public static void register(String serviceName, Class<?> implClass) {
        map.put(serviceName, implClass);
    }

    /**
     * get service
     */

    public static Class<?> get(String serviceName) {
        return map.get(serviceName);
    }

    /**
     * Delete service
     */

    public static void remove(String serviceName) {
        map.remove(serviceName);
    }

}
