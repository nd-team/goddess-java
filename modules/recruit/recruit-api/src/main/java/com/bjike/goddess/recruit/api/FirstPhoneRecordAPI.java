package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.FirstPhoneRecordBO;
import com.bjike.goddess.recruit.dto.FirstPhoneRecordDTO;
import com.bjike.goddess.recruit.to.FirstPhoneRecordTO;

import java.util.List;

/**
 * 第一次电访记录
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-08 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FirstPhoneRecordAPI {

    /**
     * 根据id查询第一次电访记录
     *
     * @param id 第一次电访记录唯一标识
     * @return class FirstPhoneRecordBO
     * @throws SerException
     */
    FirstPhoneRecordBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 第一次电访记录dto
     * @throws SerException
     */
    Long count(FirstPhoneRecordDTO dto) throws SerException;

    /**
     * 分页查询第一次电访记录
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<FirstPhoneRecordBO> list(FirstPhoneRecordDTO dto) throws SerException;

    /**
     * 保存第一次电访记录
     *
     * @param firstPhoneRecordTO
     * @return
     * @throws SerException
     */
    FirstPhoneRecordBO save(FirstPhoneRecordTO firstPhoneRecordTO) throws SerException;

    /**
     * 根据id删除第一次电访记录
     *
     * @param id 第一次电访记录id
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新第一次电访记录
     *
     * @param firstPhoneRecordTO
     * @throws SerException
     */
    void update(FirstPhoneRecordTO firstPhoneRecordTO) throws SerException;
}

