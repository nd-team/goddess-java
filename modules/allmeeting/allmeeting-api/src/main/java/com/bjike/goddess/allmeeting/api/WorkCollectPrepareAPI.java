package com.bjike.goddess.allmeeting.api;

import com.bjike.goddess.allmeeting.bo.WorkCollectPrepareBO;
import com.bjike.goddess.allmeeting.dto.WorkCollectPrepareDTO;
import com.bjike.goddess.allmeeting.to.WorkCollectPrepareTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 工作汇总议题准备信息业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 03:44 ]
 * @Description: [ 工作汇总议题准备信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WorkCollectPrepareAPI {

    WorkCollectPrepareBO findById(String id) throws SerException;

    Long count(WorkCollectPrepareDTO dto) throws SerException;

    List<WorkCollectPrepareBO> pageList(WorkCollectPrepareDTO dto) throws SerException;

    void unfreeze(String id) throws SerException;

    void freeze(String id) throws SerException;

    WorkCollectPrepareBO edit(WorkCollectPrepareTO to) throws SerException;

    WorkCollectPrepareBO add(WorkCollectPrepareTO to) throws SerException;
}