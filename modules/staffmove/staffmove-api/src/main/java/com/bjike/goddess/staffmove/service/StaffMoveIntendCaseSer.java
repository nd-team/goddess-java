package com.bjike.goddess.staffmove.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffmove.bo.StaffMoveIntendCaseBO;
import com.bjike.goddess.staffmove.dto.StaffMoveIntendCaseDTO;
import com.bjike.goddess.staffmove.entity.StaffMoveIntendCase;
import com.bjike.goddess.staffmove.to.GuidePermissionTO;
import com.bjike.goddess.staffmove.to.StaffMoveIntendCaseTO;

import java.util.List;

/**
 * 人员调动意愿情况业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-03 02:36 ]
 * @Description: [ 人员调动意愿情况业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface StaffMoveIntendCaseSer extends Ser<StaffMoveIntendCase, StaffMoveIntendCaseDTO> {
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
     * 人员调动意愿情况列表总条数
     */
    default Long count(StaffMoveIntendCaseDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个人员调动意愿情况
     *
     * @return class StaffMoveIntendCaseBO
     */
    default StaffMoveIntendCaseBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 人员调动意愿情况
     *
     * @param dto 人员调动意愿情况dto
     * @return class StaffMoveIntendCaseBO
     * @throws SerException
     */
    default List<StaffMoveIntendCaseBO> list(StaffMoveIntendCaseDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加人员调动意愿情况
     *
     * @param to 人员调动意愿情况数据to
     * @return class StaffMoveIntendCaseBO
     * @throws SerException
     */
    default StaffMoveIntendCaseBO insert(StaffMoveIntendCaseTO to) throws SerException {
        return null;
    }

    /**
     * 编辑人员调动意愿情况
     *
     * @param to 人员调动意愿情况数据to
     * @return class StaffMoveIntendCaseBO
     * @throws SerException
     */
    default StaffMoveIntendCaseBO edit(StaffMoveIntendCaseTO to) throws SerException {
        return null;
    }

    /**
     * 根据id删除人员调动意愿情况
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }

    /**
     * 获取所有姓名
     *
     * @return
     * @throws SerException
     */
    List<String> getName() throws SerException;

    /**
     * 导入
     *
     * @param caseTOS 人员调动意愿情况
     * @return class StaffMoveIntendCaseBO
     */
    default StaffMoveIntendCaseBO importExcel(List<StaffMoveIntendCaseTO> caseTOS) throws SerException {
        return null;
    }

    /**
     * 导出Excel
     *
     * @param dto
     * @throws SerException
     */
    byte[] exportExcel(StaffMoveIntendCaseDTO dto) throws SerException;

    /**
     * 导出Excel
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;

}