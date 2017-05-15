package com.bjike.goddess.rotation.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.rotation.bo.CoverRotationBO;
import com.bjike.goddess.rotation.bo.CoverRotationOpinionBO;
import com.bjike.goddess.rotation.dto.CoverRotationDTO;
import com.bjike.goddess.rotation.entity.CoverRotation;
import com.bjike.goddess.rotation.to.CoverRotationOpinionTO;
import com.bjike.goddess.rotation.to.CoverRotationTO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 岗位轮换自荐业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:18 ]
 * @Description: [ 岗位轮换自荐业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "rotationSerCache")
@Service
public class CoverRotationSerImpl extends ServiceImpl<CoverRotation, CoverRotationDTO> implements CoverRotationSer {

    @Autowired
    private UserAPI userAPI;

    @Autowired
//    private Entry archiveDetailAPI;

    @Override
    public CoverRotationBO save(CoverRotationTO to) throws SerException {
        return null;
    }

    @Override
    public CoverRotationBO update(CoverRotationTO to) throws SerException {
        return null;
    }

    @Override
    public CoverRotationBO delete(String id) throws SerException {
        return null;
    }

    @Override
    public CoverRotationBO getById(String id) throws SerException {
        return null;
    }

    @Override
    public CoverRotationOpinionBO opinion(CoverRotationOpinionTO to) throws SerException {
        return null;
    }

    @Override
    public CoverRotationBO generalOpinion(CoverRotationTO to) throws SerException {
        return null;
    }

    @Override
    public List<CoverRotationBO> maps(CoverRotationDTO dto) throws SerException {
        return null;
    }

    @Override
    public Long getTotal() throws SerException {
        return null;
    }


}