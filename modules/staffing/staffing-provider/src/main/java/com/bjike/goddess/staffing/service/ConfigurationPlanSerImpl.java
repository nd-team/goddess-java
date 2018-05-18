package com.bjike.goddess.staffing.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffing.bo.ConfigurationPlanBO;
import com.bjike.goddess.staffing.bo.PlanSonBO;
import com.bjike.goddess.staffing.dto.ConfigurationPlanDTO;
import com.bjike.goddess.staffing.dto.PlanSonDTO;
import com.bjike.goddess.staffing.entity.ConfigurationPlan;
import com.bjike.goddess.staffing.entity.PlanSon;
import com.bjike.goddess.staffing.enums.GuideAddrStatus;
import com.bjike.goddess.staffing.to.ConfigurationPlanTO;
import com.bjike.goddess.staffing.to.GuidePermissionTO;
import com.bjike.goddess.staffing.to.PlanSonTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 人数配置-计划业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-29 10:33 ]
 * @Description: [ 人数配置-计划业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffingSerCache")
@Service
public class ConfigurationPlanSerImpl extends ServiceImpl<ConfigurationPlan, ConfigurationPlanDTO> implements ConfigurationPlanSer {
    @Autowired
    private PlanSonSer planSonSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

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
            case DELETE:
                flag = guideAddIdentity();
                break;
            case SEE:
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
    @Transactional(rollbackFor = {SerException.class})
    public ConfigurationPlanBO save(ConfigurationPlanTO to) throws SerException {
        checkAddIdentity();
        String classify = to.getClassify();
        ConfigurationPlan entity = find(classify);
        if (entity == null) {
            entity = BeanTransform.copyProperties(to, ConfigurationPlan.class, true);
            super.save(entity);
        }
        PlanSonTO planSonTO = to.getPlanSonTO();
        PlanSon planSon = BeanTransform.copyProperties(planSonTO, PlanSon.class, true);
        planSon.setConfigurationPlan(entity);
        planSon.setCompileCount(planSon.getPolicyNum() + planSon.getManNum() + planSon.getExecuteNum());
        if (null != planSon.getDeployNum()) {
            planSon.setActualNum(planSon.getCompileCount() + planSon.getDeployNum());
        } else {
            planSon.setActualNum(planSon.getCompileCount());
        }
        double a = Double.valueOf(planSon.getManNum());
        double b = Double.valueOf(planSon.getCompileCount());
        String manProportion = Math.round((a / b) * 100) + "%";
        planSon.setManProportion(manProportion);
        String executeProportion = Math.round((Double.valueOf(planSon.getExecuteNum()) / Double.valueOf(planSon.getCompileCount())) * 100) + "%";
        planSon.setExecuteProportion(executeProportion);
        planSonSer.save(planSon);
        return null;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(ConfigurationPlanTO to) throws SerException {
        checkAddIdentity();
        ConfigurationPlan entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        LocalDateTime aa = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, ConfigurationPlan.class, true);
        entity.setCreateTime(aa);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        PlanSonTO planSonTO = to.getPlanSonTO();
        PlanSon planSon = planSonSer.findById(planSonTO.getId());
        planSon = BeanTransform.copyProperties(planSonTO, PlanSon.class, true);
        planSon.setConfigurationPlan(entity);
        planSon.setCompileCount(planSon.getPolicyNum() + planSon.getManNum() + planSon.getExecuteNum());
        if (null != planSon.getDeployNum()) {
            planSon.setActualNum(planSon.getCompileCount() + planSon.getDeployNum());
        } else {
            planSon.setActualNum(planSon.getCompileCount());
        }
        double a = Double.valueOf(planSon.getManNum());
        double b = Double.valueOf(planSon.getCompileCount());
        String manProportion = Math.round((a / b) * 100) + "%";
        planSon.setManProportion(manProportion);
        String executeProportion = Math.round((Double.valueOf(planSon.getExecuteNum()) / Double.valueOf(planSon.getCompileCount())) * 100) + "%";
        planSon.setExecuteProportion(executeProportion);
        planSon.setCreateTime(aa);
        planSon.setModifyTime(LocalDateTime.now());
        planSonSer.update(planSon);
    }

    @Override
    public List<ConfigurationPlanBO> list(ConfigurationPlanDTO dto) throws SerException {
        checkSeeIdentity();
        dto.getSorts().add("createTime=desc");
        List<ConfigurationPlan> list = super.findByCis(dto, true);
        List<ConfigurationPlanBO> boList = new ArrayList<>();
        int total = 0;
        for (ConfigurationPlan configurationPlan : list) {
            PlanSonDTO sonDTO = new PlanSonDTO();
            sonDTO.getConditions().add(Restrict.eq("configurationPlan.id", configurationPlan.getId()));
            List<PlanSon> sons = planSonSer.findByCis(sonDTO);
            int compileCountSum = 0;
            for (PlanSon son : sons) {
                total += son.getCompileCount();
                compileCountSum += son.getCompileCount();
            }
            configurationPlan.setTotal(compileCountSum);
        }
        int policyNumSum = 0;
        int manNumSum = 0;
        int executeNumSum = 0;
        int compileCountSum = 0;
        int deployNumSum = 0;
        int actualNumSum = 0;
        int proportionSum = 0;
        for (ConfigurationPlan configurationPlan : list) {
            PlanSonDTO sonDTO = new PlanSonDTO();
            sonDTO.getConditions().add(Restrict.eq("configurationPlan.id", configurationPlan.getId()));
            List<PlanSon> sons = planSonSer.findByCis(sonDTO);
            int departWeightSum = 0;
            for (PlanSon son : sons) {
                long departWeight = Math.round((Double.valueOf(son.getCompileCount()) / Double.valueOf(configurationPlan.getTotal()) * 100));
                son.setDepartWeight(departWeight + "%");
                departWeightSum += departWeight;
                policyNumSum += son.getPolicyNum();
                manNumSum += son.getManNum();
                executeNumSum += son.getExecuteNum();
                compileCountSum += son.getCompileCount();
                if (null != son.getDeployNum()) {
                    deployNumSum += son.getDeployNum();
                }
                actualNumSum += son.getActualNum();
            }
            long proportion = Math.round(Double.valueOf(configurationPlan.getTotal()) / Double.valueOf(total) * 100);
            proportionSum += proportion;
            configurationPlan.setProportion(proportion + "%");
            configurationPlan.setClassifyWeight(departWeightSum + "%");
            ConfigurationPlanBO configurationPlanBO = BeanTransform.copyProperties(configurationPlan, ConfigurationPlanBO.class);
            List<PlanSonBO> planSonBOs = BeanTransform.copyProperties(new ArrayList<PlanSon>(sons), PlanSonBO.class);
            configurationPlanBO.setSons(planSonBOs);
            boList.add(configurationPlanBO);
        }
        if (!boList.isEmpty()) {
            ConfigurationPlanBO bo = new ConfigurationPlanBO();
            PlanSonBO sonBO = new PlanSonBO();
            sonBO.setPolicyNum(policyNumSum);
            sonBO.setManNum(manNumSum);
            sonBO.setExecuteNum(executeNumSum);
            sonBO.setCompileCount(compileCountSum);
            sonBO.setDeployNum(deployNumSum);
            sonBO.setActualNum(actualNumSum);
            List<PlanSonBO> sons = new ArrayList<>();
            sons.add(sonBO);
            bo.setTotal(total);
            bo.setProportion(proportionSum + "%");
            bo.setSons(sons);
            bo.setStandard("1");
            boList.add(bo);
        }
        return boList;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        checkAddIdentity();
        PlanSon planSon = planSonSer.findById(id);
        if (planSon == null) {
            throw new SerException("该对象不存在");
        }
        String c_id = planSon.getConfigurationPlan().getId();
        planSonSer.delete(id);
        Set<String> set = new HashSet<>();
        List<PlanSon> planSons = planSonSer.findAll();
        for (PlanSon p : planSons) {
            set.add(p.getConfigurationPlan().getId());
        }
        if (!set.contains(c_id)) {
            super.remove(c_id);
        }
    }

    @Override
    public ConfigurationPlanBO findByID(String id) throws SerException {
        PlanSon son = planSonSer.findById(id);
        if (son == null) {
            throw new SerException("该对象不存在");
        }
        ConfigurationPlan entity = super.findById(son.getConfigurationPlan().getId());
        ConfigurationPlanBO bo = BeanTransform.copyProperties(entity, ConfigurationPlanBO.class);
        List<PlanSonBO> sonBOs = new ArrayList<>();
        sonBOs.add(BeanTransform.copyProperties(son, PlanSonBO.class));
        bo.setSons(sonBOs);
        return bo;
    }

    @Override
    public Long count(ConfigurationPlanDTO dto) throws SerException {
        if (super.count(dto) != 0) {
            return super.count(dto) + 1;
        }
        return super.count(dto);
    }

    private ConfigurationPlan find(String classify) throws SerException {
        ConfigurationPlanDTO dto = new ConfigurationPlanDTO();
        dto.getConditions().add(Restrict.eq("classify", classify));
        return super.findOne(dto);
    }
}