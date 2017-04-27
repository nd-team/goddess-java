package com.bjike.goddess.capability.api;

import com.bjike.goddess.capability.bo.SelfCapabilitySocialBO;
import com.bjike.goddess.capability.dto.SelfCapabilityDTO;
import com.bjike.goddess.capability.dto.SelfCapabilitySocialDTO;
import com.bjike.goddess.capability.service.SelfCapabilitySer;
import com.bjike.goddess.capability.service.SelfCapabilitySocialSer;
import com.bjike.goddess.capability.to.SelfCapabilitySocialTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 个人能力展示业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-23 04:22 ]
 * @Description: [ 个人能力展示业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("selfCapabilitySocialApiImpl")
public class SelfCapabilitySocialApiImpl implements SelfCapabilitySocialAPI {

    @Autowired
    private SelfCapabilitySocialSer selfCapabilitySocialSer;

    @Override
    public Long counts(SelfCapabilitySocialDTO selfCapabilitySocialDTO) throws SerException {
        return selfCapabilitySocialSer.count(selfCapabilitySocialDTO);
    }

    @Override
    public SelfCapabilitySocialBO getOne(String id) throws SerException {
        return selfCapabilitySocialSer.getOne(id);
    }

    @Override
    public List<SelfCapabilitySocialBO> listSelfCapabilitySocial(SelfCapabilitySocialDTO selfCapabilitySocialDTO) throws SerException {
        return selfCapabilitySocialSer.listSelfCapabilitySocial(selfCapabilitySocialDTO);
    }

    @Override
    public SelfCapabilitySocialBO addSelfCapabilitySocial(SelfCapabilitySocialTO selfCapabilitySocialTO) throws SerException {
        return selfCapabilitySocialSer.addSelfCapabilitySocial(selfCapabilitySocialTO);
    }

    @Override
    public SelfCapabilitySocialBO editSelfCapabilitySocial(String id ,SelfCapabilitySocialTO selfCapabilitySocialTO) throws SerException {
        return selfCapabilitySocialSer.editSelfCapabilitySocial(id , selfCapabilitySocialTO);
    }

    @Override
    public void deleteSelfCapabilitySocial(String id) throws SerException {
        selfCapabilitySocialSer.deleteSelfCapabilitySocial(id);
    }



}