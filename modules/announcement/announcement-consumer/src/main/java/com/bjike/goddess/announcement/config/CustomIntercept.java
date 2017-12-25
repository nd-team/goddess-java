package com.bjike.goddess.announcement.config;

import com.bjike.goddess.announcement.api.AnnouncementAPI;
import com.bjike.goddess.common.consumer.config.HIInfo;
import com.bjike.goddess.common.consumer.config.Interceptor;
import com.bjike.goddess.common.consumer.interceptor.limit.SmoothBurstyInterceptor;
import com.bjike.goddess.common.consumer.interceptor.login.LoginIntercept;
import com.bjike.goddess.common.consumer.interceptor.login.StorageIntercept;
import com.bjike.goddess.storage.api.StorageUserAPI;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ike on 17-5-10.
 */
@Component
public class CustomIntercept implements Interceptor {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private AnnouncementAPI announcementAPI;
    @Autowired
    private StorageUserAPI storageUserAPI;

    @Override
    public List<HIInfo> customerInterceptors() {
        /**
         * 添加限流器
         */
        SmoothBurstyInterceptor smoothInterceptor = new SmoothBurstyInterceptor(100, SmoothBurstyInterceptor.LimitType.DROP);
        HIInfo smoothInfo = new HIInfo(smoothInterceptor, "/**");

        /**
         * 登录拦截器
         */
        HIInfo loginInfo = new HIInfo(new LoginIntercept(userAPI), "/**");
        HIInfo storage = new HIInfo(new StorageIntercept(storageUserAPI, "announcement", "123456", "announcement"), "/**");

        /**
         * 请求拦截，有必读消息未读时拦截请求
         */
        HIInfo readInfo = new HIInfo(new MyInterceptor(announcementAPI), "/**");

        return Arrays.asList(smoothInfo,storage,readInfo,loginInfo);
    }
}
