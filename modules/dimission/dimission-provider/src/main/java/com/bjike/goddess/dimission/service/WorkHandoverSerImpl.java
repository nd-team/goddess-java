package com.bjike.goddess.dimission.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dimission.bo.HandoverReferenceBO;
import com.bjike.goddess.dimission.bo.WorkHandoverBO;
import com.bjike.goddess.dimission.dto.WorkHandoverDTO;
import com.bjike.goddess.dimission.entity.WorkHandover;
import com.bjike.goddess.dimission.to.HandoverSuccessTO;
import com.bjike.goddess.dimission.to.WorkHandoverTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 工作交接业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-17 02:24 ]
 * @Description: [ 工作交接业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "dimissionSerCache")
@Service
public class WorkHandoverSerImpl extends ServiceImpl<WorkHandover, WorkHandoverDTO> implements WorkHandoverSer {

    @Autowired
    private UserAPI userAPI;

    @Override
    public WorkHandoverBO save(WorkHandoverTO to) throws SerException {
        WorkHandover entity = BeanTransform.copyProperties(to, WorkHandover.class);
        super.save(entity);
        return BeanTransform.copyProperties(entity, WorkHandoverBO.class);
    }

    @Override
    public WorkHandoverBO update(WorkHandoverTO to) throws SerException {
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据id不能为空");
        try {
            WorkHandover entity = super.findById(to.getId());
            BeanTransform.copyProperties(to, entity, true);
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
            return BeanTransform.copyProperties(entity, WorkHandoverBO.class);
        } catch (SerException e) {
            throw new SerException("数据对象不能为空");
        }
    }

    @Override
    public WorkHandoverBO delete(String id) throws SerException {
        WorkHandover entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不能为空");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, HandoverReferenceBO.class);
    }

    @Override
    public WorkHandoverBO success(HandoverSuccessTO to) throws SerException {
        UserBO user = userAPI.currentUser();
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据id不能为空");
        WorkHandover entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据对象不能为空");
        if (to.getAuthority()) {//是否为福利模块负责人
            entity.setDirector(user.getUsername());
            entity.setDirectorConfirm(to.getOpinion());
        } else {
            if (user.getUsername().equals(entity.getTransferred()))
                entity.setConfirm(to.getOpinion());
        }
        super.update(entity);
        return BeanTransform.copyProperties(entity, HandoverReferenceBO.class);
    }

    @Override
    public List<WorkHandoverBO> maps(WorkHandoverDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        return BeanTransform.copyProperties(super.findByPage(dto), WorkHandoverBO.class);
    }

    @Override
    public WorkHandoverBO getById(String id) throws SerException {
        WorkHandover entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return BeanTransform.copyProperties(entity, WorkHandoverBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        WorkHandoverDTO dto = new WorkHandoverDTO();
        return super.count(dto);
    }
}