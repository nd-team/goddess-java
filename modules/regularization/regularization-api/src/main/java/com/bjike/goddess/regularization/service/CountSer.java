package com.bjike.goddess.regularization.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.regularization.bo.TaskSituationBO;
import com.bjike.goddess.regularization.dto.CountDTO;
import com.bjike.goddess.regularization.entity.Count;
import com.bjike.goddess.regularization.to.CountTO;

import java.util.List;

/**
 * 操作汇总业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-09 02:00 ]
 * @Description: [ 操作汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CountSer extends Ser<Count, CountDTO> {
    /**
     * 添加
     *
     * @param to
     * @throws SerException
     */
    void add(CountTO to) throws SerException;

    /**
     * 查看某天的任务情况
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<TaskSituationBO> countSituation(CountDTO dto) throws SerException;
}