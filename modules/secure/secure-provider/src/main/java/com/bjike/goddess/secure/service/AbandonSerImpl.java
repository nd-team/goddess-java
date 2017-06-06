package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.bo.AbandonBO;
import com.bjike.goddess.secure.dto.AbandonDTO;
import com.bjike.goddess.secure.entity.Abandon;
import com.bjike.goddess.secure.to.AbandonTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 放弃购买名单业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:52 ]
 * @Description: [ 放弃购买名单业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class AbandonSerImpl extends ServiceImpl<Abandon, AbandonDTO> implements AbandonSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private UserDetailAPI userDetailAPI;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    //填写放弃原因，点击放弃购买按钮
    public AbandonBO save(AbandonTO to) throws SerException {
        String name = userAPI.currentUser().getUsername();
        String eNum = userAPI.currentUser().getEmployeeNumber();
        String groupName = userDetailAPI.findByUserId(userAPI.currentUser().getId()).getGroupName();
        Abandon abandon = BeanTransform.copyProperties(to, Abandon.class, true);
        abandon.setName(name);
        abandon.setEmployeeNum(eNum);
        abandon.setGroup1(groupName);
        abandon.setSign(true);
        super.save(abandon);
        return BeanTransform.copyProperties(abandon, AbandonBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public AbandonBO delete(String id) throws SerException {
        super.remove(id);
        return null;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public AbandonBO edit(AbandonTO to) throws SerException {
        Abandon abandon = super.findById(to.getId());
        LocalDateTime a = abandon.getCreateTime();
        abandon = BeanTransform.copyProperties(to, Abandon.class, true);
        abandon.setCreateTime(a);
        abandon.setModifyTime(LocalDateTime.now());
        super.update(abandon);
        return BeanTransform.copyProperties(abandon, AbandonBO.class);
    }

    @Override
    public List<AbandonBO> find(AbandonDTO dto) throws SerException {
        List<Abandon> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, AbandonBO.class);
    }

    @Override
    public AbandonBO findByID(String id) throws SerException {
        Abandon abandon = super.findById(id);
        return BeanTransform.copyProperties(abandon, AbandonBO.class);
    }

    @Override
    public List<AbandonBO> findALL() throws SerException {
        List<Abandon> list = super.findAll();
        return BeanTransform.copyProperties(list, AbandonBO.class);
    }

    @Override
    public Long count(AbandonDTO dto) throws SerException {
        return super.count(dto);
    }
}