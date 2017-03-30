package com.bjike.goddess.contacts.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.bo.CommerceMemberBO;
import com.bjike.goddess.contacts.dto.CommerceMemberDTO;
import com.bjike.goddess.contacts.entity.CommerceMember;
import com.bjike.goddess.contacts.to.CommerceMemberTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 商务会员卡业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:38 ]
 * @Description: [ 商务会员卡业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contactsSerCache")
@Service
public class CommerceMemberSerImpl extends ServiceImpl<CommerceMember, CommerceMemberDTO> implements CommerceMemberSer {

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CommerceMemberBO save(CommerceMemberTO to) throws SerException {
        CommerceMember entity = BeanTransform.copyProperties(to, CommerceMember.class);
        super.save(entity);
        return BeanTransform.copyProperties(entity, CommerceMemberBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CommerceMemberBO update(CommerceMemberTO to) throws SerException {
        CommerceMember entity = BeanTransform.copyProperties(to, CommerceMember.class), member = super.findById(to.getId());
        entity.setCreateTime(member.getCreateTime());
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, CommerceMemberBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CommerceMemberBO delete(CommerceMemberTO to) throws SerException {
        CommerceMember entity = super.findById(to.getId());
        super.remove(entity);
        return BeanTransform.copyProperties(entity, CommerceMemberBO.class);
    }

    @Override
    public List<CommerceMemberBO> maps(CommerceMemberDTO dto) throws SerException {
        List<CommerceMember> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, CommerceMemberBO.class);
    }
}