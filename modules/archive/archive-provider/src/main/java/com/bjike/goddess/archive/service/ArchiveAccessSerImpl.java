package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.ArchiveAccessBO;
import com.bjike.goddess.archive.dto.ArchiveAccessDTO;
import com.bjike.goddess.archive.entity.ArchiveAccess;
import com.bjike.goddess.archive.enums.AuditType;
import com.bjike.goddess.archive.enums.GuideAddrStatus;
import com.bjike.goddess.archive.excel.ArchiveAccessExportExcel;
import com.bjike.goddess.archive.excel.ArchiveAccessImportExcel;
import com.bjike.goddess.archive.excel.SonPermissionObject;
import com.bjike.goddess.archive.to.AccessAuditTO;
import com.bjike.goddess.archive.to.ArchiveAccessTO;
import com.bjike.goddess.archive.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.bjike.goddess.archive.enums.AuditType.NONE;

/**
 * 档案调阅业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 04:03 ]
 * @Description: [ 档案调阅业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "archiveSerCache")
@Service
public class ArchiveAccessSerImpl extends ServiceImpl<ArchiveAccess, ArchiveAccessDTO> implements ArchiveAccessSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private UserDetailAPI userDetailAPI;
    @Autowired
    private ArchiveDetailSer archiveDetailSer;
    @Autowired
    private ArchiveGatherSer archiveGatherSer;
    @Autowired
    private EnclosureTypeSer enclosureTypeSer;
    @Autowired
    private ForeignStaffingSer foreignStaffingSer;
    @Autowired
    private ForeignStaffingSetSer foreignStaffingSetSer;
    @Autowired
    private LaborRelationSer laborRelationSer;
    @Autowired
    private PersonnelQualificationSer personnelQualificationSer;
    @Autowired
    private SocialSecurityTypeSer socialSecurityTypeSer;
    @Autowired
    private StaffRecordsSer staffRecordsSer;
    @Autowired
    private RotainCusPermissionSer cusPermissionSer;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private ResumeInfoSer resumeInfoSer;

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
            flag = cusPermissionSer.getRotainCusPermission("1");
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
            flag = cusPermissionSer.getRotainCusPermission("2");
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
            flag = cusPermissionSer.getRotainCusPermission("1");
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
            flag = cusPermissionSer.busRotainCusPermission("2");
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
        obj.setName("archiveaccess");
        obj.setDescribesion("档案调阅");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = archiveDetailSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("archivedetail");
        obj.setDescribesion("档案明细");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate = archiveGatherSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("archivegather");
        obj.setDescribesion("档案收集");
        if (flagSeeCate) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate1 = enclosureTypeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("enclosuretype");
        obj.setDescribesion("附件类型");
        if (flagSeeCate1) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate2 = foreignStaffingSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("foreignstaffing");
        obj.setDescribesion("对外人员信息");
        if (flagSeeCate2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate3 = foreignStaffingSetSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("foreignstaffingset");
        obj.setDescribesion("对外人员基本信息设置");
        if (flagSeeCate3) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate4 = laborRelationSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("laborrelation");
        obj.setDescribesion("劳动关系类型");
        if (flagSeeCate4) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate5 = personnelQualificationSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("personnelqualification");
        obj.setDescribesion("人员资质");
        if (flagSeeCate5) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate6 = socialSecurityTypeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("socialsecuritytype");
        obj.setDescribesion("公司社保购买类型");
        if (flagSeeCate6) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate7 = staffRecordsSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("staffrecords");
        obj.setDescribesion("员工档案");
        if (flagSeeCate7) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate8 = resumeInfoSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("resumeinfo");
        obj.setDescribesion("人员简历信息");
        if (flagSeeCate8) {
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

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ArchiveAccessBO save(ArchiveAccessTO to) throws SerException {
        UserBO user = userAPI.currentUser();
        ArchiveAccess entity = BeanTransform.copyProperties(to, ArchiveAccess.class, true);
        entity.setUsername(user.getUsername());
        StringBuilder sb = new StringBuilder(0);
        for (String name : to.getAccessNames())
            sb.append(name).append(",");
        entity.setAccess(sb.toString());
        entity.setAudit(NONE);
        super.save(entity);
        return BeanTransform.copyProperties(entity, ArchiveAccessBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ArchiveAccessBO update(ArchiveAccessTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                ArchiveAccess entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                StringBuilder sb = new StringBuilder(0);
                for (String name : to.getAccessNames())
                    sb.append(name).append(",");
                entity.setAccess(sb.toString());
                super.update(entity);
                return BeanTransform.copyProperties(entity, ArchiveAccessBO.class);
            } catch (Exception e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
    }

    @Override
    public ArchiveAccessBO delete(String id) throws SerException {
        ArchiveAccess entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, ArchiveAccessBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ArchiveAccessBO audit(AccessAuditTO to) throws SerException {
        UserBO user = userAPI.currentUser();
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据ID不能为空");
        ArchiveAccess entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        if (null == user)
            throw new SerException("请登陆重试");
//        UserDetailBO detail = userDetailAPI.findByUserId(user.getId());
        List<PositionDetailBO> positionDetailBOs = positionDetailUserAPI.getPositionDetail(user.getUsername());
        if (CollectionUtils.isEmpty(positionDetailBOs) && !"admin".equals(user.getUsername())) {
            throw new SerException("对不起,你没有权限审核");
        }
        List<String> detail = new ArrayList<>(0);
        if (!CollectionUtils.isEmpty(positionDetailBOs)) {
            detail = positionDetailBOs.stream().map(PositionDetailBO::getDepartmentName).distinct().collect(Collectors.toList());
        }
//        if (null == detail && !"admin".equals(user.getUsername()))
//            throw new SerException("对不起,你没有权限审核");
//        if (detail.getDepartmentName().indexOf("福利") > 0 ) {
        if (detail.indexOf("福利") > 0 || "admin".equals(user.getUsername())) {
            entity.setWelfare(user.getUsername());
            entity.setWelfareOpinion(to.getOpinion());
        }
        if (detail.indexOf("总经办") > 0 || "admin".equals(user.getUsername())) {
            entity.setManage(user.getUsername());
            entity.setManageOpinion(to.getOpinion());
            entity.setAudit(to.getPass() ? AuditType.ALLOWED : AuditType.DENIED);
        }
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, ArchiveAccessBO.class);
    }

    @Override
    public List<ArchiveAccessBO> maps(ArchiveAccessDTO dto) throws SerException {
        dto = findData(dto);
        searchCondition(dto);
        List<ArchiveAccess> list = super.findByPage(dto);
        for (ArchiveAccess entity : list) {
            String endTime = DateUtil.dateToString(entity.getEnd());
            if (isOrder(endTime, DateUtil.dateToString(LocalDate.now()))) {
                entity.setOverdue(true);
            }
        }
        return BeanTransform.copyProperties(list, ArchiveAccessBO.class);
    }

    @Override
    public ArchiveAccessBO getById(String id) throws SerException {
        ArchiveAccess entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return BeanTransform.copyProperties(entity, ArchiveAccessBO.class);
    }

    @Override
    public Long getTotal(ArchiveAccessDTO dto) throws SerException {
        dto = findData(dto);
        searchCondition(dto);
        return super.count(dto);
    }

    @Override
    public byte[] exportExcel(ArchiveAccessDTO dto) throws SerException {
        dto.setUsername("admin");
//        dto = findData(dto);
        searchCondition(dto);
        List<ArchiveAccess> archiveAccesses = super.findByCis(dto);
        List<ArchiveAccessBO> bos = BeanTransform.copyProperties(archiveAccesses, ArchiveAccessBO.class, false);
        List<ArchiveAccessExportExcel> archiveAccessExportExcels = transExcel(bos);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(archiveAccessExportExcels, excel);
        return bytes;
    }

    @Override
    public byte[] templateExcel() throws SerException {
        Excel excel = new Excel(0, 2);
        List<ArchiveAccessImportExcel> archiveAccessImportExcels = new ArrayList<>(0);
        ArchiveAccessImportExcel archiveAccessImportExcel = new ArchiveAccessImportExcel();
        archiveAccessImportExcels.add(archiveAccessImportExcel);
        byte[] bytes = ExcelUtil.clazzToExcel(archiveAccessImportExcels, excel);
        return bytes;
    }

    @Override
    public void upload(List<ArchiveAccessImportExcel> tos) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        if (null != tos && tos.size() > 0) {
            for (ArchiveAccessImportExcel excel : tos) {
                ArchiveAccess entity = BeanTransform.copyProperties(excel, ArchiveAccess.class, true, "audit");
                if (isOrder(excel.getEnd(), DateUtil.dateToString(LocalDate.now()))) {
                    entity.setOverdue(true);
                }
                AuditType type = transString(excel.getAudit());
                if (null != type) {
                    entity.setAudit(type);
                } else {
                    entity.setAudit(AuditType.NONE);
                }
                UserBO userBO = userAPI.currentUser();
                RpcTransmit.transmitUserToken(userToken);
                entity.setUsername(userBO.getUsername());
                super.save(entity);
            }
        }
    }

    @Override
    public List<String> findUserName() throws SerException {
        List<ArchiveAccess> archiveAccesses = super.findAll();
        if (null != archiveAccesses && archiveAccesses.size() > 0) {
            List<String> list = archiveAccesses.stream().map(ArchiveAccess::getUsername).collect(Collectors.toList());
            return list;
        }
        return null;
    }

    private List<ArchiveAccessExportExcel> transExcel(List<ArchiveAccessBO> bos) throws SerException {
        if (null != bos && bos.size() > 0) {
            List<ArchiveAccessExportExcel> archiveAccessExportExcels = BeanTransform.copyProperties(bos, ArchiveAccessExportExcel.class, "audit", "overdue");
            for (ArchiveAccessExportExcel archiveAccessExportExcel : archiveAccessExportExcels) {
                if (isOrder(archiveAccessExportExcel.getEnd(), DateUtil.dateToString(LocalDate.now()))) {
                    archiveAccessExportExcel.setOverdue("是");
                } else {
                    archiveAccessExportExcel.setOverdue("否");
                }
            }
            List<AuditType> list = bos.stream().map(ArchiveAccessBO::getAudit).collect(Collectors.toList());
            if (list.size() == archiveAccessExportExcels.size() && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    archiveAccessExportExcels.get(i).setAudit(transAudit(list.get(i)));
                }
            }
            return archiveAccessExportExcels;
        }
        return null;
    }

    private String transAudit(AuditType auditType) throws SerException {
        String str = "";
        switch (auditType) {
            case NONE:
                str = "未处理";
                break;
            case ALLOWED:
                str = "通过";
                break;
            case DENIED:
                str = "拒绝";
                break;
        }
        return str;
    }

    private AuditType transString(String auditType) throws SerException {
        AuditType str = null;
        switch (auditType) {
            case "未处理":
                str = AuditType.NONE;
                break;
            case "通过":
                str = AuditType.ALLOWED;
                break;
            case "拒绝":
                str = AuditType.DENIED;
                break;
        }
        return str;
    }

    public Boolean isOrder(String date1, String date2) throws SerException {
        Boolean tar = false;
        try {
            Date a1 = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
            Date b1 = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
            int result = a1.compareTo(b1);
            if (result <= 0) {
                tar = true;
            }
            return tar;
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }

    private void searchCondition(ArchiveAccessDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getUsername())) {
            dto.getConditions().add(Restrict.eq("username", dto.getUsername()));
        }
    }

    /**
     * 是否有权限查看所有人的信息(岗位级别)
     */
    private Boolean guideSeePositionIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.guideSeePositionIdentity();
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 根据岗位查看所有信息或个人信息
     */
    private ArchiveAccessDTO findData(ArchiveAccessDTO dto) throws SerException {
        if (!guideSeePositionIdentity()) {
            dto = new ArchiveAccessDTO();
            String userToken = RpcTransmit.getUserToken();
            UserBO userBO = userAPI.currentUser();
            RpcTransmit.transmitUserToken(userToken);
            dto.getConditions().add(Restrict.eq("username", userBO.getUsername()));
        }
        return dto;
    }
}