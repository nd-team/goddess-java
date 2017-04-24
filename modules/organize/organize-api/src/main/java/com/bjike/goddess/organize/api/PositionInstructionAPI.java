package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.PositionInstructionBO;
import com.bjike.goddess.organize.dto.PositionInstructionDTO;
import com.bjike.goddess.organize.to.PositionInstructionTO;

import java.util.List;

/**
 * 对外岗位说明书业务接口
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface PositionInstructionAPI {

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

    /**
     * 删除
     *
     * @param id 岗位说明书数据id
     * @return
     * @throws SerException
     */
    default PositionInstructionBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 岗位说明书数据传输
     * @return
     * @throws SerException
     */
    default List<PositionInstructionBO> maps(PositionInstructionDTO dto) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }

    /**
     * 根据id查询岗位说明书数据
     *
     * @param id 岗位说明书数据id
     * @return
     * @throws SerException
     */
    default PositionInstructionBO findById(String id) throws SerException {
        return null;
    }


}
