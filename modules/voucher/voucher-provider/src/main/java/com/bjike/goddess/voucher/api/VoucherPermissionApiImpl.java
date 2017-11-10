package com.bjike.goddess.voucher.api;

import com.bjike.goddess.voucher.bo.VoucherPermissionBO;
import com.bjike.goddess.voucher.dto.VoucherPermissionDTO;
import com.bjike.goddess.voucher.service.VoucherPermissionSer;
import com.bjike.goddess.voucher.to.VoucherPermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.OpinionBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户权限配置业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-12 05:43 ]
 * @Description: [ 客户权限配置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("voucherPermissionApiImpl")
public class VoucherPermissionApiImpl implements VoucherPermissionAPI {

    @Autowired
    private VoucherPermissionSer cusPermissionSer;

    @Override
    public Long countPermission(VoucherPermissionDTO cusPermissionDTO) throws SerException {
        return cusPermissionSer.countPermission(cusPermissionDTO);
    }

    @Override
    public List<VoucherPermissionBO> list(VoucherPermissionDTO cusPermissionDTO) throws SerException {
        return cusPermissionSer.list(cusPermissionDTO);
    }

    @Override
    public Long countAccountPermission(VoucherPermissionDTO cusPermissionDTO) throws SerException {
        return cusPermissionSer.countAccountPermission(cusPermissionDTO);
    }

    @Override
    public List<VoucherPermissionBO> listAccount(VoucherPermissionDTO cusPermissionDTO) throws SerException {
        return cusPermissionSer.listAccount(cusPermissionDTO);
    }

    @Override
    public VoucherPermissionBO getOneById(String id) throws SerException {
        return cusPermissionSer.getOneById(id);
    }

    @Override
    public List<OpinionBO>  listOperateById(String id) throws SerException {
        return cusPermissionSer.listOperateById(id);
    }

    @Override
    public VoucherPermissionBO add(List<VoucherPermissionTO> cusPermissionTO) throws SerException {
        return cusPermissionSer.add(cusPermissionTO);
    }

    @Override
    public VoucherPermissionBO edit(VoucherPermissionTO cusPermissionTO) throws SerException {
        return cusPermissionSer.edit(cusPermissionTO);
    }

    @Override
    public Boolean getCusPermission(String idFlag) throws SerException {
        return cusPermissionSer.getCusPermission(idFlag);
    }

    @Override
    public Boolean busCusPermission(String idFlag) throws SerException {
        return cusPermissionSer.busCusPermission(idFlag);
    }
}