package com.bjike.goddess.lendreimbursement.action.lendreimbursement;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.WanyJackson;
import com.bjike.goddess.lendreimbursement.api.ReimbursementAPI;
import com.bjike.goddess.lendreimbursement.entity.Reimbursement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * 报销
 *
 * @Author: [ wany ]
 * @Date: [ 2018-03-12 03:02 ]
 * @Description: [ 报销 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("reimbursement")
public class ReimbursementAction {

    @Autowired
    private ReimbursementAPI reimbursementAPI;

    /**
     * 报销添加
     *
     * @param data
     * @return
     */
    @PostMapping("/v1/reimbursement")
    public Result reimbursementAdd(String data) throws IOException, SerException {
        reimbursementAPI.reimbursementAdd(data);
        return new ActResult();
    }

    /**
     * 报销列表
     *
     * @param pageNum
     * @return
     */
    @GetMapping("/v1/reimbursement/{pageNum}")
    public Result getReimbursementPage(@PathVariable Integer pageNum) {
        return new ActResult("success", reimbursementAPI.getReimbursementPage(pageNum, "待审核"));
    }

    /**
     * 报销审核 审核
     *
     * @param data
     * @return
     */
    @PutMapping("/v1/examine")
    public Result examine(String data) throws IOException, SerException {
        System.out.println(data);
        Reimbursement reimbursement = WanyJackson.superman(data, Reimbursement.class);
        System.out.println(reimbursement);
        reimbursementAPI.reimbursementEdit(data, "审核");
        return new ActResult("success");
    }

    /**
     * 报销编辑
     *
     * @param data
     * @return
     */
    @PutMapping("/v1/reimbursement")
    public Result reimbursementEdit(String data) throws IOException, SerException {
        reimbursementAPI.reimbursementEdit(data, "");
        return new ActResult("success");
    }

    /**
     * 报销删除
     *
     * @param id
     * @return
     * @throws SerException
     */
    @DeleteMapping("/v1/reimbursement")
    public Result reimbursementDel(String id) throws SerException {
        reimbursementAPI.reimbursementDel(id);
        return new ActResult();
    }

    /**
     * 根据id获取单个对象
     *
     * @param id
     * @return
     * @throws SerException
     */
    @GetMapping("/v1/getOneReimbursement/{id}")
    public Result getReimbursementById(@PathVariable String id) throws SerException {
        return new ActResult("success", reimbursementAPI.getReimbursementById(id));
    }

    /**
     * 待分析列表
     *
     * @param pageNum
     * @return
     */
    @GetMapping("/v1/waitForAnalysis/{pageNum}")
    public Result getWaitForAnalysis(@PathVariable Integer pageNum) {
        return new ActResult("success", reimbursementAPI.getReimbursementPage(pageNum, "待分析"));
    }

    /**
     * 待核对列表
     *
     * @param pageNum
     * @return
     */
    @GetMapping("/v1/waitForCheck/{pageNum}")
    public Result getWaitForCheck(@PathVariable Integer pageNum) {
        return new ActResult("success", reimbursementAPI.getReimbursementPage(pageNum, "待核对"));
    }

    /**
     * 待付款列表
     *
     * @param pageNum
     * @return
     */
    @GetMapping("/v1/waitForPayment/{pageNum}")
    public Result getWaitForPayment(@PathVariable Integer pageNum) {
        return new ActResult("success", reimbursementAPI.getReimbursementPage(pageNum, "待付款"));
    }

    /**
     * 已完成列表
     *
     * @param pageNum
     * @return
     */
    @GetMapping("/v1/Completed/{pageNum}")
    public Result getCompleted(@PathVariable Integer pageNum) {
        return new ActResult("success", reimbursementAPI.getReimbursementPage(pageNum, "已完成"));
    }

    /**
     * 有误记录列表
     *
     * @param pageNum
     * @return
     */
    @GetMapping("/v1/errorRecord/{pageNum}")
    public Result getErrorRecord(@PathVariable Integer pageNum) {
        return new ActResult("success", reimbursementAPI.getReimbursementPage(pageNum, "有误记录"));
    }

    /**
     * 待核对 核对
     *
     * @param data
     * @return
     * @throws IOException
     * @throws SerException
     */
    @PutMapping("/v1/waitForCheck")
    public Result waitForCheckEdit(String data) throws IOException, SerException {
        reimbursementAPI.reimbursementEdit(data, "核对");
        return new ActResult("success");
    }

    /**
     * 待付款 付款
     *
     * @param data
     * @return
     */
    @PutMapping("/v1/waitForPayment")
    public Result waitForPaymentEdit(String data) throws IOException, SerException {
        reimbursementAPI.reimbursementEdit(data, "付款");
        return new ActResult();
    }

    /**
     * 寄件信息添加
     *
     * @param data
     * @return
     */
    @PutMapping("/v1/mailingInformation")
    public Result mailingInformation(String data) throws IOException, SerException {
        reimbursementAPI.reimbursementEdit(data, "");
        return new ActResult("success");
    }

    /**
     * 有误记录 重新编辑
     */
    @PutMapping("/v1/errorRecord")
    public Result errorRecord(String data) throws IOException, SerException {
        reimbursementAPI.reimbursementEdit(data, "有误记录");
        return new ActResult("success");
    }

    /**
     * 状态冻结
     *
     * @param data
     * @return
     */
    @PutMapping("/v1/status")
    public Result status(String data) throws IOException, SerException {
        reimbursementAPI.reimbursementEdit(data, "");
        return new ActResult("success");
    }

    /**
     * 待分析 分析
     * @param data
     * @return
     */
    @PutMapping("/v1/waitForAnalysis")
    public Result waitForAnalysis(String data) throws IOException, SerException {
        reimbursementAPI.reimbursementEdit(data, "分析");
        return new ActResult("success");
    }

}