package com.bjike.goddess.subjectcollect.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.subjectcollect.bo.CompareCollectBO;
import com.bjike.goddess.subjectcollect.bo.SubjectCollectBO;
import com.bjike.goddess.subjectcollect.dto.SubjectCollectDTO;
import com.bjike.goddess.subjectcollect.excel.SonPermissionObject;
import com.bjike.goddess.subjectcollect.service.SubjectCollectSer;
import com.bjike.goddess.subjectcollect.to.GuidePermissionTO;
import com.bjike.goddess.subjectcollect.to.SubjectCollectTO;
import com.bjike.goddess.voucher.bo.VoucherGenerateBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


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
    public List<SonPermissionObject> sonPermission() throws SerException {
        return subjectCollectSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return subjectCollectSer.guidePermission(guidePermissionTO);
    }
    @Override
    public byte[] exportExcel(SubjectCollectDTO dto) throws SerException{
        return subjectCollectSer.exportExcel(dto);
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
    public List<String> getArea() throws SerException {
        return subjectCollectSer.getArea();
    }

    @Override
    public List<SubjectCollectBO> findListSubjectCollect(SubjectCollectDTO subjectCollectDTO) throws SerException {
        return subjectCollectSer.findListSubjectCollect(subjectCollectDTO);
    }

    @Override
    public SubjectCollectBO insertSubjectCollect(SubjectCollectTO subjectCollectTO) throws SerException {
        return subjectCollectSer.insertSubjectCollect(subjectCollectTO);
    }

    @Override
    public SubjectCollectBO editSubjectCollect(SubjectCollectTO subjectCollectTO) throws SerException {
        return subjectCollectSer.editSubjectCollect(subjectCollectTO);
    }

    @Override
    public List<CompareCollectBO> collectCompare(Integer[] months) throws SerException {
        return subjectCollectSer.collectCompare(months);
    }

    @Override
    public SubjectCollectBO getSum(SubjectCollectDTO dto) throws SerException {
        return subjectCollectSer.getSum(dto);
    }

    @Override
    public Set<String> allFirstSubjects() throws SerException {
        return subjectCollectSer.allFirstSubjects();
    }

    @Override
    public Set<String> allProjectNames() throws SerException {
        return subjectCollectSer.allProjectNames();
    }

    @Override
    public List<VoucherGenerateBO> synchrodata() throws SerException {
        return subjectCollectSer.synchrodata();
    }

    @Override
    public List<SubjectCollectBO> subjectCollect(SubjectCollectDTO subjectCollectDTO) throws SerException {
        return subjectCollectSer.subjectCollect(subjectCollectDTO);
    }

    @Override
    public List<SubjectCollectBO> areaCollect(SubjectCollectDTO subjectCollectDTO) throws SerException {
        return subjectCollectSer.areaCollect(subjectCollectDTO);
    }

    @Override
    public List<SubjectCollectBO> groupCollect(SubjectCollectDTO subjectCollectDTO) throws SerException {
        return subjectCollectSer.groupCollect(subjectCollectDTO);
    }

    @Override
    public List<SubjectCollectBO> pNameCollect(SubjectCollectDTO subjectCollectDTO) throws SerException {
        return subjectCollectSer.pNameCollect(subjectCollectDTO);
    }
}