package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.task.dto.TableDTO;
import com.bjike.goddess.task.entity.Table;
import com.bjike.goddess.task.to.TableTO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 表业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class TableSerImpl extends ServiceImpl<Table, TableDTO> implements TableSer {
    @Autowired
    private ProjectSer projectSer;
    @Autowired
    private UserAPI userAPI;

    @Override
    public void add(TableTO to) throws SerException {
        Table table = BeanTransform.copyProperties(to, Table.class);
        table.setProject(projectSer.findById(to.getProjectId()));
        table.setUserId(userAPI.currentUser().getId());
        super.save(table);
    }

    @Override
    public void add(List<TableTO> tos) throws SerException {
        List<Table> tables = new ArrayList<>(tos.size());
        String userId = userAPI.currentUser().getId();
        for (TableTO to : tos) {
            Table table = BeanTransform.copyProperties(to, Table.class);
            table.setProject(projectSer.findById(to.getProjectId()));
            table.setUserId(userId);
            tables.add(table);
        }
        super.save(tables);
    }

    @Override
    public List<Table> list(TableDTO dto) throws SerException {
        return super.findByCis(dto);
    }


}
