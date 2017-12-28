package com.bjike.goddess.capability.api;

import com.bjike.goddess.capability.bo.InProjctBO;
import com.bjike.goddess.common.api.exception.SerException;

/**
 * 尚在进行中项目数业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-16 06:23 ]
 * @Description: [ 尚在进行中项目数业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InProjctAPI {
    /**
     * 添加
     *
     * @param inProjcts 尚在进行中项目数信息
     * @param companyId 公司id
     * @return class InProjctBO
     */
    default InProjctBO addInProjct(String[] inProjcts, String companyId) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param inProjcts 尚在进行中项目数信息
     * @param companyId 公司id
     * @return class InProjctBO
     */
    default InProjctBO editInProjct(String[] inProjcts, String companyId) throws SerException {
        return null;
    }

    /**
     * 删除尚在进行中项目数
     *
     * @param id id
     */
    default void deleteInProjct(String id) throws SerException {
        return;
    }

    ;


}