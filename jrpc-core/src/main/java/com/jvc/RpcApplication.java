package com.jvc;

import com.jvc.config.RpcConfig;
import com.jvc.constant.RpcConstant;
import com.jvc.utils.ConfigUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RpcApplication {
    
    private static volatile RpcConfig rpcConfig;

    /**
     * Initialization
     */

     public static void init(RpcConfig newRpcConfig) {
        rpcConfig = newRpcConfig;
        log.info("rpc init, config = {}", newRpcConfig.toString());
     }

     public static void init() {
        RpcConfig newRpcConfig;
        try {
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);            
        } catch (Exception e) {
            // Use default config if it is error
            newRpcConfig = new RpcConfig();
        }
        init(newRpcConfig);
     }

     /**
      * Get the config
      在 getRpcConfig 方法中，使用双重检查锁定机制确保 rpcConfig 只有在第一次访问时才被初始化。
      这种方式可以延迟加载配置对象，避免了在应用启动时不必要的初始化开销。
      */

      public static RpcConfig getRpcConfig() {
        if (rpcConfig == null) {
            synchronized (RpcApplication.class) {
                if (rpcConfig == null) {
                    init();
                }
            }
        }
        return rpcConfig;
      }
}
