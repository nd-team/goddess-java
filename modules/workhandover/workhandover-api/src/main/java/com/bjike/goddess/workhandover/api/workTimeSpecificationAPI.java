package com.bjike.goddess.workhandover.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.workhandover.bo.workTimeSpecificationBO;
import com.bjike.goddess.workhandover.dto.workTimeSpecificationDTO;
import com.bjike.goddess.workhandover.to.workTimeSpecificationTO;

import java.util.List;

/**
 * 工作交接时间规范业务接口
 *
 * @Author: [ chenyang ]
 * @Date: [ 2017-11-11 04:47 ]
 * @Description: [ 工作交接时间规范业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface workTimeSpecificationAPI {

    /**
     * @描述  工作时间规范列表
     * @参数 [to]
     * @返回值 java.util.List<com.bjike.goddess.workhandover.bo.workTimeSpecificationBO>
     * @创建人 chenyang
     * @创建时间 17-11-11
     * @修改人和其它信息
     */
    default List<workTimeSpecificationBO> findWorkTimeSpecification(workTimeSpecificationDTO to) throws SerException {

        return null;
    }

    /**
     * @描述  添加工作时间规范
     * @参数 [to]
     * @返回值 com.bjike.goddess.workhandover.bo.workTimeSpecificationBO
     * @创建人 chenyang
     * @创建时间 17-11-11
     * @修改人和其它信息
     */
    default workTimeSpecificationBO insertWorkTimeSpecification(workTimeSpecificationTO to) throws SerException {

        return null;
    }

    /**
     * @描述  修改工作时间规范
     * @参数 [to]
     * @返回值 com.bjike.goddess.workhandover.bo.workTimeSpecificationBO
     * @创建人 chenyang
     * @创建时间 17-11-11
     * @修改人和其它信息
     */
    default workTimeSpecificationBO editWorkTimeSpecification(workTimeSpecificationTO to) throws SerException {

        return null;
    }

    /**
     * @描述  根据id删除时间规范
     * @参数 [id]
     * @返回值 void
     * @创建人 chenyang
     * @创建时间 17-11-11
     * @修改人和其它信息
     */
    default void removeWorkTimeSpecification(String id) throws SerException {

    }

    List<String> findWorkHandoverReason() throws SerException;

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