package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.ArchiveDetailBO;
import com.bjike.goddess.archive.dto.ArchiveDetailDTO;
import com.bjike.goddess.archive.entity.ArchiveDetail;
import com.bjike.goddess.archive.to.ArchiveDetailTO;
import com.bjike.goddess.common.api.dto.Restrict;
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
 * 档案明细业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 02:05 ]
 * @Description: [ 档案明细业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "archiveSerCache")
@Service
public class ArchiveDetailSerImpl extends ServiceImpl<ArchiveDetail, ArchiveDetailDTO> implements ArchiveDetailSer {

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

    private ArchiveDetailBO transformBO(ArchiveDetail entity) throws SerException {
        ArchiveDetailBO bo = BeanTransform.copyProperties(entity, ArchiveDetailBO.class);
        bo.setPosition("");
        bo.setProject("");
        UserBO user = userAPI.findByUsername(entity.getUsername());
        if (null != user) {
            PositionDetailUserBO detailBO = positionDetailUserAPI.findOneByUser(user.getId());
            if (null != detailBO)
                for (String id : detailBO.getPositionIds().split(",")) {
                    PositionDetailBO position = positionDetailAPI.findBOById(id);
                    bo.setPosition(bo.getPosition() + "," + position.getPosition());
                    bo.setProject(bo.getProject() + "," + position.getDepartmentName());
                }
            bo.setSerialNumber(user.getEmployeeNumber());
        }
        return bo;
    }

    private List<ArchiveDetailBO> transformBOList(List<ArchiveDetail> list) throws SerException {
        List<ArchiveDetailBO> bos = new ArrayList<>(list.size());
        for (ArchiveDetail entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ArchiveDetailBO save(ArchiveDetailTO to) throws SerException {
        ArchiveDetail entity = BeanTransform.copyProperties(to, ArchiveDetail.class);
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ArchiveDetailBO update(ArchiveDetailTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                ArchiveDetail entity = super.findById(to.getId());
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
    public ArchiveDetailBO delete(String id) throws SerException {
        ArchiveDetail entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public ArchiveDetailBO findByUsername(String username) throws SerException {
        ArchiveDetailDTO dto = new ArchiveDetailDTO();
        dto.getConditions().add(Restrict.eq(USERNAME, username));
        ArchiveDetail entity = super.findOne(dto);
        return this.transformBO(entity);
    }

    @Override
    public List<ArchiveDetailBO> maps(ArchiveDetailDTO dto) throws SerException {
        return this.transformBOList(super.findByPage(dto));
    }

    @Override
    public ArchiveDetailBO getById(String id) throws SerException {
        ArchiveDetail entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return this.transformBO(entity);
    }

    @Override
    public Long getTotal() throws SerException {
        ArchiveDetailDTO dto = new ArchiveDetailDTO();
        return super.count(dto);
    }
}