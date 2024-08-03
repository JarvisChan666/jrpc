// package com.jvc.proxy;

// import java.lang.reflect.Proxy;

// public class ServiceProxyFactory {
//     /**
//      * Get proxy Object base on service.class
//      * Factory mode
//      */
//     public static <T> T getProxy(Class<T> serviceClass) {
//         return (T) Proxy.newProxyInstance(
//                 serviceClass.getClassLoader(),
//                 new Class[] { serviceClass },
//                 new ServiceProxy());
//     }

    
// }
