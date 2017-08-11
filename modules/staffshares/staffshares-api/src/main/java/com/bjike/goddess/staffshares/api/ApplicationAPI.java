package com.bjike.goddess.staffshares.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffshares.bo.ApplicationBO;
import com.bjike.goddess.staffshares.dto.ApplicationDTO;
import com.bjike.goddess.staffshares.to.ApplicationTO;

import java.util.List;

/**
 * 干股代表申请业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 10:27 ]
 * @Description: [ 干股代表申请业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ApplicationAPI {

    /**
     * 申请
     *
     * @param to
     * @throws SerException
     */
    default void save(ApplicationTO to) throws SerException {
    }

    /**
     * 干股代表申请列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<ApplicationBO> maps(ApplicationDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取干股代表申请数据
     *
     * @param id
     * @return
     * @throws SerException
     */
    default ApplicationBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @param applicationDTO
     * @return
     * @throws SerException
     */
    default Long getTotal(ApplicationDTO applicationDTO) throws SerException {
        return null;
    }

    /**
     * 审核
     *
     * @param to
     * @throws SerException
     */
    default void examine(ApplicationTO to) throws SerException {
    }
}