package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.task.dto.TableDTO;
import com.bjike.goddess.task.entity.Project;
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
        String userId = userAPI.currentUser().getId();
        String[] names = to.getNames();
        if (null != names) {
            List<Table> tables = new ArrayList<>(names.length);
            Project project = projectSer.findById(to.getProjectId());
            for (String name : names) {
                Table table = new Table();
                table.setName(name);
                table.setProject(project);
                table.setUserId(userId);
                tables.add(table);
            }
            super.save(tables);
        }

    }


    @Override
    public List<Table> list(TableDTO dto) throws SerException {
        dto.getSorts().add("createTime");
        return super.findByCis(dto);
    }


    @Override
    public List<Table> list(String projectId) throws SerException {
        TableDTO dto = new TableDTO();
        dto.getConditions().add(Restrict.eq("project.id", projectId));
        dto.getSorts().add("createTime");
        return super.findByCis(dto);
    }

    @Override
    public Table findByRowId(String rowId) throws SerException {
        String sql = "select tid from task_row where id='" + rowId + "'";
        List<Object> objects = super.findBySql(sql);
        if(null!=objects && objects.size()>0){
            String tid =  String.valueOf(objects.get(0));
            return super.findById(tid);
        }
       throw  new SerException("找不到数据行");
    }
}
