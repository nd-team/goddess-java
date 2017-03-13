package com.bjike.goddess.staffentry.action.staffentry;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.api.SalaryConfirmRecordAPI;
import com.bjike.goddess.staffentry.bo.SalaryConfirmRecordBO;
import com.bjike.goddess.staffentry.dto.SalaryConfirmRecordDTO;
import com.bjike.goddess.staffentry.vo.SalaryConfirmRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 薪资确认
 * @Author: [tanghaixiang]
 * @Date: [2017-03-11 10:31]
 * @Description: [薪资确认]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("staffentry/salaryconfirmrecord")
public class SalaryConfirmRecordAction {

    @Autowired
    private SalaryConfirmRecordAPI salaryConfirmRecordAPI;

    /**
     * 获取所有入职基本信息
     * @param salaryConfirmRecordDTO 入职基本信息dto
     * @return class salaryConfirmRecordBO
     * @throws ActException
     */
    @GetMapping("v1/listSalaryConfirmRecord")
    public ActResult findListSalaryConfirmRecord(SalaryConfirmRecordDTO salaryConfirmRecordDTO) throws ActException {
        try {
            List<SalaryConfirmRecordVO> salaryConfirmRecordVOList = BeanTransform.copyProperties(
                    salaryConfirmRecordAPI.listSalaryConfirmRecord( salaryConfirmRecordDTO ) , SalaryConfirmRecordVO.class ,true);
            return ActResult.initialize( salaryConfirmRecordVOList );
        } catch (SerException e) {
            throw  new ActException( e.getMessage());
        }
    }

    /**
     * 添加员工入职
     * @param salaryConfirmRecordBO 员工入职基本信息数据bo
     * @return class salaryConfirmRecordBO
     * @throws ActException
     */
    @PostMapping("v1/add")
    public ActResult addSalaryConfirmRecord (@Valid SalaryConfirmRecordBO salaryConfirmRecordBO) throws ActException {
        try {
            SalaryConfirmRecordBO salaryConfirmRecordBO1 = salaryConfirmRecordAPI.insertSalaryConfirmRecord(salaryConfirmRecordBO );
            return ActResult.initialize( salaryConfirmRecordBO1);
        } catch (SerException e) {
            throw  new ActException( e.getMessage() );
        }
    }


    /**
     * 编辑员工入职
     * @param salaryConfirmRecordBO 员工入职基本信息数据bo
     * @return class salaryConfirmRecordBO
     * @throws ActException
     */
    @PostMapping("v1/edit")
    public ActResult editSalaryConfirmRecord (@Valid SalaryConfirmRecordBO salaryConfirmRecordBO ) throws ActException {
        try {
            SalaryConfirmRecordBO salaryConfirmRecordBO1 = salaryConfirmRecordAPI.editSalaryConfirmRecord(salaryConfirmRecordBO );
            return ActResult.initialize( salaryConfirmRecordBO1);
        } catch (SerException e) {
            throw  new ActException( e.getMessage() );
        }
    }


    /**
     * 根据用户id删除员工入职基本信息记录
     * @param id 用户id
     * @return
     * @throws ActException
     */
    @DeleteMapping("v1/delete/{id}")
    public ActResult deleteSalaryConfirmRecord(@PathVariable String id) throws ActException {
        try {
            salaryConfirmRecordAPI.removeSalaryConfirmRecord( id );
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw  new ActException( e.getMessage());
        }
    }
}
