package com.bjike.goddess.user.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.user.bo.DepartmentBO;
import com.bjike.goddess.user.bo.DepartmentTreeBO;
import com.bjike.goddess.user.dto.DepartmentDTO;
import com.bjike.goddess.user.entity.Department;
import com.bjike.goddess.user.to.DepartmentTO;

import java.util.List;

/**
 * 对外提供部门业务接口
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-11 13:57]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface DepartmentAPI  extends Ser<Department, DepartmentDTO> {

    /**
     * 逐层查询,逐层加载
     *
     * @param id 组id
     * @return
     */
    default List<DepartmentTreeBO> treeData(String id) throws SerException {
        return null;
    }

    /**
     * 保存部门
     * @param departmentTO
     * @return
     * @throws SerException
     */
    default DepartmentBO save(DepartmentTO departmentTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除组
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {
    }
}
