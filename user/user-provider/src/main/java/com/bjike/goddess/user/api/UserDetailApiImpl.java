package com.bjike.goddess.user.api;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.bo.UserDetailBO;
import com.bjike.goddess.user.dto.UserDetailDTO;
import com.bjike.goddess.user.entity.UserDetail;
import com.bjike.goddess.user.service.UserDetailSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 对外提供用户详情接口实现
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-11 15:54]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("userDetailApiImpl")
public class UserDetailApiImpl implements UserDetailAPI {
    @Autowired
    private UserDetailSer userDetailSer;

    @Override
    public UserDetailBO findByUserId(String userId) throws SerException {
        return userDetailSer.findByUserId(userId);
    }
}
