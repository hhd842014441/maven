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
    private ResultEnum resultEnum;
}
