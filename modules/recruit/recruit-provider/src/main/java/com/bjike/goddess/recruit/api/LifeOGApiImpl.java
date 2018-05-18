package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.LifeOGBO;
import com.bjike.goddess.recruit.entity.LifeOG;
import com.bjike.goddess.recruit.service.LifeOGSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工作对赌业务接口实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:11 ]
 * @Description: [ 工作对赌业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("lifeOGApiImpl")
public class LifeOGApiImpl implements LifeOGAPI {

    @Autowired
    private LifeOGSer lifeOGSer;

    @Override
    public List<LifeOGBO> getlist(String name) throws SerException {
        return lifeOGSer.getlist(name);
    }

    @Override
    public void add(LifeOG lifeOG) throws SerException {
        lifeOGSer.add(lifeOG);
    }
}