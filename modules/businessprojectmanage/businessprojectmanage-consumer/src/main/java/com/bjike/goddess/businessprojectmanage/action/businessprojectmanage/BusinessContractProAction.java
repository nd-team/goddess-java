package com.bjike.goddess.businessprojectmanage.action.businessprojectmanage;

import com.bjike.goddess.businessprojectmanage.api.BusinessContractProAPI;
import com.bjike.goddess.businessprojectmanage.bo.BusinessContractProBO;
import com.bjike.goddess.businessprojectmanage.dto.BusinessContractProDTO;
import com.bjike.goddess.businessprojectmanage.enums.ProgressStatus;
import com.bjike.goddess.businessprojectmanage.excel.BusinessContractProExcel;
import com.bjike.goddess.businessprojectmanage.to.BusinessContractProTO;
import com.bjike.goddess.businessprojectmanage.to.GuidePermissionTO;
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

/**
 * 项目合同基本信息
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-11 10:03 ]
 * @Description: [ 项目合同基本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businesscontractpro")
public class BusinessContractProAction extends BaseFileAction {

    @Autowired
    BusinessContractProAPI businessContractProAPI;

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

            Boolean isHasPermission = businessContractProAPI.guidePermission(guidePermissionTO);
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
     * 获取项目合同列表
     *
     * @param dto dto
     * @return class BusinessContractProBO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result listBusinessContractProgress(BusinessContractProDTO dto, BindingResult bindingResult) throws ActException {
        try {
            List<BusinessContractProBO> bos = businessContractProAPI.listBusinessContactProgress(dto);
//            List<BusinessContractProVO> vos = BeanTransform.copyProperties(bos, BusinessContractProVO.class);
            return ActResult.initialize(bos);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取项目合同总数
     *
     * @param dto 　dto
     * @version v1
     */
    @GetMapping("v1/count")
    public Result countBusinessContractProgress(BusinessContractProDTO dto, BindingResult bindingResult) throws ActException {
        try {
            Long amount = businessContractProAPI.countBusinessContactProgress(dto);
            return ActResult.initialize(amount);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加合同
     *
     * @param to to
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addBusinessContractProgress(BusinessContractProTO to, BindingResult bindingResult) throws ActException {
        try {

            for (ProgressStatus progressStatus : ProgressStatus.values()) {
                System.out.println(progressStatus.getRemark());
                if (progressStatus.getRemark().equals(to.getProgressStatus())) {

                    to.setProgressStatus(progressStatus.getCode() + "");
                    break;
                }
            }
            businessContractProAPI.addBusinessContactProgress(to);
            // TODO 将数据同时添加到“合同信息表”
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改项目合同
     *
     * @param to to
     * @version v1
     */
    @PutMapping("v1/modify")
    public Result modifyBusinessContractProgress(BusinessContractProTO to, BindingResult bindingResult) throws ActException {
        try {
            businessContractProAPI.addBusinessContactProgress(to);
            // TODO 同时修改“合同信息表”的数据
            return ActResult.initialize(true);
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
            List<BusinessContractProExcel> tos = ExcelUtil.excelToClazz(is, BusinessContractProExcel.class, excel);
            List<BusinessContractProTO> tocs = new ArrayList<>();
            for (BusinessContractProExcel str : tos) {
                BusinessContractProTO contractTO = BeanTransform.copyProperties(str, BusinessContractProTO.class,
                        "measurePass", "notification", "commonSubcontractor", "taskFinish", "taskContract",
                        "scaleBalance", "solutionBalance", "implement", "partial",
                        "persist", "settlementProcess", "account", "closeSingle", "archive");
                //测算是否通过
                if (null != str.getMeasurePass()) {
                    if (str.getMeasurePass().equals("是")) {
                        contractTO.setMeasurePass(true);
                    } else {
                        contractTO.setMeasurePass(false);
                    }
                }
                //是否通报
                if (null != str.getNotification()) {
                    if (str.getNotification().equals("是")) {
                        contractTO.setNotification(true);
                    } else {
                        contractTO.setNotification(false);
                    }
                }
                //是否有共同分包单位
                if (null != str.getCommonSubcontractor()) {
                    if (str.getCommonSubcontractor().equals("是")) {
                        contractTO.setCommonSubcontractor(true);
                    } else {
                        contractTO.setCommonSubcontractor(false);
                    }
                }
                //派工归属清理是否完成
                if (null != str.getTaskFinish()) {
                    if (str.getTaskFinish().equals("是")) {
                        contractTO.setTaskFinish(true);
                    } else {
                        contractTO.setTaskFinish(false);
                    }
                }
                //是否有合同派工合同
                if (null != str.getTaskContract()) {
                    if (str.getTaskContract().equals("是")) {
                        contractTO.setTaskContract(true);
                    } else {
                        contractTO.setTaskContract(false);
                    }
                }
                //合同规模数是否有差异
                if (null != str.getScaleBalance()) {
                    if (str.getScaleBalance().equals("是")) {
                        contractTO.setScaleBalance(true);
                    } else {
                        contractTO.setScaleBalance(false);
                    }
                }
                //是否解决差异问题
                if (null != str.getSolutionBalance()) {
                    if (str.getSolutionBalance().equals("是")) {
                        contractTO.setSolutionBalance(true);
                    } else {
                        contractTO.setSolutionBalance(false);
                    }
                }
                //预估项目是否确认实施
                if (null != str.getImplement()) {
                    if (str.getImplement().equals("是")) {
                        contractTO.setImplement(true);
                    } else {
                        contractTO.setImplement(false);
                    }
                }
                //是否分批结算
                if (null != str.getPartial()) {
                    if (str.getPartial().equals("是")) {
                        contractTO.setPartial(true);
                    } else {
                        contractTO.setPartial(false);
                    }
                }
                //是否为持续
                if (null != str.getPersist()) {
                    if (str.getPersist().equals("是")) {
                        contractTO.setPersist(true);
                    } else {
                        contractTO.setPersist(false);
                    }
                }
                //是否正在走结算流程
                if (null != str.getSettlementProcess()) {
                    if (str.getSettlementProcess().equals("是")) {
                        contractTO.setSettlementProcess(true);
                    } else {
                        contractTO.setSettlementProcess(false);
                    }
                }
                //是否到账
                if (null != str.getAccount()) {
                    if (str.getAccount().equals("是")) {
                        contractTO.setAccount(true);
                    } else {
                        contractTO.setAccount(false);
                    }
                }
                //是否闭单
                if (null != str.getCloseSingle()) {
                    if (str.getCloseSingle().equals("是")) {
                        contractTO.setCloseSingle(true);
                    } else {
                        contractTO.setCloseSingle(false);
                    }
                }
                //合同是否已归档
                if (null != str.getArchive()) {
                    if (str.getArchive().equals("是")) {
                        contractTO.setArchive(true);
                    } else {
                        contractTO.setArchive(false);
                    }
                }
                tocs.add(contractTO);
            }
            //注意序列化
            businessContractProAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出excel
     *
     * @param dto 条件
     * @des 导出项目实施进度
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(@Validated() BusinessContractProDTO dto, HttpServletResponse response, BindingResult
            result) throws ActException {
        try {
            String fileName = "商务项目合同实施进度.xlsx";
            super.writeOutFile(response, businessContractProAPI.exportExcel(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

}