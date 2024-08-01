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

    public void setMessage(String message) {
        // TODO Auto-generated method stub
        this.message = message;
    }

    public void setData(Object data) {
        // TODO Auto-generated method stub
        this.data = data;
    }

    public void setDataType(Class<?> dataType) {
        // TODO Auto-generated method stub
        this.dataType = dataType;
    }

    public void setException(Exception e) {
        // TODO Auto-generated method stub
        this.exception = e;
    }
}
