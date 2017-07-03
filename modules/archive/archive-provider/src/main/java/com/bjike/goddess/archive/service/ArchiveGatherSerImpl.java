package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.ArchiveGatherBO;
import com.bjike.goddess.archive.dto.ArchiveGatherDTO;
import com.bjike.goddess.archive.entity.ArchiveGather;
import com.bjike.goddess.archive.to.ArchiveGatherTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.bo.PositionDetailUserBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 档案收集业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 02:22 ]
 * @Description: [ 档案收集业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "archiveSerCache")
@Service
public class ArchiveGatherSerImpl extends ServiceImpl<ArchiveGather, ArchiveGatherDTO> implements ArchiveGatherSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private UserDetailAPI userDetailAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    private ArchiveGatherBO transformBO(ArchiveGather entity) throws SerException {
        ArchiveGatherBO bo = BeanTransform.copyProperties(entity, ArchiveGatherBO.class);
        UserBO user = userAPI.findByUsername(entity.getUsername());
        if (null != user) {
            PositionDetailUserBO detailBO = positionDetailUserAPI.findOneByUser(user.getId());
            bo.setArea("");
            bo.setPosition("");
            bo.setProject("");
            if (null != detailBO)
                for (String id : detailBO.getPositionIds().split(",")) {
                    PositionDetailBO position = positionDetailAPI.findBOById(id);
                    bo.setPosition(bo.getPosition() + "," + position.getPosition());
                    bo.setProject(bo.getProject() + "," + position.getDepartmentName());
                    bo.setArea(bo.getArea() + "," + position.getArea());
                }
            bo.setSerialNumber(user.getEmployeeNumber());
        }
        return bo;
    }

    private List<ArchiveGatherBO> transformBOList(List<ArchiveGather> list) throws SerException {
        List<ArchiveGatherBO> bos = new ArrayList<>(list.size());
        for (ArchiveGather entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ArchiveGatherBO save(ArchiveGatherTO to) throws SerException {
        ArchiveGather entity = BeanTransform.copyProperties(to, ArchiveGather.class);
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ArchiveGatherBO update(ArchiveGatherTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                ArchiveGather entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                super.update(entity);
                return this.transformBO(entity);
            } catch (Exception e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
    }

    @Override
    public ArchiveGatherBO delete(String id) throws SerException {
        ArchiveGather entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<ArchiveGatherBO> maps(ArchiveGatherDTO dto) throws SerException {
        return this.transformBOList(super.findByPage(dto));
    }

    @Override
    public ArchiveGatherBO getById(String id) throws SerException {
        ArchiveGather entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return this.transformBO(entity);
    }

    @Override
    public Long getTotal() throws SerException {
        ArchiveGatherDTO dto = new ArchiveGatherDTO();
        return super.count(dto);
    }
}