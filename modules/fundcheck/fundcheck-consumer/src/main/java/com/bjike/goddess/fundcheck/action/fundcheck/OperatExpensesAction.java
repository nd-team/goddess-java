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
import com.bjike.goddess.fundcheck.api.OperatExpensesAPI;
import com.bjike.goddess.fundcheck.bo.OperatExpensesBO;
import com.bjike.goddess.fundcheck.dto.OperatExpensesDTO;
import com.bjike.goddess.fundcheck.entity.OperatExpenses;
import com.bjike.goddess.fundcheck.excel.OperatExpensesExcel;
import com.bjike.goddess.fundcheck.excel.OtherIncomeExcel;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import com.bjike.goddess.fundcheck.to.OperatExpensesCollectTO;
import com.bjike.goddess.fundcheck.to.OperatExpensesTO;
import com.bjike.goddess.fundcheck.to.OtherIncomeTO;
import com.bjike.goddess.fundcheck.vo.BackVO;
import com.bjike.goddess.fundcheck.vo.OperatExpensesVO;
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
 * 营业费用
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 01:54 ]
 * @Description: [ 营业费用 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("operatexpenses")
public class OperatExpensesAction extends BaseFileAction {
    @Autowired
    private OperatExpensesAPI operatExpensesAPI;
    /**
     * 功能导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = operatExpensesAPI.guidePermission(guidePermissionTO);
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
     * 营业费用列表总条数
     *
     * @param operatExpensesDTO 营业费用dto
     * @des 获取所有营业费用
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(OperatExpensesDTO operatExpensesDTO) throws ActException {
        try {
            Long count = operatExpensesAPI.count(operatExpensesDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个营业费用
     *
     * @param id
     * @return class OperatExpensesVO
     * @des 获取一个营业费用
     * @version v1
     */
    @GetMapping("v1/back/{id}")
    public Result back(@PathVariable String id) throws ActException {
        try {
            OperatExpensesBO operatExpensesBO = operatExpensesAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(operatExpensesBO, OperatExpensesVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 营业费用列表
     *
     * @param operatExpensesDTO 营业费用dto
     * @return class OperatExpensesVO
     * @des 获取所有营业费用
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(OperatExpensesDTO operatExpensesDTO, HttpServletRequest request) throws ActException {
        try {
            List<OperatExpensesVO> operatExpensesVOS = BeanTransform.copyProperties(
                    operatExpensesAPI.findList(operatExpensesDTO), OperatExpensesVO.class, request);
            return ActResult.initialize(operatExpensesVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加营业费用
     *
     * @param operatExpensesTO 营业费用to
     * @return class OperatExpensesVO
     * @des 添加营业费用
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(OperatExpensesTO.TestAdd.class) OperatExpensesTO operatExpensesTO, BindingResult bindingResult) throws ActException {
        try {
            OperatExpensesBO operatExpensesBO = operatExpensesAPI.insert(operatExpensesTO);
            return ActResult.initialize(BeanTransform.copyProperties(operatExpensesBO, OperatExpensesVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑营业费用
     *
     * @param operatExpensesTO 营业费用数据to
     * @return class OperatExpensesVO
     * @des 编辑营业费用
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(OperatExpensesTO.TestEdit.class) OperatExpensesTO operatExpensesTO, BindingResult bindingResult) throws ActException {
        try {
            OperatExpensesBO operatExpensesBO = operatExpensesAPI.edit(operatExpensesTO);
            return ActResult.initialize(BeanTransform.copyProperties(operatExpensesBO, OperatExpensesVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除营业费用
     *
     * @param id 用户id
     * @des 根据用户id删除营业费用记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            operatExpensesAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总
     *
     * @param to 营业费用数据to
     * @return class OperatExpensesVO
     * @des 汇总营业费用
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(@Validated OperatExpensesCollectTO to) throws ActException {
        try {
            List<OperatExpensesVO> operatExpensesVOS = BeanTransform.copyProperties(operatExpensesAPI.collect(to), OperatExpensesVO.class);
            return ActResult.initialize(operatExpensesVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取所有类型
     *
     * @des 获取所有类型
     * @version v1
     */
    @GetMapping("v1/listType")
    public Result listType() throws ActException {
        try {
            List<String> typeList = operatExpensesAPI.listType();
            return ActResult.initialize(typeList);
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
            List<OperatExpensesExcel> tos = ExcelUtil.excelToClazz(is, OperatExpensesExcel.class, excel);
            List<OperatExpensesTO> tocs = new ArrayList<>();
            for (OperatExpensesExcel str : tos) {
                OperatExpensesTO OperatExpensesTO = BeanTransform.copyProperties(str, OperatExpensesTO.class,"date");
                OperatExpensesTO.setDate(String.valueOf(str.getDate()));
                tocs.add(OperatExpensesTO);
            }
            //注意序列化
            operatExpensesAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * excel模板下载
     *
     * @des 下载模板营业费用
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "营业费用导入模板.xlsx";
            super.writeOutFile(response, operatExpensesAPI.templateExport( ), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

}