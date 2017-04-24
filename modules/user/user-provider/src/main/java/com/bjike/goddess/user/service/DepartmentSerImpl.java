package com.bjike.goddess.user.service;


import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.bo.DepartmentBO;
import com.bjike.goddess.user.dto.DepartmentDTO;
import com.bjike.goddess.user.entity.Department;
import com.bjike.goddess.user.to.DepartmentTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
@Service
public class DepartmentSerImpl extends ServiceImpl<Department, DepartmentDTO> implements DepartmentSer {


    @Override
    public List<DepartmentBO> treeData(String id) throws SerException {
        DepartmentDTO dto = new DepartmentDTO();
        if (StringUtils.isNotBlank(id)) {
            dto.getConditions().add(Restrict.eq("parent.id", id)); //查询该父节点下的子节点
        } else {
            dto.getConditions().add(Restrict.isNull("parent.id")); //查找根节点
        }
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));

        List<Department> departments = super.findByCis(dto);

        return BeanTransform.copyProperties(departments, DepartmentBO.class);
    }

    @Override
    public void remove(String id) throws SerException {
        List<Department> children = getChild(id);
        if (null != children && children.size() > 0) {
            throw new SerException("该记录存在子节点数据,请先删除子节点!");
        }

        Department department = super.findById(id);
        Department parent = department.getParent();
        if (null != parent) {
            children = getChild(parent.getId());
            parent.setHasChild(children.size() != 0);
            super.update(parent);
        }
        super.remove(id);
    }

    @Override
    public DepartmentBO save(DepartmentTO departmentTO) throws SerException {
        Department department = BeanTransform.copyProperties(departmentTO, Department.class, true);
        if (StringUtils.isNotBlank(departmentTO.getParentId())) {
            DepartmentDTO dto = new DepartmentDTO();
            dto.getConditions().add(Restrict.eq("id", departmentTO.getParentId()));
            Department parent = findOne(dto);
            if (null != parent) {
                department.setParent(parent);
                parent.setHasChild(true); //更新父类子节点字段为true
                super.update(parent);
            }
        }
        department.setHasChild(false);
        super.save(department);
        return BeanTransform.copyProperties(department, DepartmentBO.class);
    }

    @Override
    public void update(DepartmentTO departmentTO) throws SerException {
        Department department = super.findById(departmentTO.getId());
        Department old_parent = department.getParent();
        String parentId = departmentTO.getParentId();
        Department new_parent = null;
        if (StringUtils.isNotBlank(parentId)) {
            DepartmentDTO dto = new DepartmentDTO();
            dto.getConditions().add(Restrict.eq("id", departmentTO.getParentId()));
            new_parent = super.findOne(dto);
        }

        //更新
        department.setModifyTime(LocalDateTime.now());
        department.setName(departmentTO.getName());
        department.setParent(new_parent);
        department.setModifyTime(LocalDateTime.now());
        super.update(department);


        if (null != new_parent) {
            new_parent.setHasChild(true);
            super.update(new_parent);
        }

        /**
         * 维护该节点与其父类是否有子节点
         */
        if (null != old_parent) { //以前有父节点,删除父节点后,检查其未更改前的父节点是否还存在子节点
            List<Department> children = getChild(old_parent.getId()); //查询以未更改前其父类的所有子节点
            old_parent.setHasChild(children.size() != 0);
            super.update(old_parent);
        }
    }

    private List<Department> getChild(String id) throws SerException {
        DepartmentDTO dto = new DepartmentDTO();
        dto.getConditions().add(Restrict.eq("parent.id", id));
        List<Department> children = findByCis(dto);
        return children;
    }

}
