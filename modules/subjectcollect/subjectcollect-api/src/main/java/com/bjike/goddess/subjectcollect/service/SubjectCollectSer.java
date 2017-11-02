package com.bjike.goddess.subjectcollect.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.subjectcollect.bo.FirstSubjectBO;
import com.bjike.goddess.subjectcollect.entity.SubjectCollect;
import com.bjike.goddess.subjectcollect.dto.SubjectCollectDTO;
import com.bjike.goddess.subjectcollect.to.ExportSubjectCollectTO;

import java.util.List;

/**
* 科目汇总业务接口
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-26 02:42 ]
* @Description:	[ 科目汇总业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SubjectCollectSer extends Ser<SubjectCollect, SubjectCollectDTO> {
    /**
     * 列表
     */
    List<FirstSubjectBO> collect(SubjectCollectDTO dto) throws SerException;

    /**
     * 导出
     *
     * @param to
     * @throws SerException
     */
    byte[] exportExcel(ExportSubjectCollectTO to) throws SerException;
 }