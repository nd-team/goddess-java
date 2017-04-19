package com.bjike.goddess.user.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.bo.UserDetailBO;
import com.bjike.goddess.user.dto.UserDetailDTO;

import java.util.List;

/**
 * 对外提供用户详情接口
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-11 15:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface UserDetailAPI {
    /**
     * 通过用户id查找用户详情
     *
     * @param userId
     * @return
     * @throws SerException
     */
    default UserDetailBO findByUserId(String userId) throws SerException {
        return null;
    }

    /**
     * 根据月份分页查询用户详细信息
     *
     * @param dto 用户详细信息
     * @return 用户详细信息结果集
     */
    List<UserDetailBO> findByMonth(UserDetailDTO dto, Integer month) throws SerException;


}
