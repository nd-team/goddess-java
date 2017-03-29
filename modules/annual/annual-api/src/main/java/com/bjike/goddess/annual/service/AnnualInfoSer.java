package com.bjike.goddess.annual.service;

import com.bjike.goddess.annual.bo.AnnualInfoBO;
import com.bjike.goddess.annual.dto.AnnualInfoDTO;
import com.bjike.goddess.annual.entity.AnnualInfo;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

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
public interface AnnualInfoSer extends Ser<AnnualInfo, AnnualInfoDTO> {

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
     * 生成年假信息数据
     *
     * @throws SerException
     */
    void generate() throws SerException;

}