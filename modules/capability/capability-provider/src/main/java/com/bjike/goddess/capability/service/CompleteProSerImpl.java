package com.bjike.goddess.capability.service;

import com.bjike.goddess.capability.bo.CompleteProBO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.capability.dto.CompleteProDTO;
import com.bjike.goddess.capability.entity.CompletePro;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 公司参与项目数业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-16 06:22 ]
 * @Description: [ 公司参与项目数业务实现 ]
 * @Version: [ v1.0.0 ]
 */
@CacheConfig(cacheNames = "capabilitySerCache")
@Service
public class CompleteProSerImpl extends ServiceImpl<CompletePro, CompleteProDTO> implements CompleteProSer {
    @Transactional(rollbackFor = SerException.class)
    @Override
    public CompleteProBO addCompletePro(String[] completePros, String companyId) throws SerException {
        List<CompletePro> completeProList = new ArrayList<>();
        if (completePros != null && completePros.length > 0) {
            for (String str : completePros) {
                CompletePro completePro = new CompletePro();
                completePro.setName(str);
                completePro.setBaseId(companyId);
                completeProList.add(completePro);
            }
        }
        if (completeProList != null && completeProList.size() > 0) {
            super.save(completeProList);
        }
        return new CompleteProBO();
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public CompleteProBO editCompletePro(String[] completePros, String companyId) throws SerException {

        List<CompletePro> completeProList = new ArrayList<CompletePro>();
        if (null != completePros && completePros.length > 0) {
            this.deleteCompletePro(companyId);
            for (String str : completePros) {
                CompletePro completePro = new CompletePro();
                completePro.setBaseId(companyId);
                completePro.setName(str);
                completeProList.add(completePro);
            }
            if (null != completeProList && completeProList.size() > 0) {
                super.save(completeProList);
            }

        }
        return new CompleteProBO();
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCompletePro(String id) throws SerException {
        CompleteProDTO dto = new CompleteProDTO();
        dto.getConditions().add(Restrict.eq("baseId", id));
        List<CompletePro> list = super.findByCis(dto);
        if (list != null && list.size() > 0) {
            super.remove(list);
        }
    }
}