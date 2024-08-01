package com.jvc.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request encapsulate
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RpcRequest  implements Serializable {
    /**
     * Service name
     */
    private String serviceName;

    /**
     * Method name
     */
    private String methodName;

    /**
     * Arg types list
     */
    private Class<?>[] parameterTypes;

    /** 
     * Arg list
    */
    private Object[] args;

    public String getServiceName() {
        // TODO Auto-generated method stub
        return serviceName;
    }

    public String getMethodName() {
        // TODO Auto-generated method stub
        return methodName;
    }

    public Class<?>[] getParameterTypes() {
        // TODO Auto-generated method stub
        return parameterTypes;
    }

    public Object getArgs() {
        // TODO Auto-generated method stub
        return args;
    }
}
