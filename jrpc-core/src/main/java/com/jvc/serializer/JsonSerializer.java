package com.jvc.serializer;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jvc.model.RpcRequest;
import com.jvc.model.RpcResponse;

/**
 * JSON 序列化器
 * 提供对象与字节数组之间的序列化和反序列化功能。
 */
public class JsonSerializer implements Serializer {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public <T> byte[] serialize(T obj) throws IOException {
        return OBJECT_MAPPER.writeValueAsBytes(obj);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> classType) throws IOException {
        T obj = OBJECT_MAPPER.readValue(bytes, classType);

        if (obj instanceof RpcRequest) {
            return handleRpcRequest((RpcRequest) obj, classType);
        }
        if (obj instanceof RpcResponse) {
            return handleRpcResponse((RpcResponse) obj, classType);
        }

        return obj;
    }

    /**
     * 处理 RPC 请求对象的特殊反序列化逻辑。
     *
     * @param rpcRequest RPC 请求对象
     * @param type       请求类型
     * @param <T>        泛型类型
     * @return 反序列化后的请求对象
     * @throws IOException IO 异常
     */
    private <T> T handleRpcRequest(RpcRequest rpcRequest, Class<T> type) throws IOException {
        reserializeArguments(rpcRequest.getParameterTypes(), rpcRequest.getArgs());
        return type.cast(rpcRequest);
    }

    /**
     * 处理 RPC 响应对象的特殊反序列化逻辑。
     *
     * @param rpcResponse RPC 响应对象
     * @param type        响应类型
     * @param <T>         泛型类型
     * @return 反序列化后的响应对象
     * @throws IOException IO 异常
     */
    private <T> T handleRpcResponse(RpcResponse rpcResponse, Class<T> type) throws IOException {
        Object data = rpcResponse.getData();
        if (data != null) {
            byte[] dataBytes = OBJECT_MAPPER.writeValueAsBytes(data);
            rpcResponse.setData(OBJECT_MAPPER.readValue(dataBytes, rpcResponse.getDataType()));
        }
        return type.cast(rpcResponse);
    }

    /**
     * 重新序列化参数以处理类型擦除问题。
     *
     * @param parameterTypes 参数类型数组
     * @param args           参数数组
     * @throws IOException IO 异常
     */
    private void reserializeArguments(Class<?>[] parameterTypes, Object[] args) throws IOException {
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> paramType = parameterTypes[i];
            Object arg = args[i];
            if (!paramType.isAssignableFrom(arg.getClass())) {
                byte[] argBytes = OBJECT_MAPPER.writeValueAsBytes(arg);
                args[i] = OBJECT_MAPPER.readValue(argBytes, paramType);
            }
        }
    }
}