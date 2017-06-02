package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.bo.DemandAnalysisBO;
import com.bjike.goddess.marketdevelopment.dto.DemandAnalysisDTO;
import com.bjike.goddess.marketdevelopment.entity.DemandAnalysis;
import com.bjike.goddess.marketdevelopment.to.DemandAnalysisTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Autowired
    private MarPermissionSer marPermissionSer;

    private static final String marketCheck = "market-check";

    private static final String marketManage = "market-manage";

    private static final String demandManage = "demand-manage";

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DemandAnalysisBO save(DemandAnalysisTO to) throws SerException {
        if (!marPermissionSer.getMarPermission(marketManage) && !marPermissionSer.getMarPermission(demandManage))
            throw new SerException("您的帐号没有权限");
        DemandAnalysis entity = BeanTransform.copyProperties(to, DemandAnalysis.class);
        super.save(entity);
        return BeanTransform.copyProperties(entity, DemandAnalysisBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DemandAnalysisBO update(DemandAnalysisTO to) throws SerException {
        if (!marPermissionSer.getMarPermission(marketManage) && !marPermissionSer.getMarPermission(demandManage))
            throw new SerException("您的帐号没有权限");
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                DemandAnalysis entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                super.update(entity);
                return BeanTransform.copyProperties(entity, DemandAnalysisBO.class);
            } catch (Exception e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DemandAnalysisBO delete(DemandAnalysisTO to) throws SerException {
        if (!marPermissionSer.getMarPermission(marketManage) && !marPermissionSer.getMarPermission(demandManage))
            throw new SerException("您的帐号没有权限");
        DemandAnalysis entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
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

    @Override
    public List<DemandAnalysis> findByPage(DemandAnalysisDTO dto) throws SerException {
        if (!marPermissionSer.getMarPermission(marketManage) && !marPermissionSer.getMarPermission(demandManage) && !marPermissionSer.getMarPermission(marketCheck))
            throw new SerException("您的帐号没有权限");
        return super.findByPage(dto);
    }
}