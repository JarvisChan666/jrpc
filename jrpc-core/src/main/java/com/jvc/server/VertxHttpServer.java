package com.jvc.server;

import io.vertx.core.Vertx;

public class VertxHttpServer implements HttpServer {
    public void doStart(int port) {
        Vertx vertx = Vertx.vertx();

        // Create http server
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();

        // Listen port and handle request
        server.requestHandler(new HttpServerHandler());

        // Launch HTTP Server and listen to port
        server.listen(port, result -> {
            if (result.succeeded()) {
                System.out.println("Server is now listening on port " + port);
            }  else {
                System.err.println("Failed to run server" + result.cause());
            }
        });
    }
}
