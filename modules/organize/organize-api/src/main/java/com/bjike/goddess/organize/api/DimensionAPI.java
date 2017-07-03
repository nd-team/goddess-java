package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.DesignNumberInfoBO;
import com.bjike.goddess.organize.bo.DimensionBO;
import com.bjike.goddess.organize.dto.DimensionDTO;
import com.bjike.goddess.organize.to.DimensionTO;

import java.util.List;

/**
 * 对外维度业务接口
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:13]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface DimensionAPI {

    /**
     * 查询未冻结维度
     *
     * @return
     * @throws SerException
     */
    default List<DimensionBO> findStatus() throws SerException {
        return null;
    }

    /**
     * 保存维度
     *
     * @param to 维度传输对象
     * @return
     * @throws SerException
     */
    default DimensionBO save(DimensionTO to) throws SerException {
        return null;
    }

    /**
     * 修改维度
     *
     * @param to 维度传输对象
     * @return
     * @throws SerException
     */
    default DimensionBO update(DimensionTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 维度数据id
     * @return
     * @throws SerException
     */
    default DimensionBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 关闭
     *
     * @param id 维度数据id
     * @return
     * @throws SerException
     */
    default DimensionBO close(String id) throws SerException {
        return null;
    }

    /**
     * 开启
     *
     * @param id 维度数据id
     * @return
     * @throws SerException
     */
    default DimensionBO open(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 维度数据传输对象
     * @return
     * @throws SerException
     */
    default List<DimensionBO> maps(DimensionDTO dto) throws SerException {
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
    default DimensionBO findById(String id) throws SerException {
        return null;
    }

}
