package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.AssistancePlanBO;
import com.bjike.goddess.assistance.dto.AssistancePlanDTO;
import com.bjike.goddess.assistance.dto.RightSetDTO;
import com.bjike.goddess.assistance.entity.AssistancePlan;
import com.bjike.goddess.assistance.entity.RightSet;
import com.bjike.goddess.assistance.enums.EmpRight;
import com.bjike.goddess.assistance.enums.GuideAddrStatus;
import com.bjike.goddess.assistance.to.AssistancePlanTO;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
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
    private RightSetSer rightSetSer;
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
     * 核对查看权限（部门级别）
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
     * 核对添加修改删除审核权限（岗位级别）
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
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
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
            case COLLECT:
                flag = guideSeeIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
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
        if (StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        AssistancePlan assistancePlan = super.findById(id);

        return BeanTransform.copyProperties(assistancePlan, AssistancePlanBO.class);
    }
    @Override
    public List<AssistancePlanBO> listAssistancePlan(AssistancePlanDTO assistancePlanDTO) throws SerException {
        checkSeeIdentity();
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
        checkAddIdentity();
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
        checkAddIdentity();
        AssistancePlan assistancePlan = BeanTransform.copyProperties(assistancePlanTO, AssistancePlan.class, true);
        AssistancePlan rs = super.findById(assistancePlanTO.getId());


        rs.setModifyTime(LocalDateTime.now());
        super.update(rs);
        return BeanTransform.copyProperties(rs, AssistancePlanBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteAssistancePlan(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AssistancePlanBO auditAssistancePlan(AssistancePlanTO assistancePlanTO) throws SerException {
        checkAddIdentity();
        String userName = userAPI.currentUser().getUsername();
        RightSetDTO rightSetDTO = new RightSetDTO();
        rightSetDTO.getConditions().add(Restrict.eq("empName", userName));
        List<RightSet> rsList = rightSetSer.findByCis(rightSetDTO);
        List<EmpRight> empRightList = rsList.stream().map(RightSet::getEmpRight).collect(Collectors.toList());

//        AssistancePlan assistancePlan = BeanTransform.copyProperties(assistancePlanTO, AssistancePlan.class, true);
        AssistancePlan rs = super.findById(assistancePlanTO.getId());
        //总经办审核
        if (empRightList.contains(EmpRight.MANAGE)) {
            rs.setManageAdvice(assistancePlanTO.getManageAdvice());
        } else if (empRightList.contains(EmpRight.WAREFARE)) {
            rs.setManageAdvice(assistancePlanTO.getWarefaleAdvice());
        } else if (empRightList.contains(EmpRight.FINANCE)) {
            rs.setManageAdvice(assistancePlanTO.getFiniceAdvice());
        } else {
            throw new SerException("您没有审核权限,请去权限设置去设置后再来");
        }

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
    public List<AssistancePlanBO>  listPlanNum() throws SerException {
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