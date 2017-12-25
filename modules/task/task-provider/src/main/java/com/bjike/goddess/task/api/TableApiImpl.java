package com.bjike.goddess.task.api;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.task.bo.TableBO;
import com.bjike.goddess.task.dto.TableDTO;
import com.bjike.goddess.task.entity.Table;
import com.bjike.goddess.task.service.TableSer;
import com.bjike.goddess.task.to.TableTO;
import com.bjike.goddess.task.vo.TableVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("tableApiImpl1")
public class TableApiImpl implements TableAPI {
    @Autowired
    private TableSer tableSer;

    @Override
    public void add(TableTO to) throws SerException {
        tableSer.add(to);
    }

    @Override
    public TableBO findById(String id) throws SerException {
        Table table = tableSer.findById(id);
        if (null != table) {
            return BeanTransform.copyProperties(table, TableBO.class);
        } else {
            throw new SerException("该表不存在");
        }
    }


    @Override
    public void delete(String id) throws SerException {
        tableSer.remove(id);
    }

    @Override
    public List<TableVO> list(TableDTO dto, boolean page) throws SerException {
        if (StringUtils.isNotBlank(dto.getProjectId())) {
            dto.getConditions().add(Restrict.eq("project.id", dto.getProjectId()));
        }
        if (null != dto.getExecStatus()) {
            dto.getConditions().add(Restrict.eq("execStatus", dto.getExecStatus()));
        }
        if (null != dto.getStatus()) {
            dto.getConditions().add(Restrict.eq("status", dto.getStatus()));
        }
        List<Table> tables = tableSer.findByCis(dto, page);
        return BeanTransform.copyProperties(tables, TableVO.class);
    }


    @Override
    public void thaw(String id) throws SerException {
        Table table = tableSer.findById(id);
        if (null != table) {
            table.setStatus(Status.THAW);
            tableSer.update(table);
        } else {
            throw new SerException("项目表不存在");
        }
    }

    @Override
    public void congeal(String id) throws SerException {
        Table table = tableSer.findById(id);
        if (null != table) {
            table.setStatus(Status.CONGEAL);
            tableSer.update(table);
        } else {
            throw new SerException("项目表不存在");
        }
    }
}
