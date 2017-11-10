package com.bjike.goddess.taskallotment.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.taskallotment.bo.TableBO;
import com.bjike.goddess.taskallotment.dto.TableDTO;
import com.bjike.goddess.taskallotment.entity.Table;

import java.util.List;
import java.util.Set;

/**
 * 项目表业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 11:58 ]
 * @Description: [ 项目表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TableSer extends Ser<Table, TableDTO> {
    /**
     * 根据项目获取项目表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Set<String> tables(TableDTO dto) throws SerException;

    /**
     * 获取所有表
     *
     * @return
     * @throws SerException
     */
    List<TableBO> tableNames() throws SerException;
}