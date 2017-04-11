package com.bjike.goddess.intromanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.intromanage.bo.StaffHonorBO;
import com.bjike.goddess.intromanage.dto.StaffHonorDTO;
import com.bjike.goddess.intromanage.entity.StaffHonor;
import com.bjike.goddess.intromanage.to.StaffHonorTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工荣誉业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:34 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "intromanageSerCache")
@Service
public class StaffHonorSerImpl extends ServiceImpl<StaffHonor, StaffHonorDTO> implements StaffHonorSer {

    /**
     * 分页查询员工荣誉
     *
     * @return class StaffHonorBO
     * @throws SerException
     */
    @Override
    public List<StaffHonorBO> list(StaffHonorDTO dto) throws SerException {
        List<StaffHonor> list = super.findByPage(dto);
        List<StaffHonorBO> listBO = BeanTransform.copyProperties(list, StaffHonorBO.class);
        return listBO;
    }

    /**
     * 保存员工荣誉
     *
     * @param to 员工荣誉to
     * @return class StaffHonorBO
     * @throws SerException
     */
    @Override
    @Transactional
    public StaffHonorBO save(StaffHonorTO to) throws SerException {
        StaffHonor entity = BeanTransform.copyProperties(to, StaffHonor.class, true);
        entity = super.save(entity);
        StaffHonorBO bo = BeanTransform.copyProperties(entity, StaffHonorBO.class);
        return bo;
    }

    /**
     * 更新员工荣誉
     *
     * @param to 员工荣誉to
     * @throws SerException
     */
    @Override
    @Transactional
    public void update(StaffHonorTO to) throws SerException {
        StaffHonor entity = BeanTransform.copyProperties(to, StaffHonor.class, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    /**
     * 根据id删除员工荣誉
     *
     * @param id 员工荣誉唯一标识
     * @throws SerException
     */
    @Override
    @Transactional
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}