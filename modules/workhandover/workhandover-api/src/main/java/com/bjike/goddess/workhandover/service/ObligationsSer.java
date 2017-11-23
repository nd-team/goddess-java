package com.bjike.goddess.workhandover.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.workhandover.bo.ObligationsBO;
import com.bjike.goddess.workhandover.entity.Obligations;
import com.bjike.goddess.workhandover.dto.ObligationsDTO;
import com.bjike.goddess.workhandover.to.ObligationsTO;

import java.util.List;

/**
 * 工作交接时间规范业务接口
 *
 * @Author: [ chenyang ]
 * @Date: [ 2017-11-13 06:26 ]
 * @Description: [ 工作交接时间规范业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ObligationsSer extends Ser<Obligations, ObligationsDTO> {

    /**
     * @描述 工作交接时间规范列表
     * @参数 [dto]
     * @返回值 java.util.List<com.bjike.goddess.workhandover.bo.workTimeSpecificationBO>
     * @创建人 chenyang
     * @创建时间 17-11-11
     * @修改人和其它信息
     */
    default List<ObligationsBO> findObligations(ObligationsDTO dto) throws SerException {

        return null;
    }

    /**
     * @描述 添加工作交接时间规范
     * @参数 [to]
     * @返回值 com.bjike.goddess.workhandover.bo.workTimeSpecificationBO
     * @创建人 chenyang
     * @创建时间 17-11-11
     * @修改人和其它信息
     */
    default ObligationsBO insertObligations(ObligationsTO to) throws SerException {

        return null;
    }

    /**
     * @描述 修改工作交接时间规范
     * @参数 [to]
     * @返回值 com.bjike.goddess.workhandover.bo.workTimeSpecificationBO
     * @创建人 chenyang
     * @创建时间 17-11-11
     * @修改人和其它信息
     */
    default ObligationsBO editObligations(ObligationsTO to) throws SerException {

        return null;
    }

    /**
     * @描述 根据id删除工作交接时间规范
     * @参数 [id]
     * @返回值 void
     * @创建人 chenyang
     * @创建时间 17-11-11
     * @修改人和其它信息
     */
    default void removeObligations(String id) throws SerException {

    }

}