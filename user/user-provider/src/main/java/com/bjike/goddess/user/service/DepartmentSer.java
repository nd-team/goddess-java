package com.bjike.goddess.user.service;


import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.dto.DepartmentDTO;
import com.bjike.goddess.user.entity.Department;
import com.bjike.goddess.user.sto.DepartmentSTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

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
    public List<DepartmentSTO> list() throws SerException {
        return BeanTransform.copyProperties(super.findAll(), DepartmentSTO.class);
    }
}
