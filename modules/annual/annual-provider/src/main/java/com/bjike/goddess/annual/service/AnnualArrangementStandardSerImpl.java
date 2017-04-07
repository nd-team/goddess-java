package com.bjike.goddess.annual.service;

import com.bjike.goddess.annual.bo.AnnualArrangementStandardBO;
import com.bjike.goddess.annual.dto.AnnualArrangementStandardDTO;
import com.bjike.goddess.annual.entity.AnnualArrangementStandard;
import com.bjike.goddess.annual.entity.AnnualStandard;
import com.bjike.goddess.annual.to.AnnualArrangementStandardTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.ArrangementAPI;
import com.bjike.goddess.organize.bo.ArrangementBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 年假层级标准业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:33 ]
 * @Description: [ 年假层级标准业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "annualSerCache")
@Service
public class AnnualArrangementStandardSerImpl extends ServiceImpl<AnnualArrangementStandard, AnnualArrangementStandardDTO> implements AnnualArrangementStandardSer {

    @Autowired
    private ArrangementAPI arrangementAPI;
    @Autowired
    private AnnualStandardSer annualStandardSer;

    /**
     * 转换年假层级标准传输对象
     *
     * @param entity 年假层级标准实体数据
     * @return
     * @throws SerException
     */
    private AnnualArrangementStandardBO transformBO(AnnualArrangementStandard entity) throws SerException {
        AnnualArrangementStandardBO bo = BeanTransform.copyProperties(entity, AnnualArrangementStandardBO.class);
        ArrangementBO arrangementBO = arrangementAPI.findById(entity.getArrangement_id());
        AnnualStandard annualStandard = entity.getStandard();
        bo.setArrangement(arrangementBO.getArrangement());
        bo.setStartCycle(annualStandard.getStartCycle());
        bo.setEndCycle(annualStandard.getEndCycle());
        bo.setStandard_id(annualStandard.getId());
        bo.setAstrict(annualStandard.getAstrict());
        bo.setRemark(annualStandard.getRemark());
        return bo;
    }

    /**
     * 转换年假层级标准传输对象集合
     *
     * @param list 年假层级标准实体数据集合
     * @return
     * @throws SerException
     */
    private List<AnnualArrangementStandardBO> transformBOList(List<AnnualArrangementStandard> list) throws SerException {
        List<AnnualArrangementStandardBO> bos = new ArrayList<>(list.size());
        for (AnnualArrangementStandard entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AnnualArrangementStandardBO update(AnnualArrangementStandardTO to) throws SerException {
        AnnualArrangementStandardBO bo = this.findByArrangementStandard(to.getStandard_id(), to.getArrangement_id());
        AnnualArrangementStandard entity;
        if (bo == null) {
            entity = BeanTransform.copyProperties(to, AnnualArrangementStandard.class);
            entity.setStandard(annualStandardSer.findById(to.getStandard_id()));
            super.save(entity);
        } else {
            entity = BeanTransform.copyProperties(to, AnnualArrangementStandard.class);
            entity.setStandard(annualStandardSer.findById(to.getArrangement_id()));
            entity.setId(bo.getId());
            super.update(entity);
        }
        return this.transformBO(entity);
    }

    @Override
    public AnnualArrangementStandardBO findByArrangementStandard(String standard_id, String arrangement_id) throws SerException {
        AnnualArrangementStandardDTO dto = new AnnualArrangementStandardDTO();
        dto.getConditions().add(Restrict.eq("standard.id", standard_id));
        dto.getConditions().add(Restrict.eq("arrangement_id", arrangement_id));
        AnnualArrangementStandard entity = super.findOne(dto);
        if (null == entity)
            return null;
        return this.transformBO(entity);
    }

    @Override
    public List<AnnualArrangementStandardBO> findByStandard(String standard_id) throws SerException {
        AnnualArrangementStandardDTO dto = new AnnualArrangementStandardDTO();
        dto.getConditions().add(Restrict.eq("standard.id", standard_id));
        List<AnnualArrangementStandard> list = super.findByCis(dto);
        return this.transformBOList(list);
    }

    @Override
    public List<AnnualArrangementStandardBO> maps(AnnualArrangementStandardDTO dto) throws SerException {
        dto.getSorts().add("standard.id");
        dto.getSorts().add("arrangement_id");
        List<AnnualArrangementStandard> list = super.findByPage(dto);
        return this.transformBOList(list);
    }
}