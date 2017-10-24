package com.bjike.goddess.businessproject.action.businessproject;

import com.bjike.goddess.businessproject.api.BusinessContractAPI;
import com.bjike.goddess.businessproject.bo.BusinessContractBO;
import com.bjike.goddess.businessproject.dto.BusinessContractDTO;
import com.bjike.goddess.businessproject.excel.BusinessContractExcel;
import com.bjike.goddess.businessproject.to.BusinessContractTO;
import com.bjike.goddess.businessproject.to.GuidePermissionTO;
import com.bjike.goddess.businessproject.vo.BusinessContractADetailVO;
import com.bjike.goddess.businessproject.vo.BusinessContractVO;
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
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 商务项目合同
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-19 11:36 ]
 * @Description: [ 商务项目合同 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businesscontract")
public class BusinessContractAction extends BaseFileAction {
    @Autowired
    private BusinessContractAPI businessContractAPI;

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

            Boolean isHasPermission = businessContractAPI.guidePermission(guidePermissionTO);
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
     * @param dto 商务项目合同信息dto
     * @des 获取所有商务项目合同信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BusinessContractDTO dto) throws ActException {
        try {
            Long count = businessContractAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个商务项目合同
     *
     * @param id 项目商务项目合同信息id
     * @return class BusinessContractVO
     * @des 根据id获取项目商务项目合同信息
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            BusinessContractVO contractVOS = BeanTransform.copyProperties(
                    businessContractAPI.getOneById(id), BusinessContractVO.class);
            return ActResult.initialize(contractVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 商务项目合同列表
     *
     * @param dto 项目商务项目合同dto
     * @return class BusinessContractVO
     * @des 获取所有项目商务项目合同
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(BusinessContractDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<BusinessContractVO> contractVOS = BeanTransform.copyProperties(
                    businessContractAPI.list(dto), BusinessContractVO.class, request);
            return ActResult.initialize(contractVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加商务项目合同
     *
     * @param to 商务项目合同数据to
     * @return class BusinessContractVO
     * @des 添加商务项目合同
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated() BusinessContractTO to, BindingResult bindingResult) throws ActException {
        try {
            BusinessContractBO bo = businessContractAPI.add(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BusinessContractVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑商务项目合同
     *
     * @param to 商务项目合同数据bo
     * @return class BusinessContractVO
     * @des 编辑商务项目合同
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(BusinessContractTO to) throws ActException {
        try {
            BusinessContractBO bo = businessContractAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BusinessContractVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除商务项目合同记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            businessContractAPI.delete(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目经理意见分析
     *
     * @param to 商务项目合同数据bo
     * @return class BusinessContractVO
     * @des 项目经理意见分析
     * @version v1
     */
    @PostMapping("v1/managerIdea")
    public Result managerIdea(@Validated(BusinessContractTO.ManagerIdea.class) BusinessContractTO to,BindingResult result) throws ActException {
        try {
            BusinessContractBO bo = businessContractAPI.managerIdea(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BusinessContractVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 规划模块分析意见
     *
     * @param to 商务项目合同数据bo
     * @return class BusinessContractVO
     * @des 规划模块分析意见
     * @version v1
     */
    @PostMapping("v1/planIdea")
    public Result planIdea(@Validated(BusinessContractTO.PlanIdea.class) BusinessContractTO to,BindingResult result) throws ActException {
        try {
            BusinessContractBO bo = businessContractAPI.planIdea(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BusinessContractVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 预算模块分析意见
     *
     * @param to 商务项目合同数据bo
     * @return class BusinessContractVO
     * @des 预算模块分析意见
     * @version v1
     */
    @PostMapping("v1/budgetIdea")
    public Result budgetIdea(@Validated(BusinessContractTO.BudgetIdea.class) BusinessContractTO to,BindingResult result) throws ActException {
        try {
            BusinessContractBO bo = businessContractAPI.budgetIdea(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BusinessContractVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 预立项
     *
     * @param to 商务项目合同数据bo
     * @return class BusinessContractVO
     * @des 预立项
     * @version v1
     */
    @PostMapping("v1/hadContract")
    public Result hadContract(@Validated() BusinessContractTO to,BindingResult result) throws ActException {
        try {
            BusinessContractBO bo = businessContractAPI.hadContract(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BusinessContractVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 预立项目
     *
     * @param to 商务项目合同数据bo
     * @return class BusinessContractVO
     * @des 预立项目
     * @version v1
     */
    @PostMapping("v1/advance")
    public Result advance(@Validated(BusinessContractTO.Advance.class) BusinessContractTO to,BindingResult result) throws ActException {
        try {
            BusinessContractBO bo = businessContractAPI.advance(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BusinessContractVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 预估项目变更
     *
     * @param to 商务项目合同数据bo
     * @return class BusinessContractVO
     * @des 预估项目变更
     * @version v1
     */
    @PostMapping("v1/changes")
    public Result changes(BusinessContractTO to) throws ActException {
        try {
            BusinessContractBO bo = businessContractAPI.changes(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BusinessContractVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通报
     *
     * @param to 商务项目合同数据bo
     * @return class BusinessContractVO
     * @des 通报
     * @version v1
     */
    @PostMapping("v1/notification")
    public Result notification(@Validated(BusinessContractTO.Notification.class) BusinessContractTO to,BindingResult result) throws ActException {
        try {
            BusinessContractBO bo = businessContractAPI.notification(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BusinessContractVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据地区汇总商务合同管理明细汇总
     *
     * @param areas areas
     * @return class BusinessContractADetailVO
     * @des 根据地区汇总商务合同管理明细汇总
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(String[] areas) throws ActException {
        try {
            List<BusinessContractADetailVO> voList = BeanTransform.copyProperties(businessContractAPI.collect(areas), BusinessContractADetailVO.class);
            return ActResult.initialize(voList);
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
            Set<String> areas = businessContractAPI.areas();
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
            List<BusinessContractExcel> tos = ExcelUtil.excelToClazz(is, BusinessContractExcel.class, excel);
            List<BusinessContractTO> tocs = new ArrayList<>();
            for (BusinessContractExcel str : tos) {
                BusinessContractTO contractTO = BeanTransform.copyProperties(str, BusinessContractTO.class,
                        "measurePass", "notification", "commonSubcontractor", "taskFinish", "taskContract",
                         "scaleBalance", "solutionBalance", "implement", "partial",
                        "persist", "settlementProcess", "account", "closeSingle", "archive");
                //测算是否通过
                if (str.getMeasurePass().equals("是")) {
                    contractTO.setMeasurePass(true);
                } else {
                    contractTO.setMeasurePass(false);
                }
                //是否通报
                if (str.getNotification().equals("是")) {
                    contractTO.setNotification(true);
                } else {
                    contractTO.setNotification(false);
                }
                //是否有共同分包单位
                if (str.getCommonSubcontractor().equals("是")) {
                    contractTO.setCommonSubcontractor(true);
                } else {
                    contractTO.setCommonSubcontractor(false);
                }
                //派工归属清理是否完成
                if (str.getTaskFinish().equals("是")) {
                    contractTO.setTaskFinish(true);
                } else {
                    contractTO.setTaskFinish(false);
                }
                //是否有合同派工合同
                if (str.getTaskContract().equals("是")) {
                    contractTO.setTaskContract(true);
                } else {
                    contractTO.setTaskContract(false);
                }
                //合同规模数是否有差异
                if (str.getScaleBalance().equals("是")) {
                    contractTO.setScaleBalance(true);
                } else {
                    contractTO.setScaleBalance(false);
                }
                //是否解决差异问题
                if (str.getSolutionBalance().equals("是")) {
                    contractTO.setSolutionBalance(true);
                } else {
                    contractTO.setSolutionBalance(false);
                }
                //预估项目是否确认实施
                if (str.getImplement().equals("是")) {
                    contractTO.setImplement(true);
                } else {
                    contractTO.setImplement(false);
                }
                //是否分批结算
                if (str.getPartial().equals("是")) {
                    contractTO.setPartial(true);
                } else {
                    contractTO.setPartial(false);
                }
                //是否为持续
                if (str.getPersist().equals("是")) {
                    contractTO.setPersist(true);
                } else {
                    contractTO.setPersist(false);
                }
                //是否正在走结算流程
                if (str.getSettlementProcess().equals("是")) {
                    contractTO.setSettlementProcess(true);
                } else {
                    contractTO.setSettlementProcess(false);
                }
                //是否到账
                if (str.getAccount().equals("是")) {
                    contractTO.setAccount(true);
                } else {
                    contractTO.setAccount(false);
                }
                //是否闭单
                if (str.getCloseSingle().equals("是")) {
                    contractTO.setCloseSingle(true);
                } else {
                    contractTO.setCloseSingle(false);
                }
                //合同是否已归档
                if (str.getArchive().equals("是")) {
                    contractTO.setArchive(true);
                } else {
                    contractTO.setArchive(false);
                }
                tocs.add(contractTO);
            }
            //注意序列化
            businessContractAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



    /**
     * 导出excel
     *
     * @param dto 商务项目合同
     * @des 导出商务项目合同
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(@Validated(BusinessContractDTO.EXPORT.class) BusinessContractDTO dto, HttpServletResponse response, BindingResult result) throws ActException {
        try {
            String fileName = "商务项目合同.xlsx";
            super.writeOutFile(response, businessContractAPI.exportExcel(dto), fileName);
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
     * @des 下载模板商务项目合同
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "商务项目合同导入模板.xlsx";
            super.writeOutFile(response, businessContractAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

}