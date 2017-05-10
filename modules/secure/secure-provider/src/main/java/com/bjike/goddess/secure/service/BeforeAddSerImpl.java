package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.secure.bo.BeforeAddBO;
import com.bjike.goddess.secure.dto.BeforeAddDTO;
import com.bjike.goddess.secure.entity.BeforeAdd;
import com.bjike.goddess.secure.to.AbandonTO;
import com.bjike.goddess.secure.to.BeforeAddTO;
import com.bjike.goddess.secure.to.BuyTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 增员前业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-27 10:10 ]
 * @Description: [ 增员前业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class BeforeAddSerImpl extends ServiceImpl<BeforeAdd, BeforeAddDTO> implements BeforeAddSer {
    @Autowired
    private BuySer buySer;
    @Autowired
    private AbandonSer abandonSer;
    @Autowired
    private MessageAPI messageAPI;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public BeforeAddBO save(BeforeAddTO to) throws SerException {
        //TODO:获取转正员工
        BeforeAdd canAdd = BeanTransform.copyProperties(to, BeforeAdd.class, true);
        canAdd = super.save(canAdd);
        //TODO:17-4-22  发邮件
        MessageTO messageTO = new MessageTO("xxx", "sadasd");
        messageAPI.send(messageTO);
        return BeanTransform.copyProperties(canAdd, BeforeAddBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public BeforeAddBO completeAndConfirm(BeforeAddTO to) throws SerException {
        BeforeAdd canAdd = super.findById(to.getId());
        LocalDateTime a = canAdd.getCreateTime();
        LocalDateTime b = canAdd.getModifyTime();
        canAdd = BeanTransform.copyProperties(to, BeforeAdd.class, true);
        canAdd.setCreateTime(a);
        canAdd.setModifyTime(b);
        super.update(canAdd);
        if (canAdd.getIncrease()) {    //确认购买，添加到购买社保人员中
            BuyTO buyTO = new BuyTO();
            BeanUtils.copyProperties(canAdd, buyTO);
            buyTO.setName(canAdd.getName());
            buySer.save(buyTO);
        } else {    //放弃购买
            AbandonTO abandonTO = new AbandonTO();
            abandonTO.setName(canAdd.getName());
            abandonSer.save(abandonTO);
        }
        return BeanTransform.copyProperties(canAdd, BeforeAddBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public BeforeAddBO delete(String id) throws SerException {
        super.remove(id);
        return null;
    }

    @Override
    public List<BeforeAddBO> find(BeforeAddDTO dto) throws SerException {
        List<BeforeAdd> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, BeforeAddBO.class);
    }

    @Override
    public BeforeAddBO findByID(String id) throws SerException {
        BeforeAdd canAdd = super.findById(id);
        return BeanTransform.copyProperties(canAdd, BeforeAddBO.class);
    }
}