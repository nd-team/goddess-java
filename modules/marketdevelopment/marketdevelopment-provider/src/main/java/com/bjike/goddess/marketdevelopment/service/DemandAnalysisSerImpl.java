package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.bo.DemandAnalysisBO;
import com.bjike.goddess.marketdevelopment.dto.DemandAnalysisDTO;
import com.bjike.goddess.marketdevelopment.entity.DemandAnalysis;
import com.bjike.goddess.marketdevelopment.to.DemandAnalysisTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 市场需求分析业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:10 ]
 * @Description: [ 市场需求分析业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class DemandAnalysisSerImpl extends ServiceImpl<DemandAnalysis, DemandAnalysisDTO> implements DemandAnalysisSer {

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DemandAnalysisBO save(DemandAnalysisTO to) throws SerException {
        DemandAnalysis entity = BeanTransform.copyProperties(to, DemandAnalysis.class);
        super.save(entity);
        return BeanTransform.copyProperties(entity, DemandAnalysisBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DemandAnalysisBO update(DemandAnalysisTO to) throws SerException {
        DemandAnalysis entity = BeanTransform.copyProperties(to, DemandAnalysis.class);
        super.update(entity);
        return BeanTransform.copyProperties(entity, DemandAnalysisBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DemandAnalysisBO delete(DemandAnalysisTO to) throws SerException {
        DemandAnalysis entity = super.findById(to.getId());
        super.remove(entity);
        return BeanTransform.copyProperties(entity, DemandAnalysisBO.class);
    }

    @Override
    public List<DemandAnalysisBO> findByType(String type) throws SerException {
        DemandAnalysisDTO dto = new DemandAnalysisDTO();
        dto.getConditions().add(Restrict.eq("type", type));
        List<DemandAnalysis> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, DemandAnalysisBO.class);
    }

    @Override
    public List<DemandAnalysisBO> findByCourse(String course) throws SerException {
        DemandAnalysisDTO dto = new DemandAnalysisDTO();
        dto.getConditions().add(Restrict.eq("course", course));
        List<DemandAnalysis> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, DemandAnalysisBO.class);
    }

    @Override
    public List<DemandAnalysisBO> findByCourseType(String type, String course) throws SerException {
        DemandAnalysisDTO dto = new DemandAnalysisDTO();
        dto.getConditions().add(Restrict.eq("course", course));
        dto.getConditions().add(Restrict.eq("type", type));
        List<DemandAnalysis> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, DemandAnalysisBO.class);
    }
}