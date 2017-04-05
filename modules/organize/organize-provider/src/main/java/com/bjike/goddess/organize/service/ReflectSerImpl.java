package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.ReflectBO;
import com.bjike.goddess.organize.dto.ReflectDTO;
import com.bjike.goddess.organize.entity.Reflect;
import com.bjike.goddess.organize.to.ReflectTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 体现类型操作业务实现
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午11:29]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@Service
public class ReflectSerImpl extends ServiceImpl<Reflect, ReflectDTO> implements ReflectSer {


    @Override
    public List<ReflectBO> findStatus() throws SerException {
        return null;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReflectBO save(ReflectTO to) throws SerException {
        Reflect reflect = BeanTransform.copyProperties(to, Reflect.class);
        reflect.setCreateTime(LocalDateTime.now());
        reflect.setStatus(Status.THAW);
        super.save(reflect);
        return BeanTransform.copyProperties(reflect, ReflectBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReflectBO update(ReflectTO to) throws SerException {
        Reflect reflect = super.findById(to.getId());
        reflect.setName(to.getName());
        reflect.setDescription(to.getDescription());
        super.update(reflect);
        return BeanTransform.copyProperties(reflect, ReflectBO.class);
    }
}
