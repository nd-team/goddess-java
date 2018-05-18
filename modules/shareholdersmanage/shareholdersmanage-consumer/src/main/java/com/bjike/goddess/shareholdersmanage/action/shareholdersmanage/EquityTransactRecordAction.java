package com.bjike.goddess.shareholdersmanage.action.shareholdersmanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.api.EquityTransactRecordAPI;
import com.bjike.goddess.shareholdersmanage.api.EquityTransactRecordDetailAPI;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordBO;
import com.bjike.goddess.shareholdersmanage.bo.ShareAndTypeBO;
import com.bjike.goddess.shareholdersmanage.dto.EquityTransactRecordDTO;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecord;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import com.bjike.goddess.shareholdersmanage.to.ShareOpenDeleteFileTO;
import com.bjike.goddess.shareholdersmanage.vo.EquityTransactRecordDetailVO;
import com.bjike.goddess.shareholdersmanage.vo.EquityTransactRecordVO;
import com.bjike.goddess.shareholdersmanage.vo.ShareAndTypeVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 股权交易记录
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:05 ]
 * @Description: [ 股权交易记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("equitytransactrecord")
public class EquityTransactRecordAction{
    @Autowired
    private EquityTransactRecordAPI equityTransactRecordAPI;
    @Autowired
    private EquityTransactRecordDetailAPI equityTransactRecordDetailAPI;
    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, javax.servlet.http.HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = equityTransactRecordAPI.guidePermission(guidePermissionTO);
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
     * 列表总条数
     *
     * @param equityTransactRecordDTO 交易记录dto
     * @des 获取所有交易记录总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result countShareOpen(EquityTransactRecordDTO equityTransactRecordDTO) throws ActException {
        try {
            Long count = equityTransactRecordAPI.countTrans(equityTransactRecordDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个交易记录
     *
     * @param id 交易记录id
     * @return class EquityTransactRecordVO
     * @des 根据id获取交易记录
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            EquityTransactRecordVO equityTransactRecordVO = BeanTransform.copyProperties(
                    equityTransactRecordAPI.getOne(id), EquityTransactRecordVO.class);
            return ActResult.initialize(equityTransactRecordVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 交易记录列表
     *
     * @param equityTransactRecordDTO 交易记录dto
     * @return class EquityTransactRecordVO
     * @des 获取所有交易记录
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListTrans(EquityTransactRecordDTO equityTransactRecordDTO,HttpServletRequest request) throws ActException {
        try {
            List<EquityTransactRecordVO> equityTransactRecordVOList = BeanTransform.copyProperties(
                    equityTransactRecordAPI.findList(equityTransactRecordDTO), EquityTransactRecordVO.class, request);
            return ActResult.initialize(equityTransactRecordVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看交易记录明细
     *
     * @param shareholderName 股东姓名
     * @return class EquityTransactRecordDetailVO
     * @des 获取所有交易记录
     * @version v1
     */
    @GetMapping("v1/transDetail")
    public Result findTransDetail(@RequestParam String shareholderName, HttpServletRequest request) throws ActException {
        try {
            List<EquityTransactRecordVO> equityTransactRecordVOList = BeanTransform.copyProperties(
                    equityTransactRecordDetailAPI.findByName(shareholderName), EquityTransactRecordDetailVO.class, request);
            return ActResult.initialize(equityTransactRecordVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总
     *
     * @return class EquityTransactRecordVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/proCompare")
    public Result proCompare(HttpServletRequest request) throws ActException {
        try {
            List<EquityTransactRecordBO> boList = equityTransactRecordAPI.summationTrans();
            List<EquityTransactRecordVO> voList = BeanTransform.copyProperties(boList, EquityTransactRecordVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 所有的姓名和对应类型
     *
     * @return class ShareAndTypeVO
     * @version v1
     */
    @GetMapping("v1/allName/allType")
    public Result findNameType(HttpServletRequest request) throws ActException {
        try {
            List<ShareAndTypeBO> boList = equityTransactRecordAPI.getNameAndType();
            List<ShareAndTypeVO> voList = BeanTransform.copyProperties(boList, ShareAndTypeVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 根据对应类型查询所有的姓名
     *
     * @version v1
     */
    @GetMapping("v1/findName/type")
    public Result findNameByType(@RequestParam String  equityType, HttpServletRequest request) throws ActException {
        try {
            List<String> name = new ArrayList<>();
            name = equityTransactRecordAPI.getNameByEquityType(equityType);
            return ActResult.initialize(name);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 所有的股权类型
     *
     * @version v1
     */
    @GetMapping("v1/findEquityType")
    public Result findEquityType() throws ActException {
        try {
            List<String> type = new ArrayList<>();
            type = equityTransactRecordAPI.findEquityType();
            return ActResult.initialize(type);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}