package com.bjike.goddess.workjoin.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.workjoin.bo.WorkJoinBO;
import com.bjike.goddess.workjoin.bo.WorkJoinTimeSpecificationBO;
import com.bjike.goddess.workjoin.dto.WorkJoinDTO;
import com.bjike.goddess.workjoin.dto.WorkJoinTimeSpecificationDTO;
import com.bjike.goddess.workjoin.entity.WorkJoinTimeSpecification;
import com.bjike.goddess.workjoin.to.GuidePermissionTO;
import com.bjike.goddess.workjoin.to.WorkJoinTO;
import com.bjike.goddess.workjoin.to.WorkJoinTimeSpecificationTO;

import java.util.List;

/**
 * 工作交接时间规范业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 05:18 ]
 * @Description: [ 工作交接时间规范业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WorkJoinTimeSpecificationSer extends Ser<WorkJoinTimeSpecification, WorkJoinTimeSpecificationDTO> {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 工作交接时间规范列表总条数
     */
    default Long countWorkJoinTimeSpecification(WorkJoinTimeSpecificationDTO workJoinTimeSpecificationDTO) throws SerException {
        return null;
    }

    /**
     * 一个工作交接时间规范
     *
     * @return class WorkJoinTimeSpecificationBO
     */
    default WorkJoinTimeSpecificationBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 工作交接时间规范
     *
     * @param workJoinTimeSpecificationDTO 工作交接时间规范dto
     * @return class WorkJoinTimeSpecificationBO
     * @throws SerException
     */
    default List<WorkJoinTimeSpecificationBO> findListWorkJoinTimeSpecification(WorkJoinTimeSpecificationDTO workJoinTimeSpecificationDTO) throws SerException {
        return null;
    }

    /**
     * 添加工作交接时间规范
     *
     * @param workJoinTimeSpecificationTO 工作交接时间规范数据to
     * @return class WorkJoinTimeSpecificationBO
     * @throws SerException
     */
    default WorkJoinTimeSpecificationBO insertWorkJoinTimeSpecification(WorkJoinTimeSpecificationTO workJoinTimeSpecificationTO) throws SerException {
        return null;
    }

    /**
     * 编辑工作交接时间规范
     *
     * @param workJoinTimeSpecificationTO 工作交接时间规范数据to
     * @return class WorkJoinTimeSpecificationBO
     * @throws SerException
     */
    default WorkJoinTimeSpecificationBO editWorkJoinTimeSpecification(WorkJoinTimeSpecificationTO workJoinTimeSpecificationTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除工作交接时间规范
     *
     * @param id
     * @throws SerException
     */
    default void removeWorkJoinTimeSpecification(String id) throws SerException {

    }
}