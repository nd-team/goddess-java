package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.ReflectBO;
import com.bjike.goddess.organize.service.ReflectSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 体现类型操作业务实现
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午11:29]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@Service("reflectApiImpl")
public class ReflectApiImpl implements ReflectAPI {

    @Autowired
    private ReflectSer reflectSer;

    @Override
    public List<ReflectBO> findStatus() throws SerException {
        return reflectSer.findStatus();
    }
}
