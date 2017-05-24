package com.bjike.goddess.archive.api;

import com.bjike.goddess.archive.bo.ForeignStaffingSetBO;
import com.bjike.goddess.archive.dto.ForeignStaffingSetDTO;
import com.bjike.goddess.archive.to.ForeignStaffingSetTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;

import java.util.List;

/**
 * 对外人员基本信息设置业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 02:28 ]
 * @Description: [ 对外人员基本信息设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ForeignStaffingSetAPI {

    /**
     * 保存
     *
     * @param to 对外人员基本信息设置传输对象
     * @return
     * @throws SerException
     */
    default ForeignStaffingSetBO save(ForeignStaffingSetTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 对外人员基本信息设置传输对象
     * @return
     * @throws SerException
     */
    default ForeignStaffingSetBO update(ForeignStaffingSetTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 对外人员基本信息设置数据id
     * @return
     * @throws SerException
     */
    default ForeignStaffingSetBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 冻结
     *
     * @param id 对外人员基本信息设置数据id
     * @return
     * @throws SerException
     */
    default ForeignStaffingSetBO congeal(String id) throws SerException {
        return null;
    }

    /**
     * 解冻
     *
     * @param id 对外人员基本信息设置数据id
     * @return
     * @throws SerException
     */
    default ForeignStaffingSetBO thaw(String id) throws SerException {
        return null;
    }

    /**
     * 根据状态查询对外人员基本信息设置数据
     *
     * @param status 状态
     * @return
     * @throws SerException
     */
    default List<ForeignStaffingSetBO> findByStatus(Status status) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 对外人员基本信息设置数据传输对象
     * @return
     * @throws SerException
     */
    default List<ForeignStaffingSetBO> maps(ForeignStaffingSetDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取对外人员基本信息设置数据
     *
     * @param id 对外人员基本信息设置数据id
     * @return
     * @throws SerException
     */
    default ForeignStaffingSetBO getById(String id) throws SerException {
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