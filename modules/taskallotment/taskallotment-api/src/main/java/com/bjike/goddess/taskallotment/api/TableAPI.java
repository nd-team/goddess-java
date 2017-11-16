package com.bjike.goddess.taskallotment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.taskallotment.bo.TableBO;
import com.bjike.goddess.taskallotment.dto.ProjectNameDTO;
import com.bjike.goddess.taskallotment.dto.TableDTO;

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
public interface TableAPI {
    /**
     * 根据项目获取项目表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Set<String> tables(TableDTO dto) throws SerException;

    /**
     * 通过id数组查找表名称
     * @param ids
     * @return
     * @throws SerException
     */
    String[] names(String[] ids) throws SerException;

    /**
     * 获取所有表
     *
     * @return
     * @throws SerException
     */
    List<TableBO> tableNames() throws SerException;

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;
}