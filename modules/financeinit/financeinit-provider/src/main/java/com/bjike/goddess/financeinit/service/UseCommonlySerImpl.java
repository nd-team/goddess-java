package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.bo.UseCommonlyBO;
import com.bjike.goddess.financeinit.dto.UseCommonlyDTO;
import com.bjike.goddess.financeinit.entity.UseCommonly;
import com.bjike.goddess.financeinit.to.UseCommonlyTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 常用摘要业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-13 12:56 ]
 * @Description: [ 常用摘要业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "financeinitSerCache")
@Service
public class UseCommonlySerImpl extends ServiceImpl<UseCommonly, UseCommonlyDTO> implements UseCommonlySer {
    @Override
    public Long countUse(UseCommonlyDTO useCommonlyDTO) throws SerException {
        Long count = super.count(useCommonlyDTO);
        return null;
    }

    @Override
    public UseCommonlyBO getOneById(String id) throws SerException {
        UseCommonly useCommonly = super.findById(id);
        return BeanTransform.copyProperties(useCommonly,UseCommonlyBO.class);
    }

    @Override
    public List<UseCommonlyBO> listUse(UseCommonlyDTO useCommonlyDTO) throws SerException {
        List<UseCommonly> useCommonlies = super.findByCis(useCommonlyDTO);
        return BeanTransform.copyProperties(useCommonlies,UseCommonlyBO.class);
    }

    @Override
    public UseCommonlyBO addUse(UseCommonlyTO useCommonlyTO) throws SerException {
        UseCommonly useCommonly = BeanTransform.copyProperties(useCommonlyTO,UseCommonly.class,true);
        useCommonly.setCreateTime(LocalDateTime.now());
        super.save(useCommonly);
        return BeanTransform.copyProperties(useCommonly,UseCommonlyBO.class);
    }
}