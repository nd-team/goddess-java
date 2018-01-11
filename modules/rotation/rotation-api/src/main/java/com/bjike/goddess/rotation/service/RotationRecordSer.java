package com.bjike.goddess.rotation.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.rotation.bo.RotationRecordBO;
import com.bjike.goddess.rotation.entity.RotationRecord;
import com.bjike.goddess.rotation.dto.RotationRecordDTO;
import com.bjike.goddess.rotation.to.RotationConditionTO;
import com.bjike.goddess.rotation.to.RotationRecordTO;

import java.util.List;

/**
 * 岗位轮换记录业务接口
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2018-01-08 09:29 ]
 * @Description: [ 岗位轮换记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RotationRecordSer extends Ser<RotationRecord, RotationRecordDTO> {


    /**
     * 列表
     *
     * @param
     * @return class
     * @version v1
     */
    List<RotationRecordBO> list(RotationRecordDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param
     * @return class
     * @version v1
     */
    void add(RotationRecordTO to) throws SerException;

    /**
     * 更新
     *
     * @param
     * @return class
     * @version v1
     */
    void update(RotationRecordTO to) throws SerException;

    /**
     * 添加
     *
     * @param
     * @return class
     * @version v1
     */
    void delete(String id) throws SerException;


}