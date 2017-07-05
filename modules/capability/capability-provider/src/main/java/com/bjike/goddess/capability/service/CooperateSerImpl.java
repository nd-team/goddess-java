package com.bjike.goddess.capability.service;

import com.bjike.goddess.capability.bo.CooperateBO;
import com.bjike.goddess.capability.dto.CooperateDTO;
import com.bjike.goddess.capability.entity.Cooperate;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 公司合作对象业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-16 06:03 ]
 * @Description: [ 公司合作对象业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "capabilitySerCache")
@Service
public class CooperateSerImpl extends ServiceImpl<Cooperate, CooperateDTO> implements CooperateSer {

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CooperateBO addCooperate(String[] Cooperates, String companyId) throws SerException {
        List<Cooperate> CooperateList = new ArrayList<>();
        if (Cooperates != null && Cooperates.length > 0) {
            for (String str : Cooperates) {
                Cooperate Cooperate = new Cooperate();
                Cooperate.setName(str);
                Cooperate.setBaseId(companyId);
                CooperateList.add(Cooperate);
            }
        }
        if (CooperateList != null && CooperateList.size() > 0) {
            super.save(CooperateList);
        }
        return new CooperateBO();
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public CooperateBO editCooperate(String[] Cooperates, String companyId) throws SerException {

        List<Cooperate> CooperateList = new ArrayList<Cooperate>();
        if (null != Cooperates && Cooperates.length > 0) {
            this.deleteCooperate(companyId);
            for (String str : Cooperates) {
                Cooperate Cooperate = new Cooperate();
                Cooperate.setBaseId(companyId);
                Cooperate.setName(str);
                CooperateList.add(Cooperate);
            }
            if (null != CooperateList && CooperateList.size() > 0) {
                super.save(CooperateList);
            }

        }
        return new CooperateBO();
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCooperate(String id) throws SerException {
        CooperateDTO dto = new CooperateDTO();
        dto.getConditions().add(Restrict.eq("baseId", id));
        List<Cooperate> list = super.findByCis(dto);
        if (list != null && list.size() > 0) {
            super.remove(list);
        }
    }
}