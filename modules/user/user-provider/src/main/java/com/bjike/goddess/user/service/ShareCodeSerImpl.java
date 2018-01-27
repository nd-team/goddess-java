package com.bjike.goddess.user.service;


import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.bo.ShareCodeBO;
import com.bjike.goddess.user.dto.ShareCodeDTO;
import com.bjike.goddess.user.entity.ShareCode;
import com.bjike.goddess.user.utils.ShareCodeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 邀请码业务实现
 *
 * @Author: [chenyang]
 * @Date: [2016-01-15 10:01]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class ShareCodeSerImpl extends ServiceImpl<ShareCode, ShareCodeDTO> implements ShareCodeSer {

    @Override
    public ShareCodeBO getByCode(String code) throws SerException {
        ShareCode shareCode = new ShareCode();
        if (StringUtils.isNotBlank(code)) {
            ShareCodeDTO dto = new ShareCodeDTO();
            dto.getConditions().add(Restrict.eq("shareCode", code));
            shareCode = super.findOne(dto);
        }
        ShareCodeBO shareCodeBO = BeanTransform.copyProperties(shareCode, ShareCodeBO.class);
        return shareCodeBO;
    }



//    @Override
//    public void save(AppUserRegisterTO appUserRegisterTO) throws SerException {
//        if(appUserRegisterTO.getAuthCode() != null && !appUserRegisterTO.getAuthCode().equals("")) {
//            ShareCode shareCode = new ShareCode();
//            shareCode.getInviterShareCode(appUserRegisterTO.getAuthCode());
//            shareCode.setShareCode(ShareCodeUtils.generateShortUuid());
//            ShareCodeBO shareCodeBO = shareCodeSer.getByCode(appUserRegisterTO.getAuthCode());
//            shareCode.setInviterId(shareCodeBO.getId());
//            shareCodeSer.save(shareCode);
//        }
//    }
}
