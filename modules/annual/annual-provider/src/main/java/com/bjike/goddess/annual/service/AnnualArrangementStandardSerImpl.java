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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
        ArrangementBO arrangementBO = arrangementAPI.findById(entity.getArrangementId());
        AnnualStandard annualStandard = entity.getStandard();
        bo.setArrangement(arrangementBO.getArrangement());
        bo.setStartCycle(annualStandard.getStartCycle());
        bo.setEndCycle(annualStandard.getEndCycle());
        bo.setStandardId(annualStandard.getId());
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
        AnnualArrangementStandardBO bo = this.findByArrangementStandard(to.getStandardId(), to.getArrangementId());
        AnnualArrangementStandard entity;
        if (bo == null) {
            entity = BeanTransform.copyProperties(to, AnnualArrangementStandard.class);
            entity.setStandard(annualStandardSer.findById(to.getStandardId()));
            if (null == entity.getStandard())
                throw new SerException("年假标准为空,无法保存");
            super.save(entity);
        } else {
            entity = super.findById(bo.getId());
            BeanTransform.copyProperties(to, entity, true);
            entity.setStandard(annualStandardSer.findById(to.getArrangementId()));
            if (null == entity.getStandard())
                throw new SerException("年假标准为空,无法保存");
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
        }
        return this.transformBO(entity);
    }

    @Override
    public AnnualArrangementStandardBO findByArrangementStandard(String standardId, String arrangementId) throws SerException {
        if (StringUtils.isBlank(standardId) && StringUtils.isBlank(arrangementId))
            return new AnnualArrangementStandardBO();
        AnnualArrangementStandardDTO dto = new AnnualArrangementStandardDTO();
        dto.getConditions().add(Restrict.eq("standard.id", standardId));
        dto.getConditions().add(Restrict.eq("arrangementId", arrangementId));
        AnnualArrangementStandard entity = super.findOne(dto);
        if (null == entity)
            return null;
        return this.transformBO(entity);
    }

    @Override
    public List<AnnualArrangementStandardBO> findByStandard(String standardId) throws SerException {
        if (StringUtils.isBlank(standardId))
            return new ArrayList<>(0);
        AnnualArrangementStandardDTO dto = new AnnualArrangementStandardDTO();
        dto.getConditions().add(Restrict.eq("standard.id", standardId));
        List<AnnualArrangementStandard> list = super.findByCis(dto);
        return this.transformBOList(list);
    }

    @Override
    public List<AnnualArrangementStandardBO> maps(AnnualArrangementStandardDTO dto) throws SerException {
        dto.getSorts().add("standard.id");
        dto.getSorts().add("arrangementId");
        List<AnnualArrangementStandard> list = super.findByPage(dto);
        return this.transformBOList(list);
    }

    @Override
    public AnnualArrangementStandardBO getById(String id) throws SerException {
        AnnualArrangementStandard entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        return this.transformBO(entity);
    }

    @Override
    public Long getTotal() throws SerException {
        AnnualArrangementStandardDTO dto = new AnnualArrangementStandardDTO();
        return super.count(dto);
    }
}