package com.bjike.goddess.capability.service;

import com.bjike.goddess.capability.bo.CapacityBO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.capability.dto.CapacityDTO;
import com.bjike.goddess.capability.entity.Capacity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人资质业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-16 06:26 ]
 * @Description: [ 个人资质业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "capabilitySerCache")
@Service
public class CapacitySerImpl extends ServiceImpl<Capacity, CapacityDTO> implements CapacitySer {

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CapacityBO addCapacity(String[] capacitys, String id) throws SerException {
        List<Capacity> capacityList = new ArrayList<>();
        if (capacitys != null && capacitys.length > 0) {
            for (String str : capacitys) {
                Capacity capacity = new Capacity();
                capacity.setName(str);
                capacity.setBaseId(id);
                capacityList.add(capacity);
            }
        }
        if (capacityList != null && capacityList.size() > 0) {
            super.save(capacityList);
        }
        return new CapacityBO();
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public CapacityBO editCapacity(String[] capacitys, String id) throws SerException {

        List<Capacity> capacityList = new ArrayList<Capacity>();
        if (null != capacitys && capacitys.length > 0) {
            this.deleteCapacity(id);
            for (String str : capacitys) {
                Capacity capacity = new Capacity();
                capacity.setBaseId(id);
                capacity.setName(str);
                capacityList.add(capacity);
            }
            if (null != capacityList && capacityList.size() > 0) {
                super.save(capacityList);
            }

        }
        return new CapacityBO();
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCapacity(String id) throws SerException {
        CapacityDTO dto = new CapacityDTO();
        dto.getConditions().add(Restrict.eq("baseId", id));
        List<Capacity> list = super.findByCis(dto);
        if (list != null && list.size() > 0) {
            super.remove(list);
        }
    }

}