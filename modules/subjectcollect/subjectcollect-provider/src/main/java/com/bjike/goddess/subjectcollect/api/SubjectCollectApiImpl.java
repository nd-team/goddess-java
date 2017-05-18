package com.bjike.goddess.subjectcollect.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.subjectcollect.bo.CompareCollectBO;
import com.bjike.goddess.subjectcollect.bo.SubjectCollectBO;
import com.bjike.goddess.subjectcollect.dto.SubjectCollectDTO;
import com.bjike.goddess.subjectcollect.entity.SubjectCollect;
import com.bjike.goddess.subjectcollect.service.SubjectCollectSer;
import com.bjike.goddess.subjectcollect.to.SubjectCollectTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


/**
 * 科目汇总表业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-07 04:02 ]
 * @Description: [ 科目汇总表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("subjectCollectApiImpl")
public class SubjectCollectApiImpl implements SubjectCollectAPI {
    @Autowired
    private SubjectCollectSer subjectCollectSer;
    @Override
    public String exportExcel() throws SerException {
        return subjectCollectSer.exportExcel();
    }

    @Override
    public void removeSubjectCollect(String id) throws SerException {
        subjectCollectSer.removeSubjectCollect(id);
    }
    @Override
    public Long countSubjectCollect(SubjectCollectDTO subjectCollectDTO) throws SerException {
        return subjectCollectSer.countSubjectCollect(subjectCollectDTO);
    }

    @Override
    public SubjectCollectBO getOne(String id) throws SerException {
        return subjectCollectSer.getOne(id);
    }

    @Override
    public List<SubjectCollectBO> findListSubjectCollect(SubjectCollectDTO subjectCollectDTO) throws SerException {
        return subjectCollectSer.findListSubjectCollect(subjectCollectDTO);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SubjectCollectBO insertSubjectCollect(SubjectCollectTO subjectCollectTO) throws SerException {
        return subjectCollectSer.insertSubjectCollect(subjectCollectTO);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SubjectCollectBO editSubjectCollect(SubjectCollectTO subjectCollectTO) throws SerException {
        return subjectCollectSer.editSubjectCollect(subjectCollectTO);
    }
    @Override
    public List<CompareCollectBO> collectCompare(Integer [] months) throws SerException {
        return subjectCollectSer.collectCompare(months);
    }


}