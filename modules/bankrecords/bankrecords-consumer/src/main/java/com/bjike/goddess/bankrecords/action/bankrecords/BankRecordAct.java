package com.bjike.goddess.bankrecords.action.bankrecords;

import com.bjike.goddess.bankrecords.api.BankAccountInfoAPI;
import com.bjike.goddess.bankrecords.api.BankRecordAPI;
import com.bjike.goddess.bankrecords.dto.BankRecordDTO;
import com.bjike.goddess.bankrecords.to.BankRecordTO;
import com.bjike.goddess.bankrecords.vo.*;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.storage.api.FileAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("bankrecord")
public class BankRecordAct extends BaseFileAction {

    @Autowired
    private BankRecordAPI bankRecordAPI;
    @Autowired
    private BankAccountInfoAPI accountInfoAPI;
    @Autowired
    private FileAPI fileAPI;

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
     * 检查导入的Excel标题
     *
     * @return class ExcelTitleVO
     * @version v1
     */
    @PostMapping("v1/check")
    public Result check(HttpServletRequest request) throws ActException {
        try {
            List<ExcelTitleVO> list = BeanTransform.copyProperties(bankRecordAPI.check(super.getInputStreams(request)), ExcelTitleVO.class);
            return ActResult.initialize(list);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导入银行流水
     *
     * @param to 账号信息信息
     * @version v1
     */
    @PostMapping("v1/upload")
    public Result upload(@Validated({BankRecordTO.Upload.class}) BankRecordTO to,BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            String path = "/upload";
            List<InputStream> inputStreams = super.getInputStreams(request, path);
            List<InputStream> streams = super.getInputStreams(request, path);
            to.setInputStreams(inputStreams);
            bankRecordAPI.upload(to);
            //由于协议，必须在action处理上传到服务器
//            fileAPI.upload(streams);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询列表总条数
     *
     * @param dto 查询条件或分页条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BankRecordDTO dto) throws ActException {
        try {
            Long count = bankRecordAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
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
     * 列表
     *
     * @param dto 查询条件或分页条件
     * @return class BankRecordPageListVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(@Validated({BankRecordDTO.PageList.class}) BankRecordDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<BankRecordPageListVO> voList = BeanTransform.copyProperties(bankRecordAPI.pageList(dto), BankRecordPageListVO.class, request);
            return ActResult.initialize(voList);
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
     * 删除银行流水记录
     *
     * @param id 银行流水id
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            bankRecordAPI.delete(id);
            return new ActResult();
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