package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.organize.bo.InstructionClassifyBO;
import com.bjike.goddess.organize.dto.InstructionClassifyDTO;
import com.bjike.goddess.organize.entity.InstructionClassify;
import com.bjike.goddess.organize.to.InstructionClassifyTO;

import java.util.List;

/**
 * 岗位说明书分类业务接口
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:22]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface InstructionClassifySer extends Ser<InstructionClassify, InstructionClassifyDTO> {

    /**
     * 查询未冻结分类
     *
     * @return
     * @throws SerException
     */
    default List<InstructionClassifyBO> findStatus() throws SerException {
        return null;
    }

    /**
     * 添加岗位说明书分类
     *
     * @param to
     * @return
     * @throws SerException
     */
    default InstructionClassifyBO save(InstructionClassifyTO to) throws SerException {
        return null;
    }

    /**
     * 修改岗位说明书分类
     *
     * @param to
     * @return
     * @throws SerException
     */
    default InstructionClassifyBO update(InstructionClassifyTO to) throws SerException {
        return null;
    }
}
