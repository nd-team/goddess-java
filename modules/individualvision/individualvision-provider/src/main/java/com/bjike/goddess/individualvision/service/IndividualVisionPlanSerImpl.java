package com.bjike.goddess.individualvision.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.individualvision.bo.IndividualVisionPlanBO;
import com.bjike.goddess.individualvision.dto.IndividualVisionPlanDTO;
import com.bjike.goddess.individualvision.entity.CusPermission;
import com.bjike.goddess.individualvision.entity.IndividualVisionPlan;
import com.bjike.goddess.individualvision.enums.GuideAddrStatus;
import com.bjike.goddess.individualvision.excel.SonPermissionObject;
import com.bjike.goddess.individualvision.to.GuidePermissionTO;
import com.bjike.goddess.individualvision.to.IndividualVisionPlanTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 个人愿景计划业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-30 06:26 ]
 * @Description: [ 个人愿景计划业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "individualvisionSerCache")
@Service
public class IndividualVisionPlanSerImpl extends ServiceImpl<IndividualVisionPlan, IndividualVisionPlanDTO> implements IndividualVisionPlanSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private CareerPlanningCustomSer careerPlanningCustomSer;
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
        Boolean flagSeeInfo = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddInfo = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("individualvisionplan");
        obj.setDescribesion("个人愿景计划");
        if (flagSeeInfo || flagAddInfo) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeSystembet = careerPlanningCustomSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("careerplanningcustom");
        obj.setDescribesion("职业规划定制");
        if (flagSeeSystembet) {
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
    @Override
    public Long countIndividualVisionPlan(IndividualVisionPlanDTO individualVisionPlanDTO) throws SerException {
        Long count = super.count(individualVisionPlanDTO);
        return count;
    }

    @Override
    public IndividualVisionPlanBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        IndividualVisionPlan individualVisionPlan = super.findById(id);
        return BeanTransform.copyProperties(individualVisionPlan, IndividualVisionPlanBO.class);
    }

    @Override
    public List<IndividualVisionPlanBO> findListIndividualVisionPlan(IndividualVisionPlanDTO individualVisionPlanDTO) throws SerException {
        checkSeeIdentity();
        individualVisionPlanDTO.getSorts().add("name=desc");
        individualVisionPlanDTO.getSorts().add("area=desc");
        individualVisionPlanDTO.getSorts().add("department=desc");
        individualVisionPlanDTO.getSorts().add("entryTime=desc");
        individualVisionPlanDTO.getSorts().add("expectedBenefit=desc");
        individualVisionPlanDTO.getSorts().add("expectedSalaryIncrease=desc");
        individualVisionPlanDTO.getSorts().add("planningDate=desc");
        individualVisionPlanDTO.getSorts().add("currentState=desc");

        List<IndividualVisionPlan> individualVisionPlans = super.findByCis(individualVisionPlanDTO, true);
        List<IndividualVisionPlanBO> boList = BeanTransform.copyProperties(individualVisionPlans, IndividualVisionPlanBO.class);
        return boList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public IndividualVisionPlanBO insertIndividualVisionPlan(IndividualVisionPlanTO individualVisionPlanTO) throws SerException {
        checkAddIdentity();
        IndividualVisionPlan individualVisionPlan = BeanTransform.copyProperties(individualVisionPlanTO, IndividualVisionPlan.class, true);
        individualVisionPlan.setCreateTime(LocalDateTime.now());
        super.save(individualVisionPlan);
        return BeanTransform.copyProperties(individualVisionPlan, IndividualVisionPlanBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public IndividualVisionPlanBO editIndividualVisionPlan(IndividualVisionPlanTO individualVisionPlanTO) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(individualVisionPlanTO.getId())) {
            throw new SerException("id不能为空");
        }
        IndividualVisionPlan individualVisionPlan = super.findById(individualVisionPlanTO.getId());
        BeanTransform.copyProperties(individualVisionPlanTO, individualVisionPlan, true);
        individualVisionPlan.setModifyTime(LocalDateTime.now());
        super.update(individualVisionPlan);
        return BeanTransform.copyProperties(individualVisionPlan, IndividualVisionPlanBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeIndividualVisionPlan(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public IndividualVisionPlanBO auditIndividualVisionPlan(IndividualVisionPlanTO individualVisionPlanTO) throws SerException {
        checkAddIdentity();

        IndividualVisionPlan individualVisionPlan = super.findById(individualVisionPlanTO.getId());
                BeanTransform.copyProperties(individualVisionPlanTO, IndividualVisionPlan.class, true);
        individualVisionPlan.setAudit(individualVisionPlanTO.getAudit());
        individualVisionPlan.setAuditStatus(individualVisionPlanTO.getAuditStatus());
        super.update(individualVisionPlan);

        IndividualVisionPlanBO individualVisionPlanBO = BeanTransform.copyProperties(individualVisionPlan, IndividualVisionPlanBO.class);
        return individualVisionPlanBO;
    }
}