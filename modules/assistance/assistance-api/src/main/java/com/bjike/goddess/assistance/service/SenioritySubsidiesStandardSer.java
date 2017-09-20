package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.SenioritySubsidiesStandardBO;
import com.bjike.goddess.assistance.dto.SenioritySubsidiesStandardDTO;
import com.bjike.goddess.assistance.entity.SenioritySubsidiesStandard;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.assistance.to.SenioritySubsidiesStandardTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 工龄补助标准业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 02:07 ]
 * @Description: [ 工龄补助标准业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SenioritySubsidiesStandardSer extends Ser<SenioritySubsidiesStandard, SenioritySubsidiesStandardDTO> {
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
     * 工龄补助标准列表总条数
     */
    default Long countSenior(SenioritySubsidiesStandardDTO senioritySubsidiesStandardDTO) throws SerException {
        return null;
    }

    /**
     * 一个工龄补助标准
     *
     * @return class SenioritySubsidiesStandardBO
     */
    default SenioritySubsidiesStandardBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 工龄补助标准列表
     *
     * @return class SenioritySubsidiesStandardBO
     */
    default List<SenioritySubsidiesStandardBO> listSenior(SenioritySubsidiesStandardDTO senioritySubsidiesStandardDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param senioritySubsidiesStandardTO 工龄补助标准
     * @return class SenioritySubsidiesStandardBO
     */
    default SenioritySubsidiesStandardBO addSenior(SenioritySubsidiesStandardTO senioritySubsidiesStandardTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param senioritySubsidiesStandardTO 工龄补助标准
     * @return class SenioritySubsidiesStandardBO
     */
    default SenioritySubsidiesStandardBO editSenior(SenioritySubsidiesStandardTO senioritySubsidiesStandardTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteSubsidy(String id) throws SerException {
        return;
    }
}