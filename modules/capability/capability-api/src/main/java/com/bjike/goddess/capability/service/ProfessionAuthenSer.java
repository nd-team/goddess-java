package com.bjike.goddess.capability.service;

import com.bjike.goddess.capability.bo.ProfessionAuthenBO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.capability.entity.ProfessionAuthen;
import com.bjike.goddess.capability.dto.ProfessionAuthenDTO;

/**
* 专业资质认证业务接口
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-06-16 06:06 ]
* @Description:	[ 专业资质认证业务接口 ]
* @Version:		[ v1.0.0 ]
*/
public interface ProfessionAuthenSer extends Ser<ProfessionAuthen, ProfessionAuthenDTO> {
    /**
     *  添加
     * @param professionAuths 专业资质认证信息
     * @param companyId 公司id
     * @return class ProfessionAuthenBO
     */
    default ProfessionAuthenBO addProfessionAuthen(String [] professionAuths , String companyId ) throws SerException { return null;}

    /**
     *  编辑
     * @param professionAuths 管理资质认证信息
     * @param companyId 公司id
     * @return class ProfessionAuthenBO
     */
    default ProfessionAuthenBO editProfessionAuthen(String [] professionAuths , String companyId ) throws SerException { return null;}

    /**
     * 删除管理资质认证
     * @param id id
     */
    default void deleteProfessionAuthen(String id ) throws SerException {return;};


}