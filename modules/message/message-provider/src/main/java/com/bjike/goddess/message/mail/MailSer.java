package com.bjike.goddess.message.mail;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.message.to.email.Email;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-11 13:56]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface MailSer {

    default void send(Email email) throws SerException {

    }
}
