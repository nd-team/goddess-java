package com.bjike.goddess.capability.service;

import com.bjike.goddess.capability.bo.ManageAuthenBO;
import com.bjike.goddess.capability.to.ManageAuthenTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.capability.dto.ManageAuthenDTO;
import com.bjike.goddess.capability.entity.ManageAuthen;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理资质认证业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-16 06:05 ]
 * @Description: [ 管理资质认证业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "capabilitySerCache")
@Service
public class ManageAuthenSerImpl extends ServiceImpl<ManageAuthen, ManageAuthenDTO> implements ManageAuthenSer {

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ManageAuthenBO addManageAuthen(String[] managerAuths, String companyId) throws SerException {
        List<ManageAuthen> manageAuthenList = new ArrayList<>();
        if (managerAuths != null && managerAuths.length > 0) {
            for (String str : managerAuths) {
                ManageAuthen manageAuthen = new ManageAuthen();
                manageAuthen.setName(str);
                manageAuthen.setBaseId(companyId);
                manageAuthenList.add(manageAuthen);
            }
        }
        if (manageAuthenList != null && manageAuthenList.size() > 0) {
            super.save(manageAuthenList);
        }
        return new ManageAuthenBO();
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public ManageAuthenBO editManageAuthen(String[] managerAuths, String companyId) throws SerException {

        List<ManageAuthen> manageAuthenList = new ArrayList<ManageAuthen>();
        if (null != managerAuths && managerAuths.length > 0) {
            this.deleteManageAuthen(companyId);
            for (String str : managerAuths) {
                ManageAuthen manageAuthen = new ManageAuthen();
                manageAuthen.setBaseId(companyId);
                manageAuthen.setName(str);
                manageAuthenList.add(manageAuthen);
            }
            if (null != manageAuthenList && manageAuthenList.size() > 0) {
                super.save(manageAuthenList);
            }

        }
        return new ManageAuthenBO();
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteManageAuthen(String id) throws SerException {
        ManageAuthenDTO dto = new ManageAuthenDTO();
        dto.getConditions().add(Restrict.eq("baseId", id));
        List<ManageAuthen> list = super.findByCis(dto);
        if (list != null && list.size() > 0) {
            super.remove(list);
        }
    }
}