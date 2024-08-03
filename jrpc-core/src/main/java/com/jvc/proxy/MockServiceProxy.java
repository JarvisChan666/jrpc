package com.jvc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class MockServiceProxy implements InvocationHandler{
    
    /**
     * Call the proxy
     */

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // Generate the object base on the method's return's type
        Class<?> methodReturnType = method.getReturnType();
        log.info("MockServiceProxy invoke method: " + method.getName());
        return getDefaultObject(methodReturnType);
    }

    private Object getDefaultObject(Class<?> type) {
        // basic type
        if (type.isPrimitive()) {
            if (type == boolean.class) {
                return false;
            } else if (type == short.class) {
                return (short) 0;
            } else if (type == int.class) {
                return 0;
            } else if (type == long.class) {
                return 0L;
            }
        }
        // Object type
        return null;
    }
}
