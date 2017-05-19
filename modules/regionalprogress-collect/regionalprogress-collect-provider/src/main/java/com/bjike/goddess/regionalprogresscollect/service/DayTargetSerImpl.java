package com.bjike.goddess.regionalprogresscollect.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractquotemanager.api.ContractNodeStandardAPI;
import com.bjike.goddess.contractquotemanager.bo.ContractNodeStandardBO;
import com.bjike.goddess.contractquotemanager.bo.FilterTO;
import com.bjike.goddess.regionalprogresscollect.bo.DayTargetBO;
import com.bjike.goddess.regionalprogresscollect.bo.ReferenceTargetBO;
import com.bjike.goddess.regionalprogresscollect.dto.DayTargetDTO;
import com.bjike.goddess.regionalprogresscollect.entity.DayTarget;
import com.bjike.goddess.regionalprogresscollect.to.DayTargetTO;
import com.bjike.goddess.regionalprogresscollect.to.FindTO;
import com.bjike.goddess.regionalprogresscollect.to.StandardTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 日指标业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-17 03:15 ]
 * @Description: [ 日指标业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "regionalprogresscollectSerCache")
@Service
public class DayTargetSerImpl extends ServiceImpl<DayTarget, DayTargetDTO> implements DayTargetSer {


    @Autowired
    private ContractNodeStandardAPI contractNodeStandardAPI;
    @Autowired
    private ReferenceTargetSer referenceTargetSer;

    private DayTargetBO transformBO(DayTarget entity) throws SerException {
        DayTargetBO bo = BeanTransform.copyProperties(entity, DayTargetBO.class);
        BeanTransform.copyProperties(entity.getTarget(), bo, true);
        bo.setTargetId(entity.getTarget().getId());
        return bo;
    }

    private List<DayTargetBO> transformBOList(List<DayTarget> list) throws SerException {
        List<DayTargetBO> bos = new ArrayList<>(0);
        for (DayTarget entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Override
    public DayTargetBO save(DayTargetTO to) throws SerException {
        DayTarget entity = BeanTransform.copyProperties(to, DayTarget.class, true);
        FindTO findTO = BeanTransform.copyProperties(to, FindTO.class);
        findTO.setYear(entity.getTargetDate().getYear());
        findTO.setMonth(entity.getTargetDate().getMonthValue());
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

    private void countTarget(DayTarget entity) throws SerException {
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
    public DayTargetBO update(DayTargetTO to) throws SerException {
        DayTarget entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        FindTO findTO = BeanTransform.copyProperties(to, FindTO.class);
        findTO.setYear(entity.getTargetDate().getYear());
        findTO.setMonth(entity.getTargetDate().getMonthValue());
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
    public DayTargetBO delete(String id) throws SerException {
        DayTarget entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public DayTargetBO getById(String id) throws SerException {
        DayTarget entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return this.transformBO(entity);
    }

    @Override
    public DayTargetBO updateStandard(StandardTO to) throws SerException {
        DayTarget entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        entity.setStandard(to.getStandard());
        this.countTarget(entity);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<DayTargetBO> maps(DayTargetDTO dto) throws SerException {
        List<DayTarget> list = super.findByPage(dto);
        return this.transformBOList(list);
    }

    @Override
    public Long getTotal() throws SerException {
        DayTargetDTO dto = new DayTargetDTO();
        return super.count(dto);
    }
}