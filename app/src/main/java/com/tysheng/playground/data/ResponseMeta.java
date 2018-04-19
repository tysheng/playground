package com.tysheng.playground.data;

import java.io.Serializable;

/**
 * Created by tysheng
 * Date: 14/11/17 12:52 PM.
 * Email: tyshengsx@gmail.com
 */

public class ResponseMeta implements Serializable {
    private static final long serialVersionUID = 8699764196676828564L;

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
