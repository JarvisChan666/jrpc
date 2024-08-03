package com.jvc.server;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

import com.jvc.model.RpcRequest;
import com.jvc.model.RpcResponse;
import com.jvc.registry.LocalRegistry;
import com.jvc.serializer.JdkSerializer;
import com.jvc.serializer.Serializer;

import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

/**
 * 1. deserialize the request to object, and get the args
 * 2. get the class from localRegister based on request's args(service name)
 * 3.
 */
public class HttpServerHandler implements Handler<HttpServerRequest> {

    @Override
    public void handle(HttpServerRequest request) {
        // TODO Auto-generated method stub
        // Use serializer
        final Serializer serializer = new JdkSerializer();

        // Logs
        System.out.println("Received request: " + request.method() + " " + request.uri());

        // async handle HTTP Request
        request.bodyHandler(body -> {
            byte[] bytes = body.getBytes();
            RpcRequest rpcRequest = null;
            try {
                rpcRequest = serializer.deserialize(bytes, RpcRequest.class);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }

            // Response
            RpcResponse rpcResponse = new RpcResponse();
            // If null return
            if (rpcRequest == null) {
                rpcResponse.setMessage("rpcRequest is null");
                doResponse(request, rpcResponse, serializer);
                return;
            }

            try {
                // Get the class and call it by "invoke"
                // 获取请求中的服务名和方法名：
                String serviceName = rpcRequest.getServiceName();
                String methodName = rpcRequest.getMethodName();
                Class<?>[] parameterTypes = rpcRequest.getParameterTypes();
                Object[] args = rpcRequest.getArgs();

                // 从注册表中获取服务实现类：
                Class<?> implClass = LocalRegistry.get(serviceName);

                // 获取方法对象并调用：
                Method method = implClass.getMethod(methodName, parameterTypes);
                Object result = method.invoke(implClass.getDeclaredConstructor().newInstance(), args);

                // encapsulate
                rpcResponse.setData(result);
                rpcResponse.setDataType(method.getReturnType());
                rpcResponse.setMessage("ok");
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                rpcResponse.setMessage(e.getMessage());
                rpcResponse.setException(e);
            }

            // Response
            doResponse(request, rpcResponse, serializer);
        });
    }

    /**
     * 响应
     *
     * @param request
     * @param rpcResponse
     * @param serializer
     */
    void doResponse(HttpServerRequest request, RpcResponse rpcResponse, Serializer serializer) {
        HttpServerResponse httpServerResponse = request.response()
                .putHeader("content-type", "application/json");
        try {
            // 序列化
            byte[] serialized = serializer.serialize(rpcResponse);
            httpServerResponse.end(Buffer.buffer(serialized));
        } catch (IOException e) {
            e.printStackTrace();
            httpServerResponse.end(Buffer.buffer());
        }
    }

}
