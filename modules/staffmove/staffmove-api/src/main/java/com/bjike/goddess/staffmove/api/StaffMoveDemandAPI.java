package com.bjike.goddess.staffmove.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffmove.bo.StaffMoveDemandBO;
import com.bjike.goddess.staffmove.dto.StaffMoveDemandDTO;
import com.bjike.goddess.staffmove.excel.SonPermissionObject;
import com.bjike.goddess.staffmove.to.GuidePermissionTO;
import com.bjike.goddess.staffmove.to.StaffMoveDemandTO;

import java.util.List;

/**
 * 人员调动需求业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-03 02:03 ]
 * @Description: [ 人员调动需求业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface StaffMoveDemandAPI {
    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 功能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 人员调动需求列表总条数
     */
    default Long count(StaffMoveDemandDTO dto) throws SerException {
        return null;
    }
    /**
     * 一个人员调动需求
     *
     * @return class StaffMoveDemandBO
     */
    default StaffMoveDemandBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 人员调动需求
     *
     * @param dto 人员调动需求dto
     * @return class StaffMoveDemandBO
     * @throws SerException
     */
    default List<StaffMoveDemandBO> list(StaffMoveDemandDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加人员调动需求
     *
     * @param to 人员调动需求数据to
     * @return class StaffMoveDemandBO
     * @throws SerException
     */
    default StaffMoveDemandBO insert(StaffMoveDemandTO to) throws SerException {
        return null;
    }

    /**
     * 编辑人员调动需求
     *
     * @param to 人员调动需求数据to
     * @return class StaffMoveDemandBO
     * @throws SerException
     */
    default StaffMoveDemandBO edit(StaffMoveDemandTO to) throws SerException {
        return null;
    }

    /**
     * 根据id删除人员调动需求
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }
}