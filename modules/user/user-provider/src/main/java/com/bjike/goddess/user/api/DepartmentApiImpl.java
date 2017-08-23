package com.bjike.goddess.user.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.bo.DepartmentBO;
import com.bjike.goddess.user.service.DepartmentSer;
import com.bjike.goddess.user.to.DepartmentTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-11 14:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("departmentApiImpl")
public class DepartmentApiImpl implements DepartmentAPI {
    @Autowired
    private DepartmentSer departmentSer;


    @Override
    public List<DepartmentBO> treeData(String id) throws SerException {
        return departmentSer.treeData(id);
    }

    @Override
    public DepartmentBO save(DepartmentTO DepartmentTO) throws SerException {
        return departmentSer.save(DepartmentTO);
    }

    @Override
    public void remove(String id) throws SerException {
        departmentSer.remove(id);
    }

    @Override
    public void update(DepartmentTO departmentTO) throws SerException {
        departmentSer.update(departmentTO);
    }
}
