package com.bjike.goddess.dimission.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.dimission.bo.WorkHandoverBO;
import com.bjike.goddess.dimission.dto.WorkHandoverDTO;
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
public interface WorkHandoverAPI {

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
     *
     * @param to 工作交接传输对象
     * @return
     * @throws SerException
     */
    default WorkHandoverBO update(WorkHandoverTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 工作交接数据id
     * @return
     * @throws SerException
     */
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

    /**
     * 列表
     *
     * @param dto 工作交接数据传输对象
     * @return
     * @throws SerException
     */
    default List<WorkHandoverBO> maps(WorkHandoverDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取工作交接数据
     *
     * @param id 工作交接数据id
     * @return
     * @throws SerException
     */
    default WorkHandoverBO getById(String id) throws SerException {
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