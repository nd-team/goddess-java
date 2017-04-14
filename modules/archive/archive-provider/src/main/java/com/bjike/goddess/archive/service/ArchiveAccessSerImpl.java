package com.bjike.goddess.archive.service;

import com.bjike.goddess.annual.enums.AuditType;
import com.bjike.goddess.archive.bo.ArchiveAccessBO;
import com.bjike.goddess.archive.dto.ArchiveAccessDTO;
import com.bjike.goddess.archive.entity.ArchiveAccess;
import com.bjike.goddess.archive.to.AccessAuditTO;
import com.bjike.goddess.archive.to.ArchiveAccessTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
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
    @Autowired
    private UserDetailAPI userDetailAPI;

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ArchiveAccessBO save(ArchiveAccessTO to) throws SerException {
        ArchiveAccess entity = BeanTransform.copyProperties(to, ArchiveAccess.class, true);
        entity.setUsername(userAPI.currentUser().getUsername());
        StringBuilder sb = new StringBuilder(0);
        for (String name : to.getAccessNames())
            sb.append(name).append(",");
        entity.setAccess(sb.toString());
        entity.setAudit(AuditType.NONE);
        super.save(entity);
        return BeanTransform.copyProperties(entity, ArchiveAccessBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
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
                entity.setAccess(sb.toString());
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
        ArchiveAccess entity = super.findById(id);
        super.remove(entity);
        return BeanTransform.copyProperties(entity, ArchiveAccessBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ArchiveAccessBO audit(AccessAuditTO to) throws SerException {
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据ID不能为空");

        ArchiveAccess entity = super.findById(to.getId());
        UserBO user = userAPI.currentUser();
        if (null == user)
            throw new SerException("请登陆重试");
        UserDetailBO detail = userDetailAPI.findByUserId(user.getId());
        if (null == detail)
            throw new SerException("对不起,你没有权限审核");
        if (detail.getDepartmentName().indexOf("福利") > 0) {
            entity.setWelfare(user.getUsername());
            entity.setWelfareOpinion(to.getOpinion());
        }
        if (detail.getDepartmentName().indexOf("总经办") > 0) {
            entity.setManage(user.getUsername());
            entity.setManageOpinion(to.getOpinion());
            entity.setAudit(to.getPass() ? AuditType.ALLOWED : AuditType.DENIED);
        }
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, ArchiveAccessBO.class);
    }

    @Override
    public List<ArchiveAccessBO> maps(ArchiveAccessDTO dto) throws SerException {
        return BeanTransform.copyProperties(super.findByPage(dto), ArchiveAccessBO.class);
    }
}