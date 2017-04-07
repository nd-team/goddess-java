package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.customer.bo.CusFamilyMemberBO;
import com.bjike.goddess.customer.dto.CusFamilyMemberDTO;
import com.bjike.goddess.customer.entity.CusFamilyMember;
import com.bjike.goddess.customer.to.CusFamilyMemberTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 客户家庭成员业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T09:48:29.114 ]
 * @Description: [ 客户家庭成员业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerSerCache")
@Service
public class CusFamilyMemberSerImpl extends ServiceImpl<CusFamilyMember, CusFamilyMemberDTO> implements CusFamilyMemberSer {

    
    @Override
    public List<CusFamilyMemberBO> listCusFamilyMember(CusFamilyMemberDTO cusFamilyMemberDTO) throws SerException {
        return null;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CusFamilyMemberBO addCusFamilyMember(CusFamilyMemberTO cusFamilyMemberTO) throws SerException {
        return null;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CusFamilyMemberBO editCusFamilyMember(CusFamilyMemberTO cusFamilyMemberTO) throws SerException {
        return null;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCusFamilyMember(String id) throws SerException {

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void congealCusFamilyMember(String id) throws SerException {

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void thawCusFamilyMember(String id) throws SerException {

    }
}