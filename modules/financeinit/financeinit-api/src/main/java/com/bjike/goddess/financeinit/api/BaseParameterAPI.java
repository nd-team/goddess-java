package com.bjike.goddess.financeinit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.financeinit.bo.BaseParameterBO;
import com.bjike.goddess.financeinit.dto.BaseParameterDTO;
import com.bjike.goddess.financeinit.to.BaseParameterTO;

import java.util.List;

/**
 * 基本参数
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 04:11 ]
 * @Description: [ 基本参数 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BaseParameterAPI {
    /**
     * 基本参数列表总条数
     */
    default Long countBasicPara(BaseParameterDTO baseParameterDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取基本参数
     *
     * @return class BaseParameterBO
     */
    default BaseParameterBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 基本参数列表
     *
     * @return class BaseParameterBO
     */
    default List<BaseParameterBO> listBasicPara(BaseParameterDTO baseParameterDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param baseParameterTO 基本参数
     * @return class BaseParameterBO
     */
    default BaseParameterBO addBasicPara(BaseParameterTO baseParameterTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param baseParameterTO 基本参数
     * @return class BaseParameterBO
     */
    default BaseParameterBO editBasicPara(BaseParameterTO baseParameterTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 基本参数id
     */
    default void deleteBasicPara(String id) throws SerException {
        return;
    }
}