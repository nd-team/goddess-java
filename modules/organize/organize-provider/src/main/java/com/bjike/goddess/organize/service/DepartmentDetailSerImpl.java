package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.*;
import com.bjike.goddess.organize.dto.DepartmentDetailDTO;
import com.bjike.goddess.organize.entity.DepartmentDetail;
import com.bjike.goddess.organize.entity.Hierarchy;
import com.bjike.goddess.organize.to.DepartmentDetailTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 部门详细业务实现
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午9:54]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@Service
public class DepartmentDetailSerImpl extends ServiceImpl<DepartmentDetail, DepartmentDetailDTO> implements DepartmentDetailSer {

    @Autowired
    private WorkRangeSer workRangeSer;

    @Autowired
    private HierarchySer hierarchySer;

    @Autowired
    private PositionDetailSer positionDetailSer;

    private DepartmentDetailBO transformationToBO(DepartmentDetail entity) throws SerException {
        DepartmentDetailBO bo = BeanTransform.copyProperties(entity, DepartmentDetailBO.class);
        bo.setHierarchyId(entity.getHierarchy().getId());
        bo.setHierarchyName(entity.getHierarchy().getHierarchy());
        bo.setHierarchyNumber(entity.getHierarchy().getSerialNumber());
        //体系-部门
        bo.setShowNumber(String.format("%s-%s", entity.getHierarchy().getSerialNumber(), entity.getSerialNumber()));
        return bo;
    }

    @Override
    public String number(DepartmentDetailTO to) throws SerException {
        if (null == to.getHierarchyId()) {
            throw new SerException("必须先选体系");
        }
        if (null == to.getSerialNumber()) {
            throw new SerException("编号不能为空");
        }
        Hierarchy hierarchy = hierarchySer.findById(to.getHierarchyId());
        if (null == hierarchy) {
            throw new SerException("体系不能为空");
        }
        //体系-部门
        String number = String.format("%s-%s", hierarchy.getSerialNumber(), to.getSerialNumber());
        return number;
    }

    @Override
    public Integer getAreaNum(String startTime, String endTime) throws SerException {
        String fields[] = new String[]{"area"};
        StringBuilder sql = new StringBuilder("select count(area) from organize_department_detail ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" where createTime between '" + startTime + "' ");
            sql.append(" and '" + endTime + "' ");
        }
        List<ManagerBO> managerBOs = super.findBySql(sql.toString(), ManagerBO.class, fields);
        if (null != managerBOs && managerBOs.size() > 0) {
            List<Integer> areas = managerBOs.stream().map(ManagerBO::getArea).distinct().collect(Collectors.toList());
            return areas.get(0);
        }
        return 0;
    }

    @Override
    public List<String> getAreas(String startTime, String endTime) throws SerException {
        String fields[] = new String[]{"areas"};
        StringBuilder sql = new StringBuilder("select area as areas from organize_department_detail ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" where createTime between '" + startTime + "' ");
            sql.append(" and '" + endTime + "' ");
        }
        List<ManagerBO> managerBOs = super.findBySql(sql.toString(), ManagerBO.class, fields);
        if (null != managerBOs && managerBOs.size() > 0) {
            List<String> areas = managerBOs.stream().map(ManagerBO::getAreas).distinct().collect(Collectors.toList());
            return areas;
        }
        return null;
    }

    @Override
    public List<String> getDepartments(String startTime, String endTime) throws SerException {
        String fields[] = new String[]{"departments"};
        StringBuilder sql = new StringBuilder("select department as departments from organize_department_detail ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" where createTime between '" + startTime + "' ");
            sql.append(" and '" + endTime + "' ");
        }
        List<ManagerBO> managerBOs = super.findBySql(sql.toString(), ManagerBO.class, fields);
        if (null != managerBOs && managerBOs.size() > 0) {
            List<String> departments = managerBOs.stream().map(ManagerBO::getDepartments).distinct().collect(Collectors.toList());
            return departments;
        }
        return null;
    }

    @Override
    public Integer getDepartmentNum(String startTime, String endTime) throws SerException {
        String fields[] = new String[]{"department"};
        StringBuilder sql = new StringBuilder("select count(department) from organize_department_detail ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" where createTime between '" + startTime + "' ");
            sql.append(" and '" + endTime + "' ");
        }
        List<ManagerBO> managerBOs = super.findBySql(sql.toString(), ManagerBO.class, fields);
        if (null != managerBOs && managerBOs.size() > 0) {
            List<Integer> areas = managerBOs.stream().map(ManagerBO::getDepartment).distinct().collect(Collectors.toList());
            return areas.get(0);
        }
        return 0;
    }

    private List<DepartmentDetailBO> transformationToBOList(List<DepartmentDetail> list) throws SerException {
        List<DepartmentDetailBO> bos = new ArrayList<>(list.size());
        for (DepartmentDetail detail : list)
            bos.add(this.transformationToBO(detail));
        return bos;
    }

    @Cacheable(value = {"listCache"}, key = "#dto.toString()")
    @Override
    public List<DepartmentDetailBO> view(DepartmentDetailDTO dto) throws SerException {
        return this.transformationToBOList(super.findByPage(dto));
    }


    @Override
    public List<DepartmentDetailBO> findByHierarchy(String hierarchyId) throws SerException {
        DepartmentDetailDTO dto = new DepartmentDetailDTO();
        dto.getConditions().add(Restrict.eq("hierarchy.id", hierarchyId));
        return this.transformationToBOList(super.findByCis(dto));
    }
    //获取所有项目组/部门未冻结
    @Override
    public List<DepartmentDetailBO> findStatus() throws SerException {
        DepartmentDetailDTO departmentDTO = new DepartmentDetailDTO();
        departmentDTO.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<DepartmentDetail> list = super.findByCis(departmentDTO);
        return this.transformationToBOList(list);
    }

    @Override
    public List<DepartmentDetailBO> findByDepartmentIds(List<String> ids) throws SerException {
        DepartmentDetailDTO dto = new DepartmentDetailDTO();
        dto.getConditions().add(Restrict.in("id", ids));
        return this.transformationToBOList(super.findByCis(dto));
    }

    @Override
    public DepartmentDetailBO findBOById(String id) throws SerException {
        return this.transformationToBO(super.findById(id));
    }

    /**
     * 检测部门或部门编号是否重复
     *
     * @param to
     * @throws SerException
     */
    private void checkUnique(DepartmentDetailTO to) throws SerException {
        String[] fields = {"id", "department"};
        StringBuilder sql = new StringBuilder(" SELECT ");
        sql.append(" id,department ").append(" FROM organize_department_detail ");
        sql.append(" WHERE serialNumber='").append(to.getSerialNumber()).append("' OR department='").append(to.getDepartment()).append("'");
        List<DepartmentDetailBO> list = super.findBySql(sql.toString(), DepartmentDetailBO.class, fields);
        if (list.size() > 0)
            throw new SerException("部门或编号已存在,无法保存");
    }

    @CacheEvict(value = "listCache", allEntries = true)
    @Transactional(rollbackFor = SerException.class)
    @Override
    public DepartmentDetailBO save(DepartmentDetailTO to) throws SerException {
        this.checkUnique(to);
        StringBuilder sb = new StringBuilder();
        DepartmentDetail department = BeanTransform.copyProperties(to, DepartmentDetail.class, true);
        department.setHierarchy(hierarchySer.findById(to.getHierarchyId()));
        if (department.getHierarchy() == null)
            throw new SerException("体系不能为空");
        String[] innerProjects = to.getInnerProjects();
        for (int i = 0; i < innerProjects.length; i++) {
            if (i == innerProjects.length - 1) {
                sb.append(innerProjects[i]);
            } else {
                sb.append(innerProjects[i] + ",");
            }
        }
        department.setInnerProject(sb.toString());
        department.setCreateTime(LocalDateTime.now());
        department.setStatus(Status.THAW);
        super.save(department);
        return this.transformationToBO(department);
    }

    @CacheEvict(value = "listCache", allEntries = true)
    @Transactional(rollbackFor = SerException.class)
    @Override
    public DepartmentDetailBO update(DepartmentDetailTO to) throws SerException {
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据ID不能为空");
        StringBuilder sb = new StringBuilder();
        DepartmentDetail entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
        if (!entity.getSerialNumber().equals(to.getSerialNumber())) {
            DepartmentDetailDTO dto = new DepartmentDetailDTO();
            dto.getConditions().add(Restrict.eq("serialNumber", to.getSerialNumber()));
            if (super.count(dto) != 0)
                throw new SerException("编号已存在,无法保存");
        } else if (!entity.getDepartment().equals(to.getDepartment())) {
            DepartmentDetailDTO dto = new DepartmentDetailDTO();
            dto.getConditions().add(Restrict.eq("department", to.getDepartment()));
            if (super.count(dto) != 0)
                throw new SerException("部门已存在,无法保存");
        }
        BeanTransform.copyProperties(to, entity, true);
        entity.setHierarchy(hierarchySer.findById(to.getHierarchyId()));
        if (entity.getHierarchy() == null)
            throw new SerException("体系不能为空");
        String[] innerProjects = to.getInnerProjects();
        for (int i = 0; i < innerProjects.length; i++) {
            if (i == innerProjects.length - 1) {
                sb.append(innerProjects[i]);
            } else {
                sb.append(innerProjects[i] + ",");
            }
        }
        entity.setInnerProject(sb.toString());
        entity.setModifyTime(LocalDateTime.now());
        entity.setDescription(to.getDescription());
        super.update(entity);
        return this.transformationToBO(entity);
    }

    @Override
    public DepartmentDetailBO congeal(String id) throws SerException {
        DepartmentDetail entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return this.transformationToBO(entity);
    }

    @Override
    public DepartmentDetailBO thaw(String id) throws SerException {
        DepartmentDetail entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        entity.setStatus(Status.THAW);
        super.update(entity);
        return this.transformationToBO(entity);
    }

    @CacheEvict(value = "listCache", key = "#id", allEntries = true, condition = "#id != ''")
    @Override
    public DepartmentDetailBO delete(String id) throws SerException {
        DepartmentDetail entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        if (workRangeSer.findByDepartment(id).size() != 0 || positionDetailSer.findByDepartment(id).size() != 0)
            throw new SerException("此处已被引用,无法删除");
        super.remove(entity);
        return this.transformationToBO(entity);
    }

    @Override
    public DepartmentDetailBO getById(String id) throws SerException {
        return this.transformationToBO(super.findById(id));
    }
    //获取所有地区名称
    @Override
    public List<AreaBO> findArea() throws SerException {
        List<DepartmentDetail> list = super.findAll().stream()
                .sorted(Comparator.comparing(DepartmentDetail::getArea))
                .collect(Collectors.toList());
        List<AreaBO> bos = new ArrayList<>(0);
        String area = "";
        for (DepartmentDetail entity : list)
            if (!entity.getArea().equals(area)) {
                area = entity.getArea();
                bos.add(new AreaBO(area));
            }
        return bos;
    }

    @Override
    public List<DepartmentDetailBO> findByArea(String area) throws SerException {
        DepartmentDetailDTO dto = new DepartmentDetailDTO();
        dto.getConditions().add(Restrict.eq("area", area));
        return this.transformationToBOList(super.findByCis(dto));
    }

    @Override
    public List<String> findDepartByArea(String area) throws SerException {
        DepartmentDetailDTO dto = new DepartmentDetailDTO();
        dto.getConditions().add(Restrict.eq("area", area));
        List<DepartmentDetail> list = super.findByCis(dto);
        List<String> opinionBOList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            opinionBOList = list.stream().filter(str -> StringUtils.isNotBlank(str.getDepartment())).map(DepartmentDetail::getDepartment).collect(Collectors.toList());
        }
        return opinionBOList;
    }

    @Override
    public List<String> findPnameByAreaAndDepart(String area, String depart) throws SerException {
        DepartmentDetailDTO dto = new DepartmentDetailDTO();
        dto.getConditions().add(Restrict.eq("area", area));
        dto.getConditions().add(Restrict.eq("department", depart));
        List<DepartmentDetail> list = super.findByCis(dto);
        List<String> opinionBOList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            opinionBOList = list.stream().filter(str -> StringUtils.isNotBlank(str.getInnerProject())).map(DepartmentDetail::getInnerProject).collect(Collectors.toList());
        }
        return opinionBOList;
    }

    @Override
    public List<String> findAllProject() throws SerException {
        List<String> listProject = new ArrayList<>();
        List<DepartmentDetail> list = super.findAll();
        if (list != null && list.size() > 0) {
            listProject = list.stream().filter(str -> StringUtils.isNotBlank(str.getInnerProject())).map(DepartmentDetail::getInnerProject).collect(Collectors.toList());
        }
        return listProject;
    }

    @Override
    public List<OpinionBO> findByIds(String... ids) throws SerException {
        DepartmentDetailDTO dto = new DepartmentDetailDTO();
        dto.getConditions().add(Restrict.in(ID, ids));
        List<DepartmentDetail> list = super.findByCis(dto);
        List<OpinionBO> bos = new ArrayList<>(0);
        if (null != list)
            for (DepartmentDetail entity : list)
                bos.add(new OpinionBO(entity.getId(), entity.getDepartment()));
        return bos;
    }

    @Override
    public List<OpinionBO> findThawOpinion() throws SerException {
        List<DepartmentDetailBO> list = this.findStatus();
        List<OpinionBO> bos = new ArrayList<>(0);
        if (null != list)
            for (DepartmentDetailBO bo : list)
                bos.add(new OpinionBO(bo.getId(), bo.getDepartment()));
        return bos;
    }

    @Override
    public List<OpinionBO> findAllOpinion() throws SerException {
        List<DepartmentDetail> list = super.findAll();
        List<OpinionBO> bos = new ArrayList<>(0);
        if (null != list)
            for (DepartmentDetail entity : list)
                bos.add(new OpinionBO(entity.getId(), entity.getDepartment()));
        return bos;
    }

    @Override
    public Integer departmentTotalPeople(String department) throws SerException {
        String sql = "SELECT count(DISTINCT c.user_id) peopleCount FROM " +
                " organize_department_detail a,organize_position_detail b,organize_position_detail_user_table c " +
                " WHERE a.id = b.department_id AND b.id = c.position_id AND " +
                " department = '" + department + "'";

        List<DepartmentPeopleBO> list = super.findBySql(sql, DepartmentPeopleBO.class, new String[]{"peopleCount"});
        if (list != null && !list.isEmpty()) {
            return Integer.parseInt(list.get(0).getPeopleCount() + "");
        }
        return null;
    }

    @Override
    //chenjunhao
    public Set<String> departPersons(String departId) throws SerException {
        String sql = "SELECT name FROM organize_position_detail_user " +
                "WHERE id IN (SELECT user_id FROM organize_position_detail_user_table " +
                "WHERE position_id IN (SELECT id FROM organize_position_detail " +
                "where department_id='" + departId + "'))";
        String[] fileds = new String[]{"name"};
        List<PositionDetailUserBO> bos = super.findBySql(sql, PositionDetailUserBO.class, fileds);
        if (null != bos) {
            return bos.stream().map(positionDetailUserBO -> positionDetailUserBO.getName()).collect(Collectors.toSet());
        }
        return null;
    }

    @Override
    public List<DepartmentDetailBO> departByName(String[] departs) throws SerException {
        String[] departsTemp = new String[departs.length];
        for (int i = 0; i < departs.length; i++) {
            departsTemp[i] = "'" + departs[i] + "'";
        }
        String departStr = StringUtils.join(departsTemp, ",");
        String sql = "select id,area,department from organize_department_detail where " +
                "department in (" + departStr + ") and status=0";
        String[] fileds = new String[]{"id", "area", "department"};
        List<DepartmentDetailBO> list = super.findBySql(sql, DepartmentDetailBO.class, fileds);
        return list;
    }

    @Override
    public List<String> findAllDepartment() throws SerException {
        DepartmentDetailDTO dto = new DepartmentDetailDTO();
        List<DepartmentDetail> departmentDetails = super.findByCis(dto);
        if (null != departmentDetails && departmentDetails.size() > 0) {
            return departmentDetails.stream().map(DepartmentDetail::getDepartment).distinct().collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<DepartmentPeopleBO> peopleByDepartment() throws SerException {
        String[] fields = new String[]{"department", "peopleCount"};
        String sql = " SELECT a.department as department,count(DISTINCT c.user_id) peopleCount FROM " +
                " organize_department_detail a,organize_position_detail b,organize_position_detail_user_table c " +
                " WHERE a.id = b.department_id AND b.id = c.position_id GROUP BY a.department";
        List<DepartmentPeopleBO> list = super.findBySql(sql, DepartmentPeopleBO.class, fields);
        return list;
    }

    @Override
    public List<DepartmentPeopleBO> departmentByName(String name) throws SerException {
        String[] fields = new String[]{"department"};
        String sql = " select department " +
                " from organize_department_detail a, organize_position_detail b, organize_position_detail_user c, organize_position_detail_user_table d " +
                " where c.name = '" + name + "' " +
                " and c.id = d.user_id " +
                " and d.position_id = b.id " +
                " and b.department_id = a.id ";
        List<DepartmentPeopleBO> list = super.findBySql(sql, DepartmentPeopleBO.class, fields);
        return list;
    }

    @Override
    public String findHierarchy(String department) throws SerException {
        StringBuilder sql = new StringBuilder("SELECT ifnull(a.hierarchy,' ') FROM organize_hierarchy a, ");
        sql.append(" organize_department_detail b ");
        sql.append(" WHERE a.id = b.hierarchy_id ");
        sql.append(" AND b.department = '" + department + "';");
        List<Object> objectList = super.findBySql(sql.toString());
        if (null != objectList && objectList.size() > 0) {
            String objects = String.valueOf(objectList.get(0));
            return objects;
        }
        return null;
    }
}
