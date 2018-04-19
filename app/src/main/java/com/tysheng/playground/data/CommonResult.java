package com.tysheng.playground.data;

import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * Created by tysheng
 * Date: 14/11/17 12:36 PM.
 * Email: tyshengsx@gmail.com
 */

public class CommonResult implements Serializable {
    private static final long serialVersionUID = 2558354325715964704L;
    public static final String ERROR_OK = "ok";
    public static final String ERROR_EXIST = "exist";
    public static final String ERROR_NOT_FOUND = "not found";
    public static final String ERROR_PARAM = "param";
    public static final String ERROR_PERM = "perm";//permission deny
    private ResponseMeta resp_meta;
    private JsonObject data;

    public ResponseMeta getResp_meta() {
        return resp_meta;
    }

    public void setResp_meta(ResponseMeta resp_meta) {
        this.resp_meta = resp_meta;
    }

    public JsonObject getData() {
        return data;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }
}
