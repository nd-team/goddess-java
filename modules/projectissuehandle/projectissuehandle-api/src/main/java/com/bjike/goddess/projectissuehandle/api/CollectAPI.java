package com.bjike.goddess.projectissuehandle.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectissuehandle.bo.CollectBO;

import java.util.List;

/**
 * 汇总项目执行中的问题受理及处理结果业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-27 10:49 ]
 * @Description: [ 汇总项目执行中的问题受理及处理结果业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CollectAPI {
    /**
     * 汇总
     *
     * @param area area
     * @return class CollectBO
     */
    default List<CollectBO> collect(String[] area) throws SerException {
        return null;
    }


}