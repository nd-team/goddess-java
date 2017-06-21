package com.bjike.goddess.allmeeting.service;

import com.bjike.goddess.allmeeting.bo.ProblesAllotPrepareBO;
import com.bjike.goddess.allmeeting.dto.ProblesAllotPrepareDTO;
import com.bjike.goddess.allmeeting.entity.ProblesAllotPrepare;
import com.bjike.goddess.allmeeting.to.ProblesAllotPrepareTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 问题分配责任模块议题准备信息业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 04:55 ]
 * @Description: [ 问题分配责任模块议题准备信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProblesAllotPrepareSer extends Ser<ProblesAllotPrepare, ProblesAllotPrepareDTO> {

    List<ProblesAllotPrepareBO> pageList(ProblesAllotPrepareDTO dto) throws SerException;

    ProblesAllotPrepareBO insertModel(ProblesAllotPrepareTO to) throws SerException;

    ProblesAllotPrepareBO updateModel(ProblesAllotPrepareTO to) throws SerException;

    void freeze(String id) throws SerException;
}