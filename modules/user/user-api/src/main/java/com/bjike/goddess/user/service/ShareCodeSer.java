package com.bjike.goddess.user.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.user.bo.PositionBO;
import com.bjike.goddess.user.bo.ShareCodeBO;
import com.bjike.goddess.user.dto.PositionDTO;
import com.bjike.goddess.user.dto.ShareCodeDTO;
import com.bjike.goddess.user.entity.Position;
import com.bjike.goddess.user.entity.ShareCode;
import com.bjike.goddess.user.to.AppUserRegisterTO;
import com.bjike.goddess.user.to.ShareCodeTO;

import java.util.List;

/**
 * 邀请码业务接口
 *
 * @Author: [chenyang]
 * @Date: [2016-01-15 09:57]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ShareCodeSer extends Ser<ShareCode, ShareCodeDTO> {

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
