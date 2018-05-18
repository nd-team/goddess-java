package com.bjike.goddess.taskallotment.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.taskallotment.bo.CustomTitleBO;
import com.bjike.goddess.taskallotment.dto.CustomTitleDTO;
import com.bjike.goddess.taskallotment.entity.CustomTitle;

import java.util.List;

/**
 * 自定义字段业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 02:35 ]
 * @Description: [ 自定义字段业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CustomTitleSer extends Ser<CustomTitle, CustomTitleDTO> {
    List<CustomTitleBO> findByDTO(CustomTitleDTO dto) throws SerException;
}