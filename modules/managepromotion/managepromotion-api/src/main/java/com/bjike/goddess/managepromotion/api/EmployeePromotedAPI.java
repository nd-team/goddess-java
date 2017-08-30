package com.bjike.goddess.managepromotion.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.managepromotion.bo.CollectBO;
import com.bjike.goddess.managepromotion.bo.EmployeePromotedBO;
import com.bjike.goddess.managepromotion.dto.EmployeePromotedDTO;
import com.bjike.goddess.managepromotion.to.CollectTO;
import com.bjike.goddess.managepromotion.to.EmployeePromotedTO;
import com.bjike.goddess.managepromotion.to.GuidePermissionTO;

import java.util.List;

/**
 * 员工已晋升情况业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 09:20 ]
 * @Description: [ 员工已晋升情况业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface EmployeePromotedAPI {
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
     * 员工已晋升情况列表总条数
     */
    default Long countEmployeePromoted(EmployeePromotedDTO employeePromotedDTO) throws SerException {
        return null;
    }

    /**
     * 一个员工已晋升情况
     *
     * @return class EmployeePromotedBO
     */
    default EmployeePromotedBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 员工已晋升情况
     *
     * @param employeePromotedDTO 员工已晋升情况dto
     * @return class EmployeePromotedBO
     * @throws SerException
     */
    default List<EmployeePromotedBO> findListEmployeePromoted(EmployeePromotedDTO employeePromotedDTO) throws SerException {
        return null;
    }

    /**
     * 添加员工已晋升情况
     *
     * @param employeePromotedTO 员工已晋升情况数据to
     * @return class EmployeePromotedBO
     * @throws SerException
     */
    default EmployeePromotedBO insertEmployeePromoted(EmployeePromotedTO employeePromotedTO) throws SerException {
        return null;
    }

    /**
     * 编辑员工已晋升情况
     *
     * @param employeePromotedTO 员工已晋升情况数据to
     * @return class EmployeePromotedBO
     * @throws SerException
     */
    default EmployeePromotedBO editEmployeePromoted(EmployeePromotedTO employeePromotedTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除员工已晋升情况
     *
     * @param id
     * @throws SerException
     */
    default void removeEmployeePromoted(String id) throws SerException {

    }

    /**
     * 汇总
     *
     * @param to
     * @throws SerException
     */
    default List<CollectBO> collect(CollectTO to) throws SerException {
        return null;
    }
    /**
     * 获取所有姓名
     *
     * @throws SerException
     */
    default List<String> getName() throws SerException {
        return null;
    }
    /**
     * 获取所有状态
     *
     * @throws SerException
     */
    default List<String> getStatus() throws SerException {
        return null;
    }
}