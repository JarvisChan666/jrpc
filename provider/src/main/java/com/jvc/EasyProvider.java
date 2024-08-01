package com.jvc;

import com.jvc.registry.LocalRegistry;
import com.jvc.server.HttpServer;
import com.jvc.server.VertxHttpServer;
import com.jvc.service.UserService;

public class EasyProvider {

    public static void main(String[] args) {
        // Register service
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // Launch web service
        HttpServer httpServer  = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
