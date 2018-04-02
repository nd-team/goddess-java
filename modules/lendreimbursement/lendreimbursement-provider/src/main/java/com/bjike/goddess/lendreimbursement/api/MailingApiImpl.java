package com.bjike.goddess.lendreimbursement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.lendreimbursement.entity.Mailing;
import com.bjike.goddess.lendreimbursement.service.MailingSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 寄件信息业务接口实现
 *
 * @Author: [ wany ]
 * @Date: [ 2018-03-03 03:23 ]
 * @Description: [ 寄件信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("mailingApiImpl")
public class MailingApiImpl implements MailingAPI {

    @Autowired
    private MailingSer mailingSer;

    @Override
    public Mailing getMailing() throws SerException {

        return mailingSer.getMailing();
    }
}