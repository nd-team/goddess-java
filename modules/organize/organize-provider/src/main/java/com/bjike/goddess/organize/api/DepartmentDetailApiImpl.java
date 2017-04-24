package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.dto.DepartmentDetailDTO;
import com.bjike.goddess.organize.service.DepartmentDetailSer;
import com.bjike.goddess.organize.to.DepartmentDetailTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门详细业务实现
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午9:54]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@Service("departmentDetailApiImpl")
public class DepartmentDetailApiImpl implements DepartmentDetailAPI {

    @Autowired
    private DepartmentDetailSer departmentDetailSer;

    @Override
    public List<DepartmentDetailBO> view(DepartmentDetailDTO dto) throws SerException {
        return departmentDetailSer.view(dto);
    }


    @Override
    public List<DepartmentDetailBO> findByHierarchy(String hierarchy_id) throws SerException {
        return departmentDetailSer.findByHierarchy(hierarchy_id);
    }

    @Override
    public List<DepartmentDetailBO> findStatus() throws SerException {
        return departmentDetailSer.findStatus();
    }

    @Override
    public List<DepartmentDetailBO> findByDepartmentIds(List<String> ids) throws SerException {
        return departmentDetailSer.findByDepartmentIds(ids);
    }

    @Override
    public DepartmentDetailBO findByDepartment(String id) throws SerException {
        return departmentDetailSer.findByDepartment(id);
    }

    @Override
    public DepartmentDetailBO findBOById(String id) throws SerException {
        return departmentDetailSer.findBOById(id);
    }

    @Override
    public DepartmentDetailBO save(DepartmentDetailTO to) throws SerException {
        return departmentDetailSer.save(to);
    }

    @Override
    public DepartmentDetailBO update(DepartmentDetailTO to) throws SerException {
        return departmentDetailSer.update(to);
    }

    @Override
    public DepartmentDetailBO delete(String id) throws SerException {
        return departmentDetailSer.delete(id);
    }

    @Override
    public DepartmentDetailBO getById(String id) throws SerException {
        return departmentDetailSer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        DepartmentDetailDTO dto = new DepartmentDetailDTO();
        return departmentDetailSer.count(dto);
    }
}
