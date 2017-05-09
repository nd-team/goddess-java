package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.bo.PayDetailBO;
import com.bjike.goddess.secure.dto.PayDetailDTO;
import com.bjike.goddess.secure.entity.PayDetail;
import com.bjike.goddess.secure.to.PayDetailTO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 缴费比例明细业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-25 12:55 ]
 * @Description: [ 缴费比例明细业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class PayDetailSerImpl extends ServiceImpl<PayDetail, PayDetailDTO> implements PayDetailSer {
    @Autowired
    private UserAPI userAPI;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public PayDetailBO save(PayDetailTO to) throws SerException {
        PayDetail payDetail = BeanTransform.copyProperties(to, PayDetail.class, true);
        super.save(payDetail);
        return BeanTransform.copyProperties(payDetail, PayDetailBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(PayDetailTO to) throws SerException {
        PayDetail payDetail = super.findById(to.getId());
        LocalDateTime a = payDetail.getCreateTime();
        LocalDateTime b = payDetail.getModifyTime();
        payDetail = BeanTransform.copyProperties(to, PayDetail.class);
        payDetail.setCreateTime(a);
        payDetail.setModifyTime(b);
        super.update(payDetail);
    }

    @Override
    public List<PayDetailBO> list(PayDetailDTO dto) throws SerException {
        List<PayDetail> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, PayDetailBO.class);
    }

    @Override
    public PayDetailBO findByID(String id) throws SerException {
        PayDetail payDetail = super.findById(id);
        return BeanTransform.copyProperties(payDetail, PayDetailBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        super.remove(id);
    }
}