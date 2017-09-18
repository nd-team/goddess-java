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
    default TableBO findById(String id) throws SerException {
        return null;
    }
    default void add(TableTO to) throws SerException {

    }

    default void add(List<TableTO> tos) throws SerException {

    } default void delete(String id) throws SerException {

    }

    default List<TableVO> list(TableDTO dto) throws SerException {
        return null;
    }
}
