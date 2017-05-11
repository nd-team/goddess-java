package com.bjike.goddess.projectcost.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectcost.bo.ArtificialCostBO;
import com.bjike.goddess.projectcost.dto.ArtificialCostDTO;
import com.bjike.goddess.projectcost.entity.ArtificialCost;
import com.bjike.goddess.projectcost.to.ArtificialCostTO;
import com.bjike.goddess.projectcost.to.FindTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 人工费用业务实现
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-04 05:19 ]
 * @Description: [ 人工费用业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectcostSerCache")
@Service
public class ArtificialCostSerImpl extends ServiceImpl<ArtificialCost, ArtificialCostDTO> implements ArtificialCostSer {

    private ArtificialCostBO transformBO(ArtificialCost entity) throws SerException {
        ArtificialCostBO bo = BeanTransform.copyProperties(entity, ArtificialCostBO.class);
        FindTO to = BeanTransform.copyProperties(entity, FindTO.class);
        List<ArtificialCost> list = this.findAsTO(to);
        bo.setArtificial(this.decimal(list.stream().mapToDouble(ArtificialCost::getActualCost).sum() / entity.getActualCost()));
        return bo;
    }

    private List<ArtificialCostBO> transformBOList(List<ArtificialCost> list) throws SerException {
        List<ArtificialCostBO> bos = new ArrayList<>(list.size());
        for (ArtificialCost entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Override
    public ArtificialCostBO save(ArtificialCostTO to) throws SerException {
        ArtificialCost entity = BeanTransform.copyProperties(to, ArtificialCost.class);
        entity.setActualHour(0d);//@TODO 统计任务分配对应工时
        this.countCost(entity);
        super.save(entity);
        return this.transformBO(entity);
    }

    private void countCost(ArtificialCost entity) throws SerException {
        entity.setTargetCost(this.decimal(entity.getUnivalent() * entity.getTargetHour()));
        entity.setActualCost(this.decimal(entity.getUnivalent() * entity.getActualHour()));
        entity.setProportion(this.decimal(entity.getActualCost() / entity.getTargetCost()));
    }

    private Double decimal(Double number) throws SerException {
        return new BigDecimal(number).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public ArtificialCostBO update(ArtificialCostTO to) throws SerException {
        ArtificialCost entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据对象不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setActualHour(0d);//@TODO 统计任务分配对应工时
        this.countCost(entity);
        super.update(entity);
        return this.transformBO(entity);
    }

    @Override
    public ArtificialCostBO delete(String id) throws SerException {
        ArtificialCost entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, ArtificialCostBO.class);
    }

    @Override
    public List<ArtificialCostBO> maps(ArtificialCostDTO dto) throws SerException {
        dto.getSorts().add("year=desc");
        dto.getSorts().add("month=desc");
        dto.getSorts().add("area=desc");
        dto.getSorts().add("project=desc");
        dto.getSorts().add("name=desc");
        return this.transformBOList(super.findByPage(dto));
    }

    @Override
    public ArtificialCostBO updateActual(ArtificialCostTO to) throws SerException {
        ArtificialCost entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据对象不存在");
        BeanTransform.copyProperties(to, entity, true);
        this.countCost(entity);
        super.update(entity);
        return this.transformBO(entity);
    }

    @Override
    public ArtificialCostBO getById(String id) throws SerException {
        ArtificialCost entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不存在");
        return this.transformBO(entity);
    }

    @Override
    public Long getTotal() throws SerException {
        ArtificialCostDTO dto = new ArtificialCostDTO();
        return super.count(dto);
    }

    private List<ArtificialCost> findAsTO(FindTO to) throws SerException {
        ArtificialCostDTO dto = new ArtificialCostDTO();
        if (StringUtils.isNotBlank(to.getArea()))
            dto.getConditions().add(Restrict.eq("area", to.getArea()));
        if (StringUtils.isNotBlank(to.getProject()))
            dto.getConditions().add(Restrict.eq("project", to.getProject()));
        if (StringUtils.isNotBlank(to.getName()))
            dto.getConditions().add(Restrict.eq("name", to.getName()));
        if (null != to.getMonth())
            dto.getConditions().add(Restrict.eq("month", to.getMonth()));
        if (null != to.getYear())
            dto.getConditions().add(Restrict.eq("year", to.getYear()));
        return super.findByCis(dto);

    }

    @Override
    public List<ArtificialCostBO> findByTO(FindTO to) throws SerException {
        return this.transformBOList(this.findAsTO(to));
    }
}