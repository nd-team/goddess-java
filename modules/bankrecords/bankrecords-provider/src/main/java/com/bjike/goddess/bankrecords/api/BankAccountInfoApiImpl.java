package com.bjike.goddess.bankrecords.api;

import com.bjike.goddess.bankrecords.bo.BankAccountInfoBO;
import com.bjike.goddess.bankrecords.dto.BankAccountInfoDTO;
import com.bjike.goddess.bankrecords.service.BankAccountInfoSer;
import com.bjike.goddess.bankrecords.to.BankAccountInfoTO;
import com.bjike.goddess.bankrecords.to.GuidePermissionTO;
import com.bjike.goddess.bankrecords.to.SonPermissionObject;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账号信息业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 03:58 ]
 * @Description: [ 账号信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("bankAccountInfoApiImpl")
public class BankAccountInfoApiImpl implements BankAccountInfoAPI {

    @Autowired
    private BankAccountInfoSer bankAccountInfoSer;

    @Override
    public BankAccountInfoBO add(BankAccountInfoTO to) throws SerException {
        return bankAccountInfoSer.insertModel(to);
    }

    @Override
    public BankAccountInfoBO edit(BankAccountInfoTO to) throws SerException {
        return bankAccountInfoSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        bankAccountInfoSer.delete(id);
    }

    @Override
    public List<BankAccountInfoBO> pageList(BankAccountInfoDTO dto) throws SerException {
        return bankAccountInfoSer.pageList(dto);
    }

    @Override
    public Long count(BankAccountInfoDTO dto) throws SerException {
        return bankAccountInfoSer.count(dto);
    }

    @Override
    public BankAccountInfoBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(bankAccountInfoSer.findById(id),BankAccountInfoBO.class);
    }

    @Override
    public List<BankAccountInfoBO> findAll() throws SerException {
        return BeanTransform.copyProperties(bankAccountInfoSer.findAll(),BankAccountInfoBO.class);
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return bankAccountInfoSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO to) throws SerException {
        return bankAccountInfoSer.guidePermission(to);
    }
}