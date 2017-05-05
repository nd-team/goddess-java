package com.bjike.goddess.courier.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.courier.bo.CourierBO;
import com.bjike.goddess.courier.bo.CourierCompanyBO;
import com.bjike.goddess.courier.dto.CourierCompanyDTO;
import com.bjike.goddess.courier.dto.CourierDTO;
import com.bjike.goddess.courier.entity.CourierCompany;
import com.bjike.goddess.courier.to.CourierCompanyTO;
import com.bjike.goddess.courier.to.CourierTO;

import java.util.List;

/**
 * 快递公司信息业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-28 10:29 ]
 * @Description: [ 快递公司信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CourierCompanyAPI {
    /**
     * 添加
     *
     * @param to 快递公司信息
     * @return class CourierCompanyBO
     * @throws SerException
     */
    default CourierCompanyBO save(CourierCompanyTO to) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 快递公司信息
     * @return class CourierCompanyBO
     * @throws SerException
     */
    default CourierCompanyBO edit(CourierCompanyTO to) throws SerException {
        return null;
    }

    /**
     * 分页查找
     *
     * @param dto 快递公司分页信息
     * @return class CourierCompanyBO
     * @throws SerException
     */
    default List<CourierCompanyBO> list(CourierCompanyDTO dto) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 快递公司id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
    }

    /**
     * 通过id查找
     *
     * @param id 快递公司id
     * @return class CourierCompanyBO
     * @throws SerException
     */
    default CourierCompanyBO findByID(String id) throws SerException {
        return null;
    }
}