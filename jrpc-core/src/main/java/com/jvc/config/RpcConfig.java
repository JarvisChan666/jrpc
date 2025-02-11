package com.jvc.config;

import com.jvc.serializer.SerializerKeys;

import lombok.Data;

/**
 * RPC Config
 */
@Data
public class RpcConfig {
    /**
     * 名称
     */
    private String name = "jrpc";

    /**
     * 版本号
     */
    private String version = "1.0";

    /**
     * 服务器主机名
     */
    private String serverHost = "localhost";
    
    /**
     * 服务器端口号
     */
    private Integer serverPort = 8080;
    
    /**
     * Mock 
     */
    private boolean mock = false;

    /**
     * serializer
     */
    private String serializer = SerializerKeys.JDK;
}
