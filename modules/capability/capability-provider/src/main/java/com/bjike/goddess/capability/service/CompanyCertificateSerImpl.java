package com.bjike.goddess.capability.service;

import com.bjike.goddess.capability.bo.CompanyCertificateBO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.capability.dto.CompanyCertificateDTO;
import com.bjike.goddess.capability.entity.CompanyCertificate;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 公司荣誉证书业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-17 09:49 ]
 * @Description: [ 公司荣誉证书业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "capabilitySerCache")
@Service
public class CompanyCertificateSerImpl extends ServiceImpl<CompanyCertificate, CompanyCertificateDTO> implements CompanyCertificateSer {
    @Transactional(rollbackFor = SerException.class)
    @Override
    public CompanyCertificateBO addCompanyCertificate(String[] managerAuths, String companyId) throws SerException {
        List<CompanyCertificate> companyCertificateList = new ArrayList<>();
        if (managerAuths != null && managerAuths.length > 0) {
            for (String str : managerAuths) {
                CompanyCertificate companyCertificate = new CompanyCertificate();
                companyCertificate.setName(str);
                companyCertificate.setBaseId(companyId);
                companyCertificateList.add(companyCertificate);
            }
        }
        if (companyCertificateList != null && companyCertificateList.size() > 0) {
            super.save(companyCertificateList);
        }
        return new CompanyCertificateBO();
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public CompanyCertificateBO editCompanyCertificate(String[] managerAuths, String companyId) throws SerException {

        List<CompanyCertificate> companyCertificateList = new ArrayList<CompanyCertificate>();
        if (null != managerAuths && managerAuths.length > 0) {
            this.deleteCompanyCertificate(companyId);
            for (String str : managerAuths) {
                CompanyCertificate companyCertificate = new CompanyCertificate();
                companyCertificate.setBaseId(companyId);
                companyCertificate.setName(str);
                companyCertificateList.add(companyCertificate);
            }
            if (null != companyCertificateList && companyCertificateList.size() > 0) {
                super.save(companyCertificateList);
            }

        }
        return new CompanyCertificateBO();
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCompanyCertificate(String id) throws SerException {
        CompanyCertificateDTO dto = new CompanyCertificateDTO();
        dto.getConditions().add(Restrict.eq("baseId", id));
        List<CompanyCertificate> list = super.findByCis(dto);
        if (list != null && list.size() > 0) {
            super.remove(list);
        }
    }

}