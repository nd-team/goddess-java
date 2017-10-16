package com.bjike.goddess.dimission.service;

import com.bjike.goddess.archive.api.StaffRecordsAPI;
import com.bjike.goddess.archive.bo.StaffRecordsBO;
import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dimission.api.InterviewAPI;
import com.bjike.goddess.dimission.bo.DimissionInfoBO;
import com.bjike.goddess.dimission.bo.DimissionInfoCollectBO;
import com.bjike.goddess.dimission.bo.DimissionReasonBO;
import com.bjike.goddess.dimission.dto.DimissionInfoDTO;
import com.bjike.goddess.dimission.entity.DimissionInfo;
import com.bjike.goddess.dimission.enums.*;
import com.bjike.goddess.dimission.excel.SonPermissionObject;
import com.bjike.goddess.dimission.to.*;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.bo.PositionDetailUserBO;
import com.bjike.goddess.organize.bo.PositionUserDetailBO;
import com.bjike.goddess.organize.enums.WorkStatus;
import com.bjike.goddess.staffentry.api.EntryRegisterAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 离职信息业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-17 02:12 ]
 * @Description: [ 离职信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "dimissionSerCache")
@Service
public class DimissionInfoSerImpl extends ServiceImpl<DimissionInfo, DimissionInfoDTO> implements DimissionInfoSer {

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private UserDetailAPI userDetailAPI;

    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private HandoverReferenceSer handoverReferenceSer;
    @Autowired
    private WorkHandoverSer workHandoverSer;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private StaffRecordsAPI staffRecordsAPI;
    @Autowired
    private InterviewAPI interviewSer;
    @Autowired
    private SituationSer situationSer;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 导航栏核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("dimissioninfo");
        obj.setDescribesion("离职信息");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = handoverReferenceSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("handoverreference");
        obj.setDescribesion("交接信息参考");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate = workHandoverSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("workhandover");
        obj.setDescribesion("工作交接");
        if (flagSeeCate) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate1 = interviewSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("interview");
        obj.setDescribesion("离职管理面谈");
        if (flagSeeCate1) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate2 = situationSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("situation");
        obj.setDescribesion("离职办理节点情况");
        if (flagSeeCate2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case IMPORT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }


    private DimissionInfoBO transformBO(DimissionInfo entity) throws SerException {
        DimissionInfoBO bo = BeanTransform.copyProperties(entity, DimissionInfoBO.class);
        UserBO user = userAPI.findByUsername(entity.getUsername());
        if (user != null) {
//            bo.setEmployeeNumber(user.getEmployeeNumber());
//            bo.setPhone(user.getPhone());
            if (moduleAPI.isCheck("archive")) {
                StaffRecordsBO staffRecordsBO = staffRecordsAPI.findByName(entity.getUsername());
                if (null != staffRecordsBO) {
                    bo.setEntryTime(staffRecordsBO.getEntryTime());
                    bo.setEducation(staffRecordsBO.getEducation());
                    bo.setPhone(staffRecordsBO.getTelephone());
                }
            }
            if (moduleAPI.isCheck("organize")) {
                PositionDetailUserBO detailBO = positionDetailUserAPI.findOneByUser(user.getId());
//                bo.setEmployeeNumber(detailBO.getEmployeesNumber());
                bo.setArea("");
                bo.setPosition("");
                bo.setArrangement("");
                bo.setDepartment("");
                if (null != detailBO) {
                    List<PositionDetailBO> positionBOs = new ArrayList<>();
                    List<PositionUserDetailBO> positionUserDetailBOSList = detailBO.getDetailS();
                    if (null != positionUserDetailBOSList) {
                        for (PositionUserDetailBO p : positionUserDetailBOSList) {
                            if (WorkStatus.MAIN.equals(p.getWorkStatus())) {
                                positionBOs = positionDetailAPI.findByPostIds(p.getPositionId().split(","));
                            }
                        }
                    }
                    String area = "", department = "", arrangement = "";
                    if (positionBOs != null) {
                        for (PositionDetailBO position : positionBOs.stream()
                                .sorted(Comparator.comparing(PositionDetailBO::getArrangementId))
                                .collect(Collectors.toList())) {
                            bo.setPosition(position.getPosition() + "," + bo.getPosition());
                            if (!arrangement.equals(position.getArrangementName())) {
                                arrangement = position.getArrangementName();
                                bo.setArrangement(bo.getArrangement() + "," + position.getArrangementName());
                            }
                        }

                        for (PositionDetailBO position : positionBOs.stream()
                                .sorted(Comparator.comparing(PositionDetailBO::getDepartmentId))
                                .collect(Collectors.toList()))
                            if (!department.equals(position.getDepartmentName())) {
                                department = position.getDepartmentName();
                                bo.setDepartment(position.getDepartmentName() + "," + bo.getDepartment());
                            }

                        for (PositionDetailBO position : positionBOs.stream()
                                .sorted(Comparator.comparing(PositionDetailBO::getDepartmentId))
                                .collect(Collectors.toList()))
                            if (!area.equals(position.getArea())) {
                                area = position.getArea();
                                bo.setArea(position.getArea() + "," + bo.getArea());
                            }
                    }
                }
            }
        }

        return bo;
    }

    private List<DimissionInfoBO> tranformBOList(List<DimissionInfo> list) throws SerException {
        List<DimissionInfoBO> bos = new ArrayList<>(list.size());
        for (DimissionInfo entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Override
    public DimissionInfoBO apply(DimissionInfoAddEditTO to) throws SerException {
        UserBO user = userAPI.currentUser();
        DimissionInfo entity = BeanTransform.copyProperties(to, DimissionInfo.class, true);
        entity.setUsername(user.getUsername());
        entity.setType(DimissionType.NORMAL);
        entity.setApplyDate(LocalDate.now());
        entity.setDimission(DimissionStatus.APPLY);
        entity.setHandle(HandleStatus.PREPARE);
        entity.setDimissionConfirmation(ConfirmationType.NONE);
        entity.setSalaryConfirmation(ConfirmationType.NONE);

        entity.setStatus(EmployeeStatus.FORMAL);
        entity.setDimissionDate(entity.getApplyDate().plusDays(30));

        super.save(entity);
        return this.transformBO(entity);
    }

    @Override
    public DimissionInfoBO applyUpdate(DimissionInfoAddEditTO to) throws SerException {
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据id不能为空");
        DimissionInfo entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
        BeanTransform.copyProperties(to, entity, true);
        if (null == userAPI.findByUsername(entity.getUsername()))
            throw new SerException("该用户不存在");
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return this.transformBO(entity);
    }

    @Override
    public DimissionInfoBO presume(FromInfoTO to) throws SerException {
        DimissionInfo entity = BeanTransform.copyProperties(to, DimissionInfo.class, true);
        entity.setType(DimissionType.PRESUME);
        entity.setDimission(DimissionStatus.SUCCESS);
        entity.setHandle(HandleStatus.AFFIRM);
        entity.setDimissionConfirmation(ConfirmationType.NONE);
        entity.setSalaryConfirmation(ConfirmationType.NONE);
        if (null == userAPI.findByUsername(to.getUsername()))
            throw new SerException("该用户不存在");
        if (entity.getApplyDate() != null) {
            entity.setStatus(EmployeeStatus.FORMAL);
            entity.setDimissionDate(entity.getApplyDate().plusDays(30));
        }

        super.save(entity);
        return this.transformBO(entity);
    }

    @Override
    public DimissionInfoBO preUpdate(FromInfoTO to) throws SerException {
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据id不能为空");
        DimissionInfo entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
        BeanTransform.copyProperties(to, entity, true);
        if (null == userAPI.findByUsername(entity.getUsername()))
            throw new SerException("该用户不存在");
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return this.transformBO(entity);
    }

    @Override
    public DimissionInfoBO delete(String id) throws SerException {
        DimissionInfo entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不能为空");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public DimissionInfoBO interview(DimissionInterviewTo to) throws SerException {
        UserBO user = userAPI.currentUser();
        DimissionInfo entity = super.findById(to.getId());
        if (to.getAuthority()) {//是否为负责人面谈
            entity.setLiable(user.getUsername());
            entity.setLiableOpinion(to.getOpinion());
            entity.setContent(to.getContent());
        } else {
            entity.setManage(user.getUsername());
            entity.setManageOpinion(to.getOpinion());
        }
        super.update(entity);
        return this.transformBO(entity);
    }

    @Override
    public DimissionInfoBO audit(DimissionAuditTO to) throws SerException {
        DimissionInfo entity = super.findById(to.getId());
        entity.setGeneralOpinion(to.getOpinion());
        entity.setAdvance(to.getPass());
        if (to.getPass())
            entity.setType(DimissionType.ADVANCE);
        super.update(entity);
        return this.transformBO(entity);
    }

    @Override
    public DimissionInfoBO affirm(DimissionAffirmTO to) throws SerException {
        DimissionInfo entity = super.findById(to.getId());
        entity.setSalaryConfirmation(to.getAffirm() ? ConfirmationType.AFFIRM : ConfirmationType.DENY);
        super.update(entity);
        return this.transformBO(entity);
    }

    @Override
    public DimissionInfoBO success(String id) throws SerException {
        DimissionInfo entity = super.findById(id);
        entity.setHandle(HandleStatus.AFFIRM);
        super.update(entity);
        return this.transformBO(entity);
    }

    @Override
    public DimissionInfoBO editType(DimissionTypeTO to) throws SerException {
        DimissionInfo entity = super.findById(to.getId());
        entity.setType(to.getType());
        super.update(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<DimissionInfoBO> findByType(DimissionType type) throws SerException {
        if (null == type)
            return new ArrayList<>(0);
        DimissionInfoDTO dto = new DimissionInfoDTO();
        dto.getConditions().add(Restrict.eq("type", type.getValue()));
        List<DimissionInfo> list = super.findByCis(dto);
        return this.tranformBOList(list);
    }

    @Override
    public List<DimissionInfoBO> presumeList(DimissionInfoDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("type", DimissionType.PRESUME.getValue()));
        return this.maps(dto);
    }

    @Override
    public List<DimissionInfoBO> maps(DimissionInfoDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        return this.tranformBOList(super.findByPage(dto));
    }

    @Override
    public List<DimissionInfoBO> findByDimissionDate(String start, String end) throws SerException {
        DimissionInfoDTO dto = new DimissionInfoDTO();
        LocalDate[] dates = new LocalDate[0];
        try {
            dates = new LocalDate[]{LocalDate.parse(start), LocalDate.parse(end)};
        } catch (Exception e) {
            throw new SerException("时间格式错误(例:2010-12-31)");
        }
        dto.getConditions().add(Restrict.between("dimissionDate", dates));
        dto.getConditions().add(Restrict.eq("handle", HandleStatus.AFFIRM.getValue()));
        return this.tranformBOList(super.findByCis(dto));
    }

    @Override
    public List<DimissionInfoBO> all() throws SerException {
        return this.tranformBOList(super.findAll());
    }

    @Override
    public List<DimissionInfoCollectBO> departmentCollect(DimissionCollectTO to) throws SerException {
        List<DimissionInfoBO> list;
        List<DimissionInfoCollectBO> bos = new ArrayList<>(0);
        if (StringUtils.isNotBlank(to.getStart()) && StringUtils.isNotBlank(to.getEnd()))
            list = this.findByDimissionDate(to.getStart(), to.getEnd());
        else
            list = this.all();

        if (StringUtils.isNotBlank(to.getDepartment())) {
            list = list.stream()
                    .filter(d -> StringUtils.isNotBlank(d.getDepartment()) && d.getDepartment().equals(to.getDepartment()))
                    .collect(Collectors.toList());
            DimissionInfoCollectBO bo = new DimissionInfoCollectBO();
            bo.setDepartment(to.getDepartment());
            bos.add(this.countCollectBO(bo, list));
            return bos;
        }

        if (StringUtils.isNotBlank(to.getPosition()))
            list = list.stream()
                    .filter(d -> StringUtils.isNotBlank(d.getDepartment()) && d.getPosition().equals(to.getPosition()))
                    .sorted(Comparator.comparing(DimissionInfoBO::getDepartment))
                    .collect(Collectors.toList());


        if (StringUtils.isBlank(to.getPosition()) && StringUtils.isBlank(to.getDepartment()))
            list = list.stream()
                    .filter(d -> d.getDepartment() != null)
                    .sorted(Comparator.comparing(DimissionInfoBO::getDepartment))
                    .collect(Collectors.toList());

        String department = "";
        for (DimissionInfoBO bo : list)
            if (!bo.getDepartment().equals(department)) {
                department = bo.getDepartment();
                List<DimissionInfoBO> temp = list.stream().filter(d -> d.getDepartment().equals(bo.getDepartment())).collect(Collectors.toList());
                DimissionInfoCollectBO collectBO = new DimissionInfoCollectBO();
                collectBO.setDepartment(to.getDepartment());
                bos.add(this.countCollectBO(collectBO, temp));
            }
        return bos;
    }

    private DimissionInfoCollectBO countCollectBO(DimissionInfoCollectBO bo, List<DimissionInfoBO> list) {
        bo.setPresume(list.stream().filter(d -> d.getType() == DimissionType.PRESUME).collect(Collectors.toList()).size());
        bo.setNormal(list.stream().filter(d -> d.getType() == DimissionType.NORMAL).collect(Collectors.toList()).size());
        bo.setRefuse(list.stream().filter(d -> d.getType() == DimissionType.REFUSE).collect(Collectors.toList()).size());
        return bo;
    }

    @Override
    public List<DimissionInfoCollectBO> positionCollect(DimissionCollectTO to) throws SerException {
        List<DimissionInfoBO> list;
        List<DimissionInfoCollectBO> bos = new ArrayList<>(0);
        if (StringUtils.isNotBlank(to.getStart()) && StringUtils.isNotBlank(to.getEnd()))
            list = this.findByDimissionDate(to.getStart(), to.getEnd());
        else
            list = this.all();

        if (StringUtils.isNotBlank(to.getPosition())) {
            list = list.stream()
                    .filter(d -> StringUtils.isNotBlank(d.getPosition()) && d.getPosition().equals(to.getPosition()))
                    .collect(Collectors.toList());
            DimissionInfoCollectBO bo = new DimissionInfoCollectBO();
            bo.setPosition(to.getPosition());
            bos.add(this.countCollectBO(bo, list));
            return bos;
        }

        if (StringUtils.isNotBlank(to.getDepartment()))
            list = list.stream()
                    .filter(d -> StringUtils.isNotBlank(d.getPosition()) && d.getDepartment().equals(to.getDepartment()))
                    .sorted(Comparator.comparing(DimissionInfoBO::getPosition))
                    .collect(Collectors.toList());

        if (StringUtils.isBlank(to.getPosition()) && StringUtils.isBlank(to.getDepartment()))
            list = list.stream()
                    .filter(d -> StringUtils.isNotBlank(d.getPosition()))
                    .sorted(Comparator.comparing(DimissionInfoBO::getPosition))
                    .collect(Collectors.toList());

        String position = "";

        for (DimissionInfoBO bo : list)
            if (!bo.getPosition().equals(position)) {
                position = bo.getPosition();
                List<DimissionInfoBO> temp = list.stream()
                        .filter(d -> d.getPosition().equals(bo.getPosition()))
                        .collect(Collectors.toList());
                DimissionInfoCollectBO collectBO = new DimissionInfoCollectBO();
                collectBO.setPosition(position);
                bos.add(this.countCollectBO(collectBO, temp));
            }
        return bos;
    }

    @Override
    public List<DimissionInfoCollectBO> entryCollect(DimissionCollectTO to) throws SerException {
        List<DimissionInfoBO> list;
        List<DimissionInfoCollectBO> bos = new ArrayList<>(0);
        if (StringUtils.isNotBlank(to.getStart()) && StringUtils.isNotBlank(to.getEnd()))
            list = this.findByDimissionDate(to.getStart(), to.getEnd());
        else
            list = this.all();

        if (StringUtils.isNotBlank(to.getPosition()))
            list = list.stream()
                    .filter(d -> StringUtils.isNotBlank(d.getPosition()) && d.getPosition().equals(to.getPosition()))
                    .collect(Collectors.toList());

        if (StringUtils.isNotBlank(to.getDepartment()))
            list = list.stream()
                    .filter(d -> StringUtils.isNotBlank(d.getDepartment()) && d.getDepartment().equals(to.getDepartment()))
                    .collect(Collectors.toList());
        list = list.stream()
                .filter(d -> StringUtils.isNotBlank(d.getEntryTime()))
                .sorted(Comparator.comparing(DimissionInfoBO::getEntryTime))
                .collect(Collectors.toList());

        int year = 0, month = 0;
        for (DimissionInfoBO bo : list) {
            try {
                LocalDate entry = LocalDate.parse(bo.getEntryTime());
                if (entry.getYear() != year || entry.getMonthValue() != month) {
                    year = entry.getYear();

                    List<DimissionInfoBO> temp = list.stream()
                            .filter(d -> LocalDate.parse(d.getEntryTime()).getYear() == entry.getYear() &&
                                    entry.getMonthValue() == LocalDate.parse(d.getEntryTime()).getMonthValue())
                            .collect(Collectors.toList());
                    DimissionInfoCollectBO collectBO = new DimissionInfoCollectBO();
                    collectBO.setEntryTime(String.format("%d年%d月", year, month));
                    bos.add(this.countCollectBO(collectBO, temp));
                }
            } catch (Exception e) {
            }
        }
        return bos;
    }

    @Override
    public List<DimissionInfoCollectBO> seniorityCollect(DimissionCollectTO to) throws SerException {
        List<DimissionInfoBO> list;
        List<DimissionInfoCollectBO> bos = new ArrayList<>(0);
        if (StringUtils.isNotBlank(to.getStart()) && StringUtils.isNotBlank(to.getEnd()))
            list = this.findByDimissionDate(to.getStart(), to.getEnd());
        else
            list = this.all();

        if (StringUtils.isNotBlank(to.getPosition()))
            list = list.stream()
                    .filter(d -> StringUtils.isNotBlank(d.getPosition()) && d.getPosition().equals(to.getPosition()))
                    .collect(Collectors.toList());

        if (StringUtils.isNotBlank(to.getDepartment()))
            list = list.stream()
                    .filter(d -> StringUtils.isNotBlank(d.getDepartment()) && d.getDepartment().equals(to.getDepartment()))
                    .collect(Collectors.toList());

        list = list.stream()
                .filter(d -> StringUtils.isNotBlank(d.getSeniority()))
                .sorted(Comparator.comparing(DimissionInfoBO::getSeniority))
                .collect(Collectors.toList());

        String seniority = "";
        for (DimissionInfoBO bo : list)
            if (!seniority.equals(bo.getSeniority())) {
                seniority = bo.getSeniority();
                List<DimissionInfoBO> temp = list.stream()
                        .filter(d -> d.getSeniority().equals(bo.getSeniority()))
                        .collect(Collectors.toList());
                DimissionInfoCollectBO collectBO = new DimissionInfoCollectBO();
                collectBO.setSeniority(seniority);
                bos.add(this.countCollectBO(collectBO, temp));
            }
        return bos;
    }

    @Override
    public List<DimissionInfoCollectBO> educationCollect(DimissionCollectTO to) throws SerException {
        List<DimissionInfoBO> list;
        List<DimissionInfoCollectBO> bos = new ArrayList<>(0);
        if (StringUtils.isNotBlank(to.getStart()) && StringUtils.isNotBlank(to.getEnd()))
            list = this.findByDimissionDate(to.getStart(), to.getEnd());
        else
            list = this.all();

        if (StringUtils.isNotBlank(to.getPosition()))
            list = list.stream()
                    .filter(d -> StringUtils.isNotBlank(d.getPosition()) && d.getPosition().equals(to.getPosition()))
                    .collect(Collectors.toList());

        if (StringUtils.isNotBlank(to.getDepartment()))
            list = list.stream()
                    .filter(d -> StringUtils.isNotBlank(d.getDepartment()) && d.getDepartment().equals(to.getDepartment()))
                    .collect(Collectors.toList());

        list = list.stream()
                .filter(d -> StringUtils.isNotBlank(d.getEducation()))
                .sorted(Comparator.comparing(DimissionInfoBO::getEducation))
                .collect(Collectors.toList());

        String education = "";
        for (DimissionInfoBO bo : list)
            if (!education.equals(bo.getEducation())) {
                education = bo.getEducation();
                List<DimissionInfoBO> temp = list.stream()
                        .filter(d -> d.getEducation().equals(bo.getEducation()))
                        .collect(Collectors.toList());
                DimissionInfoCollectBO collectBO = new DimissionInfoCollectBO();
                collectBO.setEducation(education);
                bos.add(this.countCollectBO(collectBO, temp));
            }

        return bos;
    }

    @Override
    public List<DimissionReasonBO> reasonCollect(DimissionCollectTO to) throws SerException {
        List<DimissionInfoBO> list;
        List<DimissionReasonBO> bos = new ArrayList<>(0);
        if (StringUtils.isNotBlank(to.getStart()) && StringUtils.isNotBlank(to.getEnd()))
            list = this.findByDimissionDate(to.getStart(), to.getEnd());
        else
            list = this.all();

        if (StringUtils.isNotBlank(to.getPosition()))
            list = list.stream()
                    .filter(d -> StringUtils.isNotBlank(d.getPosition()) && d.getPosition().equals(to.getPosition()))
                    .collect(Collectors.toList());

        if (StringUtils.isNotBlank(to.getDepartment()))
            list = list.stream()
                    .filter(d -> StringUtils.isNotBlank(d.getDepartment()) && d.getDepartment().equals(to.getDepartment()))
                    .collect(Collectors.toList());

        list = list.stream()
                .filter(d -> d.getType() != null)
                .sorted(Comparator.comparing(DimissionInfoBO::getType))
                .collect(Collectors.toList());
        DimissionType type = null;
        for (DimissionInfoBO bo : list)
            if (type != bo.getType()) {
                type = bo.getType();
                List<DimissionInfoBO> temp = list.stream()
                        .filter(d -> d.getType() == bo.getType())
                        .sorted(Comparator.comparing(DimissionInfoBO::getReason))
                        .collect(Collectors.toList());
                DimissionReasonBO reasonBO = new DimissionReasonBO();
                StringBuilder detail = new StringBuilder(0);
                String reason = "";
                for (DimissionInfoBO infoBO : temp)
                    if (!reason.equals(infoBO.getReason()))
                        detail.append(infoBO.getReason()).append(",");
                reasonBO.setDetail(detail.toString());
                reasonBO.setType(type.getStringValue());
                reasonBO.setNumber(temp.size());
                bos.add(reasonBO);
            }
        return bos;
    }


    @Override
    public DimissionInfoBO getById(String id) throws SerException {
        DimissionInfo entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return transformBO(entity);
    }

    @Override
    public Long getTotal() throws SerException {
        DimissionInfoDTO dto = new DimissionInfoDTO();
        return super.count(dto);
    }

    @Override
    public List<String> getAllName() throws SerException {
        if (moduleAPI.isCheck("organize")) {
            List<UserBO> userBOList = positionDetailUserAPI.findUserListInOrgan();
            if (!CollectionUtils.isEmpty(userBOList)) {
                List<String> list = userBOList.stream().map(UserBO::getUsername).distinct().collect(Collectors.toList());
                return list;
            }
        }
        return null;
    }

    @Override
    public Integer getDimissionNum(String[] date) throws SerException {
        Integer number = 0;
        DimissionInfoDTO dimissionInfoDTO = new DimissionInfoDTO();
        dimissionInfoDTO.getConditions().add(Restrict.between("dimissionDate", date));
        List<DimissionInfo> dimissionInfos = super.findByCis(dimissionInfoDTO);
        if (dimissionInfos != null && dimissionInfos.size() > 0) {
            number = dimissionInfos.size();
        }
        return number;
    }

    @Override
    public String getDate() throws SerException {
        String sql = "select min(dimissionDate) as dimissionDate from " + getTableName(DimissionInfo.class);
        List<Object> objects = super.findBySql(sql);
        String startDate = "";
        if(objects!=null && objects.size()>0){
            startDate = String.valueOf(objects.get(0));
        }
        return startDate;
    }

    @Override
    public List<DimissionInfo> findByName(String userName) throws SerException {
        DimissionInfoDTO dto = new DimissionInfoDTO();
        dto.getConditions().add(Restrict.eq("username", userName));
        List<DimissionInfo> list = super.findByCis(dto);
        return list;
    }
}