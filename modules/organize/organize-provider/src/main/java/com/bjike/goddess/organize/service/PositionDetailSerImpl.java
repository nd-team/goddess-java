package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.*;
import com.bjike.goddess.organize.dto.PositionDetailDTO;
import com.bjike.goddess.organize.dto.PositionUserDetailDTO;
import com.bjike.goddess.organize.entity.*;
import com.bjike.goddess.organize.enums.WorkStatus;
import com.bjike.goddess.organize.to.PositionDetailTO;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 岗位详细业务实现
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午10:08]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@Service
public class PositionDetailSerImpl extends ServiceImpl<PositionDetail, PositionDetailDTO> implements PositionDetailSer {

    @Autowired
    private DepartmentDetailSer departmentDetailSer;
    @Autowired
    private ArrangementSer arrangementSer;
    @Autowired
    private ModuleTypeSer moduleTypeSer;
    @Autowired
    private PositionDetailUserSer positionDetailUserSer;
    @Autowired
    private PositionInstructionSer positionInstructionSer;
    @Autowired
    private HierarchySer hierarchySer;
    @Autowired
    private PositionUserDetailSer positionUserDetailSer;
    @Autowired
    private UserAPI userAPI;

    private PositionDetailBO transformationToBO(PositionDetail entity) throws SerException {
        PositionDetailBO bo = BeanTransform.copyProperties(entity, PositionDetailBO.class);
        DepartmentDetailBO department = departmentDetailSer.findBOById(entity.getDepartment().getId());
        Arrangement arrangement = entity.getArrangement();
        ModuleType moduleType = entity.getModule();
        bo.setArea(department.getArea());
        bo.setDepartmentId(department.getId());
        bo.setDepartmentName(department.getDepartment());
        bo.setArrangementName(arrangement.getArrangement());
        bo.setDepartmentName(department.getDepartment());
        bo.setHierarchyName(department.getHierarchyName());
        bo.setArrangementId(arrangement.getId());
        if (null != moduleType) {
            bo.setModuleId(moduleType.getId());
            bo.setModuleName(moduleType.getModule());
        }
        bo.setCurrent(positionDetailUserSer.findByPosition(entity.getId()).size() + "人");
        //部门编号-层级编号+体系编号+职位编号
        bo.setShowNumber(String.format("%s-%s%s%s", department.getShowNumber(), arrangement.getSerialNumber(), department.getHierarchyNumber(), entity.getSerialNumber()));
        return bo;
    }

    @Override
    public String number(PositionDetailTO to) throws SerException {
        if (null == to.getDepartmentId()) {
            throw new SerException("必须先选部门");
        }
        if (null == to.getArrangementId()) {
            throw new SerException("必须先选岗位层级");
        }
        if (null == to.getSerialNumber()) {
            throw new SerException("编号不能为空");
        }
        DepartmentDetail departmentDetail = departmentDetailSer.findById(to.getDepartmentId());
        if (null == departmentDetail) {
            throw new SerException("部门不存在");
        }
        DepartmentDetailBO department = departmentDetailSer.findBOById(departmentDetail.getId());
        Arrangement arrangement = arrangementSer.findById(to.getArrangementId());
        if (null == arrangement) {
            throw new SerException("岗位层级不存在");
        }
        //部门编号-层级编号+体系编号+职位编号
        String number = String.format("%s-%s%s%s", department.getShowNumber(), arrangement.getSerialNumber(), department.getHierarchyNumber(), to.getSerialNumber());
        return number;
    }

    @Override
    public Long getPositionNum(String startTime, String endTime) throws SerException {
        String fields[] = new String[]{"positionNum"};
        StringBuilder sql = new StringBuilder("select count(position) from organize_position_detail ");
        sql.append(" where createTime between '" + startTime + "' ");
        sql.append(" and '" + endTime + "' ");
        List<ManagerBO> managerBOs = super.findBySql(sql.toString(), ManagerBO.class, fields);
        if (null != managerBOs && managerBOs.size() > 0) {
            List<Long> areas = managerBOs.stream().map(ManagerBO::getPositionNum).distinct().collect(Collectors.toList());
            return areas.get(0);
        }
        return 0l;
    }

    @Override
    public List<PositionDetailBO> transformationToBOList(Collection<PositionDetail> list) throws SerException {
        List<PositionDetailBO> bos = new ArrayList<>(list.size());
        for (PositionDetail entity : list)
            bos.add(this.transformationToBO(entity));
        return bos;
    }

    @Override
    public PositionDetailBO congeal(String id) throws SerException {
        PositionDetail entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return this.transformationToBO(entity);
    }

    @Override
    public PositionDetailBO thaw(String id) throws SerException {
        PositionDetail entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        entity.setStatus(Status.THAW);
        super.update(entity);
        return this.transformationToBO(entity);
    }

    @Override
    public List<PositionDetailBO> findStatus() throws SerException {
        PositionDetailDTO dto = new PositionDetailDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<PositionDetail> list = super.findByCis(dto);
        return this.transformationToBOList(list);
    }

    @Override
    public List<PositionDetailBO> findByPostIds(String[] ids) throws SerException {
        PositionDetailDTO dto = new PositionDetailDTO();
        dto.getConditions().add(Restrict.in("id", ids));
        return this.transformationToBOList(super.findByCis(dto));
    }

    @Override
    public List<PositionDetailBO> findChildByArrangement(String postId) throws SerException {
        PositionDetail entity = super.findById(postId);
        if (null == entity)
            throw new SerException("该岗位不存在");
        List<ArrangementBO> arrangementList = arrangementSer.findChild(entity.getArrangement().getId());
        PositionDetailDTO dto = new PositionDetailDTO();
        dto.getConditions().add(Restrict.eq("department.id", entity.getDepartment().getId()));
        try {
            for (ArrangementBO arrangement : arrangementList)
                dto.getConditions().add(Restrict.eq("arrangement.id", arrangement.getId()));
        } catch (Exception e) {
            return new ArrayList<>(0);
        }
        return this.transformationToBOList(super.findByCis(dto));
    }

    @Override
    public List<PositionDetailBO> findParentByArrangement(String postId) throws SerException {
        PositionDetail entity = super.findById(postId);
        if (null == entity)
            throw new SerException("该岗位不存在");
        Arrangement arrangement = arrangementSer.findById(entity.getArrangement().getId());
        if (arrangement.getParent() == null)
            return new ArrayList<>(0);
        PositionDetailDTO dto = new PositionDetailDTO();
        dto.getConditions().add(Restrict.eq("arrangement.id", arrangement.getParent().getId()));
        dto.getConditions().add(Restrict.eq("department.id", entity.getDepartment().getId()));
        return this.transformationToBOList(super.findByCis(dto));
    }

    @Override
    public PositionDetailBO findBOById(String id) throws SerException {
        return this.transformationToBO(super.findById(id));
    }

    /**
     * 检测岗位详细编号或岗位是否重复
     *
     * @param to
     * @throws SerException
     */
    private void checkUnique(PositionDetailTO to) throws SerException {
        String[] fields = {"id", "position"};
        StringBuilder sql = new StringBuilder(" SELECT ");
        sql.append(" id,position ").append(" FROM organize_position_detail ");
        sql.append(" WHERE serialNumber='").append(to.getSerialNumber()).append("' OR position='").append(to.getPosition()).append("'");
        List<PositionDetail> list = super.findBySql(sql.toString(), DepartmentDetail.class, fields);
        if (list.size() > 0)
            throw new SerException("岗位或编号已存在,无法保存");
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public PositionDetailBO save(PositionDetailTO to) throws SerException {
        this.checkUnique(to);
        PositionDetail entity = BeanTransform.copyProperties(to, PositionDetail.class);
        entity.setDepartment(departmentDetailSer.findById(to.getDepartmentId()));
        if (null == entity.getDepartment())
            throw new SerException("部门不存在");
        entity.setArrangement(arrangementSer.findById(to.getArrangementId()));
        if (null == entity.getArrangement())
            throw new SerException("岗位层级不存在");
        if (null != to.getModuleId()) {
            entity.setModule(moduleTypeSer.findById(to.getModuleId()));
            if (null == entity.getModule())
                throw new SerException("模块类型不存在");
        }
        if (this.findByNumber(to.getSerialNumber()) != null)
            throw new SerException("编号已存在,无法保存");
        if (this.findByPosition(to.getPosition()) != null)
            throw new SerException("岗位已存在,无法保存");
        entity.setStatus(entity.getDepartment().getStatus());
        super.save(entity);
        return this.transformationToBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public PositionDetailBO update(PositionDetailTO to) throws SerException {
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据ID不能为空");
        PositionDetail entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
        if (!entity.getSerialNumber().equals(to.getSerialNumber()) && this.findByNumber(to.getSerialNumber()) != null)
            throw new SerException("编号已存在,无法保存");
        if (!entity.getPosition().equals(to.getPosition()) && this.findByPosition(to.getPosition()) != null)
            throw new SerException("岗位已存在,无法保存");
        BeanTransform.copyProperties(to, entity, true);
        entity.setDepartment(departmentDetailSer.findById(to.getDepartmentId()));
        if (null == entity.getDepartment())
            throw new SerException("部门不存在");
        entity.setArrangement(arrangementSer.findById(to.getArrangementId()));
        if (null == entity.getArrangement())
            throw new SerException("岗位层级不存在");
        if (null != to.getModuleId()) {
            entity.setModule(moduleTypeSer.findById(to.getModuleId()));
            if (null == entity.getModule())
                throw new SerException("模块类型不存在");
        }
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return this.transformationToBO(entity);
    }

    @Override
    public PositionDetailBO delete(String id) throws SerException {
        PositionDetail entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        if (positionInstructionSer.findByPosition(id).size() != 0 || positionDetailUserSer.findByPosition(id).size() != 0)
            throw new SerException("此处已被引用,无法删除");
        super.remove(entity);
        return this.transformationToBO(entity);
    }

    @Override
    public List<PositionDetailBO> maps(PositionDetailDTO dto) throws SerException {
        dto.getSorts().add("department_id=asc");
        return this.transformationToBOList(super.findByPage(dto));
    }

    @Override
    public List<ReHierarchyBO> list() throws SerException {
        List<ReHierarchyBO> hierarchyBOS = new ArrayList<>();
        List<DepartmentDetail> departs = new ArrayList<>();
        List<PositionDetailBO> positionDetailBOS = this.transformationToBOList(super.findAll());
        for (PositionDetailBO p : positionDetailBOS) {
            String departId = p.getDepartmentId();
            DepartmentDetail depart = departmentDetailSer.findById(departId);
            departs.add(depart);
            Hierarchy hierarchy = depart.getHierarchy();
            ReHierarchyBO reHierarchyBO = BeanTransform.copyProperties(hierarchy, ReHierarchyBO.class);
            if (!hierarchyBOS.isEmpty()) {
                Set<String> hierarchyIds = hierarchyBOS.stream().map(hierarchyBO -> hierarchyBO.getId()).collect(Collectors.toSet());
                if (!hierarchyIds.contains(hierarchy.getId())) {
                    hierarchyBOS.add(reHierarchyBO);
                }
            } else {
                hierarchyBOS.add(reHierarchyBO);
            }
        }
        for (ReHierarchyBO h : hierarchyBOS) {
            Set<String> departIds = departs.stream().filter(departmentDetail -> h.getId().equals(departmentDetail.getHierarchy().getId())).map(departmentDetail -> departmentDetail.getId()).collect(Collectors.toSet());
            List<ReDepartBO> departS = new ArrayList<>();
            for (String id : departIds) {
                departS.add(BeanTransform.copyProperties(departmentDetailSer.findById(id), ReDepartBO.class));
            }
            for (ReDepartBO d : departS) {
                PositionDetailDTO dto = new PositionDetailDTO();
                dto.getConditions().add(Restrict.eq("department.id", d.getId()));
                List<PositionDetail> list = super.findByCis(dto);
                Set<String> arrangementIds = list.stream().map(positionDetail -> positionDetail.getArrangement().getId()).collect(Collectors.toSet());
                List<ReArrangementBO> reArrangementBOS = new ArrayList<>();
                for (String id : arrangementIds) {
                    reArrangementBOS.add(BeanTransform.copyProperties(arrangementSer.findById(id), ReArrangementBO.class));
                }
                for (ReArrangementBO reArrangementBO : reArrangementBOS) {
                    PositionDetailDTO dto1 = new PositionDetailDTO();
                    dto1.getConditions().add(Restrict.eq("department.id", d.getId()));
                    dto1.getConditions().add(Restrict.eq("arrangement.id", reArrangementBO.getId()));
                    List<PositionDetail> list1 = super.findByCis(dto1);
//                    Set<String> moduleTypeIds = list1.stream().filter(positionDetail -> null != positionDetail.getModule()).map(positionDetail -> positionDetail.getModule().getId()).collect(Collectors.toSet());
//                    List<ReModuleBO> moduleBOS = new ArrayList<>();
//                    for (String id : moduleTypeIds) {
//                        moduleBOS.add(BeanTransform.copyProperties(moduleTypeSer.findById(id), ReModuleBO.class));
//                    }
                    List<RePositionBO> bos = new ArrayList<>();
                    for (PositionDetail p : list1) {
                        PositionUserDetailDTO detailDTO = new PositionUserDetailDTO();
                        detailDTO.getConditions().add(Restrict.eq("positionId", p.getId()));
                        detailDTO.getConditions().add(Restrict.eq("workStatus", WorkStatus.MAIN));
                        List<PositionUserDetail> mains = positionUserDetailSer.findByCis(detailDTO);
                        String main = get(mains);
                        PositionUserDetailDTO detailDTO1 = new PositionUserDetailDTO();
                        detailDTO1.getConditions().add(Restrict.eq("positionId", p.getId()));
                        detailDTO1.getConditions().add(Restrict.eq("workStatus", WorkStatus.PARTJOB));
                        List<PositionUserDetail> parts = positionUserDetailSer.findByCis(detailDTO1);
                        String part = get(parts);
                        PositionUserDetailDTO detailDTO2 = new PositionUserDetailDTO();
                        detailDTO2.getConditions().add(Restrict.eq("positionId", p.getId()));
                        detailDTO2.getConditions().add(Restrict.eq("agent", Boolean.FALSE));
                        List<PositionUserDetail> agents = positionUserDetailSer.findByCis(detailDTO2);
                        String agent = get(agents);
                        RePositionBO positionBO = BeanTransform.copyProperties(p, RePositionBO.class, "module");
                        positionBO.setMain(main);
                        positionBO.setPart(part);
                        positionBO.setAgent(agent);
                        if (null != p.getModule()) {
                            positionBO.setModule(p.getModule().getModule());
                        }
                        positionBO.setCurrent(positionDetailUserSer.findByPosition(p.getId()).size() + "人");
                        bos.add(positionBO);
                    }
                    reArrangementBO.setPositionS(bos);
//                    reArrangementBO.setModuleS(moduleBOS);
                }
                d.setArrangementS(reArrangementBOS);
            }
            h.setDeparts(departS);
        }
        return hierarchyBOS;
    }

    private String get(List<PositionUserDetail> list) throws SerException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                String name = userAPI.findNameById(list.get(i).getUserId());
                if (null != name) {
                    sb.append(name);
                }
            } else {
                String name = userAPI.findNameById(list.get(i).getUserId());
                if (null != name) {
                    sb.append(name + ",");
                }
            }
        }
        return sb.toString();
    }

    @Override
    public List<OpinionBO> findThawOpinion() throws SerException {
        PositionDetailDTO dto = new PositionDetailDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<PositionDetail> list = super.findByCis(dto);
        List<OpinionBO> bos = new ArrayList<>(0);
        for (PositionDetail entity : list)
            bos.add(new OpinionBO(entity.getId(), entity.getPosition()));
        return bos;
    }

    @Override
    public List<PositionDetailBO> findByDepartment(String... departmentIds) throws SerException {
        List<PositionDetailBO> list = findStatus(), bos = new ArrayList<>(0);
        if (null != departmentIds && list != null)
            for (PositionDetailBO bo : list)
                for (String id : departmentIds)
                    if (bo.getDepartmentId().equals(id))
                        bos.add(bo);
        return bos;
    }

    @Override
    public List<OpinionBO> findByIds(String... ids) throws SerException {
        PositionDetailDTO dto = new PositionDetailDTO();
        dto.getConditions().add(Restrict.in(ID, ids));
        List<PositionDetail> list = super.findByCis(dto);
        List<OpinionBO> bos = new ArrayList<>(0);
        if (null != list)
            for (PositionDetail entity : list)
                bos.add(new OpinionBO(entity.getId(), entity.getPosition()));
        return bos;
    }

    @Override
    public List<OpinionBO> findAllOpinion() throws SerException {
        List<PositionDetail> list = super.findAll();
        List<OpinionBO> bos = new ArrayList<>(0);
        for (PositionDetail entity : list)
            bos.add(new OpinionBO(entity.getId(), entity.getPosition()));
        return bos;
    }

    @Override
    public PositionDetailBO findByNumber(String serialNumber) throws SerException {
        PositionDetailDTO dto = new PositionDetailDTO();
        dto.getConditions().add(Restrict.eq("serialNumber", serialNumber));
        PositionDetail entity = super.findOne(dto);
        if (null == entity)
            return null;
        else
            return this.transformationToBO(entity);
    }

    @Override
    public PositionDetailBO findByPosition(String position) throws SerException {
        PositionDetailDTO dto = new PositionDetailDTO();
        dto.getConditions().add(Restrict.eq("position", position));
        PositionDetail entity = super.findOne(dto);
        if (null == entity)
            return null;
        else
            return this.transformationToBO(entity);
    }

    @Override
    public List<String> getPositions(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        String[] field = new String[]{"id"};
        String sql = "select id from organize_position_detail where arrangement_id = '" + id + "'";
        List<String> stringList = new ArrayList<>(0);
        List<PositionDetail> positionDetailList = super.findBySql(sql, PositionDetail.class, field);
        if (null != positionDetailList && positionDetailList.size() > 0) {
            for (PositionDetail entity : positionDetailList) {
                stringList.add(entity.getId());
            }
        }
        return stringList;
    }
}
