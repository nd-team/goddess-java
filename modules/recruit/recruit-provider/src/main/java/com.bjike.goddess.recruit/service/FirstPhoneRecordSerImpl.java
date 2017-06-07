package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.FirstPhoneRecordBO;
import com.bjike.goddess.recruit.dto.FirstPhoneRecordDTO;
import com.bjike.goddess.recruit.entity.FirstPhoneRecord;
import com.bjike.goddess.recruit.to.FirstPhoneRecordTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 第一次电访记录
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-14 09:03]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class FirstPhoneRecordSerImpl extends ServiceImpl<FirstPhoneRecord, FirstPhoneRecordDTO> implements FirstPhoneRecordSer {

    /**
     * 分页查询电访记录
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public List<FirstPhoneRecordBO> list(FirstPhoneRecordDTO dto) throws SerException {
        List<FirstPhoneRecord> firstPhoneRecordList = super.findByPage(dto);
        List<FirstPhoneRecordBO> firstPhoneRecordBOList = BeanTransform.copyProperties(firstPhoneRecordList, FirstPhoneRecordBO.class, true);
        return firstPhoneRecordBOList;
    }

    /**
     * 保存第一次电访记录
     *
     * @param firstPhoneRecordTO
     * @return
     * @throws SerException
     */
    @Override
    @Transactional
    public FirstPhoneRecordBO save(FirstPhoneRecordTO firstPhoneRecordTO) throws SerException {
        FirstPhoneRecord firstPhoneRecord = BeanTransform.copyProperties(firstPhoneRecordTO, FirstPhoneRecord.class,true);
        firstPhoneRecord = super.save(firstPhoneRecord);
        FirstPhoneRecordBO firstPhoneRecordBO = BeanTransform.copyProperties(firstPhoneRecord, FirstPhoneRecordBO.class);
        return firstPhoneRecordBO;
    }

    /**
     * 更新第一次电访记录
     *
     * @param firstPhoneRecordTO
     * @throws SerException
     */
    @Override
    @Transactional
    public void update(FirstPhoneRecordTO firstPhoneRecordTO) throws SerException {
        FirstPhoneRecord firstPhoneRecord = BeanTransform.copyProperties(firstPhoneRecordTO, FirstPhoneRecord.class, true);
        super.update(firstPhoneRecord);
    }

    /**
     * 根据id删除第一次电访记录
     *
     * @param id 第一次电访记录id
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}
