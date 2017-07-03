package com.bjike.goddess.capability.service;

import com.bjike.goddess.capability.bo.InProjctBO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.capability.dto.InProjctDTO;
import com.bjike.goddess.capability.entity.InProjct;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 尚在进行中项目数业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-16 06:23 ]
 * @Description: [ 尚在进行中项目数业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "capabilitySerCache")
@Service
public class InProjctSerImpl extends ServiceImpl<InProjct, InProjctDTO> implements InProjctSer {
    @Transactional(rollbackFor = SerException.class)
    @Override
    public InProjctBO addInProjct(String[] inProjcts, String companyId) throws SerException {
        List<InProjct> inProjctList = new ArrayList<>();
        if (inProjcts != null && inProjcts.length > 0) {
            for (String str : inProjcts) {
                InProjct inProjct = new InProjct();
                inProjct.setName(str);
                inProjct.setBaseId(companyId);
                inProjctList.add(inProjct);
            }
        }
        if (inProjctList != null && inProjctList.size() > 0) {
            super.save(inProjctList);
        }
        return new InProjctBO();
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public InProjctBO editInProjct(String[] inProjcts, String companyId) throws SerException {

        List<InProjct> inProjctList = new ArrayList<InProjct>();
        if (null != inProjcts && inProjcts.length > 0) {
            this.deleteInProjct(companyId);
            for (String str : inProjcts) {
                InProjct inProjct = new InProjct();
                inProjct.setBaseId(companyId);
                inProjct.setName(str);
                inProjctList.add(inProjct);
            }
            if (null != inProjctList && inProjctList.size() > 0) {
                super.save(inProjctList);
            }
        }
        return new InProjctBO();
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteInProjct(String id) throws SerException {
        InProjctDTO dto = new InProjctDTO();
        dto.getConditions().add(Restrict.eq("baseId", id));
        List<InProjct> list = super.findByCis(dto);
        if (list != null && list.size() > 0) {
            super.remove(list);
        }
    }
}