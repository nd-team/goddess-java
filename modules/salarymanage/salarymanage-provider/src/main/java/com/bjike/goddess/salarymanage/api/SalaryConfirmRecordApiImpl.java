package com.bjike.goddess.salarymanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.salarymanage.bo.SalaryConfirmRecordBO;
import com.bjike.goddess.salarymanage.dto.SalaryConfirmRecordDTO;
import com.bjike.goddess.salarymanage.dto.SalaryInformationDTO;
import com.bjike.goddess.salarymanage.service.SalaryConfirmRecordSer;
import com.bjike.goddess.salarymanage.to.GuidePermissionTO;
import com.bjike.goddess.salarymanage.to.SalaryConfirmRecordTO;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 招聘面谈薪资确认记录业务接口实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-15 02:20 ]
* @Description:	[ 招聘面谈薪资确认记录业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("salaryConfirmRecordApiImpl")
public class SalaryConfirmRecordApiImpl implements SalaryConfirmRecordAPI  {
    @Autowired
    private SalaryConfirmRecordSer salaryConfirmRecordSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return salaryConfirmRecordSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return salaryConfirmRecordSer.guidePermission(guidePermissionTO);
    }

    @Override
    public void add(SalaryConfirmRecordTO to) throws SerException {
        salaryConfirmRecordSer.add(to);
    }

    @Override
    public void delete(String id) throws SerException {
        salaryConfirmRecordSer.delete(id);
    }

    @Override
    public void edit(SalaryConfirmRecordTO to) throws SerException {
        salaryConfirmRecordSer.edit(to);
    }

    @Override
    public List<SalaryConfirmRecordBO> pageList(SalaryConfirmRecordDTO dto) throws SerException {
        return salaryConfirmRecordSer.pageList(dto);
    }

    @Override
    public Long count(SalaryConfirmRecordDTO dto) throws SerException {
        return salaryConfirmRecordSer.count(dto);
    }

    @Override
    public SalaryConfirmRecordBO findOne(String id) throws SerException {
        return salaryConfirmRecordSer.findOne(id);
    }


    @Override
    public List<UserBO> findUserList() throws SerException {
        return salaryConfirmRecordSer.findUserList();
    }

    @Override
    public void confirm(String id,Boolean ifConfirm) throws SerException {
        salaryConfirmRecordSer.confirm(id,ifConfirm);
    }
}