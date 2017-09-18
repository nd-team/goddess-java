package com.bjike.goddess.projectroyalty.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectroyalty.to.WeightalTO;

/**
 * 项目提成权重分配表业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-14 01:55 ]
 * @Description: [ 项目提成权重分配表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WeightalAPI {

    /**
     * 保存项目提成权重分配表
     *
     * @param to
     * @throws SerException
     */
    default void save(WeightalTO to) throws SerException {
        return;
    }
}