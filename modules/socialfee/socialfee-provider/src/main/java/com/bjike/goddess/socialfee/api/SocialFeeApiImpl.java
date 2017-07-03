package com.bjike.goddess.socialfee.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.socialfee.bo.SocialFeeBO;
import com.bjike.goddess.socialfee.bo.VoucherDataBO;
import com.bjike.goddess.socialfee.dto.SocialFeeDTO;
import com.bjike.goddess.socialfee.entity.SocialFee;
import com.bjike.goddess.socialfee.service.SocialFeeSer;
import com.bjike.goddess.socialfee.to.SocialFeeTO;
import com.bjike.goddess.socialfee.to.VoucherDataTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 社会缴费业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-19 03:25 ]
 * @Description: [ 社会缴费业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("socialFeeApiImpl")
public class SocialFeeApiImpl implements SocialFeeAPI {

    @Autowired
    private SocialFeeSer socialFeeSer;

    @Override
    public Long countSocialFee(SocialFeeDTO socialFeeDTO) throws SerException {
        return socialFeeSer.countSocialFee(socialFeeDTO);
    }

    @Override
    public List<SocialFeeBO> listSocialFee(SocialFeeDTO socialFeeDTO) throws SerException {
        return socialFeeSer.listSocialFee(socialFeeDTO);
    }

    @Override
    public SocialFeeBO addSocialFee(SocialFeeTO socialFeeTO) throws SerException {
        return socialFeeSer.addSocialFee( socialFeeTO);
    }

    @Override
    public SocialFeeBO editSocialFee(SocialFeeTO socialFeeTO) throws SerException {
        return socialFeeSer.editSocialFee(socialFeeTO);
    }

    @Override
    public void deleteSocialFee(String id) throws SerException {
        socialFeeSer.deleteSocialFee(id);
    }

    @Override
    public SocialFeeBO getOneById(String id) throws SerException {
        return socialFeeSer.getOneById(id);
    }

    @Override
    public List<SocialFeeBO> collect(SocialFeeDTO socialFeeDTO) throws SerException {
        return socialFeeSer.collect(socialFeeDTO);
    }

    @Override
    public List<String> listPayFeer() throws SerException {
        return socialFeeSer.listPayFeer();
    }

    @Override
    public List<String> listEmpName() throws SerException {
        return socialFeeSer.listEmpName();
    }

    @Override
    public VoucherDataBO vGenerate(String[] ids) throws SerException {
        return socialFeeSer.vGenerate(ids);
    }

    @Override
    public VoucherDataBO voucher(VoucherDataTO voucherDataTO) throws SerException {
        return socialFeeSer.voucher(voucherDataTO);
    }

    @Override
    public String export(SocialFeeDTO socialFeeDTO) throws SerException {
        return socialFeeSer.export(socialFeeDTO);
    }
}