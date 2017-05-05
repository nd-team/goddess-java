package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.bo.AttachedBO;
import com.bjike.goddess.secure.dto.AttachedDTO;
import com.bjike.goddess.secure.entity.Attached;
import com.bjike.goddess.secure.to.AttachedTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD
import java.time.LocalDateTime;
=======
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
import java.util.List;

/**
 * 挂靠业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:12 ]
 * @Description: [ 挂靠业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class AttachedSerImpl extends ServiceImpl<Attached, AttachedDTO> implements AttachedSer {
    @Override
    @Transactional
    public AttachedBO save(AttachedTO to) throws SerException {
<<<<<<< HEAD
        Attached attached = BeanTransform.copyProperties(to, Attached.class, true);
        attached = super.save(attached);
        return BeanTransform.copyProperties(attached, AttachedBO.class);
=======
        Attached attached= BeanTransform.copyProperties(to,Attached.class,true);
        attached=super.save(attached);
        return BeanTransform.copyProperties(attached,AttachedBO.class);
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
    }

    @Override
    @Transactional
    public AttachedBO exameAndComplete(AttachedTO to) throws SerException {
<<<<<<< HEAD
        Attached attached = super.findById(to.getId());
=======
        Attached attached=super.findById(to.getId());
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
        attached.setMoney(to.getMoney());
        attached.setPaid(to.getPaid());
        attached.setRecord(to.getRecord());
        attached.setDecription(to.getDecription());
        attached.setAdvice(to.getAdvice());
        super.update(attached);
<<<<<<< HEAD
        return BeanTransform.copyProperties(attached, AttachedBO.class);
=======
        return BeanTransform.copyProperties(attached,AttachedBO.class);
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
    }

    @Override
    @Transactional
    public AttachedBO delete(String id) throws SerException {
        super.remove(id);
        return null;
    }

    @Override
    public List<AttachedBO> find(AttachedDTO dto) throws SerException {
<<<<<<< HEAD
        List<Attached> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, AttachedBO.class);
=======
        List<Attached> list=super.findByCis(dto,true);
        return BeanTransform.copyProperties(list,AttachedBO.class);
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
    }

    @Override
    public AttachedBO findByID(String id) throws SerException {
<<<<<<< HEAD
        Attached attached = super.findById(id);
        return BeanTransform.copyProperties(attached, AttachedBO.class);
=======
        Attached attached=super.findById(id);
        return BeanTransform.copyProperties(attached,AttachedBO.class);
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
    }

    @Override
    public List<AttachedBO> findALL() throws SerException {
<<<<<<< HEAD
        List<Attached> list = super.findAll();
        return BeanTransform.copyProperties(list, AttachedBO.class);
=======
        List<Attached> list=super.findAll();
        return BeanTransform.copyProperties(list,AttachedBO.class);
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
    }

    @Override
    @Transactional
    public AttachedBO update(AttachedTO to) throws SerException {
<<<<<<< HEAD
        Attached attached = super.findById(to.getId());
        LocalDateTime a = attached.getCreateTime();
        LocalDateTime b = attached.getModifyTime();
        attached = BeanTransform.copyProperties(to, Attached.class, true);
        attached.setCreateTime(a);
        attached.setModifyTime(b);
        super.update(attached);
        return BeanTransform.copyProperties(attached, AttachedBO.class);
=======
        Attached attached=super.findById(to.getId());
        attached=BeanTransform.copyProperties(to,Attached.class,true);
        super.update(attached);
        return BeanTransform.copyProperties(attached,AttachedBO.class);
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
    }
}