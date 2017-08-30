package com.bjike.goddess.staffentry.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.bo.SalaryConfirmRecordBO;
import com.bjike.goddess.staffentry.dto.SalaryConfirmRecordDTO;
import com.bjike.goddess.staffentry.entity.SalaryConfirmRecord;
import com.bjike.goddess.staffentry.service.SalaryConfirmRecordSer;
import com.bjike.goddess.staffentry.to.GuidePermissionTO;
import com.bjike.goddess.staffentry.to.SalaryConfirmRecordTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 薪资确认业务接口
 * @Author: [tanghaixiang]
 * @Date: [2017-03-10 17:25]
 * @Description: [薪资确认业务接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("salaryConfirmRecordApiImpl")
public class SalaryConfirmRecordApiImpl implements SalaryConfirmRecordAPI{

    @Autowired
    private SalaryConfirmRecordSer salaryConfirmRecordSer;


    @Override
    public Boolean sonPermission() throws SerException {
        return salaryConfirmRecordSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return salaryConfirmRecordSer.guidePermission( guidePermissionTO );
    }


    @Override
    public Long countSalaryConfirmRecord(SalaryConfirmRecordDTO salaryConfirmRecordDTO) throws SerException {
        return salaryConfirmRecordSer.countSalaryConfirmRecord(salaryConfirmRecordDTO);
    }

    @Override
    public SalaryConfirmRecordBO getOne(String id) throws SerException {
        return salaryConfirmRecordSer.getOne(id);
    }

    @Override
    public List<SalaryConfirmRecordBO> listSalaryConfirmRecord(SalaryConfirmRecordDTO salaryConfirmRecordDTO) throws SerException {
        List<SalaryConfirmRecord> salaryConfirmRecords = salaryConfirmRecordSer.listSalaryConfirmRecord(salaryConfirmRecordDTO);
        return BeanTransform.copyProperties( salaryConfirmRecords ,SalaryConfirmRecordBO.class );
    }

    @Override
    public SalaryConfirmRecordBO insertSalaryConfirmRecord(SalaryConfirmRecordTO salaryConfirmRecordTO) throws SerException {
        return salaryConfirmRecordSer.insertSalaryConfirmRecord( salaryConfirmRecordTO );
    }

    @Override
    public SalaryConfirmRecordBO editSalaryConfirmRecord(SalaryConfirmRecordTO salaryConfirmRecordTO) throws SerException {
        return salaryConfirmRecordSer.editSalaryConfirmRecord(salaryConfirmRecordTO);
    }

    @Override
    public void removeSalaryConfirmRecord(String id) throws SerException {
        salaryConfirmRecordSer.removeSalaryConfirmRecord(id);
    }
}
