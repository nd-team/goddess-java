package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.ArchiveAccessBO;
import com.bjike.goddess.archive.dto.ArchiveAccessDTO;
import com.bjike.goddess.archive.entity.ArchiveAccess;
import com.bjike.goddess.archive.to.ArchiveAccessTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 档案调阅业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 04:03 ]
 * @Description: [ 档案调阅业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "archiveSerCache")
@Service
public class ArchiveAccessSerImpl extends ServiceImpl<ArchiveAccess, ArchiveAccessDTO> implements ArchiveAccessSer {

    @Autowired
    private UserAPI userAPI;

    @Override
    public ArchiveAccessBO save(ArchiveAccessTO to) throws SerException {
        ArchiveAccess entity = BeanTransform.copyProperties(to, ArchiveAccess.class, true);
        entity.setUsername(userAPI.currentUser().getUsername());
        StringBuilder sb = new StringBuilder(0);
        for (String name : to.getAccessNames())
            sb.append(name).append(",");
        entity.setAccess(sb.toString());
        super.save(entity);
        return BeanTransform.copyProperties(entity, ArchiveAccessBO.class);
    }

    @Override
    public ArchiveAccessBO update(ArchiveAccessTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                ArchiveAccess entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                StringBuilder sb = new StringBuilder(0);
                for (String name : to.getAccessNames())
                    sb.append(name).append(",");
                super.update(entity);
                return BeanTransform.copyProperties(entity, ArchiveAccessBO.class);
            } catch (SerException e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
    }

    @Override
    public ArchiveAccessBO delete(String id) throws SerException {
        return null;
    }

    @Override
    public ArchiveAccessBO audit(ArchiveAccessTO to) throws SerException {
        return null;
    }

    @Override
    public List<ArchiveAccessBO> maps(ArchiveAccessDTO dto) throws SerException {
        return null;
    }
}