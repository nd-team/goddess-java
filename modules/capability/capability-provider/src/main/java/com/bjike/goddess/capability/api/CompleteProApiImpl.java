package com.bjike.goddess.capability.api;

import com.bjike.goddess.capability.bo.CompleteProBO;
import com.bjike.goddess.capability.service.CompleteProSer;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* 公司参与项目数业务接口实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-06-16 06:22 ]
* @Description:	[ 已完成项目数业务接口实现 ]
* @Version:		[ v1.0.0 ]
*/
@Service("completeProApiImpl")
public class CompleteProApiImpl implements CompleteProAPI  {

    @Autowired
    private CompleteProSer completeProSer;

    @Override
    public CompleteProBO addCompletePro(String[] completePros, String companyId) throws SerException {
        return completeProSer.addCompletePro(completePros, companyId);
    }

    @Override
    public CompleteProBO editCompletePro(String[] completePros, String companyId) throws SerException {
        return completeProSer.editCompletePro(completePros, companyId);
    }

    @Override
    public void deleteCompletePro(String id) throws SerException {
        completeProSer.deleteCompletePro(id);
    }
 }