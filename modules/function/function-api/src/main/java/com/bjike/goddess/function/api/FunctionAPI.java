package com.bjike.goddess.function.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.function.bo.FunctionBO;
import com.bjike.goddess.function.enums.FunctionType;
import com.bjike.goddess.function.to.FunctionTO;

import java.util.List;

/**
 * 模块功能业务接口
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-22 11:07 ]
 * @Description: [ 模块功能业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FunctionAPI {

    /**
     * 添加功能
     *
     * @param functionTO
     * @return
     */
    default FunctionBO add(FunctionTO functionTO) throws SerException {
        return null;
    }

    /**
     * 编辑功能
     *
     * @param functionTO
     */
    default void edit(FunctionTO functionTO) throws SerException {

    }

    /**
     * 开关功能
     *
     * @param id
     * @param enable
     */
    default void enable(String id, Boolean enable) throws SerException {

    }

    /**
     * 删除功能
     *
     * @param id
     */
    default void delete(String id) throws SerException {

    }

    /**
     * 通过类型获取功能列表
     *
     * @return
     */
    default List<FunctionBO> list(FunctionType type) throws SerException {
        return null;
    }

    /**
     * 用户功能
     *
     * @return
     */
    default List<FunctionBO> userFunctions() throws SerException {
        return null;
    }

}