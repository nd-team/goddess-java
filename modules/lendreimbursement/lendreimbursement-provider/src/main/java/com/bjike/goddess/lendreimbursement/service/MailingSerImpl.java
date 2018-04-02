package com.bjike.goddess.lendreimbursement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.lendreimbursement.dto.MailingDTO;
import com.bjike.goddess.lendreimbursement.entity.Mailing;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 寄件信息业务实现
 *
 * @Author: [ wany ]
 * @Date: [ 2018-03-03 03:23 ]
 * @Description: [ 寄件信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "lendreimbursementSerCache")
@Service
public class MailingSerImpl extends ServiceImpl<Mailing, MailingDTO> implements MailingSer {

    @Override
    public Mailing getMailing() throws SerException {
        return super.findById("121");
    }
}