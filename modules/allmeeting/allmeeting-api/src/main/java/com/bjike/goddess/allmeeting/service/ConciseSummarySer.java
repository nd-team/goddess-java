package com.bjike.goddess.allmeeting.service;

import com.bjike.goddess.allmeeting.bo.ConciseSummaryBO;
import com.bjike.goddess.allmeeting.bo.OrganizeForSummaryBO;
import com.bjike.goddess.allmeeting.dto.ConciseSummaryDTO;
import com.bjike.goddess.allmeeting.entity.ConciseSummary;
import com.bjike.goddess.allmeeting.to.ConciseSummaryTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 简洁交流讨论纪要业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-01 10:19 ]
 * @Description: [ 简洁交流讨论纪要业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ConciseSummarySer extends Ser<ConciseSummary, ConciseSummaryDTO> {


    ConciseSummaryBO updateModel(ConciseSummaryTO to) throws SerException;

    void freeze(String id) throws SerException;

    List<ConciseSummaryBO> pageList(ConciseSummaryDTO dto) throws SerException;

    ConciseSummaryBO findAndSet(String id) throws SerException;

    OrganizeForSummaryBO organize(String id) throws SerException;

    void unfreeze(String id) throws SerException;
}