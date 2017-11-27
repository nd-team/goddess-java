package com.bjike.goddess.task.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.task.bo.CollectSchemeBO;
import com.bjike.goddess.task.bo.CustomizeBO;
import com.bjike.goddess.task.dto.CollectSchemeDTO;
import com.bjike.goddess.task.to.CollectSchemeTO;

import java.util.List;

/**
 * 汇总方案业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-18 04:33 ]
 * @Description: [ 汇总方案业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CollectSchemeAPI {
    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<CollectSchemeBO> list(CollectSchemeDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    void save(CollectSchemeTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(CollectSchemeTO to) throws SerException;

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 通过id查找
     *
     * @param id
     * @return
     * @throws SerException
     */
    CollectSchemeBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(CollectSchemeDTO dto) throws SerException;

    /**
     * 定时邮件发送
     *
     * @throws SerException
     */
    void quartz() throws SerException;

    /**
     * 及时汇总
     *
     * @param id
     * @return
     * @throws SerException
     */
    String collect(String id) throws SerException;

    /**
     * 汇总字段
     * @param to
     * @return
     * @throws SerException
     */
    List<String> fileds(CollectSchemeTO to) throws SerException;

    /**
     * 现在通报
     * @param to
     * @throws SerException
     */
    void notice(CollectSchemeTO to) throws SerException;

    /**
     * 查看详情
     * @param id
     * @return
     * @throws SerException
     */
    String detail(String id) throws SerException;

    /**
     * 获取所有汇总表
     * @return
     * @throws SerException
     */
    List<CustomizeBO> all() throws SerException;
}