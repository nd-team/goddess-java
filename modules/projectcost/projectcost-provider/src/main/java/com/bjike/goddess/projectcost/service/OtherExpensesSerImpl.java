package com.bjike.goddess.projectcost.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectcost.bo.OtherExpensesBO;
import com.bjike.goddess.projectcost.dto.OtherExpensesDTO;
import com.bjike.goddess.projectcost.entity.OtherExpenses;
import com.bjike.goddess.projectcost.to.FindTO;
import com.bjike.goddess.projectcost.to.OtherExpensesTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 其他费用业务实现
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-04 05:02 ]
 * @Description: [ 其他费用业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectcostSerCache")
@Service
public class OtherExpensesSerImpl extends ServiceImpl<OtherExpenses, OtherExpensesDTO> implements OtherExpensesSer {

    private static final String type = "项目上";

    private void countExpenses(OtherExpenses entity) throws SerException {
        entity.setRatio(new BigDecimal(entity.getTarget() / entity.getActual() * 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
        entity.setBalance(entity.getTarget() - entity.getActual());
    }

    @Override
    public OtherExpensesBO save(OtherExpensesTO to) throws SerException {
        OtherExpenses entity = new OtherExpenses();
        entity.setType(type);
        this.countExpenses(entity);
        super.save(entity);
        return BeanTransform.copyProperties(entity, OtherExpensesBO.class);
    }

    @Override
    public OtherExpensesBO update(OtherExpensesTO to) throws SerException {
        OtherExpenses entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        this.countExpenses(entity);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, OtherExpensesBO.class);
    }

    @Override
    public OtherExpensesBO delete(String id) throws SerException {
        OtherExpenses entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, OtherExpensesBO.class);
    }

    @Override
    public List<OtherExpensesBO> maps(OtherExpensesDTO dto) throws SerException {
        dto.getSorts().add("year=desc");
        dto.getSorts().add("month=desc");
        dto.getSorts().add("area=desc");
        dto.getSorts().add("project=desc");
        dto.getSorts().add("name=desc");
        return BeanTransform.copyProperties(super.findByPage(dto), OtherExpensesBO.class);
    }

    @Override
    public OtherExpensesBO getById(String id) throws SerException {
        OtherExpenses entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return BeanTransform.copyProperties(entity, OtherExpensesBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        OtherExpensesDTO dto = new OtherExpensesDTO();
        return super.count(dto);
    }

    @Override
    public List<OtherExpensesBO> findByTO(FindTO to) throws SerException {
        OtherExpensesDTO dto = new OtherExpensesDTO();
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
        return BeanTransform.copyProperties(super.findByCis(dto), OtherExpensesBO.class);
    }
}