package com.bjike.goddess.lendreimbursement.action.lendreimbursement;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.lendreimbursement.api.LoanAPI;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 借款
 *
 * @Author: [ wany ]
 * @Date: [ 2018-03-01 05:48 ]
 * @Description: [ 借款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("loan")
public class LoanAction extends BaseFileAction{

    @Autowired
    private LoanAPI loanAPI;

    @Autowired
    private FileAPI fileAPI;

    @GetMapping("/test")
    public String test() {
        return "hello world";
    }

    /**
     * 根据id获取单个对象
     * @param id
     * @return
     * @throws SerException
     */
    @GetMapping("/getLoan/{id}")
    public Result getLoan(@PathVariable String id) throws SerException {
        return new ActResult("success", loanAPI.getLoan(id));
    }

    /**
     * 待审核列表
     * @param pageNum
     * @return
     * @throws IOException
     */
    @GetMapping("/v1/waitForAudited/{pageNum}")
    public Result waitForAudited(@PathVariable Integer pageNum) {
        return new ActResult("success", loanAPI.getLoanPage(pageNum,"待审核"));
    }

    /**
     * 待审核添加
     *
     * @param data
     * @return
     * @throws IOException
     */
    @PostMapping("/v1/waitForAuditedAdd")
    public Result waitForAuditedAdd(String data) throws IOException, SerException {
        loanAPI.loanSave(data);
        return new ActResult("success");
    }

    /**
     * 待审核编辑
     * @param data
     * @return
     * @throws IOException
     */
    @PutMapping("/v1/waitForAuditedEdit")
    public Result waitForAuditedEdit(String data) throws IOException, SerException {
        loanAPI.loanUpdate(data, "待审核");
        return new ActResult("success");
    }

    /**
     * 待审核删除
     * @param id
     * @return
     */
    @DeleteMapping("/v1/waitForAuditedDel")
    public Result waitForAuditedDel(String id) throws SerException {
        loanAPI.loanDel(id);
        return new ActResult();
    }

    /**
     * 待审核 审核
     * @param data
     * @return
     */
    @PutMapping("/v1/waitForAuditedExamine")
    public Result waitForAuditedExamine(String data) throws IOException, SerException {
        loanAPI.loanUpdate(data,"审核");
        return new ActResult();
    }

    /**
     * 待审何导出
     * @return
     */
    @GetMapping("/v1/waitForAuditedExport")
    public Result waitForAuditedExport() {

        return new ActResult();
    }

    /**
     * 待分析列表
     *
     * @param pageNum
     * @return
     */
    @GetMapping("/v1/waitForAnalysis/{pageNum}")
    public Result waitForAnalysis(@PathVariable Integer pageNum) {
        return new ActResult("success", loanAPI.getAnalysisLoan(pageNum, "待分析"));
    }

    /**
     * 待分析 分析
     * @param data
     * @return
     * @throws IOException
     * @throws SerException
     */
    @PutMapping("/v1/waitForAnalysis")
    public Result waitForAnalysis(String data) throws IOException, SerException {
        System.out.println(data);
        loanAPI.loanUpdate(data, "待分析");
        return new ActResult("success");
    }

    /**
     * 待付款列表
     *
     * @param pageNum
     * @return
     */
    @GetMapping("/v1/waitForPayment/{pageNum}")
    public Result waitForPayment(@PathVariable Integer pageNum) {

        return new ActResult("success", loanAPI.getLoanPage(pageNum, "待付款"));
    }

    /**
     * 待付款 付款
     * @param data
     * @return
     */
    @PutMapping("/v1/waitForPaymentEdit")
    public Result waitForPaymentEdit(String data) throws IOException, SerException {
        loanAPI.loanUpdate(data, "付款");
        return new ActResult("success");
    }

    /**
     * 待付款导出
     * @return
     */
    @GetMapping("/v1/waitForPaymentExport")
    public Result waitForPaymentExport() {
        return new ActResult();
    }

    /**
     * 待确认列表
     *
     * @param pageNum
     * @return
     */
    @GetMapping("/v1/waitForConfirm/{pageNum}")
    public Result waitForConfirm(@PathVariable Integer pageNum) {
        return new ActResult("success",loanAPI.getLoanPage(pageNum,"待确认"));
    }

    /**
     * 待确认 确认收款
     * @param data
     * @return
     */
    @PutMapping("/v1/waitForConfirmEdit")
    public Result waitForConfirmEdit(String data) throws IOException, SerException {
        loanAPI.loanUpdate(data,"确认收款");
        return new ActResult();
    }

    /**
     * 待还款列表
     *
     * @param pageNum
     * @return
     */
    @GetMapping("/v1/waitForRepayment/{pageNum}")
    public Result waitForRepayment(@PathVariable Integer pageNum) {
        return new ActResult("success", loanAPI.getLoanPage(pageNum, "待还款"));
    }

    /**
     * 待还款  还款
     * @param data
     * @return
     * @throws IOException
     * @throws SerException
     */
    @PutMapping("/v1/waitForRepayment")
    public Result waitForRepayment(String data) throws IOException, SerException {
        loanAPI.loanUpdate(data, "还款");
        return new ActResult("success");
    }

    /**
     * 待核对列表
     *
     * @param pageNum
     * @return
     */
    @GetMapping("/v1/waitForCheck/{pageNum}")
    public Result waitForCheck(@PathVariable Integer pageNum) {
        return new ActResult("success", loanAPI.getLoanPage(pageNum, "待核对"));
    }

    /**
     * 待核对 核对
     * @param data
     * @return
     */
    @PutMapping("/v1/waitForCheck")
    public Result waitForCheck(String data) throws IOException, SerException {
        System.out.println(data);
        loanAPI.loanUpdate(data, "核对");
        return new ActResult("success");
    }

    /**
     * 已完成列表
     *
     * @return
     */
    @GetMapping("/v1/Completed/{pageNum}")
    public Result Completed(@PathVariable Integer pageNum) {

        return new ActResult("success", loanAPI.getLoanPage(pageNum, "已完成"));
    }


    /**
     * 有误记录列表
     * @return
     */
    @GetMapping("/v1/errorRecord/{pageNum}")
    public Result errorRecord(@PathVariable Integer pageNum) {
        return new ActResult("success", loanAPI.getLoanPage(pageNum, "有误记录"));
    }

    /**
     * 有误记录编辑
     *
     * @param data
     * @return
     * @throws ActException
     */
    @PutMapping("/v1/errorRecord")
    public Result errorRecord(String data) throws IOException, SerException {
        loanAPI.loanUpdate(data, "有误编辑");
        return new ActResult("success");
    }

    /**
     * 寄件信息 添加
     * @param data
     * @return
     */
    @PutMapping("/v1/mailingInformation")
    public Result mailingInformation(String data) throws IOException, SerException {
        loanAPI.loanUpdate(data, "");
        return new ActResult("success");
    }

    /**
     * 上传附件
     *
     * @des 审核项目签订与立项
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String paths = "/lendreimbursement/loan/" + id;
            List<InputStream> inputStreams = getInputStreams(request, paths);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     *
     * @param id 签订与立项id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /businessproject/id/....
            String path = "/lendreimbursement/loan/" + id;
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            List<FileVO> files = BeanTransform.copyProperties(fileAPI.list(fileInfo), FileVO.class);
            return ActResult.initialize(files);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件下载
     *
     * @param path 文件信息路径
     * @version v1
     */
    @GetMapping("v1/downloadFile")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {


            //该文件的路径
            Object storageToken = request.getAttribute("storageToken");
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            fileInfo.setStorageToken(storageToken.toString());
            String filename = StringUtils.substringAfterLast(fileInfo.getPath(), "/");
            byte[] buffer = fileAPI.download(fileInfo);
            writeOutFile(response, buffer, filename);
            return new ActResult("download success");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }

    }

//    /**
//     * 删除文件或文件夹
//     *
//     * @param siginManageDeleteFileTO 多文件信息路径
//     * @version v1
//     */
//    @LoginAuth
//    @PostMapping("v1/deleteFile")
//    public Result delFile(@Validated(SiginManageDeleteFileTO.TestDEL.class) SiginManageDeleteFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
//        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
//            Object storageToken = request.getAttribute("storageToken");
//            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
//        }
//        return new ActResult("delFile success");
//    }

}