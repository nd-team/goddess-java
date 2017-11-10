package com.bjike.goddess.taskallotment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.taskallotment.bo.TableBO;
import com.bjike.goddess.taskallotment.dto.TableDTO;
import com.bjike.goddess.taskallotment.entity.Table;
import com.bjike.goddess.taskallotment.enums.Status;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 项目表业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 11:58 ]
 * @Description: [ 项目表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "taskallotmentSerCache")
@Service
public class TableSerImpl extends ServiceImpl<Table, TableDTO> implements TableSer {
    @Override
    public Set<String> tables(TableDTO dto) throws SerException {
        dto.getConditions().add(Restrict.in("projectId", dto.getProjectIds()));
        List<Table> list = super.findByCis(dto);
        return list.stream().map(table -> table.getName()).collect(Collectors.toSet());
    }

    @Override
    public List<TableBO> tableNames() throws SerException {
        TableDTO dto = new TableDTO();
        dto.getConditions().add(Restrict.eq("status", Status.START));
        return BeanTransform.copyProperties(super.findByCis(dto),TableBO.class);
    }
}