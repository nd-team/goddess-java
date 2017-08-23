package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.AssistancePlanBO;
import com.bjike.goddess.assistance.dto.AssistancePlanDTO;
import com.bjike.goddess.assistance.entity.AssistancePlan;
import com.bjike.goddess.assistance.enums.GuideAddrStatus;
import com.bjike.goddess.assistance.to.*;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 补助方案业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-13 09:27 ]
 * @Description: [ 补助方案业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "assistanceSerCache")
@Service
public class AssistancePlanSerImpl extends ServiceImpl<AssistancePlan, AssistancePlanDTO> implements AssistancePlanSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

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
                throw new SerException("您不是相应部门的人员，不可以操作");
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
     * 模块审核
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAuditIdentity(String idFlag) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission(idFlag);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity(String idFlag) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission(idFlag);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 审核核对查看权限（部门级别）
     */
    private Boolean guideAuditIdentity(String idFlag) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission(idFlag);
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity("1");
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideSeeIdentity("2");
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAudit = guideAuditIdentity("zhzyb-Audit");
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagFiance = guideAuditIdentity("finace-Audit");
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagManage = guideAuditIdentity("manage-Audit");
        if (flagSee || flagAdd || flagAudit || flagFiance || flagManage) {
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
                flag = guideSeeIdentity("1");
                break;
            case ADD:
                flag = guideSeeIdentity("2");
                break;
            case EDIT:
                flag = guideSeeIdentity("2");
                break;
            case RESOURCEAUDIT:
                flag = guideAuditIdentity("zhzyb-Audit");
                break;
            case FINACEAUDIT:
                flag = guideAuditIdentity("finace-Audit");
                break;
            case MANAGEAUDIT:
                flag = guideAuditIdentity("manage-Audit");
                break;
            case DELETE:
                flag = guideSeeIdentity("2");
                break;
            case COLLECT:
                flag = guideSeeIdentity("1");
                break;
            case SEE:
                flag = guideSeeIdentity("1");
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }


    @Override
    public Long countAssistancePlan(AssistancePlanDTO assistancePlanDTO) throws SerException {
        if (StringUtils.isNotBlank(assistancePlanDTO.getPlanNum())) {
            assistancePlanDTO.getConditions().add(Restrict.like("planNum", assistancePlanDTO.getPlanNum()));
        }
        if (StringUtils.isNotBlank(assistancePlanDTO.getTypeName())) {
            assistancePlanDTO.getConditions().add(Restrict.like("typeName", assistancePlanDTO.getTypeName()));
        }
        if (StringUtils.isNotBlank(assistancePlanDTO.getHelpObject())) {
            assistancePlanDTO.getConditions().add(Restrict.like("helpObject", assistancePlanDTO.getHelpObject()));
        }
        assistancePlanDTO.getSorts().add("typeName=desc");
        Long count = super.count(assistancePlanDTO);
        return count;
    }

    @Override
    public AssistancePlanBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        AssistancePlan assistancePlan = super.findById(id);

        return BeanTransform.copyProperties(assistancePlan, AssistancePlanBO.class);
    }

    @Override
    public List<AssistancePlanBO> listAssistancePlan(AssistancePlanDTO assistancePlanDTO) throws SerException {
        if (StringUtils.isNotBlank(assistancePlanDTO.getPlanNum())) {
            assistancePlanDTO.getConditions().add(Restrict.like("planNum", assistancePlanDTO.getPlanNum()));
        }
        if (StringUtils.isNotBlank(assistancePlanDTO.getTypeName())) {
            assistancePlanDTO.getConditions().add(Restrict.like("typeName", assistancePlanDTO.getTypeName()));
        }
        if (StringUtils.isNotBlank(assistancePlanDTO.getHelpObject())) {
            assistancePlanDTO.getConditions().add(Restrict.like("helpObject", assistancePlanDTO.getHelpObject()));
        }
        assistancePlanDTO.getSorts().add("typeName=desc");
        List<AssistancePlan> list = super.findByCis(assistancePlanDTO, true);

        return BeanTransform.copyProperties(list, AssistancePlanBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AssistancePlanBO addAssistancePlan(AssistancePlanTO assistancePlanTO) throws SerException {
        AssistancePlan assistancePlan = BeanTransform.copyProperties(assistancePlanTO, AssistancePlan.class, true);
        assistancePlan.setCreateTime(LocalDateTime.now());

        //设置方案
        Integer seriNum = generatePlanNum();
        assistancePlan.setPlanNum("方案" + seriNum);
        assistancePlan.setSeriNum(seriNum);
        super.save(assistancePlan);
        return BeanTransform.copyProperties(assistancePlan, AssistancePlanBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AssistancePlanBO editAssistancePlan(AssistancePlanTO assistancePlanTO) throws SerException {
        AssistancePlan assistancePlan = BeanTransform.copyProperties(assistancePlanTO, AssistancePlan.class, true);
        AssistancePlan rs = super.findById(assistancePlanTO.getId());


        rs.setModifyTime(LocalDateTime.now());
        super.update(rs);
        return BeanTransform.copyProperties(rs, AssistancePlanBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteAssistancePlan(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AssistancePlanBO auditAssistancePlan(ResourceAuditPlanTO assistancePlanTO) throws SerException {

        if (StringUtils.isBlank(assistancePlanTO.getId())) {
            throw new SerException("id不能为空");
        }
        AssistancePlan rs = super.findById(assistancePlanTO.getId());
        //综合资源审核
        rs.setWarefaleAdvice(assistancePlanTO.getWarefaleAdvice());
        rs.setModifyTime(LocalDateTime.now());
        super.update(rs);
        return BeanTransform.copyProperties(rs, AssistancePlanBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AssistancePlanBO finaceAuditAssistancePlan(FinaceAuditPlanTO assistancePlanTO) throws SerException {

        if (StringUtils.isBlank(assistancePlanTO.getId())) {
            throw new SerException("id不能为空");
        }
        AssistancePlan rs = super.findById(assistancePlanTO.getId());
        //财务审核
        rs.setFiniceAdvice(assistancePlanTO.getFiniceAdvice());
        rs.setModifyTime(LocalDateTime.now());
        super.update(rs);
        return BeanTransform.copyProperties(rs, AssistancePlanBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AssistancePlanBO manageAuditAssistancePlan(ManageAuditPlanTO assistancePlanTO) throws SerException {

        if (StringUtils.isBlank(assistancePlanTO.getId())) {
            throw new SerException("id不能为空");
        }
        AssistancePlan rs = super.findById(assistancePlanTO.getId());
        //总经办审核
        rs.setManageAdvice(assistancePlanTO.getManageAdvice());
        rs.setModifyTime(LocalDateTime.now());
        super.update(rs);
        return BeanTransform.copyProperties(rs, AssistancePlanBO.class);
    }


    private Integer generatePlanNum() throws SerException {
        String result = super.findByMaxField("seriNum", AssistancePlan.class);
        Integer max = 1;
        if (StringUtils.isNotBlank(result)) {
            max = Integer.parseInt(result) + 1;
        }
        return max;
    }


    @Override
    public List<AssistancePlanBO> listPlanNum() throws SerException {
        AssistancePlanDTO dto = new AssistancePlanDTO();
        List<AssistancePlan> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, AssistancePlanBO.class);
    }

    @Override
    public List<AssistancePlanBO> getPlanByNum(AssistancePlanDTO assistancePlanDTO) throws SerException {
        assistancePlanDTO.getConditions().add(Restrict.eq("planNum", assistancePlanDTO.getPlanNum()));
        List<AssistancePlan> list = super.findByCis(assistancePlanDTO);
        return BeanTransform.copyProperties(list, AssistancePlanBO.class);
    }
}