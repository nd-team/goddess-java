package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.bo.DepartmentPeopleBO;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.organize.dto.DepartmentDetailDTO;
import com.bjike.goddess.organize.service.DepartmentDetailSer;
import com.bjike.goddess.organize.to.DepartmentDetailTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
    public DepartmentDetailBO congeal(String id) throws SerException {
        return departmentDetailSer.congeal(id);
    }

    @Override
    public DepartmentDetailBO thaw(String id) throws SerException {
        return departmentDetailSer.thaw(id);
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

    @Override
    public List<AreaBO> findArea() throws SerException {
        return departmentDetailSer.findArea();
    }

    @Override
    public List<DepartmentDetailBO> findByArea(String area) throws SerException {
        return departmentDetailSer.findByArea(area);
    }

    @Override
    public List<String> findDepartByArea(String area) throws SerException {
        return departmentDetailSer.findDepartByArea( area );
    }

    @Override
    public List<String> findPnameByAreaAndDepart(String area, String depart) throws SerException {
        return departmentDetailSer.findPnameByAreaAndDepart( area ,depart);
    }

    @Override
    public List<String> findAllProject() throws SerException {
        return departmentDetailSer.findAllProject();
    }

    @Override
    public List<OpinionBO> findByIds(String... ids) throws SerException {
        return departmentDetailSer.findByIds(ids);
    }

    @Override
    public List<OpinionBO> findThawOpinion() throws SerException {
        return departmentDetailSer.findThawOpinion();
    }

    @Override
    public List<OpinionBO> findAllOpinion() throws SerException {
        return departmentDetailSer.findAllOpinion();
    }

    @Override
    public String number(DepartmentDetailTO to) throws SerException {
        return departmentDetailSer.number(to);
    }

    @Override
    public Integer departmentTotalPeople(String department) throws SerException {
        return departmentDetailSer.departmentTotalPeople(department);
    }

    @Override
    public Integer getAreaNum(String startTime, String endTime) throws SerException {
        return departmentDetailSer.getAreaNum(startTime, endTime);
    }

    @Override
    public Set<String> departPersons(String departId) throws SerException {
        return departmentDetailSer.departPersons(departId);
    }

    @Override
    public List<DepartmentDetailBO> departByName(String[] departs) throws SerException {
        return departmentDetailSer.departByName(departs);
    }

    @Override
    public List<String> findAllDepartment() throws SerException {
        return departmentDetailSer.findAllDepartment();
    }

    @Override
    public List<DepartmentPeopleBO> peopleByDepartment() throws SerException {
        return departmentDetailSer.peopleByDepartment();
    }

    @Override
    public List<DepartmentPeopleBO> departmentByName(String name) throws SerException {
        return departmentDetailSer.departmentByName(name);
    }
}
