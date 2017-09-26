package com.bjike.goddess.task.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.task.bo.FieldBO;
import com.bjike.goddess.task.service.FieldSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-19 11:10]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("nodeApiImpl")
public class NodeApiImpl implements NodeAPI {
    @Autowired
    private FieldSer fieldSer;

    @Override
    public List<String> node(String tableId) throws SerException {
        String sql = "SELECT node FROM task_field WHERE tid = '" + tableId + "' GROUP BY node";
        List<Object> objects = fieldSer.findBySql(sql);
        List<String> nodeNames = new ArrayList<>(objects.size());
        for (Object o : objects) {
            nodeNames.add(String.valueOf(o));
        }
        return nodeNames;
    }

    @Override
    public List<FieldBO> detail(String tableId, String node) throws SerException {
        String sql = " SELECT id,name,is_need as need,seq,type FROM task_field " +
                " WHERE tid = '" + tableId + "' " +
                " AND node =" + node + " ";
        String[] fields = new String[]{"id", "name", "need", "seq", "type"};
        return fieldSer.findBySql(sql, FieldBO.class, fields);
    }
}
