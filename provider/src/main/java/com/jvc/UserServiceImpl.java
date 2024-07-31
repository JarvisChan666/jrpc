package com.jvc;

import com.jvc.model.User;
import com.jvc.service.UserService;

public class UserServiceImpl implements UserService {

    public User getUser(User user) {
        // TODO Auto-generated method stub

        System.out.println("user name:" + user.getName());
        return user;

    }

}
