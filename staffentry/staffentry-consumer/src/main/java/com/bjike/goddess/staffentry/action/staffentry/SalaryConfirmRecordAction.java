package com.bjike.goddess.staffentry.action.staffentry;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.api.SalaryConfirmRecordAPI;
import com.bjike.goddess.staffentry.bo.SalaryConfirmRecordBO;
import com.bjike.goddess.staffentry.dto.SalaryConfirmRecordDTO;
import com.bjike.goddess.staffentry.to.SalaryConfirmRecordTO;
import com.bjike.goddess.staffentry.vo.SalaryConfirmRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 薪资确认
 *
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
     * 入职基本信息列表
     *
     * @param salaryConfirmRecordDTO 入职基本信息dto
     * @des 获取所有入职基本信息
     * @version v1
     */
    @GetMapping("v1/listSalaryConfirmRecord")
    public Result findListSalaryConfirmRecord(SalaryConfirmRecordDTO salaryConfirmRecordDTO) throws ActException {
        try {
            List<SalaryConfirmRecordVO> salaryConfirmRecordVOList = BeanTransform.copyProperties(
                    salaryConfirmRecordAPI.listSalaryConfirmRecord(salaryConfirmRecordDTO), SalaryConfirmRecordVO.class, true);
            return ActResult.initialize(salaryConfirmRecordVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param salaryConfirmRecordTO 员工入职基本信息数据to
     * @des 添加员工入职
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addSalaryConfirmRecord(@Valid SalaryConfirmRecordTO salaryConfirmRecordTO) throws ActException {
        try {
            SalaryConfirmRecordBO salaryConfirmRecordBO1 = salaryConfirmRecordAPI.insertSalaryConfirmRecord(salaryConfirmRecordTO);
            return ActResult.initialize(salaryConfirmRecordBO1);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑
     *
     * @param salaryConfirmRecordTO 员工入职基本信息数据to
     * @des 编辑员工入职
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result editSalaryConfirmRecord(@Valid SalaryConfirmRecordTO salaryConfirmRecordTO) throws ActException {
        try {
            SalaryConfirmRecordBO salaryConfirmRecordBO1 = salaryConfirmRecordAPI.editSalaryConfirmRecord(salaryConfirmRecordTO);
            return ActResult.initialize(salaryConfirmRecordBO1);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 删除员工入职记录
     *
     * @param id 用户id
     * @des 根据用户id删除员工入职基本信息记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deleteSalaryConfirmRecord(@PathVariable String id) throws ActException {
        try {
            salaryConfirmRecordAPI.removeSalaryConfirmRecord(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
