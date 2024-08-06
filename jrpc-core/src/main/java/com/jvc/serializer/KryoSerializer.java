package com.jvc.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Kryo 序列化器
 * 提供对象与字节数组之间的序列化和反序列化功能。
 */
public class KryoSerializer implements Serializer {
    /**
     * Kryo 线程不安全，使用 ThreadLocal 保证每个线程只有一个 Kryo 实例。
     */
    private static final ThreadLocal<Kryo> KRYO_THREAD_LOCAL = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        // 设置动态序列化和反序列化，不提前注册所有类
        kryo.setRegistrationRequired(false);
        return kryo;
    });

    @Override
    public <T> byte[] serialize(T obj) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                Output output = new Output(byteArrayOutputStream)) {
            getKryoInstance().writeObject(output, obj);
            output.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Serialization failed", e);
        }
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> classType) {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                Input input = new Input(byteArrayInputStream)) {
            return getKryoInstance().readObject(input, classType);
        } catch (IOException e) {
            throw new RuntimeException("Deserialization failed", e);
        }
    }

    /**
     * 获取当前线程的 Kryo 实例。
     *
     * @return 当前线程的 Kryo 实例
     */
    private Kryo getKryoInstance() {
        return KRYO_THREAD_LOCAL.get();
    }
}