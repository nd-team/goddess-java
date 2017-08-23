package com.bjike.goddess.message.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.message.to.email.Email;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-24 17:27]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface EmailAPI {
    default void send(Email email) throws SerException {

    }
}
