package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.bo.HandlePlanStageBO;
import com.bjike.goddess.qualifications.bo.QualificationsHandlePlanBO;
import com.bjike.goddess.qualifications.dto.HandlePlanStageDTO;
import com.bjike.goddess.qualifications.entity.HandlePlanStage;
import com.bjike.goddess.qualifications.enums.GuideAddrStatus;
import com.bjike.goddess.qualifications.to.GuidePermissionTO;
import com.bjike.goddess.qualifications.to.HandlePlanStageTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 资质办理计划阶段划分业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:55 ]
 * @Description: [ 资质办理计划阶段划分业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "qualificationsSerCache")
@Service
public class HandlePlanStageSerImpl extends ServiceImpl<HandlePlanStage, HandlePlanStageDTO> implements HandlePlanStageSer {

    @Autowired
    private QualificationsHandlePlanSer handlePlanSer;
    @Autowired
    private HandlePlanImplementSer handlePlanImplementSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Transactional(rollbackFor = SerException.class)
    @Override
    public HandlePlanStageBO save(HandlePlanStageTO to) throws SerException {
        HandlePlanStage entity = BeanTransform.copyProperties(to, HandlePlanStage.class, true);
        entity.setPlan(handlePlanSer.findById(to.getPlanId()));
        if (entity.getPlan() == null)
            throw new SerException("办理计划不存在,无法保存");
        super.save(entity);
        return BeanTransform.copyProperties(entity, HandlePlanStageBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public HandlePlanStageBO update(HandlePlanStageTO to) throws SerException {
        HandlePlanStage entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        entity.setPlan(handlePlanSer.findById(to.getPlanId()));
        if (entity.getPlan() == null)
            throw new SerException("办理计划不存在,无法保存");
        super.update(entity);
        return BeanTransform.copyProperties(entity, HandlePlanStageBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public HandlePlanStageBO delete(String id) throws SerException {
        HandlePlanStage entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        if (handlePlanImplementSer.findByStage(id).size() != 0)
            throw new SerException("存在依赖关系,无法删除");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, HandlePlanStageBO.class);
    }

    @Override
    public List<HandlePlanStageBO> findByPlanIds(String[] planIds) throws SerException {
        if (planIds == null || planIds.length == 0)
            return null;
        HandlePlanStageDTO dto = new HandlePlanStageDTO();
        dto.getConditions().add(Restrict.in("plan.id", planIds));
        dto.getSorts().add("plan.id");
        List<HandlePlanStage> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, HandlePlanStageBO.class);
    }

    @Override
    public List<HandlePlanStageBO> findByPlan(String planId) throws SerException {
        HandlePlanStageDTO dto = new HandlePlanStageDTO();
        dto.getConditions().add(Restrict.eq("plan.id", planId));
        List<HandlePlanStage> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, HandlePlanStageBO.class);
    }

    @Override
    public List<HandlePlanStageBO> findByHandle(String handleId) throws SerException {
        List<String> planIds = handlePlanSer.findByHandle(handleId).stream().map(QualificationsHandlePlanBO::getId).collect(Collectors.toList());
        if (planIds.size() != 0)
            return this.findByPlanIds(planIds.toArray(new String[0]));
        else
            return null;
    }

    @Override
    public HandlePlanStageBO getById(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id), HandlePlanStageBO.class);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if( flagSee || flagAdd ){
            return true;
        } else {
            return false;
        }
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
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
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
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }
}