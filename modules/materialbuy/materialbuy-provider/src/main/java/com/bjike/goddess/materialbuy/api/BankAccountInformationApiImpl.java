package com.bjike.goddess.materialbuy.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialbuy.bo.BankAccountInformationBO;
import com.bjike.goddess.materialbuy.dto.BankAccountInformationDTO;
import com.bjike.goddess.materialbuy.service.BankAccountInformationSer;
import org.springframework.beans.factory.annotation.Autowired;
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
@Service("bankAccountInformationApiImpl")
public class BankAccountInformationApiImpl implements BankAccountInformationAPI {
    @Autowired
    private BankAccountInformationSer bankAccountInformationSer;



    @Override
    public List<BankAccountInformationBO> list(BankAccountInformationDTO dto) throws SerException {
        return bankAccountInformationSer.list(dto);
    }

    @Override
    public List<BankAccountInformationBO> listAccount() throws SerException{
        //String sql="select theBankAccount from materialbuy_bankaccountinformation";
        return bankAccountInformationSer.listAccount();
    }
}
