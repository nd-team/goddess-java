package com.bjike.goddess.organize.vo;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.restful.Result;

/**
 * Created by huanghuanlai on 2017/1/14.
 */
public class ActResultOrgan implements Result {
    private int code = 0;
    private String msg;
    private Object data;

    public ActResultOrgan() {
    }

    public ActResultOrgan(String msg) {
        this.msg = msg;
    }

    public ActResultOrgan(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ActResultOrgan(String msg, Object data) {
        this.msg = msg;
        this.data = data;
    }

    public ActResultOrgan(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{\"code\":");
        sb.append(code);
        sb.append(",");
        sb.append("\"msg\":\"");
        sb.append(msg);
        sb.append("\"");
        if (null != data) {
            sb.append(",\"data\":\"");
            sb.append(JSON.toJSONString(data));
            sb.append("\"");
        }
        sb.append("}");
        return sb.toString();
    }

    public static ActResultOrgan initialize(Object data) {
        return new ActResultOrgan(null,data);
    }
}
