package com.bjike.goddess.coststandard.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.coststandard.bo.CostStandardBO;
import com.bjike.goddess.coststandard.bo.CostStandardOpinionBO;
import com.bjike.goddess.coststandard.dto.CostStandardDTO;
import com.bjike.goddess.coststandard.entity.CostStandard;
import com.bjike.goddess.coststandard.to.CostStandardTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 费用标准业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-24 11:22 ]
 * @Description: [ 费用标准业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "coststandardSerCache")
@Service
public class CostStandardSerImpl extends ServiceImpl<CostStandard, CostStandardDTO> implements CostStandardSer {

    @Override
    public CostStandardBO save(CostStandardTO to) throws SerException {
        CostStandard entity = BeanTransform.copyProperties(to, CostStandard.class, true);
        entity.setStatus(Status.THAW);
        entity.setDraftDate(LocalDate.now());
        super.save(entity);
        return BeanTransform.copyProperties(entity, CostStandardBO.class);
    }

    @Override
    public CostStandardBO update(CostStandardTO to) throws SerException {
        CostStandard entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, CostStandardBO.class);
    }

    @Override
    public CostStandardBO delete(String id) throws SerException {
        CostStandard entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, CostStandardBO.class);
    }

    @Override
    public CostStandardBO congeal(String id) throws SerException {
        CostStandard entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return BeanTransform.copyProperties(entity, CostStandardBO.class);
    }

    @Override
    public CostStandardBO thaw(String id) throws SerException {
        CostStandard entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        entity.setStatus(Status.THAW);
        super.update(entity);
        return BeanTransform.copyProperties(entity, CostStandardBO.class);
    }

    @Override
    public CostStandardBO getById(String id) throws SerException {
        CostStandard entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        return BeanTransform.copyProperties(entity, CostStandardBO.class);
    }

    @Override
    public List<CostStandardBO> maps(CostStandardDTO dto) throws SerException {
        dto.getSorts().add("status=asc");
        return BeanTransform.copyProperties(super.findByPage(dto), CostStandardBO.class);
    }

    @Override
    public List<CostStandardBO> findThaw() throws SerException {
        CostStandardDTO dto = new CostStandardDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        return BeanTransform.copyProperties(super.findByCis(dto), CostStandardBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        CostStandardDTO dto = new CostStandardDTO();
        return super.count(dto);
    }

    @Override
    public List<CostStandardOpinionBO> findOpinion() throws SerException {
        List<CostStandardOpinionBO> bos = new ArrayList<>(0);
        List<CostStandardBO> list = this.findThaw();
        String format = "名称:%s 地区:%s 适用部门:%s 标准:%s";
        for (CostStandardBO bo : list) {
            CostStandardOpinionBO opinionBO = new CostStandardOpinionBO();
            opinionBO.setId(bo.getId());
            opinionBO.setValue(String.format(format, bo.getName(), bo.getArea(), bo.getDepartment(), bo.getStandard()));
            bos.add(opinionBO);
        }
        return bos;
    }
}