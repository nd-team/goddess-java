package com.bjike.goddess.workprogress.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractquotemanager.api.ContractNodeStandardAPI;
import com.bjike.goddess.contractquotemanager.bo.ContractNodeStandardBO;
import com.bjike.goddess.contractquotemanager.dto.ContractNodeStandardDTO;
import com.bjike.goddess.contractquotemanager.to.FilterTO;
import com.bjike.goddess.workprogress.bo.ReferenceTargetBO;
import com.bjike.goddess.workprogress.bo.WeekTargetBO;
import com.bjike.goddess.workprogress.dto.WeekTargetDTO;
import com.bjike.goddess.workprogress.entity.WeekTarget;
import com.bjike.goddess.workprogress.enums.GuideAddrStatus;
import com.bjike.goddess.workprogress.to.FindTO;
import com.bjike.goddess.workprogress.to.GuidePermissionTO;
import com.bjike.goddess.workprogress.to.StandardTO;
import com.bjike.goddess.workprogress.to.WeekTargetTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 周指标业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-17 03:13 ]
 * @Description: [ 周指标业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "workprogressSerCache")
@Service
public class WeekTargetSerImpl extends ServiceImpl<WeekTarget, WeekTargetDTO> implements WeekTargetSer {

    @Autowired
    private ContractNodeStandardAPI contractNodeStandardAPI;
    @Autowired
    private ReferenceTargetSer referenceTargetSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    private WeekTargetBO transformBO(WeekTarget entity) throws SerException {
        WeekTargetBO bo = BeanTransform.copyProperties(entity, WeekTargetBO.class);
        BeanTransform.copyProperties(entity.getTarget(), bo, true);
        bo.setId(entity.getId());
        bo.setTargetId(entity.getTarget().getId());
        return bo;
    }

    private List<WeekTargetBO> transformBOList(List<WeekTarget> list) throws SerException {
        List<WeekTargetBO> bos = new ArrayList<>(0);
        for (WeekTarget entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }


    @Override
    public WeekTargetBO save(WeekTargetTO to) throws SerException {
        WeekTarget entity = BeanTransform.copyProperties(to, WeekTarget.class);
        FindTO findTO = BeanTransform.copyProperties(to, FindTO.class);
        ReferenceTargetBO bo = referenceTargetSer.findByTO(findTO);
        if (null == bo)
            throw new SerException("没有对应参考指标无法保存");
        List<ContractNodeStandardBO> contractNodeStandardBOs = contractNodeStandardAPI.findByTo(BeanTransform.copyProperties(findTO, FilterTO.class));
        entity.setTarget(referenceTargetSer.findById(bo.getId()));
        entity.setStandard(contractNodeStandardBOs.stream()
                .mapToInt(ContractNodeStandardBO::getNodeStandard)
                .sum() + 0d);
        this.countTarget(entity);
        super.save(entity);
        return this.transformBO(entity);
    }

    private void countTarget(WeekTarget entity) throws SerException {
        if (entity.getStandard() != 0) {
            entity.setTaskPlanTarget(this.decimal(entity.getTaskPlan() / entity.getStandard()));
            entity.setTaskActualTarget(this.decimal(entity.getTaskActual() / entity.getStandard()));
        } else {
            entity.setTaskPlanTarget(0d);
            entity.setTaskActualTarget(0d);
        }
        entity.setTaskPlanWarn(entity.getArtificialPlan() - entity.getTaskPlanTarget());
        entity.setArtificialPlanTarget(this.decimal(entity.getArtificialPlan() * entity.getStandard()));
        entity.setArtificialPlanWarn(entity.getArtificialPlan() - entity.getArtificialPlanTarget());
        entity.setTaskActualWarn(entity.getArtificialActual() - entity.getTaskActualTarget());
        entity.setArtificialActualTarget(this.decimal(entity.getArtificialActual() * entity.getStandard()));
        entity.setArtificialActualWarn(entity.getTaskActual() - entity.getArtificialActualTarget());
        entity.setUndone(entity.getTaskPlan() - entity.getTaskActual());
        entity.setRate(this.decimal(entity.getTaskActual() / entity.getArtificialActual()));
        entity.setDifference(entity.getRate() - entity.getStandard());
    }

    private Double decimal(Double number) throws SerException {
        return new BigDecimal(number).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public WeekTargetBO update(WeekTargetTO to) throws SerException {
        WeekTarget entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        FindTO findTO = BeanTransform.copyProperties(to, FindTO.class);
        ReferenceTargetBO bo = referenceTargetSer.findByTO(findTO);
        if (null == bo)
            throw new SerException("没有对应参考指标无法保存");
        List<ContractNodeStandardBO> contractNodeStandardBOs = contractNodeStandardAPI.findByTo(BeanTransform.copyProperties(findTO, FilterTO.class));
        entity.setTarget(referenceTargetSer.findById(bo.getId()));
        entity.setStandard(contractNodeStandardBOs.stream()
                .mapToInt(ContractNodeStandardBO::getNodeStandard)
                .sum() + 0d);
        this.countTarget(entity);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return this.transformBO(entity);
    }

    @Override
    public WeekTargetBO delete(String id) throws SerException {
        WeekTarget entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public WeekTargetBO getById(String id) throws SerException {
        WeekTarget entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return this.transformBO(entity);
    }

    @Override
    public WeekTargetBO updateStandard(StandardTO to) throws SerException {
        WeekTarget entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        entity.setStandard(to.getStandard());
        this.countTarget(entity);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<WeekTargetBO> maps(WeekTargetDTO dto) throws SerException {
        List<WeekTarget> list = super.findByPage(dto);
        return this.transformBOList(list);
    }

    @Override
    public Long getTotal() throws SerException {
        WeekTargetDTO dto = new WeekTargetDTO();
        return super.count(dto);
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
        return flag;
    }

    @Override
    public List<Integer> getStandard() throws SerException {
        ContractNodeStandardDTO dto = new ContractNodeStandardDTO();
        List<ContractNodeStandardBO> boList = contractNodeStandardAPI.list(dto);
        List<Integer> stringList = new ArrayList<>();
        if(null != boList && boList.size() > 0 ){
            for(ContractNodeStandardBO bo : boList){
                stringList.add(bo.getNodeStandard());
            }
        }
        return stringList;
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
}