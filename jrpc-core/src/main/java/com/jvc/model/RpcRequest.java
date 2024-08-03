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

}
