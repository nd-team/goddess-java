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
import com.bjike.goddess.fundcheck.api.OtherIncomeAPI;
import com.bjike.goddess.fundcheck.bo.OtherIncomeBO;
import com.bjike.goddess.fundcheck.bo.StockMoneyBO;
import com.bjike.goddess.fundcheck.dto.OtherIncomeDTO;
import com.bjike.goddess.fundcheck.dto.StockMoneyDTO;
import com.bjike.goddess.fundcheck.entity.OtherIncome;
import com.bjike.goddess.fundcheck.excel.OtherIncomeExcel;
import com.bjike.goddess.fundcheck.excel.OtherSpendExcel;
import com.bjike.goddess.fundcheck.to.*;
import com.bjike.goddess.fundcheck.vo.OtherIncomeVO;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 其他收入
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-30 04:53 ]
 * @Description: [ 其他收入 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("otherincome")
public class OtherIncomeAction extends BaseFileAction{
    @Autowired
    private OtherIncomeAPI otherIncomeAPI;
    /**
     * 功能导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = otherIncomeAPI.guidePermission(guidePermissionTO);
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
     * 其他收入列表总条数
     *
     * @param otherIncomeDTO 其他收入dto
     * @des 获取所有其他收入
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(OtherIncomeDTO otherIncomeDTO) throws ActException {
        try {
            Long count = otherIncomeAPI.count(otherIncomeDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个其他收入
     *
     * @param id
     * @return class OtherIncomeVO
     * @des 获取一个其他收入
     * @version v1
     */
    @GetMapping("v1/other/{id}")
    public Result other(@PathVariable String id) throws ActException {
        try {
            OtherIncomeBO otherIncomeBO = otherIncomeAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(otherIncomeBO, OtherIncomeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 其他收入列表
     *
     * @param otherIncomeDTO 其他收入dto
     * @return class OtherIncomeVO
     * @des 获取所有其他收入
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(OtherIncomeDTO otherIncomeDTO, HttpServletRequest request) throws ActException {
        try {
            List<OtherIncomeVO> otherIncomeVOS = BeanTransform.copyProperties(
                    otherIncomeAPI.findList(otherIncomeDTO), OtherIncomeVO.class, request);
            return ActResult.initialize(otherIncomeVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加其他收入
     *
     * @param otherIncomeTO 其他收入to
     * @return class OtherIncomeVO
     * @des 添加其他收入
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) OtherIncomeTO otherIncomeTO, BindingResult bindingResult) throws ActException {
        try {
            OtherIncomeBO otherIncomeBO = otherIncomeAPI.insert(otherIncomeTO);
            return ActResult.initialize(BeanTransform.copyProperties(otherIncomeBO, OtherIncomeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑其他收入
     *
     * @param otherIncomeTO 其他收入数据to
     * @return class OtherIncomeVO
     * @des 编辑其他收入
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) OtherIncomeTO otherIncomeTO, BindingResult bindingResult) throws ActException {
        try {
            OtherIncomeBO otherIncomeBO = otherIncomeAPI.edit(otherIncomeTO);
            return ActResult.initialize(BeanTransform.copyProperties(otherIncomeBO, OtherIncomeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除其他收入
     *
     * @param id 用户id
     * @des 根据用户id删除其他收入记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            otherIncomeAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总
     *
     * @param to 其他收入数据to
     * @des 汇总其他收入
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(@Validated OtherIncomeCollectTO to, BindingResult bindingResult) throws ActException {
        try {
            LinkedHashMap<String,String> map  = otherIncomeAPI.collect(to);
            return ActResult.initialize(map);
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
            List<String> userList = otherIncomeAPI.listFirstSubject();
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
            List<String> userList = otherIncomeAPI.listSubByFirst(firstSub);
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
            List<String> userList = otherIncomeAPI.listTubByFirst(firstSub, secondSub);
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
            List<OtherIncomeExcel> tos = ExcelUtil.excelToClazz(is, OtherIncomeExcel.class, excel);
            List<OtherIncomeTO> tocs = new ArrayList<>();
            for (OtherIncomeExcel str : tos) {
                OtherIncomeTO otherIncomeTO = BeanTransform.copyProperties(str, OtherIncomeTO.class,"date");
                otherIncomeTO.setDate(String.valueOf(str.getDate()));
                tocs.add(otherIncomeTO);
            }
            //注意序列化
            otherIncomeAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * excel模板下载
     *
     * @des 下载模板其他收入
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "其他收入导入模板.xlsx";
            super.writeOutFile(response, otherIncomeAPI.templateExport( ), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

}