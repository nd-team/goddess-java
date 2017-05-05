package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.bo.PayDetailBO;
import com.bjike.goddess.secure.dto.PayDetailDTO;
<<<<<<< HEAD
import com.bjike.goddess.secure.entity.PayDetail;
import com.bjike.goddess.secure.to.PayDetailTO;
import com.bjike.goddess.user.api.UserAPI;
=======
import com.bjike.goddess.secure.entity.EmployeeSecure;
import com.bjike.goddess.secure.entity.PayDetail;
import com.bjike.goddess.secure.entity.SecureCart;
import com.bjike.goddess.secure.to.PayDetailTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import org.springframework.beans.BeanUtils;
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD
import java.time.LocalDateTime;
=======
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
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
<<<<<<< HEAD

    @Override
    @Transactional
    public PayDetailBO save(PayDetailTO to) throws SerException {
        PayDetail payDetail = BeanTransform.copyProperties(to, PayDetail.class, true);
        super.save(payDetail);
        return BeanTransform.copyProperties(payDetail, PayDetailBO.class);
=======
    @Override
    @Transactional
    public PayDetailBO save(PayDetailTO to) throws SerException {
        PayDetail payDetail=BeanTransform.copyProperties(to,PayDetail.class,true);
        super.save(payDetail);
        return BeanTransform.copyProperties(payDetail,PayDetailBO.class);
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
    }

    @Override
    @Transactional
    public void edit(PayDetailTO to) throws SerException {
<<<<<<< HEAD
        PayDetail payDetail = super.findById(to.getId());
        LocalDateTime a = payDetail.getCreateTime();
        LocalDateTime b = payDetail.getModifyTime();
        payDetail = BeanTransform.copyProperties(to, PayDetail.class);
        payDetail.setCreateTime(a);
        payDetail.setModifyTime(b);
=======
        PayDetail payDetail=super.findById(to.getId());
        payDetail=BeanTransform.copyProperties(to,PayDetail.class);
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
        super.update(payDetail);
    }

    @Override
    public List<PayDetailBO> list(PayDetailDTO dto) throws SerException {
<<<<<<< HEAD
        List<PayDetail> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, PayDetailBO.class);
=======
        List<PayDetail> list=super.findByCis(dto,true);
        return BeanTransform.copyProperties(list,PayDetailBO.class);
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
    }

    @Override
    public PayDetailBO findByID(String id) throws SerException {
<<<<<<< HEAD
        PayDetail payDetail = super.findById(id);
        return BeanTransform.copyProperties(payDetail, PayDetailBO.class);
=======
        PayDetail payDetail=super.findById(id);
        return BeanTransform.copyProperties(payDetail,PayDetailBO.class);
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
    }

    @Override
    @Transactional
    public void delete(String id) throws SerException {
        super.remove(id);
    }
}