package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.task.dto.TableDTO;
import com.bjike.goddess.task.entity.Table;
import com.bjike.goddess.task.to.TableTO;

import java.util.List;

/**
 * 表业务接口
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-16 08:50]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface TableSer extends Ser<Table, TableDTO> {
    /**
     * 添加表
     * @param to
     * @throws SerException
     */
    default void add(TableTO to) throws SerException {
    }

    /**
     * 表列表
     * @param dto
     * @return
     * @throws SerException
     */
    default List<Table> list(TableDTO dto) throws SerException {
        return null;
    }

    /**
     * 通过id查询表
     * @param ids
     * @return
     * @throws SerException
     */
    default List<Table> findById(String ... ids) throws SerException {
        return null;
    }

    /**
     * 查询某项目下的表
     * @param projectId
     * @return
     * @throws SerException
     */
    default List<Table> list(String projectId) throws SerException {
        return null;
    }

    /**
     * 查询某行归属于哪个表
     * @param rowId
     * @return
     * @throws SerException
     */
    default Table findByRowId(String rowId) throws SerException {
        return null;
    }

}
