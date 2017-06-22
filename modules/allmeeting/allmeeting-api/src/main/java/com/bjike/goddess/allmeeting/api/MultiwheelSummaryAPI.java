package com.bjike.goddess.allmeeting.api;

import com.bjike.goddess.allmeeting.bo.MultiwheelSummaryBO;
import com.bjike.goddess.allmeeting.dto.ConciseSummaryDTO;
import com.bjike.goddess.allmeeting.dto.MultiwheelSummaryDTO;
import com.bjike.goddess.allmeeting.to.MultiwheelSummaryTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 简洁交流讨论纪要业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-01 10:44 ]
 * @Description: [ 简洁交流讨论纪要业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MultiwheelSummaryAPI {

    MultiwheelSummaryBO findById(String id) throws SerException;

    Long count(MultiwheelSummaryDTO dto) throws SerException;

    MultiwheelSummaryBO edit(MultiwheelSummaryTO to) throws SerException;

    List<MultiwheelSummaryBO> pageList(MultiwheelSummaryDTO dto) throws SerException;

    void freeze(String id) throws SerException;
}