package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.FirstPhoneRecordBO;
import com.bjike.goddess.recruit.dto.FirstPhoneRecordDTO;
import com.bjike.goddess.recruit.to.FirstPhoneRecordTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 第一次电访记录
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 17:27]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("firstPhoneRecordApiImpl")
public class FirstPhoneRecordApiImpl implements FirstPhoneRecordAPI {
    @Override
    public List<FirstPhoneRecordBO> list(FirstPhoneRecordDTO dto) throws SerException {
        return null;
    }

    @Override
    public FirstPhoneRecordBO save(FirstPhoneRecordTO firstPhoneRecordTO) throws SerException {
        return null;
    }

    @Override
    public void remove(String id) throws SerException {

    }

    @Override
    public void update(FirstPhoneRecordTO firstPhoneRecordTO) throws SerException {

    }
}
