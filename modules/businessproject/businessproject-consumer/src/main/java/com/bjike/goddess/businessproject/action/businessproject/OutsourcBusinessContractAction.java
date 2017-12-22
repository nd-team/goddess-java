package com.bjike.goddess.businessproject.action.businessproject;

import com.bjike.goddess.businessproject.api.OutsourcBusinessContractAPI;
import com.bjike.goddess.businessproject.bo.OutsourcBusinessContractBO;
import com.bjike.goddess.businessproject.dto.OutsourcBusinessContractDTO;
import com.bjike.goddess.businessproject.excel.OutsourcBusinessContractExcel;
import com.bjike.goddess.businessproject.to.GuidePermissionTO;
import com.bjike.goddess.businessproject.to.OutsourcBusinessContractTO;
import com.bjike.goddess.businessproject.vo.OutsourcBusinessContractVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
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
import java.util.Set;

/**
 * 外包半外包项目合同管理
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-19 11:55 ]
 * @Description: [ 外包半外包项目合同管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("outsourcbusinesscontract")
public class OutsourcBusinessContractAction extends BaseFileAction {
    @Autowired
    private OutsourcBusinessContractAPI outsourcBusinessContractAPI;

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

            Boolean isHasPermission = outsourcBusinessContractAPI.guidePermission(guidePermissionTO);
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
     * @param dto 外包半外包项目合同管理信息dto
     * @des 获取所有外包半外包项目合同管理信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(OutsourcBusinessContractDTO dto) throws ActException {
        try {
            Long count = outsourcBusinessContractAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个外包半外包项目合同管理
     *
     * @param id 外包半外包项目合同管理信息id
     * @return class OutsourcBusinessContractVO
     * @des 根据id获取外包半外包项目合同管理信息
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            OutsourcBusinessContractVO contractVOS = BeanTransform.copyProperties(
                    outsourcBusinessContractAPI.getOneById(id), OutsourcBusinessContractVO.class);
            return ActResult.initialize(contractVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 外包半外包项目合同管理列表
     *
     * @param dto 外包半外包项目合同管理dto
     * @return class OutsourcBusinessContractVO
     * @des 获取所有外包半外包项目合同管理
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(OutsourcBusinessContractDTO dto, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<OutsourcBusinessContractVO> contractVOS = BeanTransform.copyProperties(
                    outsourcBusinessContractAPI.list(dto), OutsourcBusinessContractVO.class, request);
            return ActResult.initialize(contractVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加外包半外包项目合同管理
     *
     * @param to 外包半外包项目合同管理信息数据to
     * @return class OutsourcBusinessContractVO
     * @des 添加外包半外包项目合同管理
     * @version v1
     */
//    @LoginAuth
//    @PostMapping("v1/add")
//    public Result add(@Validated(DispatchSheetTO.TestAdd.class) OutsourcBusinessContractTO to , BindingResult bindingResult) throws ActException {
//        try {
//            OutsourcBusinessContractBO bo = outsourcBusinessContractAPI.add(to);
//            return ActResult.initialize(BeanTransform.copyProperties(bo, OutsourcBusinessContractVO.class));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 编辑供应商
     *
     * @param to 外包半外包项目合同管理数据bo
     * @return class OutsourcBusinessContractVO
     * @des 编辑供应商
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/supplier")
    public Result supplier(OutsourcBusinessContractTO to) throws ActException {
        try {
            OutsourcBusinessContractBO bo = outsourcBusinessContractAPI.supplier(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, OutsourcBusinessContractVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑外包半外包项目合同管理
     *
     * @param to 外包半外包项目合同管理数据bo
     * @return class OutsourcBusinessContractVO
     * @des 编辑外包半外包项目合同管理
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(OutsourcBusinessContractTO to) throws ActException {
        try {
            OutsourcBusinessContractBO bo = outsourcBusinessContractAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, OutsourcBusinessContractVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除外包半外包项目合同管理记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            outsourcBusinessContractAPI.delete(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有地区
     *
     * @version v1
     */
    @GetMapping("v1/areas")
    public Result areas() throws ActException {
        try {
            Set<String> areas = outsourcBusinessContractAPI.areas();
            return ActResult.initialize(areas);
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
            List<OutsourcBusinessContractExcel> tos = ExcelUtil.excelToClazz(is, OutsourcBusinessContractExcel.class, excel);
            List<OutsourcBusinessContractTO> tocs = new ArrayList<>();
            for (OutsourcBusinessContractExcel str : tos) {
                OutsourcBusinessContractTO contractTO = BeanTransform.copyProperties(str, OutsourcBusinessContractTO.class,
                        "measurePass", "cooperation", "complete",
                        "qualifiedGist", "accept", "acceptorPass", "account", "pay", "closedLoop");
//              //测算是否通过
                if (str.getMeasurePass().equals("是")) {
                    contractTO.setMeasurePass(true);
                } else {
                    contractTO.setMeasurePass(false);
                }
                //是否有合同派工
//                if (str.getTaskContract().equals("是")) {
//                    contractTO.setTaskContract(true);
//                } else {
//                    contractTO.setTaskContract(false);
//                }
                //是否有合同立项
//                if (contractTO.getMakeContract().equals("是")) {
//                    contractTO.setMakeContract(true);
//                } else {
//                    contractTO.setMakeContract(false);
//                }
                //是否确定合作
                if (str.getCooperation().equals("是")) {
                    contractTO.setCooperation(true);
                } else {
                    contractTO.setCooperation(false);
                }
                //是否完工
                if (str.getComplete().equals("是")) {
                    contractTO.setComplete(true);
                } else {
                    contractTO.setComplete(false);
                }
                //是否提供完工合格依据
                if (str.getQualifiedGist().equals("是")) {
                    contractTO.setQualifiedGist(true);
                } else {
                    contractTO.setQualifiedGist(false);
                }
                //是否验收
                if (str.getAccept().equals("是")) {
                    contractTO.setAccept(true);
                } else {
                    contractTO.setAccept(false);
                }
                //验收是否通过
                if (str.getAcceptorPass().equals("是")) {
                    contractTO.setAcceptorPass(true);
                } else {
                    contractTO.setAcceptorPass(false);
                }
                //是否到账
                if (str.getAccount().equals("是")) {
                    contractTO.setAccount(true);
                } else {
                    contractTO.setAccount(false);
                }
                //是否付款成功
                if (str.getPay().equals("是")) {
                    contractTO.setPay(true);
                } else {
                    contractTO.setPay(false);
                }
                //是否闭环
                if (str.getClosedLoop().equals("是")) {
                    contractTO.setClosedLoop(true);
                } else {
                    contractTO.setClosedLoop(false);
                }
                tocs.add(contractTO);
            }
            //注意序列化
            outsourcBusinessContractAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导出excel
     *
     * @param dto 外包半外包项目合同管理
     * @des 导出外包半外包项目合同管理
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(OutsourcBusinessContractDTO dto, HttpServletResponse response, BindingResult result) throws ActException {
        try {
            String fileName = "外包半外包项目合同管理.xlsx";
            super.writeOutFile(response, outsourcBusinessContractAPI.exportExcel(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * excel模板下载
     *
     * @des 下载模板外包半外包项目合同管理
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "外包半外包项目合同管理导入模板.xlsx";
            super.writeOutFile(response, outsourcBusinessContractAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


}