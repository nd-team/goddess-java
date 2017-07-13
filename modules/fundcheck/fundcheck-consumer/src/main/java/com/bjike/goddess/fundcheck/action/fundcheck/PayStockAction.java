package com.bjike.goddess.fundcheck.action.fundcheck;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.fundcheck.api.PayStockAPI;
import com.bjike.goddess.fundcheck.bo.BackBO;
import com.bjike.goddess.fundcheck.bo.PayStockBO;
import com.bjike.goddess.fundcheck.dto.BackDTO;
import com.bjike.goddess.fundcheck.dto.PayStockDTO;
import com.bjike.goddess.fundcheck.excel.PayStockExcel;
import com.bjike.goddess.fundcheck.excel.StockMoneyExcel;
import com.bjike.goddess.fundcheck.to.*;
import com.bjike.goddess.fundcheck.vo.BackVO;
import com.bjike.goddess.fundcheck.vo.PayStockVO;
import com.bjike.goddess.fundcheck.vo.StockMoneyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 支付给股东
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 01:55 ]
 * @Description: [ 支付给股东 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("paystock")
public class PayStockAction extends BaseFileAction{
    @Autowired
    private PayStockAPI payStockAPI;
    /**
     * 功能导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = payStockAPI.guidePermission(guidePermissionTO);
            if(! isHasPermission ){
                //int code, String msg
                return new ActResult(0,"没有权限",false );
            }else{
                return new ActResult(0,"有权限",true );
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 支付给股东列表总条数
     *
     * @param payStockDTO 支付给股东dto
     * @des 获取所有支付给股东
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(PayStockDTO payStockDTO) throws ActException {
        try {
            Long count = payStockAPI.count(payStockDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个支付给股东
     *
     * @param id
     * @return class PayStockVO
     * @des 获取一个支付给股东
     * @version v1
     */
    @GetMapping("v1/payStock/{id}")
    public Result payStock(@PathVariable String id) throws ActException {
        try {
            PayStockBO payStockBO = payStockAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(payStockBO, PayStockVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 支付给股东列表
     *
     * @param payStockDTO 支付给股东dto
     * @return class PayStockVO
     * @des 获取所有支付给股东
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(PayStockDTO payStockDTO, HttpServletRequest request) throws ActException {
        try {
            List<PayStockVO> payStockVOS = BeanTransform.copyProperties(
                    payStockAPI.findListBack(payStockDTO), PayStockVO.class, request);
            return ActResult.initialize(payStockVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加支付给股东
     *
     * @param payStockTO 支付给股东to
     * @return class PayStockVO
     * @des 添加支付给股东
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) PayStockTO payStockTO, BindingResult bindingResult) throws ActException {
        try {
            PayStockBO payStockBO = payStockAPI.insert(payStockTO);
            return ActResult.initialize(BeanTransform.copyProperties(payStockBO, PayStockVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑支付给股东
     *
     * @param payStockTO 支付给股东数据to
     * @return class PayStockVO
     * @des 编辑支付给股东
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) PayStockTO payStockTO, BindingResult bindingResult) throws ActException {
        try {
            PayStockBO payStockBO = payStockAPI.edit(payStockTO);
            return ActResult.initialize(BeanTransform.copyProperties(payStockBO, PayStockVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除支付给股东
     *
     * @param id 用户id
     * @des 根据用户id删除支付给股东记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            payStockAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总
     *
     * @param to 支付给股东数据to
     * @return class PayStockVO
     * @des 汇总支付给股东
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(@Validated PayStockCollectTO to, BindingResult bindingResult) throws ActException {
        try {
            List<PayStockVO> payStockVOS = BeanTransform.copyProperties(payStockAPI.collect(to), PayStockVO.class);
            return ActResult.initialize(payStockVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取所有一级科目
     *
     * @des 获取所有一级科目
     * @version v1
     */
    @GetMapping("v1/listFirstSubject")
    public Result listFirstSubject() throws ActException {
        try {
            List<String> userList = payStockAPI.listFirstSubject();
            return ActResult.initialize(userList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有二级科目
     *
     * @des 根据一级科目获取所有二级科目
     * @version v1
     */
    @GetMapping("v1/listSubByFirst")
    public Result listSubByFirst(@RequestParam String firstSub) throws ActException {
        try {
            List<String> userList = payStockAPI.listSubByFirst(firstSub);
            return ActResult.initialize(userList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有三级科目
     *
     * @des 根据一级二级科目获取所有三级科目
     * @version v1
     */
    @GetMapping("v1/listTubByFirst")
    public Result listTubByFirst(@RequestParam String firstSub, @RequestParam String secondSub) throws ActException {
        try {
            List<String> userList = payStockAPI.listTubByFirst(firstSub, secondSub);
            return ActResult.initialize(userList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导入Excel
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/importExcel")
    public Result importExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<PayStockExcel> tos = ExcelUtil.excelToClazz(is, PayStockExcel.class, excel);
            List<PayStockTO> tocs = new ArrayList<>();
            for (PayStockExcel str : tos) {
                PayStockTO payStockTO = BeanTransform.copyProperties(str, PayStockTO.class,"date");
                payStockTO.setDate(String.valueOf(str.getDate()));
                tocs.add(payStockTO);
            }
            //注意序列化
            payStockAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * excel模板下载
     *
     * @des 下载模板支付给股东
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "支付给股东导入模板.xlsx";
            super.writeOutFile(response, payStockAPI.templateExport( ), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }
}