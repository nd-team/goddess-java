package com.bjike.goddess.checkhost.service;

import com.bjike.goddess.checkhost.bo.CheckHostCollectBO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.checkhost.dto.CheckHostCollectDTO;
import com.bjike.goddess.checkhost.entity.CheckHostCollect;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 汇总表业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 05:13 ]
 * @Description: [ 汇总表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "checkhostSerCache")
@Service
public class CheckHostCollectSerImpl extends ServiceImpl<CheckHostCollect, CheckHostCollectDTO> implements CheckHostCollectSer {

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CheckHostCollectBO listCheckHostCollect(String name) throws SerException {
        CheckHostCollectDTO dto = new CheckHostCollectDTO();
        dto.getConditions().add(Restrict.eq("name",name));
        CheckHostCollectBO checkHostCollectBO = BeanTransform.copyProperties(super.findOne(dto),CheckHostCollectBO.class);
        return checkHostCollectBO;
    }
}