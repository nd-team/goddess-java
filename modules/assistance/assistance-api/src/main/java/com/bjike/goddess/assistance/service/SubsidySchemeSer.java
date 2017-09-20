package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.SubsidySchemeBO;
import com.bjike.goddess.assistance.dto.SubsidySchemeDTO;
import com.bjike.goddess.assistance.entity.SubsidyScheme;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.assistance.to.SubsidySchemeTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 公司补助方案业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 10:30 ]
 * @Description: [ 公司补助方案业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SubsidySchemeSer extends Ser<SubsidyScheme, SubsidySchemeDTO> {
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
     * 公司补助方案列表总条数
     */
    default Long countSubsidy(SubsidySchemeDTO subsidySchemeDTO) throws SerException {
        return null;
    }

    /**
     * 一个公司补助方案
     *
     * @return class SubsidySchemeBO
     */
    default SubsidySchemeBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 公司补助方案列表
     *
     * @return class SubsidySchemeBO
     */
    default List<SubsidySchemeBO> listSubsidy(SubsidySchemeDTO subsidySchemeDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param subsidySchemeTO 公司补助方案
     * @return class SubsidySchemeBO
     */
    default SubsidySchemeBO addSubsidy(SubsidySchemeTO subsidySchemeTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param subsidySchemeTO 公司补助方案
     * @return class SubsidySchemeBO
     */
    default SubsidySchemeBO editSubsidy(SubsidySchemeTO subsidySchemeTO) throws SerException {
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

    /**
     * 项目经理审核
     * @param subsidySchemeTO
     * @throws SerException
     */
    default void manageAudit(SubsidySchemeTO subsidySchemeTO) throws SerException{
        return;
    }
}