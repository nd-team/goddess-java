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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    @Autowired
    private MarPermissionSer marPermissionSer;

    private static final String marketCheck = "market-check";

    private static final String marketManage = "market-manage";

    /**
     * 转换业务方向科目传输对象
     *
     * @param entity 业务方向科目实体
     * @return
     */
    private BusinessCourseBO transformBO(BusinessCourse entity) {
        BusinessCourseBO bo = BeanTransform.copyProperties(entity, BusinessCourseBO.class);
        bo.setTypeId(entity.getType().getId());
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
        if (!marPermissionSer.getMarPermission(marketManage))
            throw new SerException("您的帐号没有权限");
        BusinessCourse entity = BeanTransform.copyProperties(to, BusinessCourse.class);
        entity.setType(typeSer.findById(to.getTypeId()));
        entity.setStatus(Status.THAW);
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessCourseBO update(BusinessCourseTO to) throws SerException {
        if (!marPermissionSer.getMarPermission(marketManage))
            throw new SerException("您的帐号没有权限");
        if (StringUtils.isNotBlank(to.getId())) {
            BusinessCourse entity = super.findById(to.getId());
            if (null == entity)
                throw new SerException("数据对象不能为空");
            BeanTransform.copyProperties(to, entity, true);
            entity.setType(typeSer.findById(to.getTypeId()));
            if (entity.getType() == null)
                throw new SerException("业务类型不存在");
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
            return this.transformBO(entity);
        } else
            throw new SerException("数据ID不能为空");
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessCourseBO congeal(BusinessCourseTO to) throws SerException {
        if (!marPermissionSer.getMarPermission(marketManage))
            throw new SerException("您的帐号没有权限");
        BusinessCourse entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessCourseBO thaw(BusinessCourseTO to) throws SerException {
        if (!marPermissionSer.getMarPermission(marketManage))
            throw new SerException("您的帐号没有权限");
        BusinessCourse entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
        entity.setStatus(Status.THAW);
        super.update(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessCourseBO delete(BusinessCourseTO to) throws SerException {
        if (!marPermissionSer.getMarPermission(marketManage))
            throw new SerException("您的帐号没有权限");
        BusinessCourse entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
        try {
            super.remove(entity);
        } catch (SerException e) {
            throw new SerException("存在依赖关系无法删除");
        }
        return this.transformBO(entity);
    }

    @Override
    public List<BusinessCourseBO> findByType(String typeId) throws SerException {
        BusinessCourseDTO dto = new BusinessCourseDTO();
        dto.getConditions().add(Restrict.eq("type.id", typeId));
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

    @Override
    public BusinessCourseBO getById(String id) throws SerException {
        if (!marPermissionSer.getMarPermission(marketManage))
            throw new SerException("您的帐号没有权限");
        try {
            return this.transformBO(super.findById(id));
        } catch (SerException e) {
            throw new SerException("数据对象不能为空");
        }
    }

    @Override
    public List<BusinessCourseBO> maps(BusinessCourseDTO dto) throws SerException {
        if (!marPermissionSer.getMarPermission(marketCheck))
            throw new SerException("您的帐号没有权限");
        dto.getSorts().add("typeId=desc");
        return this.transformBOList(super.findByPage(dto));
    }
}