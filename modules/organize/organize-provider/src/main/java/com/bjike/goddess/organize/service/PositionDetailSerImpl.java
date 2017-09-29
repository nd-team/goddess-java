package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.*;
import com.bjike.goddess.organize.dto.DepartmentDetailDTO;
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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.*;
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
        bo.setHierarchyID(department.getHierarchyId());
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
        List<PositionDetailBO> positionDetailBOS = this.transformationToBOList(super.findAll());
        TreeSet<ReHierarchyBO> reHierarchyBOs = filter();
        for (PositionDetailBO p : positionDetailBOS) {
            String departId = p.getDepartmentId();
            DepartmentDetail depart = departmentDetailSer.findById(departId);
            Hierarchy hierarchy = depart.getHierarchy();
            ReHierarchyBO reHierarchyBO = BeanTransform.copyProperties(hierarchy, ReHierarchyBO.class);
            reHierarchyBOs.add(reHierarchyBO);
        }
        List<ReHierarchyBO> hierarchyBOS = new ArrayList<>(reHierarchyBOs);
        for (ReHierarchyBO h : hierarchyBOS) {
            TreeSet<DepartmentDetail> departmentDetails = new TreeSet<>(new Comparator<DepartmentDetail>() {
                @Override
                public int compare(DepartmentDetail o1, DepartmentDetail o2) {
                    if (o1.getDepartment().equals(o2.getDepartment())) {
                        return 0;
                    }
                    return 1;
                }
            });
            DepartmentDetailDTO departmentDetailDTO = new DepartmentDetailDTO();
            departmentDetailDTO.getConditions().add(Restrict.eq("hierarchy.id", h.getId()));
            List<DepartmentDetail> departmentDetails1 = departmentDetailSer.findByCis(departmentDetailDTO);
            departmentDetails.addAll(departmentDetails1);
            List<DepartmentDetail> departs = new ArrayList<>(departmentDetails);
            List<ReDepartBO> departS = BeanTransform.copyProperties(departs, ReDepartBO.class);
            if (null != departS) {
                for (ReDepartBO d : departS) {
                    PositionDetailDTO dto = new PositionDetailDTO();
                    dto.getConditions().add(Restrict.eq("department.id", d.getId()));
                    List<PositionDetail> list = super.findByCis(dto);
//                    TreeSet<Arrangement> arrangements = new TreeSet<>(new Comparator<Arrangement>() {
//                        @Override
//                        public int compare(Arrangement o1, Arrangement o2) {
//                            if (o1.getId().equals(o2.getId())) {
//                                return 0;
//                            }
//                            return 1;
//                        }
//                    });
//                    for (PositionDetail p : list) {
//                        arrangements.add(p.getArrangement());
//                    }
                    Set<String> arrangementsID=list.stream().map(positionDetail -> positionDetail.getArrangement().getId()).collect(Collectors.toSet());
                    List<Arrangement> arrangements1 = new ArrayList<>();
                    for (String id:arrangementsID){
                        arrangements1.add(arrangementSer.findById(id));
                    }
                    List<ReArrangementBO> reArrangementBOS = BeanTransform.copyProperties(arrangements1, ReArrangementBO.class);
                    if (null != reArrangementBOS) {
                        for (ReArrangementBO reArrangementBO : reArrangementBOS) {
                            PositionDetailDTO dto1 = new PositionDetailDTO();
                            dto1.getConditions().add(Restrict.eq("department.id", d.getId()));
                            dto1.getConditions().add(Restrict.eq("arrangement.id", reArrangementBO.getId()));
                            List<PositionDetail> list1 = super.findByCis(dto1);
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
                        }
                    }
                    d.setArrangementS(reArrangementBOS);
                }
            }
            h.setDeparts(departS);
        }
        return hierarchyBOS;
    }

    private <T> TreeSet<T> filter() throws SerException {
        TreeSet<T> treeSet = new TreeSet<>(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                Field[] field = o1.getClass().getDeclaredFields();//获取实体类的所有属性，返回field数组
                int num = 0;   //用于识别属性相同的个数
                int sum = 0;    //用于识别该对象除了集合的属性值个数
                for (Field f : field) {//遍历所有的属性
                    String type = f.getGenericType().toString();//获取属性的类型
                    if (type.indexOf("java.util.List") < 0) {
                        sum++;
                        String name = f.getName(); // 获取属性的名字
                        name = name.substring(0, 1).toUpperCase() + name.substring(1);// 将属性的首字符大写，方便构造get，set方法
                        try {
                            Method m = o1.getClass().getMethod("get" + name);
                            Object value = m.invoke(o1);// 调用getter方法获取属性值
                            Method m1 = o2.getClass().getMethod("get" + name);
                            Object value1 = m1.invoke(o2);
                            if (value.equals(value1)) {    //判断该属性值是否相同
                                num++;
                            }
                        } catch (Exception e) {

                        }
                    }
                }
                if (num == sum) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        return treeSet;
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
