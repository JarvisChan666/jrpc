package com.jvc;

import java.io.IOException;

import com.jvc.model.RpcRequest;
import com.jvc.model.RpcResponse;
import com.jvc.model.User;
import com.jvc.service.UserService;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;

import com.jvc.serializer.JdkSerializer;
import com.jvc.serializer.Serializer;

public class UserServiceProxy implements UserService {
    public User getUser(User user) {
        final Serializer serializer = new JdkSerializer();

        // Send request
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(UserService.class.getName())
                .methodName("getUser")
                .parameterTypes(new Class[] { User.class })
                .args(new Object[] { user })
                .build();

        try {
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                    .body(bodyBytes)
                    .execute()) {
                byte[] result = httpResponse.bodyBytes();
                RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
                return (User) rpcResponse.getData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
