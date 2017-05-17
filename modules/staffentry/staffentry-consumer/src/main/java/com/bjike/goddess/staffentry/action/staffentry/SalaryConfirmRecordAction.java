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
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("salaryconfirmrecord")
public class SalaryConfirmRecordAction {

    @Autowired
    private SalaryConfirmRecordAPI salaryConfirmRecordAPI;

    /**
     * 薪资确认列表总条数
     *
     * @param salaryConfirmRecordDTO 注册用户信息dto
     * @des 获取所有薪资确认信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SalaryConfirmRecordDTO salaryConfirmRecordDTO) throws ActException {
        try {
            Long count = salaryConfirmRecordAPI.countSalaryConfirmRecord(salaryConfirmRecordDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个薪资确认
     *
     * @param id 薪资确认信息id
     * @return class SalaryConfirmRecordVO
     * @des 根据id查询薪资确认
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id) throws ActException {
        try {
            SalaryConfirmRecordVO salaryConfirmRecordVOList = BeanTransform.copyProperties(
                    salaryConfirmRecordAPI.getOne(id), SalaryConfirmRecordVO.class);
            return ActResult.initialize(salaryConfirmRecordVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 薪资确认列表
     *
     * @param salaryConfirmRecordDTO 薪资确认dto
     * @des 获取所有薪资确认
     * @return class SalaryConfirmRecordVO
     * @version v1
     */
    @GetMapping("v1/list")
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
     * @param salaryConfirmRecordTO 员工薪资确认数据to
     * @des 添加薪资确认
     * @return class SalaryConfirmRecordVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addSalaryConfirmRecord(@Validated(SalaryConfirmRecordTO.TestAdd.class) SalaryConfirmRecordTO salaryConfirmRecordTO) throws ActException {
        try {
            SalaryConfirmRecordBO salaryConfirmRecordBO1 = salaryConfirmRecordAPI.insertSalaryConfirmRecord(salaryConfirmRecordTO);
            return ActResult.initialize(BeanTransform.copyProperties(salaryConfirmRecordBO1,SalaryConfirmRecordVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑
     *
     * @param salaryConfirmRecordTO 员工薪资确认数据to
     * @des 编辑薪资确认
     * @return class SalaryConfirmRecordVO
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result editSalaryConfirmRecord(@Validated(SalaryConfirmRecordTO.TestAdd.class) SalaryConfirmRecordTO salaryConfirmRecordTO) throws ActException {
        try {
            SalaryConfirmRecordBO salaryConfirmRecordBO1 = salaryConfirmRecordAPI.editSalaryConfirmRecord(salaryConfirmRecordTO);
            return ActResult.initialize(BeanTransform.copyProperties(salaryConfirmRecordBO1,SalaryConfirmRecordVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 删除薪资确认
     *
     * @param id 用户id
     * @des 根据用户id删除员工薪资确认记录
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
