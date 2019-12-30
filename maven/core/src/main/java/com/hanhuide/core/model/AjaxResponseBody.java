package com.hanhuide.core.model;

import com.hanhuide.core.enums.ResultEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class AjaxResponseBody implements Serializable {

    private Integer status;
    private String msg;
    private Object result;
    private String jwtToken;
    private Boolean bool;

    public AjaxResponseBody() {
    }

    public AjaxResponseBody(Integer status, String msg, Object result, String jwtToken, Boolean bool) {
        this.status = status;
        this.msg = msg;
        this.result = result;
        this.jwtToken = jwtToken;
        this.bool = bool;
    }

    public AjaxResponseBody(Integer status, String msg, String jwtToken, Boolean bool) {
        this.status = status;
        this.msg = msg;
        this.jwtToken = jwtToken;
        this.bool = bool;
    }

    public AjaxResponseBody(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
