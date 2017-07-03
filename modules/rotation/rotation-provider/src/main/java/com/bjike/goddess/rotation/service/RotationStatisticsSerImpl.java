package com.bjike.goddess.rotation.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rotation.bo.CoverRotationBO;
import com.bjike.goddess.rotation.bo.RecommendRotationBO;
import com.bjike.goddess.rotation.bo.RotationStatisticsBO;
import com.bjike.goddess.rotation.dto.RotationStatisticsDTO;
import com.bjike.goddess.rotation.entity.RotationStatistics;
import com.bjike.goddess.rotation.enums.AuditType;
import com.bjike.goddess.rotation.to.RotationStatisticsTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 岗位轮换统计业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:38 ]
 * @Description: [ 岗位轮换统计业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "rotationSerCache")
@Service
public class RotationStatisticsSerImpl extends ServiceImpl<RotationStatistics, RotationStatisticsDTO> implements RotationStatisticsSer {

    @Autowired
    private SubsidyStandardSer subsidyStandardSer;
    @Autowired
    private CoverRotationSer coverRotationSer;
    @Autowired
    private RecommendRotationSer recommendRotationSer;

    private RotationStatisticsBO transformBO(RotationStatistics entity) throws SerException {
        RotationStatisticsBO bo = BeanTransform.copyProperties(entity, RotationStatisticsBO.class);
        bo.setArrangementName(entity.getArrangement().getArrangement());
        bo.setArrangementId(entity.getArrangement().getId());
        return bo;
    }

    private List<RotationStatisticsBO> transformBOList(List<RotationStatistics> list) throws SerException {
        List<RotationStatisticsBO> bos = new ArrayList<>(0);
        for (RotationStatistics entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Override
    public RotationStatisticsBO save(RotationStatisticsTO to) throws SerException {
        RotationStatistics entity = BeanTransform.copyProperties(to, RotationStatistics.class, true);
        entity.setArrangement(subsidyStandardSer.findById(to.getArrangementId()));
        if (null == entity.getArrangement())
            throw new SerException("选择的岗位层级不存在");
        this.countStatistics(entity);
        super.save(entity);
        return this.transformBO(entity);
    }

    private void countStatistics(RotationStatistics entity) throws SerException {
        List<CoverRotationBO> coverBOs = coverRotationSer.findByUserArrangement(entity.getUsername(), entity.getArrangement().getId()).stream()
                .filter(c -> c.getAudit() == AuditType.ALLOWED && StringUtils.isNotBlank(c.getRotationDate()))
                .sorted(Comparator.comparing(CoverRotationBO::getRotationDate).reversed())
                .collect(Collectors.toList());

        List<RecommendRotationBO> recommendBOs = recommendRotationSer.findByUserArrangement(entity.getUsername(), entity.getArrangement().getId()).stream()
                .filter(r -> r.getAudit() == AuditType.ALLOWED && StringUtils.isNotBlank(r.getRotationDate()))
                .sorted(Comparator.comparing(RecommendRotationBO::getRotationDate).reversed())
                .collect(Collectors.toList());

        if (coverBOs.size() == 0 && recommendBOs.size() == 0)
            throw new SerException("该用户并没有当前选择层级数据");

        String startTime = entity.getOccupyStart().toString();
        Boolean check = false;

        for (CoverRotationBO bo : coverBOs)
            if (bo.getRotationDate().equals(startTime)) {
                check = true;
                break;
            }
        if (!check)
            for (RecommendRotationBO bo : recommendBOs)
                if (bo.getRotationDate().equals(startTime)) {
                    check = true;
                    break;
                }
        if (!check)
            throw new SerException(String.format("并没有当前用户在%s开始轮岗%s记录", startTime, entity.getArrangement().getArrangement()));

        Long cycle = entity.getSubsidyStart().until(entity.getSubsidyEnd(), ChronoUnit.MONTHS);
        entity.setCycle(cycle.intValue());
        if (entity.getSubsidy() > entity.getCycle())
            throw new SerException(String.format("周期内补贴天数不能大于%d", entity.getCycle()));
    }

    @Override
    public RotationStatisticsBO update(RotationStatisticsTO to) throws SerException {
        RotationStatistics entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setArrangement(subsidyStandardSer.findById(to.getArrangementId()));
        if (null == entity.getArrangement())
            throw new SerException("选择的岗位层级不存在");
        this.countStatistics(entity);
        super.update(entity);
        return this.transformBO(entity);
    }

    @Override
    public RotationStatisticsBO delete(String id) throws SerException {
        RotationStatistics entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        super.remove(entity);
        return null;
    }

    @Override
    public RotationStatisticsBO getById(String id) throws SerException {
        RotationStatistics entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return this.transformBO(entity);
    }

    @Override
    public List<RotationStatisticsBO> maps(RotationStatisticsDTO dto) throws SerException {
        dto.getSorts().add("occupyStart=desc");
        dto.getSorts().add("username=desc");
        return this.transformBOList(super.findByPage(dto));
    }

    @Override
    public Long getTotal() throws SerException {
        RotationStatisticsDTO dto = new RotationStatisticsDTO();
        return super.count(dto);
    }
}