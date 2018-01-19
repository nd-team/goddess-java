package com.bjike.goddess.user.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.bo.PositionBO;
import com.bjike.goddess.user.bo.ShareCodeBO;
import com.bjike.goddess.user.dto.PositionDTO;
import com.bjike.goddess.user.service.PositionSer;
import com.bjike.goddess.user.service.ShareCodeSer;
import com.bjike.goddess.user.to.AppUserRegisterTO;
import com.bjike.goddess.user.to.ShareCodeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 邀请码业务实现
 *
 * @Author: [chenyang]
 * @Date: [2018-01-16 11:54]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("shareCodeApiImpl")
public class ShareCodeApiImpl  implements ShareCodeAPI {

    @Autowired
    private ShareCodeSer shareCodeSer;

    @Override
    public ShareCodeBO getByCode(String code) throws SerException {
        return shareCodeSer.getByCode(code);
    }

//    @Override
//    public void save(AppUserRegisterTO appUserRegisterTO) throws SerException {
//        shareCodeSer.save(appUserRegisterTO);
//    }
}
