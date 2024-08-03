package com.jvc;

import com.jvc.registry.LocalRegistry;
import com.jvc.server.HttpServer;
import com.jvc.server.VertxHttpServer;
import com.jvc.service.UserService;

public class EasyProviderExample {
    public static void main(String[] args) {
        
        // Initialization
        RpcApplication.init();

        // Register Service
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);
    
        // Start web service
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
