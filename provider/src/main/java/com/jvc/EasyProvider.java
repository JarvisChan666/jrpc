package com.jvc;

import com.jvc.server.HttpServer;
import com.jvc.server.VertxHttpServer;

public class EasyProvider {

    public static void main(String[] args) {
        HttpServer httpServer  = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
