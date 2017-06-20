package com.bjike.goddess.capability.api;

import com.bjike.goddess.capability.bo.ProfessionAuthenBO;
import com.bjike.goddess.capability.service.ProfessionAuthenSer;
import com.bjike.goddess.capability.to.ProfessionAuthenTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* 专业资质认证业务接口实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-06-16 06:06 ]
* @Description:	[ 专业资质认证业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("professionAuthenApiImpl")
public class ProfessionAuthenApiImpl implements ProfessionAuthenAPI  {


    @Autowired
    private ProfessionAuthenSer professionAuthenSer;

    @Override
    public ProfessionAuthenBO addProfessionAuthen(String[] professionAuths, String companyId) throws SerException {
        return professionAuthenSer.addProfessionAuthen(professionAuths, companyId);
    }

    @Override
    public ProfessionAuthenBO editProfessionAuthen(String[] professionAuths, String companyId) throws SerException {
        return professionAuthenSer.editProfessionAuthen(professionAuths,companyId);
    }

    @Override
    public void deleteProfessionAuthen(String id) throws SerException {

    }
}