package com.bjike.goddess.progressmanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.progressmanage.bo.TableHeadBO;
import com.bjike.goddess.progressmanage.dto.ProgressTableDTO;
import com.bjike.goddess.progressmanage.entity.TableHead;
import com.bjike.goddess.progressmanage.dto.TableHeadDTO;
import com.bjike.goddess.progressmanage.to.TableHeadTO;

import java.util.List;

/**
* 进度表表头业务接口
* @Author:			[ Jason ]
* @Date:			[  2017-06-09 05:08 ]
* @Description:	[ 进度表表头业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface TableHeadSer extends Ser<TableHead, TableHeadDTO> {

    List<TableHeadBO> pageList(TableHeadDTO dto) throws SerException;

    TableHeadBO insertModel(TableHeadTO to) throws SerException;

    TableHeadBO editModel(TableHeadTO to) throws SerException;

    void delete(String id) throws SerException;
}