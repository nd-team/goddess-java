package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.recruit.bo.LifeOGBO;
import com.bjike.goddess.recruit.dto.LifeOGDTO;
import com.bjike.goddess.recruit.entity.LifeOG;

import java.util.List;

/**
 * 工作对赌业务接口
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:11 ]
 * @Description: [ 工作对赌业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface LifeOGSer extends Ser<LifeOG, LifeOGDTO> {

    List<LifeOGBO> getlist(String name) throws SerException;

    void add(LifeOG lifeOG) throws SerException;

}