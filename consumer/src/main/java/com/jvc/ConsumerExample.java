package com.jvc;

import com.jvc.config.RpcConfig;
import com.jvc.utils.ConfigUtils;

public class ConsumerExample {
     public static void main(String[] args) {
        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpc);
    }
}
