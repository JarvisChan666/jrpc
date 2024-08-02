package com.jvc;

import com.jvc.model.User;
import com.jvc.proxy.ServiceProxyFactory;
import com.jvc.service.UserService;

public class EasyConsumer {
    public static void main(String[] args) {
        // UserService userService = new UserServiceProxy();
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("jvc");

        // Call
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
    }
}
