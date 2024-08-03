package com.jvc.proxy;

import java.lang.reflect.Proxy;

import com.jvc.RpcApplication;

public class ServiceProxyFactory {
    /**
     * Get proxy Object base on service.class
     * Factory mode
     */
    public static <T> T getProxy(Class<T> serviceClass) {
        if (RpcApplication.getRpcConfig().isMock()) {
            return getMockProxy(serviceClass);
        }
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[] { serviceClass },
                new ServiceProxy());

    }
    // Get mock proxy instance base on service class

    public static <T> T getMockProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[] { serviceClass },
                new MockServiceProxy());
    }
}
