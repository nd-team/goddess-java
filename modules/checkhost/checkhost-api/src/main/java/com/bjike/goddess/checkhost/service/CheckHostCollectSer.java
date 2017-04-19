package com.bjike.goddess.checkhost.service;

import com.bjike.goddess.checkhost.bo.CheckHostCollectBO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.checkhost.entity.CheckHostCollect;
import com.bjike.goddess.checkhost.dto.CheckHostCollectDTO;

import java.util.List;

/**
 * 汇总表业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 05:13 ]
 * @Description: [ 汇总表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CheckHostCollectSer extends Ser<CheckHostCollect, CheckHostCollectDTO> {

    /**
     * 汇总表
     * @return class CheckHostCollectBO
     */
    default CheckHostCollectBO listCheckHostCollect(String name) throws SerException {
        return null;
    }

}