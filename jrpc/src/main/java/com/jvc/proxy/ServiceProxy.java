package com.jvc.proxy;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.jvc.model.RpcRequest;
import com.jvc.model.RpcResponse;
import com.jvc.serializer.JdkSerializer;
import com.jvc.serializer.Serializer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;

public class ServiceProxy implements InvocationHandler {

    // When user call method, it will change to call "invoke"
    // We can get the args of the method we want to call
    // and use these args to build object
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Serializer serializer = new JdkSerializer();

        // build request (dynamic way)
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();

        try {
            // Serialize
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            // Send request
            // TODO need to use register center and服务发现机制
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                    .body(bodyBytes)
                    .execute()) {
                byte[] result = httpResponse.bodyBytes();
                // Deserializer
                RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
                return rpcResponse.getData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
