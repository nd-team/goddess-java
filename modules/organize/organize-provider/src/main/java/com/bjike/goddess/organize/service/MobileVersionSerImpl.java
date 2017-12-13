package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.organize.bo.MobileVersionBO;
import com.bjike.goddess.organize.dto.MobileVersionDTO;
import com.bjike.goddess.organize.entity.MobileVersion;
import com.bjike.goddess.organize.to.MobileVersionTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 移动端版本业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-12-07 01:54 ]
 * @Description: [ 移动端版本业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "organizeSerCache")
@Service
public class MobileVersionSerImpl extends ServiceImpl<MobileVersion, MobileVersionDTO> implements MobileVersionSer {
    @Override
    public Long count(MobileVersionDTO dto) throws SerException {
        return null;
    }

    @Override
    public MobileVersionBO getOne(String id) throws SerException {
        return null;
    }

    @Override
    public List<MobileVersionBO> list(MobileVersionDTO dto) throws SerException {
        return null;
    }

    @Override
    public MobileVersionBO insert(MobileVersionTO to) throws SerException {
        return null;
    }

    @Override
    public MobileVersionBO edit(MobileVersionTO to) throws SerException {
        return null;
    }

    @Override
    public void remove(String id) throws SerException {
    }
}