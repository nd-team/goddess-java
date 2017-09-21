package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.task.dto.RowDTO;
import com.bjike.goddess.task.entity.Row;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * 行业务接口
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-16 08:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface RowSer extends Ser<Row, RowDTO> {
    /**
     * 行数据(api调用请序列化)
     *
     * @throws SerException
     */
    default List<Object> list(RowDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加一行
     *
     * @param fieldValMap
     * @param tableId
     * @param node
     * @throws SerException
     */
    default void add(Map<String, String> fieldValMap, String tableId, String node) throws SerException {

    }

    /**
     * 从excel导入数据
     *
     * @param inputStreams
     * @throws SerException
     */
    default void excelImport(List<InputStream> inputStreams,RowDTO dto) throws SerException {

    }

    /**
     * 导出数据
     *
     * @throws SerException
     */
    default ByteArrayOutputStream excelExport(RowDTO dto) throws SerException {
        return null;
    }

    /**
     * 查询行最大的序列
     *
     * @param tableId
     * @return
     * @throws SerException
     */
    default Integer getSeq(String tableId) throws SerException {
        return null;
    }
    /**
     * 查询行的工作内容
     *
     * @param id
     * @return
     * @throws SerException
     */
    default  String findContent(String id) throws SerException {
        return null;
    }

}
