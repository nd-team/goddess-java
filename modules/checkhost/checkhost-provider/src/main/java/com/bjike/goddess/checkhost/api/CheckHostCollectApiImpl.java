package com.bjike.goddess.checkhost.api;

import com.bjike.goddess.checkhost.bo.CheckHostCollectBO;
import com.bjike.goddess.checkhost.dto.CheckHostCollectDTO;
import com.bjike.goddess.checkhost.service.CheckHostCollectSer;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 汇总表业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 05:13 ]
 * @Description: [ 汇总表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("checkHostCollectApiImpl")
public class CheckHostCollectApiImpl implements CheckHostCollectAPI {
    @Autowired
    private CheckHostCollectSer checkHostCollectSer;
    @Override
    public CheckHostCollectBO listCheckHostCollect(String name) throws SerException {
        return checkHostCollectSer.listCheckHostCollect(name);
    }

}