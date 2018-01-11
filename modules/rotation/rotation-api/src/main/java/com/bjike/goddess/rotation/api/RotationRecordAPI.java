package com.bjike.goddess.rotation.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.rotation.bo.RotationRecordBO;
import com.bjike.goddess.rotation.dto.RotationRecordDTO;

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
public interface RotationRecordAPI {

    /**
     * 列表
     *
     * @param
     * @return class
     * @version v1
     */
    List<RotationRecordBO> list(RotationRecordDTO dto) throws SerException;

}