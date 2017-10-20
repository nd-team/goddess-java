package com.bjike.goddess.taskallotment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.taskallotment.dto.TableDTO;
import com.bjike.goddess.taskallotment.entity.Table;
import com.bjike.goddess.taskallotment.service.TableSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 项目表业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 11:58 ]
 * @Description: [ 项目表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("tableApiImpl")
public class TableApiImpl implements TableAPI {
    @Autowired
    private TableSer tableSer;

    @Override
    public Set<String> tables(TableDTO dto) throws SerException {
        return tableSer.tables(dto);
    }

    @Override
    public String[] names(String[] ids) throws SerException {
        List<String> list=new ArrayList<>();
        for (String s:ids){
            Table table=tableSer.findById(s);
            list.add(table.getName());
        }
        String[] strings=new String[list.size()];
        strings=list.toArray(strings);
        return strings;
    }
}