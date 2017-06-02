package com.bjike.goddess.annual.api;

import com.bjike.goddess.annual.bo.AnnualInfoBO;
import com.bjike.goddess.annual.dto.AnnualInfoDTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 年假信息业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 03:30 ]
 * @Description: [ 年假信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AnnualInfoAPI {

    /**
     * 获取指定用户的年假信息
     *
     * @param username 用户名
     * @return
     * @throws SerException
     */
    default List<AnnualInfoBO> findByUsername(String username) throws SerException {
        return null;
    }

    /**
     * 根据用户名查询年假信息
     *
     * @param username 用户名
     * @return
     * @throws SerException
     */
    default List<AnnualInfoBO> findByUsers(String... username) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 年假信息数据传输对象
     * @return
     * @throws SerException
     */
    default List<AnnualInfoBO> maps(AnnualInfoDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取年假信息数据
     *
     * @param id 年假信息数据id
     * @return
     * @throws SerException
     */
    default AnnualInfoBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }
}