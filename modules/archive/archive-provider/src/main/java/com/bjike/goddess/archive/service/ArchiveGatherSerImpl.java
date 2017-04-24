package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.ArchiveGatherBO;
import com.bjike.goddess.archive.dto.ArchiveGatherDTO;
import com.bjike.goddess.archive.entity.ArchiveGather;
import com.bjike.goddess.archive.to.ArchiveGatherTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.bo.UserDetailBO;
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

    private ArchiveGatherBO transformBO(ArchiveGather entity) throws SerException {
        ArchiveGatherBO bo = BeanTransform.copyProperties(entity, ArchiveGatherBO.class);
        UserBO user = userAPI.findByUsername(entity.getUsername());
        if (null != user) {
            UserDetailBO detailBO = userDetailAPI.findByUserId(user.getId());
            if (null != detailBO) {
                DepartmentDetailBO departmentDetail = departmentDetailAPI.findByDepartment(detailBO.getDepartmentId());
                if (null != departmentDetail) bo.setArea(departmentDetail.getArea());
                bo.setPosition(detailBO.getPositionName());
                bo.setProject(detailBO.getDepartmentName());
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
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<ArchiveGatherBO> maps(ArchiveGatherDTO dto) throws SerException {
        return this.transformBOList(super.findByPage(dto));
    }
}