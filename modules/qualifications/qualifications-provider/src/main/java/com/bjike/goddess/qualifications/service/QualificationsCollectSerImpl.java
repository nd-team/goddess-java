package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.bo.QualificationsCollectBO;
import com.bjike.goddess.qualifications.dto.QualificationsCollectDTO;
import com.bjike.goddess.qualifications.entity.QualificationsCollect;
import com.bjike.goddess.qualifications.enums.GuideAddrStatus;
import com.bjike.goddess.qualifications.excel.SonPermissionObject;
import com.bjike.goddess.qualifications.to.GuidePermissionTO;
import com.bjike.goddess.qualifications.to.QualificationsCollectFilterTO;
import com.bjike.goddess.qualifications.to.QualificationsCollectTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 资质办理进度汇总业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-20 10:39 ]
 * @Description: [ 资质办理进度汇总业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "qualificationsSerCache")
@Service
public class QualificationsCollectSerImpl extends ServiceImpl<QualificationsCollect, QualificationsCollectDTO> implements QualificationsCollectSer {

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private AuditMaterialSer auditMaterialSer;

    @Autowired
    private CompanyInfoSer companyInfoSer;

    @Autowired
    private FacilityInformationSer facilityInformationSer;

    @Autowired
    private FinanceInfoSer financeInfoSer;

    @Autowired
    private HandlePlanImplementSer handlePlanImplementSer;

    @Autowired
    private HandlePlanStageSer handlePlanStageSer;

    @Autowired
    private PersonnelInformationSer personnelInformationSer;

    @Autowired
    private QualificationsGatherSer qualificationsGatherSer;

    @Autowired
    private QualificationsHandlePlanSer qualificationsHandlePlanSer;

    @Autowired
    private QualificationsHandleSer qualificationsHandleSer;

    @Autowired
    private QualificationsInfoSer qualificationsInfoSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Override
    public QualificationsCollectBO save(QualificationsCollectTO to) throws SerException {
        UserBO user = userAPI.currentUser();
        QualificationsCollect entity = BeanTransform.copyProperties(to, QualificationsCollect.class, true);
        entity.setWriter(user.getUsername());
        entity.setModifyTime(LocalDateTime.now());
        super.save(entity);
        return BeanTransform.copyProperties(entity, QualificationsCollectBO.class);
    }

    @Override
    public QualificationsCollectBO update(QualificationsCollectTO to) throws SerException {
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据id不能为空");
        try {
            QualificationsCollect entity = super.findById(to.getId());
            BeanTransform.copyProperties(to, entity, true);
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
            return BeanTransform.copyProperties(entity, QualificationsCollectBO.class);
        } catch (SerException e) {
            throw new SerException("数据对象不存在");
        }
    }

    @Override
    public QualificationsCollectBO delete(String id) throws SerException {
        if (StringUtils.isBlank(id))
            throw new SerException("数据id不能为空");
        QualificationsCollect entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, QualificationsCollectBO.class);
    }

    @Override
    public List<QualificationsCollectBO> findByFilter(QualificationsCollectFilterTO to) throws SerException {
        QualificationsCollectDTO dto = new QualificationsCollectDTO();
        if (StringUtils.isNotBlank(to.getCompany()))
            dto.getConditions().add(Restrict.eq("company", to.getCompany()));
        if (StringUtils.isNotBlank(to.getQualifications()))
            dto.getConditions().add(Restrict.eq("qualifications", to.getQualifications()));
        dto.getSorts().add("modifyTime=desc");
        List<QualificationsCollect> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, QualificationsCollectBO.class);
    }

    @Override
    public List<QualificationsCollectBO> maps(QualificationsCollectDTO dto) throws SerException {
        dto.getSorts().add("modifyTime=desc");
        List<QualificationsCollect> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, QualificationsCollectBO.class);
    }

    @Override
    public Integer getTotal() throws SerException {
        return super.findAll().size();
    }

    @Override
    public QualificationsCollectBO getById(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id), QualificationsCollectBO.class);
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        List<SonPermissionObject> list = new ArrayList<>();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("qualificationscollect");
        obj.setDescribesion("资质办理进度汇总");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = auditMaterialSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("auditmaterial");
        obj.setDescribesion("审核资料");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate = companyInfoSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("companyinfo");
        obj.setDescribesion("公司基本信息");
        if (flagSeeCate) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeEmail = facilityInformationSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("facilityinformation");
        obj.setDescribesion("设备信息");
        if (flagSeeEmail) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeBase = financeInfoSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("financeinfo");
        obj.setDescribesion("财务资料");
        if (flagSeeBase) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeBase1 = handlePlanImplementSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("handleplanimplement");
        obj.setDescribesion("资质办理计划阶段实施工作记录");
        if (flagSeeBase1) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeBase2 = handlePlanStageSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("handleplanstage");
        obj.setDescribesion("资质办理计划阶段划分");
        if (flagSeeBase2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeBase3 = personnelInformationSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("personnelinformation");
        obj.setDescribesion("人员信息资料");
        if (flagSeeBase3) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeBase4 = qualificationsGatherSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("qualificationsgather");
        obj.setDescribesion("资质办理信息采集");
        if (flagSeeBase4) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeBase5 = qualificationsHandlePlanSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("qualificationshandleplan");
        obj.setDescribesion("资质办理计划");
        if (flagSeeBase5) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeBase6 = qualificationsHandleSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("qualificationshandle");
        obj.setDescribesion("资质办理管理");
        if (flagSeeBase6) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeBase7 = qualificationsInfoSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("qualificationsinfo");
        obj.setDescribesion("资质信息管理");
        if (flagSeeBase7) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
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

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }




}