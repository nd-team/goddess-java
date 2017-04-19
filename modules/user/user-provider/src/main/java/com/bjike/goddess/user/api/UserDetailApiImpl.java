package com.bjike.goddess.user.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.bo.UserDetailBO;
import com.bjike.goddess.user.dto.UserDetailDTO;
import com.bjike.goddess.user.service.UserDetailSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
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

    @Override
    public List<UserDetailBO> findByMonth(UserDetailDTO dto ,Integer month) throws SerException {
        return userDetailSer.findByMonth(dto,month);
    }
}
