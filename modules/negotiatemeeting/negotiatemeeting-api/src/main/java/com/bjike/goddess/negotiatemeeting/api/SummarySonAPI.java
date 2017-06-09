package com.bjike.goddess.negotiatemeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.negotiatemeeting.bo.SummarySonBO;
import com.bjike.goddess.negotiatemeeting.dto.SummarySonDTO;
import com.bjike.goddess.negotiatemeeting.entity.SummarySon;
import com.bjike.goddess.negotiatemeeting.to.SummarySonTO;

import java.util.List;

/**
 * 协商会议纪要子表业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-01 05:14 ]
 * @Description: [ 协商会议纪要子表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SummarySonAPI {
    /**
     * 通过会议纪要id查找记录
     *
     * @param summaryId 会议纪要id
     * @return
     * @throws SerException
     */
    List<SummarySon> findBySummaryId(String summaryId) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    SummarySonBO save(SummarySonTO to) throws SerException;

    /**
     * 修改个人意见
     *
     * @param to
     * @throws SerException
     */
    void editPerson(SummarySonTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(SummarySonTO to) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto dto
     * @return
     * @throws SerException
     */
    Long count(SummarySonDTO dto) throws SerException;

    /**
     * 通过id查找
     *
     * @param id
     * @return
     * @throws SerException
     */
    SummarySonBO findByID(String id) throws SerException;
}