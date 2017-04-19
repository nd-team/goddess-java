package com.bjike.goddess.voucher.config;

import com.bjike.goddess.common.consumer.config.HIInfo;
import com.bjike.goddess.common.consumer.config.Interceptor;
import com.bjike.goddess.common.consumer.interceptor.limit.SmoothBurstyInterceptor;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import com.bjike.goddess.voucher.api.VoucherTotalAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 拦截器添加
 *
 * @Author: [liguiqin]
 * @Date: [2017-04-15 09:39]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Component
public class MyIntercept  implements Interceptor {
    @Autowired
    private VoucherGenerateAPI voucherGenerateAPI;
    @Autowired
    private VoucherTotalAPI voucherTotalAPI;

    @Override
    public List<HIInfo> customerInterceptors() {
        /**
         * 添加限流器
         */
        SmoothBurstyInterceptor smoothInterceptor = new SmoothBurstyInterceptor(100, SmoothBurstyInterceptor.LimitType.DROP);
        HIInfo smoothInfo = new HIInfo(smoothInterceptor, "/**");

//        /**
//         * 登录拦截器
//         */
//        HIInfo loginInfo = new HIInfo(new LoginIntercept(userAPI), "/**");

        /**
         * 权限拦截器
         */
//        AuthIntercept authIntercept = new AuthIntercept(voucherTotalAPI);
//        String[] excludes = new String[]{"*/login", "*/register"};
//        HIInfo authInfo = new HIInfo(new AuthIntercept(permissionAPI,excludes), "/**");

        return Arrays.asList(smoothInfo);
    }
}
