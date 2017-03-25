package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.organize.bo.PositionInstructionBO;
import com.bjike.goddess.organize.dto.PositionInstructionDTO;
import com.bjike.goddess.organize.entity.PositionInstruction;
import com.bjike.goddess.organize.to.PositionInstructionTO;

import java.util.List;

/**
 * 岗位说明书业务接口
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface PositionInstructionSer extends Ser<PositionInstruction, PositionInstructionDTO> {

    /**
     * 根据职位查询说明书
     *
     * @param id 职位详细ID
     * @return
     */
    default List<PositionInstructionBO> findByPosition(String id) throws SerException {
        return null;
    }

    /**
     * 分页数据
     *
     * @param dto
     * @return
     */
    default List<PositionInstructionBO> findPage(PositionInstructionDTO dto) throws SerException {
        return null;
    }

    /**
     * 保存说明书
     *
     * @param to
     * @return
     */
    default PositionInstructionBO save(PositionInstructionTO to) throws SerException {
        return null;
    }

    /**
     * 修改说明书
     *
     * @param to
     * @return
     */
    default PositionInstructionBO update(PositionInstructionTO to) throws SerException {
        return null;
    }

}
