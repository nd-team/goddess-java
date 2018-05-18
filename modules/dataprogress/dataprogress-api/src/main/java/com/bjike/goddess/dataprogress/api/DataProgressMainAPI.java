package com.bjike.goddess.dataprogress.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.dataprogress.bo.DataProgressMainBO;
import com.bjike.goddess.dataprogress.dto.DataProgressChildDTO;
import com.bjike.goddess.dataprogress.dto.DataProgressMainDTO;
import com.bjike.goddess.dataprogress.to.DataProgressMainTO;

import java.util.List;

/**
 * 资料收集进度管理主表业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-28 10:03 ]
 * @Description: [ 资料收集进度管理主表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DataProgressMainAPI {
    /**
     * 资料收集进度总条数
     *
     * @param dataProgressChildDTO
     * @return
     * @throws SerException
     */
    Long count(DataProgressChildDTO dataProgressChildDTO) throws SerException;

    /**
     * 资料收集进度id
     *
     * @param id
     * @return
     * @throws SerException
     */
    DataProgressMainBO getId(String id) throws SerException;

    /**
     * 资料收集进度列表
     *
     * @param dataProgressMainDTO
     * @return
     * @throws SerException
     */
    List<DataProgressMainBO> list(DataProgressMainDTO dataProgressMainDTO) throws SerException;

    /**
     * 资料收集进度添加
     *
     * @param to
     * @throws SerException
     */
    void add(DataProgressMainTO to) throws SerException;

    /**
     * 资料收集进度编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(DataProgressMainTO to) throws SerException;

    /**
     * 资料收集进度删除
     *
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;
    /**
     * 资料收集进度跟进
     *
     * @param to
     * @throws SerException
     */
    void follow(DataProgressMainTO to) throws SerException;
}