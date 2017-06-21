package com.bjike.goddess.allmeeting.service;

import com.bjike.goddess.allmeeting.bo.WorkCollectPrepareBO;
import com.bjike.goddess.allmeeting.to.WorkCollectPrepareTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.allmeeting.entity.WorkCollectPrepare;
import com.bjike.goddess.allmeeting.dto.WorkCollectPrepareDTO;

import java.util.List;

/**
* 工作汇总议题准备信息业务接口
* @Author:			[ Jason ]
* @Date:			[  2017-05-31 03:44 ]
* @Description:	[ 工作汇总议题准备信息业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface WorkCollectPrepareSer extends Ser<WorkCollectPrepare, WorkCollectPrepareDTO> {

    List<WorkCollectPrepareBO> pageList(WorkCollectPrepareDTO dto) throws SerException;

    void unfreeze(String id) throws SerException;

    void freeze(String id) throws SerException;

    WorkCollectPrepareBO updateModel(WorkCollectPrepareTO to) throws SerException;

    WorkCollectPrepareBO insertModel(WorkCollectPrepareTO to) throws SerException;


}