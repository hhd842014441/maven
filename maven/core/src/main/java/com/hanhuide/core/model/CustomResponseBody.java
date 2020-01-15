package com.hanhuide.core.model;

import com.hanhuide.core.enums.ResultEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class CustomResponseBody implements Serializable {

    private Integer status;
    private String msg;
    private Object result;
    private String jwtToken;
    private Boolean bool;
    private String user;

    public CustomResponseBody() {
    }

    @Override
    public String toString() {
        return "{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                ", jwtToken='" + jwtToken + '\'' +
                ", bool=" + bool +
                '}';
    }

    public CustomResponseBody(Integer status, String msg, Object result, String jwtToken, Boolean bool) {
        this.status = status;
        this.msg = msg;
        this.result = result;
        this.jwtToken = jwtToken;
        this.bool = bool;
    }

    public CustomResponseBody(Integer status, String msg, String jwtToken, Boolean bool) {
        this.status = status;
        this.msg = msg;
        this.jwtToken = jwtToken;
        this.bool = bool;
    }

    public CustomResponseBody(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
