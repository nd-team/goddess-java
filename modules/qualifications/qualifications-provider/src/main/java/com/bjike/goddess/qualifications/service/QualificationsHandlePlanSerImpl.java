package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.bo.QualificationsHandlePlanBO;
import com.bjike.goddess.qualifications.dto.QualificationsHandlePlanDTO;
import com.bjike.goddess.qualifications.entity.QualificationsHandlePlan;
import com.bjike.goddess.qualifications.to.QualificationsHandlePlanTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 资质办理计划业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:46 ]
 * @Description: [ 资质办理计划业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "qualificationsSerCache")
@Service
public class QualificationsHandlePlanSerImpl extends ServiceImpl<QualificationsHandlePlan, QualificationsHandlePlanDTO> implements QualificationsHandlePlanSer {

    @Autowired
    private QualificationsHandleSer handleSer;
    @Autowired
    private HandlePlanStageSer handlePlanStageSer;

    @Transactional(rollbackFor = SerException.class)

    private QualificationsHandlePlanBO transformBO(QualificationsHandlePlan entity) throws SerException {
        QualificationsHandlePlanBO bo = BeanTransform.copyProperties(entity, QualificationsHandlePlanBO.class);
        bo.setHandleId(entity.getHandle().getId());
        return bo;
    }

    private List<QualificationsHandlePlanBO> transformBOList(List<QualificationsHandlePlan> list) throws SerException {
        List<QualificationsHandlePlanBO> bos = new ArrayList<>(0);
        for (QualificationsHandlePlan entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Override
    public QualificationsHandlePlanBO save(QualificationsHandlePlanTO to) throws SerException {
        QualificationsHandlePlan entity = BeanTransform.copyProperties(to, QualificationsHandlePlan.class, true);
        entity.setHandle(handleSer.findById(to.getHandleId()));
        if (null == entity.getHandle())
            throw new SerException("资质办理数据为空");
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QualificationsHandlePlanBO update(QualificationsHandlePlanTO to) throws SerException {
        QualificationsHandlePlan entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        entity.setHandle(handleSer.findById(to.getHandleId()));
        if (null == entity.getHandle())
            throw new SerException("资质办理数据为空");
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QualificationsHandlePlanBO delete(String id) throws SerException {
        QualificationsHandlePlan entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        if (handlePlanStageSer.findByPlan(id).size() != 0)
            throw new SerException("存在依赖关系,无法删除");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<QualificationsHandlePlanBO> findByHandle(String handleId) throws SerException {
        QualificationsHandlePlanDTO dto = new QualificationsHandlePlanDTO();
        dto.getConditions().add(Restrict.eq("handle.id", handleId));
        List<QualificationsHandlePlan> list = super.findByCis(dto);
        return this.transformBOList(list);
    }

    @Override
    public List<QualificationsHandlePlanBO> maps(QualificationsHandlePlanDTO dto) throws SerException {
        return this.transformBOList(super.findByPage(dto));
    }

    @Override
    public Integer getTotal() throws SerException {
        return super.findAll().size();
    }

    @Override
    public QualificationsHandlePlanBO getById(String id) throws SerException {
        QualificationsHandlePlan entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return this.transformBO(entity);
    }
}