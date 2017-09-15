package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.organize.bo.ManagerBO;
import com.bjike.goddess.organize.bo.PositionWorkDetailsBO;
import com.bjike.goddess.organize.dto.PositionWorkDetailsDTO;
import com.bjike.goddess.organize.entity.PositionWorkDetails;
import com.bjike.goddess.organize.to.PositionWorkDetailsTO;

import java.util.List;

/**
 * 岗位工作明细表业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-12 01:41 ]
 * @Description: [ 岗位工作明细表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PositionWorkDetailsSer extends Ser<PositionWorkDetails, PositionWorkDetailsDTO> {
    /**
     * 添加岗位工作明细
     *
     * @param to
     * @return
     * @throws SerException
     */
    default void save(PositionWorkDetailsTO to) throws SerException {
        return;
    }

    /**
     * 修改岗位工作明细信息
     *
     * @param to
     * @throws SerException
     */
    default void update(PositionWorkDetailsTO to) throws SerException {
        return;
    }

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
        return;
    }

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<PositionWorkDetailsBO> maps(PositionWorkDetailsDTO dto) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal(PositionWorkDetailsDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据ID查询岗位工作明细
     *
     * @param id
     * @return
     * @throws SerException
     */
    default PositionWorkDetailsBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 组织结构管理周汇总
     *
     * @param startTime
     * @param endTime
     * @return
     * @throws SerException
     */
    default List<ManagerBO> weekCollect(String startTime, String endTime) throws SerException {
        return null;
    }

    /**
     * 组织结构管理月汇总
     */
    default List<ManagerBO> monthCollect(String month) throws SerException {
        return null;
    }

    /**
     * 组织结构管理累计汇总
     */
    default List<ManagerBO> totalCollect() throws SerException {
        return null;
    }
}