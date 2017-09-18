package com.bjike.goddess.task.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.task.dto.RowDTO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-16 08:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface RowAPI {
    /**
     * 行数据(api调用请序列化)
     *
     * @throws SerException
     */
    default String list(RowDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加数据行
     * @param fieldValMap
     * @param tableId
     * @param node
     * @throws SerException
     */

    default void add(Map<String, String> fieldValMap, String tableId, String node) throws SerException {

    }
    /**
     * 删除数据行
     * @param id
     * @throws SerException
     */

    default void delete(String id) throws SerException {

    }


    /**
     * 从excel导入数据
     *
     * @param inputStreams
     * @throws SerException
     */
    default void excelImport(List<InputStream> inputStreams, RowDTO dto) throws SerException {

    }

    /**
     * 导出数据
     *
     * @param dto
     * @throws SerException
     */
    default byte[] excelExport(RowDTO dto) throws SerException {
        return  null;
    }

}
