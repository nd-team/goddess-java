package com.bjike.goddess.dimission.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.dimission.bo.WorkHandoverBO;
import com.bjike.goddess.dimission.dto.WorkHandoverDTO;
import com.bjike.goddess.dimission.entity.WorkHandover;
import com.bjike.goddess.dimission.to.HandoverSuccessTO;
import com.bjike.goddess.dimission.to.WorkHandoverTO;

import java.util.List;

/**
 * 工作交接业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-17 02:24 ]
 * @Description: [ 工作交接业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WorkHandoverSer extends Ser<WorkHandover, WorkHandoverDTO> {

    /**
     * 保存
     *
     * @param to 工作交接传输对象
     * @return
     * @throws SerException
     */
    default WorkHandoverBO save(WorkHandoverTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     * @param to 工作交接传输对象
     * @return
     * @throws SerException
     */
    default WorkHandoverBO update(WorkHandoverTO to) throws SerException {
        return null;
    }

    default WorkHandoverBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 确认
     *
     * @param to 工作交接确认信息传输对象
     * @return
     * @throws SerException
     */
    default WorkHandoverBO success(HandoverSuccessTO to) throws SerException {
        return null;
    }


    default List<WorkHandoverBO> maps(WorkHandoverDTO dto) throws SerException {
        return null;
    }

}