package com.bjike.goddess.user.service;


import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.dto.DepartmentDTO;
import com.bjike.goddess.user.entity.Department;
import com.bjike.goddess.user.bo.DepartmentBO;
import com.bjike.goddess.user.bo.DepartmentTreeBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 部门业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2016-12-28 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "userSerCache")
@Service("departmentSer")
public class DepartmentSer extends ServiceImpl<Department, DepartmentDTO> implements DepartmentAPI {


    @Override
    public List<DepartmentTreeBO> treeData(String id) throws SerException {
        DepartmentDTO dto = new DepartmentDTO();
        if (StringUtils.isNotBlank(id)) {
            dto.getConditions().add(Restrict.eq("parent.id", id)); //查询该父节点下的子节点
        } else {
            dto.getConditions().add(Restrict.isNull("parent.id")); //查找根节点
        }
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));

        List<Department> departments = super.findByCis(dto);
        List<DepartmentTreeBO> departmentTreeBOS = new ArrayList<>(departments.size());
        departments.stream().forEach(permission -> {
            DepartmentTreeBO bo = new DepartmentTreeBO();
            bo.setName(permission.getName());
            bo.setId(permission.getId());
            bo.setParent(null == permission.getParent());
            departmentTreeBOS.add(bo);
        });
        return departmentTreeBOS;
    }

    @Override
    public void remove(String id) throws SerException {
        DepartmentDTO dto = new DepartmentDTO();
        dto.getConditions().add(Restrict.eq("parent.id", id));
        List<Department> children = findByCis(dto);
        if (null != children && children.size() > 0) {
            throw new SerException("该记录存在子节点数据,请先删除子节点!");
        }
        super.remove(id);
    }

    @Override
    public DepartmentBO saveByBO(DepartmentBO bo) throws SerException {
        Department department = BeanTransform.copyProperties(bo, Department.class, true);
        super.save(department);
        bo.setId(department.getId());//复制id
        return bo;
    }
}
