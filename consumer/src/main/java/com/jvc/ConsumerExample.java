package com.jvc;

import com.jvc.config.RpcConfig;
import com.jvc.model.User;
import com.jvc.proxy.ServiceProxyFactory;
import com.jvc.service.UserService;
import com.jvc.utils.ConfigUtils;

public class ConsumerExample {
    public static void main(String[] args) {
        // RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        // System.out.println(rpc);

        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("jvc");

        // Call
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("User not found");
        }
        long number = userService.getNumber();
        System.out.println(number);
    }
}
