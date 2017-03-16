package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.organize.bo.ReflectBO;
import com.bjike.goddess.organize.dto.ReflectDTO;
import com.bjike.goddess.organize.entity.Reflect;

import java.util.List;

/**
 * 体现类型业务接口
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:31]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ReflectSer extends Ser<Reflect, ReflectDTO> {

    /**
     * 查询未冻结体现类型
     *
     * @return
     * @throws SerException
     */
    default List<ReflectBO> findStatus() throws SerException {
        return null;
    }

}
