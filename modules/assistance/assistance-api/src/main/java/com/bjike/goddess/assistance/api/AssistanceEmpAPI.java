package com.bjike.goddess.assistance.api;

import com.bjike.goddess.assistance.bo.AssistanceEmpBO;
import com.bjike.goddess.assistance.dto.AssistanceEmpDTO;
import com.bjike.goddess.assistance.to.AssistanceEmpTO;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 补助员工名单业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:12 ]
 * @Description: [ 补助员工名单业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AssistanceEmpAPI {
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
     * 补助员工名单列表总条数
     *
     */
    default Long countAssistanceEmp(AssistanceEmpDTO assistanceEmpDTO) throws SerException {
        return null;
    }
    /**
     * 补助员工名单列表
     * @return class AssistanceEmpBO
     */
    default List<AssistanceEmpBO> listAssistanceEmp(AssistanceEmpDTO assistanceEmpDTO) throws SerException {return null;}
    /**
     *  添加
     * @param assistanceEmpTO 补助员工名单信息
     * @return class AssistanceEmpBO
     */
    default AssistanceEmpBO addAssistanceEmp(AssistanceEmpTO assistanceEmpTO) throws SerException { return null;}

    /**
     *  编辑
     * @param assistanceEmpTO 补助员工名单信息
     * @return class AssistanceEmpBO
     */
    default AssistanceEmpBO editAssistanceEmp(AssistanceEmpTO assistanceEmpTO) throws SerException { return null;}

    /**
     * 删除级别
     * @param id id
     */
    default void deleteAssistanceEmp(String id ) throws SerException {return;};

}