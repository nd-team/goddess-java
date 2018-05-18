package com.bjike.goddess.task.api;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.task.dto.RowDTO;
import com.bjike.goddess.task.service.RowSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * 行业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("rowApiImpl")
public class RowApiImpl implements RowAPI {
    @Autowired
    private RowSer rowSer;

    @Override
    public void add(Map<String, String> fieldValMap, String tableId, String node) throws SerException {
        rowSer.add(fieldValMap, tableId, node);

    }

    @Override
    public void delete(String id) throws SerException {
        rowSer.remove(id);
    }

    @Override
    public String list(RowDTO dto) throws SerException {
        return JSON.toJSONString(rowSer.list(dto));
    }

    @Override
    public Long count(RowDTO dto) throws SerException {
        return rowSer.count(dto);
    }

    @Override
    public void excelImport(List<InputStream> inputStreams, RowDTO dto) throws SerException {
        rowSer.excelImport(inputStreams, dto);
    }

    @Override
    public byte[] excelExport(RowDTO dto) throws SerException {
        return rowSer.excelExport(dto).toByteArray();
    }
}
