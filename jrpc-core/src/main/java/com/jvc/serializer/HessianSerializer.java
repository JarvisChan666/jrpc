package com.jvc.serializer;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Hessian 序列化器
 *
 * @author 
 * @learn 
 * @from 
 */
public class HessianSerializer implements Serializer {

    @Override
    public <T> byte[] serialize(T object) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        HessianOutput ho = new HessianOutput(bos);
        try {
            ho.writeObject(object);
            return bos.toByteArray();
        } finally {
            ho.close();
        }
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> tClass) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        HessianInput hi = new HessianInput(bis);
        try {
            return (T) hi.readObject(tClass);
        } finally {
            hi.close();
        }
    }
}
