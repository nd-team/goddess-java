package com.bjike.goddess.accruedtax.api;

import com.bjike.goddess.accruedtax.bo.ProjectTaxBO;
import com.bjike.goddess.accruedtax.dto.ProjectTaxDTO;
import com.bjike.goddess.accruedtax.to.GuidePermissionTO;
import com.bjike.goddess.accruedtax.to.ProjectTaxTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 项目上税金业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-24 09:18 ]
 * @Description: [ 项目上税金业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectTaxAPI {
    /**
     * 下拉导航权限
     */
    Boolean sonPermission() throws SerException;

    /**
     * 导航权限
     */
    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;

    /**
     * 项目上税金列表总条数
     *
     */
    default Long countProjectTax(ProjectTaxDTO projectTaxDTO) throws SerException {
        return null;
    }


    /**
     * 项目上列表id
     *
     * @return class ProjectTaxBO
     */
    default ProjectTaxBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 项目上税金列表
     * @return class ProjectTaxBO
     */
    default List<ProjectTaxBO> listProjectTax(ProjectTaxDTO projectTaxDTO) throws SerException {return null;}
    /**
     *  添加
     * @param projectTaxTO 项目上税金信息
     * @return class ProjectTaxBO
     */
    default ProjectTaxBO addProjectTax(ProjectTaxTO projectTaxTO) throws SerException { return null;}

    /**
     *  编辑
     * @param projectTaxTO 项目上税金信息
     * @return class ProjectTaxBO
     */
    default ProjectTaxBO editProjectTax(ProjectTaxTO projectTaxTO) throws SerException { return null;}

    /**
     * 删除
     * @param id id
     */
    default void deleteProjectTax(String id ) throws SerException {return;};


    /**
     * 根据项目组汇总
     *
     * @return class ProjectTaxBO
     */
    default List<ProjectTaxBO> collectCompany(ProjectTaxDTO projectTaxDTO) throws SerException {
        return null;
    }

    /**
     * 根据税种汇总
     *
     * @return class ProjectTaxBO
     */
    default List<ProjectTaxBO> collectTaxType(ProjectTaxDTO projectTaxDTO) throws SerException {
        return null;
    }

    /**
     * 获取所有项目组
     *
     */
    default List<String> listProject( ) throws SerException {
        return null;
    }
    /**
     * 获取所有税种
     */
    default List<String> listTaxType( ) throws SerException {
        return null;
    }
    
}