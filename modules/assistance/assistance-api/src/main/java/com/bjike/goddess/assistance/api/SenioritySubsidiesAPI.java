package com.bjike.goddess.assistance.api;

import com.bjike.goddess.assistance.bo.SenioritySubsidiesBO;
import com.bjike.goddess.assistance.dto.SenioritySubsidiesDTO;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.assistance.to.SenioritySubsidiesTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 工龄补助业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 11:34 ]
 * @Description: [ 工龄补助业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SenioritySubsidiesAPI {
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
     * 工龄补助列表总条数
     */
    default Long countSenSub(SenioritySubsidiesDTO senioritySubsidiesDTO) throws SerException {
        return null;
    }

    /**
     * 一个工龄补助
     *
     * @return class SenioritySubsidiesBO
     */
    default SenioritySubsidiesBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 工龄补助列表
     *
     * @return class SenioritySubsidiesBO
     */
    default List<SenioritySubsidiesBO> listSenSub(SenioritySubsidiesDTO senioritySubsidiesDTO) throws SerException {
        return null;
    }
    /**
     * 添加
     *
     */
    default void saveSen(SenioritySubsidiesTO senioritySubsidiesTO) throws SerException {
        return;
    }
    /**
     * 编辑
     *
     */
    default void editSen(SenioritySubsidiesTO senioritySubsidiesTO) throws SerException {
        return;
    }

    /**
     * 每月二十日检测本公司工龄（月）
     * @throws SerException
     */
    default void checkDate() throws SerException{return;}
    /**
     * 导出Excel
     * @throws SerException
     */
    byte[] exportExcel() throws SerException;

    /**
     * 导出Excel
     * @throws SerException
     */
    byte[] templateExport() throws SerException;

    /**
     *  导入
     * @param senioritySubsidiesTOS 工龄补助
     */
    void importExcel(List<SenioritySubsidiesTO> senioritySubsidiesTOS) throws SerException;

    /**
     * 根据员工姓名获取工龄补助
     */
    SenioritySubsidiesBO findAge(String employeeName) throws SerException;

}