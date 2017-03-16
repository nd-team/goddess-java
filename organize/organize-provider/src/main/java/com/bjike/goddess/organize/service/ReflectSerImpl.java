package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.organize.bo.ReflectBO;
import com.bjike.goddess.organize.dto.ReflectDTO;
import com.bjike.goddess.organize.entity.Reflect;
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
@Service
public class ReflectSerImpl extends ServiceImpl<Reflect,ReflectDTO> implements ReflectSer {


    @Override
    public List<ReflectBO> findStatus() throws SerException {
        return null;
    }
}
