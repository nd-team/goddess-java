package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.task.bo.RecordBO;
import com.bjike.goddess.task.dto.RecordDTO;
import com.bjike.goddess.task.entity.Record;
import com.bjike.goddess.task.to.RecordTO;

import java.util.List;

/**
 * 内部协助单记录业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-16 11:30 ]
 * @Description: [ 内部协助单记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RecordSer extends Ser<Record, RecordDTO> {
    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<RecordBO> list(RecordDTO dto) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(RecordDTO dto) throws SerException;
}