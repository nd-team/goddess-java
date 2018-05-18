package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.MobileVersionBO;
import com.bjike.goddess.organize.dto.MobileVersionDTO;
import com.bjike.goddess.organize.entity.MobileVersion;
import com.bjike.goddess.organize.service.MobileVersionSer;
import com.bjike.goddess.organize.to.MobileVersionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 移动端版本业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-12-07 01:54 ]
 * @Description: [ 移动端版本业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("mobileVersionApiImpl")
public class MobileVersionApiImpl implements MobileVersionAPI {
    @Autowired
    private MobileVersionSer mobileVersionSer;
    @Override
    public Long count(MobileVersionDTO dto) throws SerException {
        return mobileVersionSer.count(dto);
    }

    @Override
    public MobileVersionBO getOne(String id) throws SerException {
        return mobileVersionSer.getOne(id);
    }

    @Override
    public List<MobileVersionBO> list(MobileVersionDTO dto) throws SerException {
        return mobileVersionSer.list(dto);
    }

    @Override
    public MobileVersionBO insert(MobileVersionTO to) throws SerException {
        return mobileVersionSer.insert(to);
    }

    @Override
    public MobileVersionBO edit(MobileVersionTO to) throws SerException {
        return mobileVersionSer.edit(to);
    }

    @Override
    public void remove(String id) throws SerException {
        mobileVersionSer.remove(id);
    }
}