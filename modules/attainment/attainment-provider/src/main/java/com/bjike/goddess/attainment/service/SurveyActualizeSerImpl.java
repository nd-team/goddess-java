package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.SurveyActualizeBO;
import com.bjike.goddess.attainment.dto.SurveyActualizeDTO;
import com.bjike.goddess.attainment.entity.SurveyActualize;
import com.bjike.goddess.attainment.enums.SurveyStatus;
import com.bjike.goddess.attainment.to.SurveyActualizeTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 调研实施记录业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:58 ]
 * @Description: [ 调研实施记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attainmentSerCache")
@Service
public class SurveyActualizeSerImpl extends ServiceImpl<SurveyActualize, SurveyActualizeDTO> implements SurveyActualizeSer {

    @Autowired
    private SurveyPlanSer surveyPlanSer;

    private SurveyActualizeBO transformBO(SurveyActualize entity) throws SerException {
        SurveyActualizeBO bo = BeanTransform.copyProperties(surveyPlanSer.findBOById(entity.getPlan().getId()), SurveyActualizeBO.class);
        bo.setPlan_id(entity.getPlan().getId());
        bo.setStart(bo.getStartTime());
        bo.setEnd(bo.getEndTime());
        bo.setFinish(bo.getFinishTime());
        BeanTransform.copyProperties(entity, bo, true);
        return bo;
    }

    private List<SurveyActualizeBO> transformBOList(List<SurveyActualize> list) throws SerException {
        List<SurveyActualizeBO> bos = new ArrayList<>(0);
        for (SurveyActualize entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyActualizeBO save(SurveyActualizeTO to) throws SerException {
        SurveyActualize entity = BeanTransform.copyProperties(to, SurveyActualize.class, true);
        entity.setPlan(surveyPlanSer.findById(to.getPlan_id()));
        if (null == entity.getPlan())
            throw new SerException("调研计划不存在,无法保存");
        entity.setStartTime(LocalDateTime.now());
        entity.setSurvey(SurveyStatus.UNDERWAY);
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyActualizeBO update(SurveyActualizeTO to) throws SerException {
        SurveyActualize entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setPlan(surveyPlanSer.findById(to.getPlan_id()));
        if (null == entity.getPlan())
            throw new SerException("调研计划不存在,无法保存");
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyActualizeBO delete(String id) throws SerException {
        SurveyActualize entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyActualizeBO over(String id) throws SerException {
        SurveyActualize entity = super.findById(id);
        entity.setSurvey(SurveyStatus.FINISH);
        entity.setEndTime(LocalDateTime.now());
        super.update(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<SurveyActualizeBO> maps(SurveyActualizeDTO dto) throws SerException {
        dto.getSorts().add("startTime=desc");
        return this.transformBOList(super.findByPage(dto));
    }

    @Override
    public SurveyActualizeBO getById(String id) throws SerException {
        SurveyActualize entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        return this.transformBO(entity);
    }

    @Override
    public Long getTotal() throws SerException {
        SurveyActualizeDTO dto = new SurveyActualizeDTO();
        return super.count(dto);
    }
}