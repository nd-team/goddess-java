package com.bjike.goddess.projectroyalty.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectroyalty.bo.TargetAuotaBO;
import com.bjike.goddess.projectroyalty.dto.TargetAuotaDTO;
import com.bjike.goddess.projectroyalty.entity.TargetAuota;
import com.bjike.goddess.projectroyalty.entity.WeightAllocation;
import com.bjike.goddess.projectroyalty.to.TargetAuotaTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目提成目标定额业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:27 ]
 * @Description: [ 项目提成目标定额业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectroyaltySerCache")
@Service
public class TargetAuotaSerImpl extends ServiceImpl<TargetAuota, TargetAuotaDTO> implements TargetAuotaSer {

    @Autowired
    private WeightAllocationSer weightAllocationSer;

    private TargetAuotaBO transform(TargetAuota entity) throws SerException {
        TargetAuotaBO bo = BeanTransform.copyProperties(entity, TargetAuotaBO.class);
        bo.setAllocationId(entity.getAllocation().getId());
        bo.setArea(entity.getAllocation().getArea());
        bo.setProject(entity.getAllocation().getProject());
        return bo;
    }

    private List<TargetAuotaBO> transformList(List<TargetAuota> list) throws SerException {
        List<TargetAuotaBO> bos = new ArrayList<>(0);
        for (TargetAuota entity : list)
            bos.add(this.transform(entity));
        return bos;
    }

    @Override
    public TargetAuotaBO targetSave(TargetAuotaTO to) throws SerException {
        TargetAuota entity = BeanTransform.copyProperties(to, TargetAuota.class);
        entity.setAllocation(weightAllocationSer.findById(to.getAllocationId()));
        if (null == entity.getAllocation())
            throw new SerException("所选的权重分配不存在");
        this.countAuota(entity);
        entity.setPlan(Boolean.TRUE);
        super.save(entity);
        return this.transform(entity);
    }

    @Override
    public TargetAuotaBO actualSave(TargetAuotaTO to) throws SerException {
        TargetAuota entity = BeanTransform.copyProperties(to, TargetAuota.class);
        entity.setAllocation(weightAllocationSer.findById(to.getAllocationId()));
        if (null == entity.getAllocation())
            throw new SerException("所选的权重分配不存在");
        this.countAuota(entity);
        entity.setPlan(Boolean.FALSE);
        super.save(entity);
        return this.transform(entity);
    }

    private void countAuota(TargetAuota entity) throws SerException {
        WeightAllocation allocation = entity.getAllocation();
        Double money = allocation.getProfit();
        entity.setBusiness(this.decimal(money * allocation.getBusiness() / 100));
        entity.setManage(this.decimal(money * allocation.getManage() / 100));
        entity.setCapital(this.decimal(money * allocation.getCapital() / 100));
        entity.setStaff(this.decimal(money * allocation.getStaff() / 100));
        entity.setRisk(this.decimal(money * allocation.getRisk() / 100));
        entity.setProfit(this.decimal(money * allocation.getProfit() / 100));
    }

    private Double decimal(double number) {
        return new BigDecimal(number).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public TargetAuotaBO update(TargetAuotaTO to) throws SerException {
        TargetAuota entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setAllocation(weightAllocationSer.findById(to.getAllocationId()));
        if (null == entity.getAllocation())
            throw new SerException("所选的权重分配不存在");
        this.countAuota(entity);
        super.update(entity);
        return this.transform(entity);
    }

    @Override
    public TargetAuotaBO delete(String id) throws SerException {
        TargetAuota entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        super.remove(entity);
        return this.transform(entity);
    }

    @Override
    public TargetAuotaBO getById(String id) throws SerException {
        TargetAuota entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        return this.transform(entity);
    }

    @Override
    public List<TargetAuotaBO> targetMaps(TargetAuotaDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("plan", !Boolean.TRUE));
        dto.getSorts().add("createTime=desc");
        return this.transformList(super.findByPage(dto));
    }

    @Override
    public List<TargetAuotaBO> actualMaps(TargetAuotaDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("plan", !Boolean.FALSE));
        dto.getSorts().add("createTime=desc");
        return this.transformList(super.findByPage(dto));
    }

    @Override
    public Long getTargetTotal() throws SerException {
        TargetAuotaDTO dto = new TargetAuotaDTO();
        dto.getConditions().add(Restrict.eq("plan", !Boolean.TRUE));
        return super.count(dto);
    }

    @Override
    public Long getActualTotal() throws SerException {
        TargetAuotaDTO dto = new TargetAuotaDTO();
        dto.getConditions().add(Restrict.eq("plan", !Boolean.FALSE));
        return super.count(dto);
    }
}