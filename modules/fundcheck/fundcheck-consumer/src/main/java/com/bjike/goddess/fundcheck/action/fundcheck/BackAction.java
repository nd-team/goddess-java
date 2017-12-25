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
import com.bjike.goddess.fundcheck.api.BackAPI;
import com.bjike.goddess.fundcheck.bo.BackBO;
import com.bjike.goddess.fundcheck.dto.BackDTO;
import com.bjike.goddess.fundcheck.excel.BackExcel;
import com.bjike.goddess.fundcheck.excel.SonPermissionObject;
import com.bjike.goddess.fundcheck.to.BackTO;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import com.bjike.goddess.fundcheck.vo.BackVO;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.receivable.excel.ReceivableSubsidiaryExcel;
import com.bjike.goddess.receivable.to.ReceivableSubsidiaryTO;
import org.apache.log4j.Logger;
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
 * 回款
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-30 04:48 ]
 * @Description: [ 回款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("back")
public class BackAction extends BaseFileAction{
    @Autowired
    private BackAPI backAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result i() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
                //int code, String msg
                obj.setFlag(false);
            } else {
                obj.setFlag(true);
            }
            list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = backAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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

            Boolean isHasPermission = backAPI.guidePermission(guidePermissionTO);
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
     * 回款列表总条数
     *
     * @param backDTO 回款dto
     * @des 获取所有回款
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BackDTO backDTO) throws ActException {
        try {
            Long count = backAPI.count(backDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
//    /**
//     * 一个回款
//     *
//     * @param id
//     * @return class BackVO
//     * @des 获取一个回款
//     * @version v1
//     */
//    @GetMapping("v1/back/{id}")
//    public Result back(@PathVariable String id) throws ActException {
//        try {
//            BackBO backBO = backAPI.getOne(id);
//            return ActResult.initialize(BeanTransform.copyProperties(backBO, BackVO.class));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 回款列表
//     *
//     * @param backDTO 回款dto
//     * @return class BackVO
//     * @des 获取所有回款
//     * @version v1
//     */
//    @GetMapping("v1/list")
//    public Result list(BackDTO backDTO, HttpServletRequest request) throws ActException {
//        try {
//            List<BackVO> backVOS = BeanTransform.copyProperties(
//                    backAPI.findListBack(backDTO), BackVO.class, request);
//            return ActResult.initialize(backVOS);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 添加回款
//     *
//     * @param backTO 回款to
//     * @return class BackVO
//     * @des 添加回款
//     * @version v1
//     */
//    @LoginAuth
//    @PostMapping("v1/add")
//    public Result add(@Validated(BackTO.TestAdd.class) BackTO backTO, BindingResult bindingResult) throws ActException {
//        try {
//            BackBO backBO = backAPI.insert(backTO);
//            return ActResult.initialize(BeanTransform.copyProperties(backBO, BackVO.class));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 编辑回款
//     *
//     * @param backTO 回款数据to
//     * @return class BackVO
//     * @des 编辑回款
//     * @version v1
//     */
//    @LoginAuth
//    @PostMapping("v1/edit")
//    public Result edit(@Validated(BackTO.TestEdit.class) BackTO backTO, BindingResult bindingResult) throws ActException {
//        try {
//            BackBO backBO = backAPI.edit(backTO);
//            return ActResult.initialize(BeanTransform.copyProperties(backBO, BackVO.class));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 删除回款
//     *
//     * @param id 用户id
//     * @des 根据用户id删除回款记录
//     * @version v1
//     */
//    @LoginAuth
//    @DeleteMapping("v1/delete/{id}")
//    public Result delete(@PathVariable String id) throws ActException {
//        try {
//            backAPI.remove(id);
//            return new ActResult("delete success!");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 回款
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return class BackVO
     * @des 根据时间获取回款信息
     * @version v1
     */
    @GetMapping("v1/backinfo")
    public Result backinfo(String startTime,String endTime) throws ActException {
        try {
            List<BackVO> backVOS = BeanTransform.copyProperties(
                    backAPI.backinfo(startTime,endTime), BackVO.class);
            return ActResult.initialize(backVOS);
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
            List<BackExcel> tos = ExcelUtil.excelToClazz(is, BackExcel.class, excel);
            List<BackTO> tocs = new ArrayList<>();
            for (BackExcel str : tos) {
                BackTO backTO = BeanTransform.copyProperties(str, BackTO.class,"date");
                backTO.setDate(String.valueOf(str.getDate()));
                tocs.add(backTO);
            }
            //注意序列化
            backAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
     /**
     * excel模板下载
     *
     * @des 下载模板回款
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "回款导入模板.xlsx";
            super.writeOutFile(response, backAPI.templateExport( ), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


}