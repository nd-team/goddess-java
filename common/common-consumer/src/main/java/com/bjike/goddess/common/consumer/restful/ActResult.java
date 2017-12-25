package com.bjike.goddess.common.consumer.restful;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.restful.Result;

/**
 * Created by huanghuanlai on 2017/1/14.
 */
public class ActResult implements Result {
    private int code = 0;
    private String msg;
    private Object data;

    public ActResult() {
    }

    public ActResult(String msg) {
        this.msg = msg;
    }

    public ActResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ActResult(String msg, Object data) {
        this.msg = msg;
        this.data = data;
    }

    public ActResult(int code, String msg, Object data) {
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

    public static ActResult initialize(Object data) {
        return new ActResult(null,data);
    }
}
