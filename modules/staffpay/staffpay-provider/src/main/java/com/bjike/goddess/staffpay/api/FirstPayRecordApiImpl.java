package com.bjike.goddess.staffpay.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffpay.bo.FirstPayRecordBO;
import com.bjike.goddess.staffpay.bo.PayRecordBO;
import com.bjike.goddess.staffpay.dto.FirstPayRecordDTO;
import com.bjike.goddess.staffpay.entity.FirstPayRecord;
import com.bjike.goddess.staffpay.service.FirstPayRecordSer;
import com.bjike.goddess.staffpay.to.FirstPayRecordTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 第一次已付款记录业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-18 02:01 ]
 * @Description: [ 第一次已付款记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("firstPayRecordApiImpl")
public class FirstPayRecordApiImpl implements FirstPayRecordAPI {
    @Autowired
    private FirstPayRecordSer firstPayRecordSer;

    @Override
    public Long countFirstPayRecord(FirstPayRecordDTO firstPayRecordDTO) throws SerException {
        return firstPayRecordSer.countFirstPayRecord(firstPayRecordDTO);
    }

    @Override
    public FirstPayRecordBO getOne(String id) throws SerException {
        return firstPayRecordSer.getOne(id);
    }

    @Override
    public List<FirstPayRecordBO> findListFirstPayRecord(FirstPayRecordDTO firstPayRecordDTO) throws SerException {
        return firstPayRecordSer.findListFirstPayRecord(firstPayRecordDTO);
    }
    @Override
    public void removeFirstPayRecord(String id) throws SerException {
        firstPayRecordSer.removeFirstPayRecord(id);
    }
    @Override
    public PayRecordBO payment(FirstPayRecordTO firstPayRecordTO) throws SerException {
        return firstPayRecordSer.payment(firstPayRecordTO);
    }
}