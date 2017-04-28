package com.bjike.goddess.managefee.config;

import com.bjike.goddess.managefee.api.*;
import org.springframework.stereotype.Component;

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
public class MyIntercept {
//    implements
// Interceptor {
//    @Autowired
//    private CustomerBaseInfoAPI managefeeBaseInfoAPI;
//    @Autowired
//    private CustomerDetailAPI managefeeDetailAPI;
//    @Autowired
//    private CusFamilyMemberAPI cusFamilyMemberAPI;
//    @Autowired
//    private CustomerLevelAPI managefeeLevelAPI;
//    @Autowired
//    private CusEmailAPI cusEmailAPI;
//
//    @Override
//    public List<HIInfo> managefeeInterceptors() {
//        /**
//         * 添加限流器
//         */
//        SmoothBurstyInterceptor smoothInterceptor = new SmoothBurstyInterceptor(1, SmoothBurstyInterceptor.LimitType.DROP);
//        HIInfo smoothInfo = new HIInfo(smoothInterceptor, "/**");
//        /**
//         * 权限拦截器
//         */
//        HIInfo authInfo = new HIInfo(new AuthIntercept(), "/**");
//        /**
//         * 登录拦截器
//         */
//        HIInfo loginInfo = new HIInfo(new LoginIntercept(managefeeBaseInfoAPI), "/**");
//
//        return Arrays.asList(smoothInfo, authInfo, loginInfo);
//    }
}
