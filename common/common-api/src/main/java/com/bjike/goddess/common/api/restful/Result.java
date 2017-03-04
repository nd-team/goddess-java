package com.bjike.goddess.common.api.restful;

/**
 * Created by huanghuanlai on 2017/1/14.
 */
public interface Result {
    /**
     * 消息码
     */
    int getCode();

    /**
     * 错误消息
     */
    String getMsg();

    /**
     * 返回数据
     */
    Object getData();
}
