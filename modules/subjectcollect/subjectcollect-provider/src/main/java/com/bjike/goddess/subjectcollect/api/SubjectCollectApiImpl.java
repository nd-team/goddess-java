package com.bjike.goddess.subjectcollect.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.subjectcollect.bo.FirstSubjectBO;
import com.bjike.goddess.subjectcollect.dto.SubjectCollectsDTO;
import com.bjike.goddess.subjectcollect.service.SubjectCollectSer;
import com.bjike.goddess.subjectcollect.to.ExportSubjectCollectTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 科目汇总业务接口实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-26 02:42 ]
* @Description:	[ 科目汇总业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("subjectCollectApiImpl")
public class SubjectCollectApiImpl implements SubjectCollectAPI {
    @Autowired
    private SubjectCollectSer subjectCollectSer;
    @Override
    public List<FirstSubjectBO> collect(SubjectCollectsDTO dto) throws SerException {
        return subjectCollectSer.collect(dto);
    }

    @Override
    public byte[] exportExcel(ExportSubjectCollectTO to) throws SerException {
        return subjectCollectSer.exportExcel(to);
    }
}