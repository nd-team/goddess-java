package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.task.dto.FieldDTO;
import com.bjike.goddess.task.entity.Field;
import com.bjike.goddess.task.to.FieldTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 列业务接口
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class FieldSerImpl extends ServiceImpl<Field, FieldDTO> implements FieldSer {
    @Autowired
    private TableSer tableSer;

    @Override
    public void add(FieldTO to) throws SerException {
        String[] names = to.getNames();
        Set<String> set = new HashSet<>();
        set.addAll(Arrays.asList(names));
        if(set.size() == names.length){
            String str_name = StringUtils.join(names,"','");
            List<Field> fields = new ArrayList<>(names.length);
            Integer seq = getSeq(to.getTableId(), to.getNode());
            List<Object> objects =  super.findBySql("SELECT name  FROM task_field WHERE node='"+to.getNode()+"' && name IN('"+str_name+"')");
            if(null==objects || objects.size()==0){
                for (String name : names) {
                    Field field = BeanTransform.copyProperties(to, Field.class);
                    field.setTable(tableSer.findById(to.getTableId()));
                    field.setName(name);
                    field.setSeq(seq++);
                    fields.add(field);
                }
                super.save(fields);
            }else {
                throw  new SerException("["+StringUtils.join(objects,",")+"]列名不可重复使用");
            }
        }else {
            throw  new SerException("["+ StringUtils.join(names,",")+"]"+"不能包含重复列名");
        }


    }


    @Override
    public List<Field> list(String tableId, String node) throws SerException {
        FieldDTO dto = new FieldDTO();
        dto.getConditions().add(Restrict.eq("table.id",tableId));
        dto.getConditions().add(Restrict.eq("node",node));
        dto.getSorts().add("seq=ASC");
        return super.findByCis(dto);
    }
    @Override
    public Integer getSeq(String tableId, String node) throws SerException {
        String sql = "SELECT IFNULL(MAX(seq),0) AS seq FROM task_field WHERE tid = '%s' AND node = '%s'";
        sql = String.format(sql, tableId, node);
        List<Object> objs = super.findBySql(sql);
        return Integer.parseInt(String.valueOf(objs.get(0)));

    }
}
