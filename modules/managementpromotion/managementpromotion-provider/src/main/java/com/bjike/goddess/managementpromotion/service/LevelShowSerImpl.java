package com.bjike.goddess.managementpromotion.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managementpromotion.bo.LevelShowBO;
import com.bjike.goddess.managementpromotion.dto.LevelShowDTO;
import com.bjike.goddess.managementpromotion.entity.LevelShow;
import com.bjike.goddess.managementpromotion.to.LevelShowTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 管理等级情况慨览业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-22 01:53 ]
 * @Description: [ 管理等级情况慨览业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "managementpromotionSerCache")
@Service
public class LevelShowSerImpl extends ServiceImpl<LevelShow, LevelShowDTO> implements LevelShowSer {
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public LevelShowBO save(LevelShowTO to) throws SerException {
        LevelShow entity = BeanTransform.copyProperties(to, LevelShow.class, true);
        super.save(entity);
        return BeanTransform.copyProperties(entity, LevelShowBO.class);
    }

    @Override
    public List<LevelShowBO> find(LevelShowDTO dto) throws SerException {
        return BeanTransform.copyProperties(super.findByCis(dto, true), LevelShowBO.class);
    }

    @Override
    public Long count(LevelShowDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public List<LevelShowBO> findAll(LevelShowDTO dto) throws SerException {
        return BeanTransform.copyProperties(super.findByCis(dto), LevelShowBO.class);
    }

    @Override
    public LevelShowBO findByID(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id), LevelShowBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void update(LevelShowTO to) throws SerException {
        LevelShow entity = super.findById(to.getId());
        if (entity==null){
            throw new SerException("对象不存在");
        }
        LocalDateTime a=entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, LevelShow.class, true);
        entity.setCreateTime(a);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public LevelShow findBySql(String employeeId) throws SerException {
        String[] strings = new String[]{employeeId};
        List<LevelShow> list = null;
        for (String s : strings) {
            String sql = "SELECT id from managementpromotion_levelshow\n" +
                    "where employeeId='" + s + "'";
            String[] fileds = new String[]{"id"};
            list = super.findBySql(sql, LevelShow.class, fileds);
        }
        if ((list != null) && (list.size() != 0)) {
            return list.get(0);
        }
        return null;
    }
}