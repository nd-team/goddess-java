package com.bjike.goddess.allmeeting.api;

import com.bjike.goddess.allmeeting.bo.ProblesClassifyPrepareBO;
import com.bjike.goddess.allmeeting.dto.ProblesClassifyPrepareDTO;
import com.bjike.goddess.allmeeting.to.ProblesClassifyPrepareTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 问题分类议题准备信息业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 05:44 ]
 * @Description: [ 问题分类议题准备信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProblesClassifyPrepareAPI {

    ProblesClassifyPrepareBO findById(String id) throws SerException;

    Long count(ProblesClassifyPrepareDTO dto) throws SerException;

    ProblesClassifyPrepareBO add(ProblesClassifyPrepareTO to) throws SerException;

    ProblesClassifyPrepareBO edit(ProblesClassifyPrepareTO to) throws SerException;

    void freeze(String id) throws SerException;

    List<ProblesClassifyPrepareBO> pageList(ProblesClassifyPrepareDTO dto) throws SerException;

    void unfreeze(String id) throws SerException;
}