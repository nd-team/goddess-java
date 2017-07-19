package com.bjike.goddess.archive.service;

import com.bjike.goddess.annual.enums.AuditType;
import com.bjike.goddess.archive.bo.ArchiveAccessBO;
import com.bjike.goddess.archive.dto.ArchiveAccessDTO;
import com.bjike.goddess.archive.entity.ArchiveAccess;
import com.bjike.goddess.archive.enums.GuideAddrStatus;
import com.bjike.goddess.archive.excel.SonPermissionObject;
import com.bjike.goddess.archive.to.AccessAuditTO;
import com.bjike.goddess.archive.to.ArchiveAccessTO;
import com.bjike.goddess.archive.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.bo.UserDetailBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
            flag = cusPermissionSer.busRotainCusPermission("2");
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
        entity.setAudit(AuditType.NONE);
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
        UserDetailBO detail = userDetailAPI.findByUserId(user.getId());
        if (null == detail)
            throw new SerException("对不起,你没有权限审核");
        if (detail.getDepartmentName().indexOf("福利") > 0) {
            entity.setWelfare(user.getUsername());
            entity.setWelfareOpinion(to.getOpinion());
        }
        if (detail.getDepartmentName().indexOf("总经办") > 0) {
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
        return BeanTransform.copyProperties(super.findByPage(dto), ArchiveAccessBO.class);
    }

    @Override
    public ArchiveAccessBO getById(String id) throws SerException {
        ArchiveAccess entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return BeanTransform.copyProperties(entity, ArchiveAccessBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        ArchiveAccessDTO dto = new ArchiveAccessDTO();
        return super.count(dto);
    }
}