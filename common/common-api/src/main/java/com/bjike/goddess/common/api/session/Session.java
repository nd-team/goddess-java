package com.bjike.goddess.common.api.session;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-01 09:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class Session {
    public static  Set<String> CURRENT_USER ;
    public Session(Set<String> current_user) {
        CURRENT_USER = current_user;
    }

}
