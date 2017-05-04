package com.bjike.goddess.workjoin.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.workjoin.bo.TaskJoinBO;
import com.bjike.goddess.workjoin.bo.WorkJoinBO;
import com.bjike.goddess.workjoin.dto.TaskJoinDTO;
import com.bjike.goddess.workjoin.dto.WorkJoinDTO;
import com.bjike.goddess.workjoin.entity.WorkJoin;
import com.bjike.goddess.workjoin.entity.WorkJoinTimeSpecification;
import com.bjike.goddess.workjoin.to.TaskJoinTO;
import com.bjike.goddess.workjoin.to.WorkJoinTO;

import java.util.List;

/**
 * 工作交接业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 05:14 ]
 * @Description: [ 工作交接业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WorkJoinSer extends Ser<WorkJoin, WorkJoinDTO> {
    /**
     * 工作交接列表总条数
     */
    default Long countWorkJoin(WorkJoinDTO workJoinDTO) throws SerException {
        return null;
    }

    /**
     * 一个工作交接
     *
     * @return class WorkJoinBO
     */
    default WorkJoinBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 工作交接
     *
     * @param workJoinDTO 工作交接dto
     * @return class WorkJoinBO
     * @throws SerException
     */
    default List<WorkJoinBO> findListWorkJoin(WorkJoinDTO workJoinDTO) throws SerException {
        return null;
    }

    /**
     * 添加工作交接
     *
     * @param workJoinTO 工作交接数据to
     * @return class WorkJoinBO
     * @throws SerException
     */
    default WorkJoinBO insertWorkJoin(WorkJoinTO workJoinTO) throws SerException {
        return null;
    }

    /**
     * 编辑工作交接
     *
     * @param workJoinTO 工作交接数据to
     * @return class WorkJoinBO
     * @throws SerException
     */
    default WorkJoinBO editWorkJoin(WorkJoinTO workJoinTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除工作交接
     *
     * @param id
     * @throws SerException
     */
    default void removeWorkJoin(String id) throws SerException {

    }

}