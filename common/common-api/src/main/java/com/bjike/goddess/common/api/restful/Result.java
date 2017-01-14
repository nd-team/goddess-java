package com.bjike.goddess.common.api.restful;

/**
 * Created by huanghuanlai on 2017/1/14.
 */
public interface Result {
    int getCode();

    String getMsg();

    Object getData();
}
