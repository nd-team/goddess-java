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
     * @param dto 岗位说明书数据传输
     * @return
     */
    default List<PositionInstructionBO> findPage(PositionInstructionDTO dto) throws SerException {
        return null;
    }

    /**
     * 保存说明书
     *
     * @param to 岗位说明书传输对象
     * @return
     */
    default PositionInstructionBO save(PositionInstructionTO to) throws SerException {
        return null;
    }

    /**
     * 修改说明书
     *
     * @param to 岗位说明书传输对象
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
     * 根据id查询岗位说明书数据
     *
     * @param id 岗位说明书数据id
     * @return
     * @throws SerException
     */
    default PositionInstructionBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 根据角度id查询说明书数据
     *
     * @param angleId 角度数据id
     * @return
     * @throws SerException
     */
    default List<PositionInstructionBO> findByAngle(String angleId) throws SerException {
        return null;
    }

    /**
     * 根据维度id查询说明书数据
     *
     * @param dimensionId 维度数据id
     * @return
     * @throws SerException
     */
    default List<PositionInstructionBO> findByDimension(String dimensionId) throws SerException {
        return null;
    }

    /**
     * 根据分类id查询说明书数据
     *
     * @param classifyId 分类数据id
     * @return
     * @throws SerException
     */
    default List<PositionInstructionBO> findByClassify(String classifyId) throws SerException {
        return null;
    }

    /**
     * 根据操作类型id查询说明书数据
     *
     * @param operateId 操作类型数据id
     * @return
     * @throws SerException
     */
    default List<PositionInstructionBO> findByOperate(String operateId) throws SerException {
        return null;
    }

    /**
     * 根据体现类型查询说明书数据
     *
     * @param reflectId 体现类型数据id
     * @return
     * @throws SerException
     */
    default List<PositionInstructionBO> findByReflect(String reflectId) throws SerException {
        return null;
    }
    /**
     * 获取所有输出结果
     *
     * @return class String
     * @throws SerException
     */
    default List<String> getOutCome() throws SerException {
        return null;
    }
}
