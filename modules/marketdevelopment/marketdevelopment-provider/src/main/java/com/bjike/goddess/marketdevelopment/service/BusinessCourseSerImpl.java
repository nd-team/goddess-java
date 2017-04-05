package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.bo.BusinessCourseBO;
import com.bjike.goddess.marketdevelopment.dto.BusinessCourseDTO;
import com.bjike.goddess.marketdevelopment.entity.BusinessCourse;
import com.bjike.goddess.marketdevelopment.to.BusinessCourseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务方向科目业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:21 ]
 * @Description: [ 业务方向科目业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class BusinessCourseSerImpl extends ServiceImpl<BusinessCourse, BusinessCourseDTO> implements BusinessCourseSer {

    @Autowired
    private BusinessTypeSer typeSer;

    /**
     * 转换业务方向科目传输对象
     *
     * @param entity 业务方向科目实体
     * @return
     */
    private BusinessCourseBO transformBO(BusinessCourse entity) {
        BusinessCourseBO bo = BeanTransform.copyProperties(entity, BusinessCourseBO.class);
        bo.setType_id(entity.getType().getId());
        bo.setTypeName(entity.getType().getType());
        return bo;
    }

    /**
     * 转换业务方向科目传输对象集合
     *
     * @param list 业务方向科目实体集合
     * @return
     */
    private List<BusinessCourseBO> transformBOList(List<BusinessCourse> list) {
        List<BusinessCourseBO> bos = new ArrayList<>(list.size());
        for (BusinessCourse entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessCourseBO save(BusinessCourseTO to) throws SerException {
        BusinessCourse entity = BeanTransform.copyProperties(to, BusinessCourse.class);
        entity.setType(typeSer.findById(to.getType_id()));
        entity.setStatus(Status.THAW);
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessCourseBO update(BusinessCourseTO to) throws SerException {
        BusinessCourse entity = BeanTransform.copyProperties(to, BusinessCourse.class);
        entity.setType(typeSer.findById(to.getType_id()));
        super.update(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessCourseBO congeal(BusinessCourseTO to) throws SerException {
        BusinessCourse entity = super.findById(to.getId());
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessCourseBO thaw(BusinessCourseTO to) throws SerException {
        BusinessCourse entity = super.findById(to.getId());
        entity.setStatus(Status.THAW);
        super.update(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessCourseBO delete(BusinessCourseTO to) throws SerException {
        BusinessCourse entity = super.findById(to.getId());
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<BusinessCourseBO> findByType(String type_id) throws SerException {
        BusinessCourseDTO dto = new BusinessCourseDTO();
        dto.getConditions().add(Restrict.eq("type.id", type_id));
        List<BusinessCourse> list = super.findByCis(dto);
        return this.transformBOList(list);
    }

    @Override
    public List<BusinessCourseBO> findThaw() throws SerException {
        BusinessCourseDTO dto = new BusinessCourseDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<BusinessCourse> list = super.findByCis(dto);
        return this.transformBOList(list);
    }
}