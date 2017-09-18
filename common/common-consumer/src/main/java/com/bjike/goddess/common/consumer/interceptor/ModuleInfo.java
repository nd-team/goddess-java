package com.bjike.goddess.common.consumer.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-09-15 15:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public final class ModuleInfo {
    private ModuleInfo(){}
    private static Logger CONSOLE = LoggerFactory.getLogger(ModuleInfo.class);


    public static String MODULE_NAME = "未知模块,请修改.";

}
