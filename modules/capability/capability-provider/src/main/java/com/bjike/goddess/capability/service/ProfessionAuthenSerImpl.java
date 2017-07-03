package com.bjike.goddess.capability.service;

import com.bjike.goddess.capability.bo.ProfessionAuthenBO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.capability.dto.ProfessionAuthenDTO;
import com.bjike.goddess.capability.entity.ProfessionAuthen;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* 专业资质认证业务实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-06-16 06:06 ]
* @Description:	[ 专业资质认证业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="capabilitySerCache")
@Service
public class ProfessionAuthenSerImpl extends ServiceImpl<ProfessionAuthen, ProfessionAuthenDTO> implements ProfessionAuthenSer {

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProfessionAuthenBO addProfessionAuthen(String[] professionAuths, String companyId) throws SerException {
        List<ProfessionAuthen> professionAuthenList = new ArrayList<>();
        if( professionAuths != null && professionAuths.length> 0 ){
            for(String str : professionAuths ){
                ProfessionAuthen professionAuthen = new ProfessionAuthen();
                professionAuthen.setName( str );
                professionAuthen.setBaseId( companyId );
                professionAuthenList.add( professionAuthen );
            }
        }
        if( professionAuthenList != null && professionAuthenList.size()> 0 ){
            super.save( professionAuthenList );
        }
        return new ProfessionAuthenBO();
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProfessionAuthenBO editProfessionAuthen(String[] professionAuths, String companyId) throws SerException {
        List<ProfessionAuthen> professionAuthenList = new ArrayList<ProfessionAuthen>();
        if(null != professionAuths && professionAuths.length > 0){
            this.deleteProfessionAuthen(companyId);
            for(String str : professionAuths){
                ProfessionAuthen professionAuthen = new ProfessionAuthen();
                professionAuthen.setBaseId(companyId);
                professionAuthen.setName(str);
                professionAuthenList.add(professionAuthen);
            }
            if(null != professionAuthenList && professionAuthenList.size() > 0){
                super.save(professionAuthenList);
            }

        }
        return new ProfessionAuthenBO();
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void deleteProfessionAuthen(String id) throws SerException {
        ProfessionAuthenDTO dto = new ProfessionAuthenDTO();
        dto.getConditions().add(Restrict.eq("baseId", id));
        List<ProfessionAuthen> list = super.findByCis(dto);
        if (list != null && list.size() > 0) {
            super.remove(list);
        }
    }
}