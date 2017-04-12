package com.bjike.goddess.subjectcollect.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.subjectcollect.bo.SubjectCollectBO;
import com.bjike.goddess.subjectcollect.service.SubjectCollectSer;
import com.bjike.goddess.subjectcollect.to.SubjectCollectTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public SubjectCollectBO collectSubjectCollect(String[] firstSubject) throws SerException {
        return subjectCollectSer.collectSubjectCollect(firstSubject);
    }

    @Override
    public SubjectCollectBO collectArea(String[] area) throws SerException {
        return subjectCollectSer.collectArea(area);
    }
    @Override
    public SubjectCollectBO collectProjectName(String[] projectName) throws SerException {
        return subjectCollectSer.collectProjectName(projectName);
    }
    @Override
    public SubjectCollectBO collectProjectGroup(String[] projectGroup) throws SerException {
        return subjectCollectSer.collectProjectGroup(projectGroup);
    }
    @Override
    public List<SubjectCollectBO> collectCompare(SubjectCollectTO subjectCollectTO) throws SerException {
        return subjectCollectSer.collectCompare(subjectCollectTO);
    }

}