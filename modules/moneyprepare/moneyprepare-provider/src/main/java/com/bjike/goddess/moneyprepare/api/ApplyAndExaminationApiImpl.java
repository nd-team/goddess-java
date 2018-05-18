package com.bjike.goddess.moneyprepare.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyprepare.bo.ApplyAndExaminationBO;
import com.bjike.goddess.moneyprepare.bo.FundPrepareApplyBO;
import com.bjike.goddess.moneyprepare.bo.FundPrepareBO;
import com.bjike.goddess.moneyprepare.dto.ApplyAndExaminationDTO;
import com.bjike.goddess.moneyprepare.excel.SonPermissionObject;
import com.bjike.goddess.moneyprepare.service.ApplyAndExaminationSer;
import com.bjike.goddess.moneyprepare.to.ApplyAndExaminationTO;
import com.bjike.goddess.moneyprepare.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 申请和审批业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 04:16 ]
 * @Description: [ 申请和审批业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("applyAndExaminationApiImpl")
public class ApplyAndExaminationApiImpl implements ApplyAndExaminationAPI {
    @Autowired
    private ApplyAndExaminationSer applyAndExaminationSer;


    @Override
    public Long countApplyAndExamination(ApplyAndExaminationDTO applyAndExaminationDTO) throws SerException {
        return applyAndExaminationSer.countApplyAndExamination(applyAndExaminationDTO);
    }

    @Override
    public ApplyAndExaminationBO getOneById(String id) throws SerException {
        return applyAndExaminationSer.getOneById(id);
    }

    @Override
    public List<ApplyAndExaminationBO> listApplyAndExamination(ApplyAndExaminationDTO applyAndExaminationDTO) throws SerException {
        return applyAndExaminationSer.listApplyAndExamination(applyAndExaminationDTO);
    }

    @Override
    public ApplyAndExaminationBO addApplyAndExamination(ApplyAndExaminationTO applyAndExaminationTO) throws SerException {
        return applyAndExaminationSer.addApplyAndExamination(applyAndExaminationTO);
    }

    @Override
    public ApplyAndExaminationBO editApplyAndExamination(ApplyAndExaminationTO applyAndExaminationTO) throws SerException {
        return applyAndExaminationSer.editApplyAndExamination(applyAndExaminationTO);
    }

    @Override
    public void deleteApplyAndExamination(String id) throws SerException {
        applyAndExaminationSer.deleteApplyAndExamination(id);
    }

    @Override
    public ApplyAndExaminationBO auditApplyAndExamination(ApplyAndExaminationTO applyAndExaminationTO) throws SerException {
        return applyAndExaminationSer.auditApplyAndExamination( applyAndExaminationTO);
    }

    @Override
    public List<FundPrepareApplyBO> record(String id) throws SerException {
        return applyAndExaminationSer.record(id);
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return applyAndExaminationSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return applyAndExaminationSer.guidePermission( guidePermissionTO );
    }
}