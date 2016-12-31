package com.bjike.goddess.user.login.boot;

import com.dounine.corgi.rpc.spring.SpringProcessor;
import org.springframework.stereotype.Component;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [rpc]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Component
public class MyProcessor extends SpringProcessor {

    @Override
    public boolean exportRpcApp() {
        return false;
    }
}
