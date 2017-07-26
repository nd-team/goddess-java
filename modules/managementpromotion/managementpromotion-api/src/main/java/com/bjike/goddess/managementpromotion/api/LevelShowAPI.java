package com.bjike.goddess.managementpromotion.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.managementpromotion.bo.LevelShowBO;
import com.bjike.goddess.managementpromotion.dto.LevelShowDTO;
import com.bjike.goddess.managementpromotion.entity.LevelShow;
import com.bjike.goddess.managementpromotion.to.GuidePermissionTO;
import com.bjike.goddess.managementpromotion.to.LevelShowTO;

import java.util.List;

/**
 * 管理等级情况慨览业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-22 01:53 ]
 * @Description: [ 管理等级情况慨览业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface LevelShowAPI {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param to 管理等级情况慨览信息
     * @return class LevelShowBO
     * @throws SerException
     */
    default LevelShowBO save(LevelShowTO to) throws SerException {
        return null;
    }

    /**
     * 通过id查找
     *
     * @param id 管理等级情况慨览id
     * @return class LevelShowBO
     * @throws SerException
     */
    default LevelShowBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 更新
     *
     * @param to 管理等级情况慨览信息
     * @throws SerException
     */
    default void update(LevelShowTO to) throws SerException {
    }

    /**
     * 管理等级情况慨览列表
     *
     * @param dto 管理等级情况慨览dto
     * @return class LevelShowBO
     * @throws SerException
     */
    default List<LevelShowBO> find(LevelShowDTO dto) throws SerException {
        return null;
    }

    /**
     * 查找总记录数
     *
     * @param dto dto
     * @throws SerException
     */
    default Long count(LevelShowDTO dto) throws SerException {
        return null;
    }

    /**
     * 查找所有
     *
     * @param dto dto
     * @return class LevelShowBO
     * @throws SerException
     */
    default List<LevelShowBO> findAll(LevelShowDTO dto) throws SerException {
        return null;
    }

    /**
     * 通过员工编号查找
     *
     * @param employeeId 员工编号
     * @return class LevelShow
     * @throws SerException
     */
    LevelShow findByEmployeeId(String employeeId) throws SerException;
}