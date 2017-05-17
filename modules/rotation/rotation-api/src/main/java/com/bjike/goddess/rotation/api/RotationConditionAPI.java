package com.bjike.goddess.rotation.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.rotation.bo.RotationConditionBO;
import com.bjike.goddess.rotation.dto.RotationConditionDTO;
import com.bjike.goddess.rotation.to.RotationConditionTO;

import java.util.List;

/**
 * 岗位轮换条件业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:41 ]
 * @Description: [ 岗位轮换条件业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RotationConditionAPI {

    /**
     * 保存
     *
     * @param to 岗位轮换条件传输对象
     * @return
     * @throws SerException
     */
    default RotationConditionBO save(RotationConditionTO to) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 岗位轮换条件传输对象
     * @return
     * @throws SerException
     */
    default RotationConditionBO update(RotationConditionTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 岗位轮换条件数据id
     * @return
     * @throws SerException
     */
    default RotationConditionBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据id查询岗位轮换条件数据
     *
     * @param id 岗位轮换条件数据id
     * @return
     * @throws SerException
     */
    default RotationConditionBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 岗位轮换条件数据传输对象
     * @return
     * @throws SerException
     */
    default List<RotationConditionBO> maps(RotationConditionDTO dto) throws SerException {
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

}