package com.jvc.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RpcResponse implements Serializable{
    /**
     * Response data
     */
    private Object data;

    /**
     * Response data type
     */
    private Class<?> dataType;

    /**
     * Response message
     */
    private String message;

    /**
     * Error message
     */
    private Exception exception;


}
