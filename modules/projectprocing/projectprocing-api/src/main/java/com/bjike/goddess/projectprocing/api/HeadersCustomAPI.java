package com.bjike.goddess.projectprocing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectprocing.bo.HeadersCustomBO;
import com.bjike.goddess.projectprocing.dto.HeadersCustomDTO;
import com.bjike.goddess.projectprocing.to.HeadersCustomTO;

import java.util.List;

/**
 * 表头定制业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:26 ]
 * @Description: [ 表头定制业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface HeadersCustomAPI {
    /**
     * 表头定制总条数
     */
    default Long countHeaders(HeadersCustomDTO headersCustomDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取结算流程存储记录
     *
     * @return class HeadersCustomBO
     */
    default HeadersCustomBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 表头定制列表
     *
     * @return class HeadersCustomBO
     */
    default List<HeadersCustomBO> listHeaders(HeadersCustomDTO headersCustomDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param headersCustomTO 表头定制
     * @return class HeadersCustomBO
     */
    default HeadersCustomBO addHeaders(HeadersCustomTO headersCustomTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param headersCustomTO 表头定制
     * @return class HeadersCustomBO
     */
    default HeadersCustomBO editHeaders(HeadersCustomTO headersCustomTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteHeaders(String id) throws SerException {
        return;
    }

    /**
     * 根据外包单位获取所有表头字段
     *
     * @return class HeadersCustomBO
     */
    default List<HeadersCustomBO> getHeaderByOutUnit(String outUnit) throws SerException {
        return null;
    }
}