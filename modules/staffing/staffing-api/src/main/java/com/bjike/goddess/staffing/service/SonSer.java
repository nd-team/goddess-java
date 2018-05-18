package com.bjike.goddess.staffing.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffing.dto.SonDTO;
import com.bjike.goddess.staffing.entity.Son;

/**
 * 人工成本计划子表业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-31 11:57 ]
 * @Description: [ 人工成本计划子表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SonSer extends Ser<Son, SonDTO> {
    void delete(String id) throws SerException;
}