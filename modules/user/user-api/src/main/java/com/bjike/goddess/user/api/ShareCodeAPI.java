package com.bjike.goddess.user.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.bo.PositionBO;
import com.bjike.goddess.user.bo.ShareCodeBO;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.dto.PositionDTO;
import com.bjike.goddess.user.to.AppUserRegisterTO;
import com.bjike.goddess.user.to.ShareCodeTO;

import java.util.List;

/**
 * 邀请码业务接口
 *
 * @Author: [chenyang]
 * @Date: [2018-01-15 09:54]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ShareCodeAPI {

//    /**
//     * 保存邀请码
//     *
//     * @param appUserRegisterTO
//     * @throws SerException
//     */
//    default void save(AppUserRegisterTO appUserRegisterTO) throws SerException {
//
//    }

    /**
     * 根据邀请码拿到用户
     *
     * @param code
     * @return
     * @throws SerException
     */
    default ShareCodeBO getByCode(String code) throws SerException {
        return null;
    }


}
