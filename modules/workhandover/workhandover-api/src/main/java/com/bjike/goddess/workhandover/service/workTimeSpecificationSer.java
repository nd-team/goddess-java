package com.bjike.goddess.workhandover.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.workhandover.bo.workTimeSpecificationBO;
import com.bjike.goddess.workhandover.entity.workTimeSpecification;
import com.bjike.goddess.workhandover.dto.workTimeSpecificationDTO;
import com.bjike.goddess.workhandover.to.workTimeSpecificationTO;

import java.util.List;

/**
 * 责任与义务业务接口
 *
 * @Author: [ chenyang ]
 * @Date: [ 2017-11-11 04:47 ]
 * @Description: [ 责任与义务业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface workTimeSpecificationSer extends Ser<workTimeSpecification, workTimeSpecificationDTO> {

    /**
     * @描述  责任与义务列表
     * @参数 [dto]
     * @返回值 java.util.List<com.bjike.goddess.workhandover.bo.workTimeSpecificationBO>
     * @创建人 chenyang
     * @创建时间 17-11-11
     * @修改人和其它信息
     */
    default List<workTimeSpecificationBO> findworkTimeSpecification(workTimeSpecificationDTO dto) throws SerException {

        return null;
    }

    /**
     * @描述  添加责任与义务
     * @参数 [to]
     * @返回值 com.bjike.goddess.workhandover.bo.workTimeSpecificationBO
     * @创建人 chenyang
     * @创建时间 17-11-11
     * @修改人和其它信息
     */
    default workTimeSpecificationBO insertworkTimeSpecification(workTimeSpecificationTO to) throws SerException {

        return null;
    }

    /**
     * @描述  修改责任与义务
     * @参数 [to]
     * @返回值 com.bjike.goddess.workhandover.bo.workTimeSpecificationBO
     * @创建人 chenyang
     * @创建时间 17-11-11
     * @修改人和其它信息
     */
    default workTimeSpecificationBO editworkTimeSpecification(workTimeSpecificationTO to) throws SerException {

        return null;
    }

    /**
     * @描述  根据id删除责任与义务
     * @参数 [id]
     * @返回值 void
     * @创建人 chenyang
     * @创建时间 17-11-11
     * @修改人和其它信息
     */
    default void removeworkTimeSpecification(String id) throws SerException {

    }

    /**
     * 获取工作交接原因
     * @return
     * @throws SerException
     */
    default List<String> findWorkHandoverReason() throws SerException {
        return null;
    }

    /**
     * 导入
     * @param to
     * @return
     * @throws SerException
     */
    default workTimeSpecificationBO importExcel(List<workTimeSpecificationTO> to) throws SerException {

        return null;
    }

    /**
     * 导出Excel
     *
     * @param dto
     * @throws SerException
     */
    byte[] exportExcel(workTimeSpecificationDTO dto) throws SerException;

    /**
     * 导出Excel导入模板
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;

}