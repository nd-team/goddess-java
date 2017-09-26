package com.bjike.goddess.task.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.task.bo.TableBO;
import com.bjike.goddess.task.dto.TableDTO;
import com.bjike.goddess.task.to.TableTO;
import com.bjike.goddess.task.vo.TableVO;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-16 08:50]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface TableAPI {
    /**
     * id查询表
     *
     * @param id
     * @return
     * @throws SerException
     */
    default TableBO findById(String id) throws SerException {
        return null;
    }

    /**
     * 添加表
     *
     * @param to
     * @throws SerException
     */
    default void add(TableTO to) throws SerException {

    }


    /**
     * 删除表
     */
    default void delete(String id) throws SerException {

    }

    /**
     * 所有表
     *
     * @param dto
     * @param page
     * @return
     * @throws SerException
     */
    default List<TableVO> list(TableDTO dto,boolean page) throws SerException {
        return null;
    }


    /**
     * 解冻表
     *
     * @param id 表id
     * @throws SerException
     */
    default void thaw(String id) throws SerException {

    }

    /**
     * 冻结表
     * @param id 表id
     * @throws SerException
     */
    default void congeal(String id) throws SerException {

    }

}
