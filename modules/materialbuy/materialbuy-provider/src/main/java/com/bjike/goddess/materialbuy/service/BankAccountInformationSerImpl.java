package com.bjike.goddess.materialbuy.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialbuy.bo.BankAccountInformationBO;
import com.bjike.goddess.materialbuy.dto.BankAccountInformationDTO;
import com.bjike.goddess.materialbuy.entity.BankAccountInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 搜索全局银行账户信息
 *
 * @Author: [ yangruizhen ]
 * @Date: [ 2018-04-02 09:36 ]
 * @Description: [ 搜索全局银行账户信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialbuySerCache")
@Service
public class BankAccountInformationSerImpl extends ServiceImpl<BankAccountInformation, BankAccountInformationDTO> implements BankAccountInformationSer {

    @Override
    public List<BankAccountInformationBO> list(BankAccountInformationDTO dto) throws SerException {
        List<BankAccountInformation> lsitba = super.findByPage(dto);
        return BeanTransform.copyProperties(lsitba, BankAccountInformationBO.class);
    }

    @Override
    public List<BankAccountInformationBO> listAccount() throws SerException{
        String sql="select theBankAccount from materialbuy_bankaccountinformation";
        String []field=new String[]{"theBankAccount"};
        return super.findBySql(sql,BankAccountInformationBO.class,field);
    }
}