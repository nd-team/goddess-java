package com.bjike.goddess.marketactivitymanage.action.marketactivitymanage;

import com.bjike.goddess.assemble.api.ModuleAPI;
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
import com.bjike.goddess.marketactivitymanage.api.CustomerInfoAPI;
import com.bjike.goddess.marketactivitymanage.api.MarketServeApplyAPI;
import com.bjike.goddess.marketactivitymanage.api.MarketServeRecordAPI;
import com.bjike.goddess.marketactivitymanage.bo.CustomerInfoBO;
import com.bjike.goddess.marketactivitymanage.bo.MarketServeRecordBO;
import com.bjike.goddess.marketactivitymanage.dto.MarketServeRecordDTO;
import com.bjike.goddess.marketactivitymanage.excel.MarketServeRecordImprotExcel;
import com.bjike.goddess.marketactivitymanage.to.*;
import com.bjike.goddess.marketactivitymanage.type.AuditType;
import com.bjike.goddess.marketactivitymanage.vo.CustomerInfoVO;
import com.bjike.goddess.marketactivitymanage.vo.MarketServeRecordVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.apache.commons.lang3.StringUtils;
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
 * 市场招待记录
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-18T10:56:16.087 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("marketserverecord")
public class MarketServeRecordAct extends BaseFileAction {

    @Autowired
    private MarketServeRecordAPI marketServeRecordAPI;
    @Autowired
    private MarketServeApplyAPI marketServeApplyAPI;
//    @Autowired
//    private MarketInfoAPI marketInfoAPI;

    @Autowired
    private CustomerInfoAPI customerInfoAPI;
    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private ModuleAPI moduleAPI;

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

            Boolean isHasPermission = marketServeRecordAPI.guidePermission(guidePermissionTO);
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
     * 根据id查询市场招待记录
     *
     * @param id 市场招待记录唯一标识
     * @return class MarketServeRecordVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/marketserverecord/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            MarketServeRecordBO bo = marketServeRecordAPI.findById(id);
            MarketServeRecordVO vo = BeanTransform.copyProperties(bo, MarketServeRecordVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 市场招待记录dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated MarketServeRecordDTO dto, BindingResult result) throws ActException {
        try {
            Long count = marketServeRecordAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 市场招待记录dto
     * @return class MarketServeRecordVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated MarketServeRecordDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<MarketServeRecordBO> boList = marketServeRecordAPI.list(dto);
            List<MarketServeRecordVO> voList = BeanTransform.copyProperties(boList, MarketServeRecordVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加市场招待记录
     *
     * @param to 市场招待记录to信息
     * @return class MarketServeRecordVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) MarketServeRecordTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            MarketServeRecordBO bo = marketServeRecordAPI.save(to);
            MarketServeRecordVO vo = BeanTransform.copyProperties(bo, MarketServeRecordVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除市场招待记录
     *
     * @param id 市场招待记录唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            marketServeRecordAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑市场招待记录
     *
     * @param to 市场招待记录to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) MarketServeRecordTO to, BindingResult result) throws ActException {
        try {
            marketServeRecordAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 运营商务部资金模块意见
     *
     * @param id                市场招待记录唯一标识
     * @param fundModuleOpinion 运营商务部资金模块意见
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/fundmodule/{id}")
    public Result fundModuleOpinion(@PathVariable(value = "id") String id, @RequestParam(value = "fundModuleOpinion") String fundModuleOpinion) throws ActException {
        try {
            marketServeRecordAPI.fundModuleOpinion(id, fundModuleOpinion);
            return new ActResult("fundModuleOpinion success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 决策层意见
     *
     * @param id                    市场招待记录唯一标识
     * @param executiveAuditOpinion 决策层审核意见
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/executive/{id}")
    public Result executiveOpinion(@PathVariable String id, @RequestParam(value = "executiveAuditOpinion") AuditType executiveAuditOpinion) throws ActException {
        try {
            marketServeRecordAPI.executiveOpinion(id, executiveAuditOpinion);
            return new ActResult("executiveOpinion success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看详情
     *
     * @param id 市场招待记录唯一标识
     * @return class MarketServeRecordVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/checkdetail/{id}")
    public Result checkDetails(@PathVariable String id) throws ActException {
        try {
            MarketServeRecordBO bo = marketServeRecordAPI.checkDetails(id);
            MarketServeRecordVO vo = BeanTransform.copyProperties(bo, MarketServeRecordVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加客户信息
     *
     * @param to 客户信息to
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/addCustomerInfo")
    public Result addClientInfo(@Validated({ADD.class}) CustomerInfoTO to, BindingResult result) throws ActException {
        try {
            marketServeRecordAPI.addClientInfo(to);
            return ActResult.initialize("addcustomerinfo success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑客户信息
     *
     * @param to 客户信息to
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/editCustomerInfo")
    public Result editClientInfo(@Validated(value = {CustomerInfoTO.EditCustomer.class}) CustomerInfoTO to, BindingResult result) throws ActException {
        try {
            marketServeRecordAPI.editClientInfo(to);
            return ActResult.initialize("editClientInfo success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看客户信息
     *
     * @param id 市场活动申请唯一标识
     * @return class CustomerInfoVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/checkCustomerInfo/{id}")
    public Result checkCustomerInfo(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            List<CustomerInfoBO> listBO = customerInfoAPI.findByMarketServeId(id);
            List<CustomerInfoVO> listVO = BeanTransform.copyProperties(listBO, CustomerInfoVO.class, request);
            return ActResult.initialize(listVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String path = "/" + id;
            List<InputStream> inputStreams = getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     *
     * @param id id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            String path = "/" + id;
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
     * @param path 文件路径
     * @version v1
     */
    @GetMapping("v1/downloadFile")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {
            //该文件的路径
            FileInfo fileInfo = new FileInfo();
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            fileInfo.setPath(path);
            String filename = StringUtils.substringAfterLast(fileInfo.getPath(), "/");
            byte[] buffer = fileAPI.download(fileInfo);
            writeOutFile(response, buffer, filename);
            return new ActResult("download success");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 删除文件或文件夹
     *
     * @param siginManageDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(SiginManageDeleteFileTO.TestDEL.class) SiginManageDeleteFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }

    /**
     * 导出Excel
     *
     * @param areas     地区
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/exportExcel")
    public Result exportExcel(String[] areas, @RequestParam String startTime, @RequestParam String endTime, HttpServletResponse response) throws ActException {
        try {
            String fileName = "市场活动记录.xlsx";
            super.writeOutFile(response, marketServeRecordAPI.exportExcel(areas, startTime, endTime), fileName);
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
     * @des 下载市场活动申请模板
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "市场活动模板.xlsx";
            super.writeOutFile(response, marketServeRecordAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
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
            List<MarketServeRecordImprotExcel> tos = ExcelUtil.excelToClazz(is, MarketServeRecordImprotExcel.class, excel);
            List<MarketServeRecordImprotTO> tocs = new ArrayList<>();
            for (MarketServeRecordImprotExcel str : tos) {
                MarketServeRecordImprotTO marketServeRecordImprotTO = BeanTransform.copyProperties(str, MarketServeRecordImprotTO.class, "planActivityTiming", "whetherTemporaryServe", "executiveAuditOpinion", "actualActivityTiming");
                marketServeRecordImprotTO.setPlanActivityTiming(String.valueOf(str.getPlanActivityTiming()).replace("T", " ").substring(0, 19));
                marketServeRecordImprotTO.setActualActivityTiming(String.valueOf(str.getActualActivityTiming()).replace("T", " ").substring(0, 19));
                marketServeRecordImprotTO.setWhetherTemporaryServe(convertWhetherTemporaryServe(str.getWhetherTemporaryServe()));
                marketServeRecordImprotTO.setExecutiveAuditOpinion(convertExecutiveAuditOpinion(str.getExecutiveAuditOpinion()));
                tocs.add(marketServeRecordImprotTO);
            }
            //注意序列化
            marketServeRecordAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    private Boolean convertWhetherTemporaryServe(String WhetherTemporaryServe) throws ActException {
        Boolean bool = true;
        if (null == WhetherTemporaryServe) {
            throw new ActException("临时招待状态填写不正确,导入失败,正确填写方式（是/否）");
        } else {
            if (WhetherTemporaryServe.equals("是")) {
                bool = true;
            } else if (WhetherTemporaryServe.equals("否")) {
                bool = false;
            } else {
                throw new ActException("临时招待状态填写不正确,导入失败,正确填写方式（是/否）");
            }
        }
        return bool;
    }

    private AuditType convertExecutiveAuditOpinion(String type) throws ActException {
        AuditType status;
        if (null != type) {
            switch (type) {
                case "未通过":
                    status = AuditType.NONE;
                    break;
                case "通过":
                    status = AuditType.ALLOWED;
                    break;
                case "拒绝":
                    status = AuditType.DENIED;
                default:
                    throw new ActException("审核意见填写不正确,导入失败,正确填写方式（未通过/通过/拒绝）");
            }
            return status;
        } else {
            return null;
        }
    }

    /**
     * 查询所有市场活动记录的地区
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findAreas")
    public Result findAreas() throws ActException {
        try {
            List<String> areas = new ArrayList<>();
            areas = marketServeRecordAPI.findAllAreas();
            return ActResult.initialize(areas);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加编辑中所有的项目代号
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findProjectCode")
    public Result findProjectCode() throws ActException {
        try {
            List<String> projectCode = new ArrayList<>();
            projectCode = marketServeApplyAPI.findProjectCode();
            return ActResult.initialize(projectCode);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加编辑中所有的项目性质
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findMarket/projectNature")
    public Result findMarketPNature() throws ActException {
        try {
            List<String> projectNature = new ArrayList<>();
//            if(moduleAPI.isCheck("market")){
//                projectNature = marketInfoRecordAPI.find();
//            }
            return ActResult.initialize(projectNature);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

}