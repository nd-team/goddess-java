package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.DesignNumberInfoBO;
import com.bjike.goddess.organize.dto.DesignNumberInfoDTO;
import com.bjike.goddess.organize.to.DesignNumberInfoTO;

import java.util.List;

/**
 * 对外编号设计信息业务接口
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:11]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface DesignNumberInfoAPI {

    /**
     * 保存编号设计信息
     *
     * @param to 编号设计细心传输对象
     * @return
     * @throws SerException
     */
    default DesignNumberInfoBO save(DesignNumberInfoTO to) throws SerException {
        return null;
    }

    /**
     * 修改编号设计信息
     *
     * @param to 编号设计信息传输对象
     * @return
     * @throws SerException
     */
    default DesignNumberInfoBO update(DesignNumberInfoTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 编号设计信息数据id
     * @return
     * @throws SerException
     */
    default DesignNumberInfoBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 编号设计信息数据传输对象
     * @return
     * @throws SerException
     */
    default List<DesignNumberInfoBO> maps(DesignNumberInfoDTO dto) throws SerException {
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

    /**
     * 根据id查询编号设计信息数据
     *
     * @param id 编号设计信息数据id
     * @return
     * @throws SerException
     */
    default DesignNumberInfoBO findById(String id) throws SerException {
        return null;
    }

}
