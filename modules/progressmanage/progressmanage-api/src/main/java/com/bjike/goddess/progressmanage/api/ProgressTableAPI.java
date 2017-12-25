package com.bjike.goddess.progressmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.progressmanage.bo.ProgressTableBO;
import com.bjike.goddess.progressmanage.bo.TableListForHeadBO;
import com.bjike.goddess.progressmanage.dto.ProgressTableDTO;
import com.bjike.goddess.progressmanage.to.ProgressTableTO;
import com.bjike.goddess.progressmanage.to.TableHeadValueTO;

import java.util.List;

/**
* 进度表业务接口
* @Author:			[ Jason ]
* @Date:			[  2017-06-09 04:46 ]
* @Description:	[ 进度表业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ProgressTableAPI  {

    List<ProgressTableBO> pageList(ProgressTableDTO dto) throws SerException;

    ProgressTableBO add(ProgressTableTO to) throws SerException;

    ProgressTableBO edit(ProgressTableTO to) throws SerException;

    void delete(String id) throws SerException;

    List<TableListForHeadBO> tables(String projectId) throws SerException;

    void freeze(String id) throws SerException;

    void unfreeze(String id) throws SerException;
}