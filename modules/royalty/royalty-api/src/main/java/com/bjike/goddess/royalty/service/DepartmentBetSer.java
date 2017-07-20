package com.bjike.goddess.royalty.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.royalty.bo.DepartmentBetABO;
import com.bjike.goddess.royalty.bo.DepartmentBetBO;
import com.bjike.goddess.royalty.dto.DepartmentBetBDTO;
import com.bjike.goddess.royalty.dto.DepartmentBetDDTO;
import com.bjike.goddess.royalty.dto.DepartmentBetDTO;
import com.bjike.goddess.royalty.entity.DepartmentBet;
import com.bjike.goddess.royalty.to.DepartmentBetATO;
import com.bjike.goddess.royalty.to.DepartmentBetTO;
import com.bjike.goddess.royalty.to.GuidePermissionTO;

import java.util.List;

/**
 * 部门间对赌表业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:16 ]
 * @Description: [ 部门间对赌表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DepartmentBetSer extends Ser<DepartmentBet, DepartmentBetDTO> {
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
     * 部门间对赌表列表总条数
     */
    default Long count(DepartmentBetDDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个部门间对赌表
     *
     * @return class DepartmentBetABO
     */
    default DepartmentBetABO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 部门间对赌表
     *
     * @param dto 部门间对赌表数据dto
     * @return class DepartmentBetBO
     * @throws SerException
     */
    default List<DepartmentBetBO> list(DepartmentBetDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加部门间对赌表
     *
     * @param departmentBetATO 部门间对赌表数据to
     * @throws SerException
     */
    default void insert(DepartmentBetATO departmentBetATO) throws SerException {
    }

    /**
     * 编辑部门间对赌表
     *
     * @param departmentBetATO 部门间对赌表数据to
     * @throws SerException
     */
    default void edit(DepartmentBetATO departmentBetATO) throws SerException {
    }

    /**
     * 根据id删除部门间对赌表
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {

    }
}