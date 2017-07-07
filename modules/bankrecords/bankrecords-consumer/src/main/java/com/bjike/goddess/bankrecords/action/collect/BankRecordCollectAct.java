package com.bjike.goddess.bankrecords.action.collect;

import com.bjike.goddess.bankrecords.api.BankAccountInfoAPI;
import com.bjike.goddess.bankrecords.api.BankRecordAPI;
import com.bjike.goddess.bankrecords.dto.BankRecordDTO;
import com.bjike.goddess.bankrecords.to.BankRecordTO;
import com.bjike.goddess.bankrecords.to.CommunicateDeleteFileTO;
import com.bjike.goddess.bankrecords.to.GuidePermissionTO;
import com.bjike.goddess.bankrecords.vo.*;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

/**
 * 银行流水
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 05:35 ]
 * @Description: [ 银行流水 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("collect")
public class BankRecordCollectAct extends BaseFileAction {

    @Autowired
    private BankRecordAPI bankRecordAPI;
    @Autowired
    private BankAccountInfoAPI accountInfoAPI;
    @Autowired
    private FileAPI fileAPI;


    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = bankRecordAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 账号列表查询
     *
     * @return class BankAccountInfoVO
     * @version v1
     */
    @GetMapping("v1/accounts")
    public Result accounts(HttpServletRequest request) throws ActException {
        try {
            List<BankAccountInfoVO> voList = BeanTransform.copyProperties(accountInfoAPI.findAll(), BankAccountInfoVO.class, request);
            return ActResult.initialize(voList);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询银行流水
     *
     * @param id 银行流水Id
     * @return class BankRecordInfoVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result findByid(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            BankRecordInfoVO vo = BeanTransform.copyProperties(bankRecordAPI.find(id), BankRecordInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总
     *
     * @param year        年份
     * @param month       月份
     * @param accountName 账户名称
     * @return class BankRecordCollectVO
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(@RequestParam Integer year, @RequestParam Integer month, String accountName, HttpServletRequest request) throws ActException {
        try {
            List<BankRecordCollectVO> voList = BeanTransform.copyProperties(bankRecordAPI.collect(year, month, accountName), BankRecordCollectVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分析
     *
     * @param year        年份
     * @param month       月份
     * @param accountName 账户名称
     * @return class BankRecordAnalyzeVO
     * @version v1
     */
    @GetMapping("v1/analyze")
    public Result analyze(@RequestParam Integer year, @RequestParam Integer month, String accountName, HttpServletRequest request) throws ActException {
        try {
            BankRecordAnalyzeVO voList = BeanTransform.copyProperties(bankRecordAPI.analyze(year, month, accountName), BankRecordAnalyzeVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 对比
     *
     * @param year  年份
     * @param month 月份
     * @return class BankRecordCompareVO
     * @version v1
     */
    @GetMapping("v1/compare")
    public Result compare(@RequestParam Integer year, @RequestParam Integer month, HttpServletRequest request) throws ActException {
        try {
            BankRecordCompareVO vo = BeanTransform.copyProperties(bankRecordAPI.compare(year, month), BankRecordCompareVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}