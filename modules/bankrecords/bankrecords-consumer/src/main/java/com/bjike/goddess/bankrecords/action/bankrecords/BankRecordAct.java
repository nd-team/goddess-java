package com.bjike.goddess.bankrecords.action.bankrecords;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.bankrecords.api.BankRecordAPI;
import com.bjike.goddess.bankrecords.dto.BankRecordDTO;
import com.bjike.goddess.bankrecords.to.BankRecordTO;
import com.bjike.goddess.bankrecords.vo.BankRecordAnalyzeVO;
import com.bjike.goddess.bankrecords.vo.BankRecordCollectVO;
import com.bjike.goddess.bankrecords.vo.BankRecordInfoVO;
import com.bjike.goddess.bankrecords.vo.BankRecordPageListVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 银行流水
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 05:35 ]a
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
    private FileAPI fileAPI;

    /**
     * 检查导入的Excel标题
     *
     * @version v1
     */
    @PostMapping("v1/check")
    public Result check(HttpServletRequest request) throws ActException {
        try {
            List<String> list = bankRecordAPI.check(super.getInputStreams(request));
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
    public Result upload(@Validated({BankRecordTO.Upload.class}) BankRecordTO to, HttpServletRequest request, BindingResult bindingResult) throws ActException {
        try {
            String path = "/upload";
            List<InputStream> inputStreams = super.getInputStreams(request, path);
            List<InputStream> streams =  super.getInputStreams(request, path);
            to.setInputStreams(inputStreams);
            bankRecordAPI.upload(to);
            //由于协议，必须在action处理上传到服务器
            fileAPI.upload(streams);
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
            BankRecordInfoVO vo = BeanTransform.copyProperties(bankRecordAPI.findById(id), BankRecordInfoVO.class, request);
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
    public Result pageList(BankRecordDTO dto, HttpServletRequest request) throws ActException {
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
     * 汇总
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
     * 通过流获取文件信息
     *
     * @param obj
     * @return
     */
    private String getFileInfo(Object obj) {
        String json_info = new String((byte[]) obj);
        if (!StringUtils.isEmpty(json_info)) {
            json_info = json_info.replaceAll("\\\\", "");
            json_info = json_info.substring(1, json_info.length() - 1);
            FileInfo fileInfo = JSON.parseObject(json_info, FileInfo.class);
            return fileInfo.getFileName();
        }
        return null;
    }
}