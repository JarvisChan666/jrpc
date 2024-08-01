package com.jvc.serializer;

import java.io.IOException;

public interface Serializer {
    
    /**
     * Serializer
     */
    <T> byte[] serialize(T object) throws IOException;

    /**
     * deserializer
     */

     <T> T deserialize(byte[] bytes, Class<T> type) throws IOException;
     
}
