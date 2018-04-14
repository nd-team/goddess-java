package com.bjike.goddess.businessproject.action.businessproject;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.businessproject.api.BusinessContractAPI;
import com.bjike.goddess.businessproject.bo.BusinessContractADetailBO;
import com.bjike.goddess.businessproject.bo.BusinessContractsBO;
import com.bjike.goddess.businessproject.bo.OptionMakeBO;
import com.bjike.goddess.businessproject.dto.BusinessContractDTO;
import com.bjike.goddess.businessproject.dto.BusinessContractDTOV2;
import com.bjike.goddess.businessproject.excel.BusinessContractExcel;
import com.bjike.goddess.businessproject.to.*;
import com.bjike.goddess.businessproject.vo.BusinessContractVO;
import com.bjike.goddess.common.api.constant.RpcCommon;
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
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.taskallotment.api.TaskNodeAPI;
import com.bjike.goddess.taskallotment.to.CollectDataTO;
import com.bjike.goddess.taskallotment.vo.CollectDataVO;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
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
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

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
    public Result count(BusinessContractDTOV2 dto) throws ActException {
        try {
            Long count = businessContractAPI.countV2(dto);
            return ActResult.initialize(count);
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
    public Result list(BusinessContractDTOV2 dto, HttpServletRequest request) throws ActException {
        try {
            List<BusinessContractVO> contractVOS = BeanTransform.copyProperties(
                    businessContractAPI.listV2(dto), BusinessContractVO.class, request);
            return ActResult.initialize(contractVOS);
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
     * 添加商务项目合同
     *
     * @param to 商务项目合同数据to
     * @return class BusinessContractTOV2
     * @des 添加商务项目合同
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) BusinessContractTOV2 to, BindingResult bindingResult) throws ActException {
        try {
            BusinessContractsBO bo = businessContractAPI.addV2(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BusinessContractVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 合同基本信息
     *
     * @param to to
     * @return class BusinessContractTOV2
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/baseInfo")
    public Result baseInfo(@Validated(ADD.class) BusinessContractTOV2 to, BindingResult bindingResult) throws ActException {
        try {
            BusinessContractTO businessContractTO = BeanTransform.copyProperties(to, BusinessContractTO.class);
            BusinessContractsBO bo = businessContractAPI.edit(businessContractTO);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BusinessContractVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 合同明细
     *
     * @param to to
     * @return class BusinessContractDetailsTO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/contractDetails")
    public Result contractDetails(@Validated(EDIT.class) BusinessContractDetailsTO to, BindingResult bindingResult) throws ActException {
        try {
            BusinessContractTO businessContractTO = BeanTransform.copyProperties(to, BusinessContractTO.class);
            BusinessContractsBO bo = businessContractAPI.edit(businessContractTO);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BusinessContractVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 合同金额信息
     *
     * @param to to
     * @return class BusinessContractMoneyTO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/contractMoney")
    public Result contractMoney(@Validated(EDIT.class) BusinessContractMoneyTO to, BindingResult bindingResult) throws ActException {
        try {
            BusinessContractTO businessContractTO = BeanTransform.copyProperties(to, BusinessContractTO.class);
            BusinessContractsBO bo = businessContractAPI.edit(businessContractTO);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BusinessContractVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 合同实施过程信息
     *
     * @param to to
     * @return class BusinessContractImplTO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/contractImpl")
    public Result contractImpl(@Validated(EDIT.class) BusinessContractImplTO to, BindingResult bindingResult) throws ActException {
        try {
            BusinessContractTO businessContractTO = BeanTransform.copyProperties(to, BusinessContractTO.class);
            BusinessContractsBO bo = businessContractAPI.edit(businessContractTO);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BusinessContractVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 合同结算过程信息
     *
     * @param to to
     * @return class BusinessContractAccountTO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/contractAccount")
    public Result contractAccount(@Validated(EDIT.class) BusinessContractAccountTO to, BindingResult bindingResult) throws ActException {
        try {
            BusinessContractTO businessContractTO = BeanTransform.copyProperties(to, BusinessContractTO.class);
            BusinessContractsBO bo = businessContractAPI.edit(businessContractTO);
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
            BusinessContractsBO bo = businessContractAPI.edit(to);
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
     * 获取发送邮件模板
     *
     * @param id 商务项目合同数据bo
     * @des 获取发送邮件模板
     * @version v1
     */
    @PostMapping("v1/findNotisDate")
    public Result findNotisDate(String id) throws ActException {
        try {
            String content = businessContractAPI.findNotisDate(id);
            return ActResult.initialize(content);
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
    public Result managerIdea(@Validated(BusinessContractTO.ManagerIdea.class) BusinessContractTO to, BindingResult result) throws ActException {
        try {
            BusinessContractsBO bo = businessContractAPI.managerIdea(to);
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
    public Result planIdea(@Validated(BusinessContractTO.PlanIdea.class) BusinessContractTO to, BindingResult result) throws ActException {
        try {
            BusinessContractsBO bo = businessContractAPI.planIdea(to);
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
    public Result budgetIdea(@Validated(BusinessContractTO.BudgetIdea.class) BusinessContractTO to, BindingResult result) throws ActException {
        try {
            BusinessContractsBO bo = businessContractAPI.budgetIdea(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BusinessContractVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 发邮件预立项目
     *
     * @param to 商务项目合同数据bo
     * @return class BusinessContractVO
     * @des 发邮件预立项目
     * @version v1
     */
    @PostMapping("v1/hadContract")
    public Result hadContract(BusinessContractTO to, BindingResult result) throws ActException {
        try {
            BusinessContractsBO bo = businessContractAPI.hadContract(to);
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
    public Result advance(@Validated(BusinessContractTO.Advance.class) BusinessContractTO to, BindingResult result) throws ActException {
        try {
            BusinessContractsBO bo = businessContractAPI.advance(to);
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
            BusinessContractsBO bo = businessContractAPI.changes(to);
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
    public Result notification(@Validated(BusinessContractTO.Notification.class) BusinessContractTO to, BindingResult result) throws ActException {
        try {
            BusinessContractsBO bo = businessContractAPI.notification(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BusinessContractVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 商务合同管理明细汇总
     *
     * @return class BusinessContractADetailBO
     * @des 商务合同管理明细汇总
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect() throws ActException {
        try {
            List<BusinessContractADetailBO> voList = businessContractAPI.collect();
//            for(BusinessContractADetailBO bo:voList){
//                bo.setId(UUID.randomUUID().toString());
//            }
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑商务合同管理明细汇总
     *
     * @return class BusinessContractADetailBO
     * @des 编辑商务合同管理明细汇总
     * @version v1
     */
    @GetMapping("v1/collectUpdate")
    public Result collectUpdate(CollectUpdateTO to) throws ActException {
        try {
            List<BusinessContractADetailBO> voList = businessContractAPI.collectUpdate(to);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 查询地区
     *
     * @return class AreaVO
     * @version v1
     */
    @GetMapping("v1/area")
    public Result findArea(HttpServletRequest request) throws ActException {
        try {
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            List<AreaBO> list = new ArrayList<>();
            if (moduleAPI.isCheck("organize")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
                list = departmentDetailAPI.findArea();
            }
            Set set = new HashSet();
            for (AreaBO bo : list) {
                set.add(bo.getArea());
            }
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询未冻结部门项目组详细信息
     *
     * @return class DepartmentDetailVO
     * @version v1
     */
    @GetMapping("v1/department")
    public Result department(HttpServletRequest request) throws ActException {
        try {
            List<DepartmentDetailBO> list = new ArrayList<>();
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            if (moduleAPI.isCheck("organize")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
                list = departmentDetailAPI.findStatus();
            }
            Set set = new HashSet();
            for (DepartmentDetailBO bo : list) {
                set.add(bo.getDepartment());
            }
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取商务合同中的所有地区
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/areas")
    public Result areas() throws ActException {
        try {
            List<String> areas = businessContractAPI.areas();
            return ActResult.initialize(areas);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取商务合同中的所有所属项目组
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/projectGroups")
    public Result projectGroups() throws ActException {
        try {
            List<String> areas = businessContractAPI.projectGroups();
            return ActResult.initialize(areas);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取商务合同中的所有单次合同名称
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/singleContractNames")
    public Result singleContractNames() throws ActException {
        try {
            List<String> areas = businessContractAPI.singleContractNames();
            return ActResult.initialize(areas);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取商务合同中的所有总包单位
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/majorCompanys")
    public Result majorCompanys() throws ActException {
        try {
            List<String> areas = businessContractAPI.majorCompanys();
            return ActResult.initialize(areas);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取商务合同中的所有业务方向科目
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/businessSubjects")
    public Result businessSubjects() throws ActException {
        try {
            List<String> areas = businessContractAPI.businessSubjects();
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
//    @LoginAuth
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
                        "measurePass", "notification", "commonSubcontractor", "taskFinish",
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
//    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport( BusinessContractDTO dto, HttpServletResponse response, BindingResult
            result) throws ActException {
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

    /**
     * 个人图表周汇总
     *
     * @param to
     * @des 个人图表周汇总
     * @version v1
     */
    @GetMapping("v1/weekPersonFigure")
    public Result weekPersonFigure(PersonTO to) throws ActException {
        try {
            OptionMakeBO bo = businessContractAPI.weekPersonFigure(to);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 个人图表月汇总
     *
     * @param to
     * @des 个人图表月汇总
     * @version v1
     */
    @GetMapping("v1/monthPersonFigure")
    public Result monthPersonFigure(PersonTO to) throws ActException {
        try {
            OptionMakeBO bo = businessContractAPI.monthPersonFigure(to);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 个人图表季度汇总
     *
     * @param to
     * @des 个人图表季度汇总
     * @version v1
     */
    @GetMapping("v1/quarterPersonFigure")
    public Result quarterPersonFigure(PersonTO to) throws ActException {
        try {
            OptionMakeBO bo = businessContractAPI.quarterPersonFigure(to);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 个人图表年汇总
     *
     * @param to
     * @des 个人图表周汇总
     * @version v1
     */
    @GetMapping("v1/yearPersonFigure")
    public Result yearPersonFigure(PersonTO to) throws ActException {
        try {
            OptionMakeBO bo = businessContractAPI.yearPersonFigure(to);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 部门图表周汇总
     *
     * @param to
     * @des 部门图表周汇总
     * @version v1
     */
    @GetMapping("v1/weekDepartFigure")
    public Result weekDepartFigure(PersonTO to) throws ActException {
        try {
            OptionMakeBO bo = businessContractAPI.weekDepartFigure(to);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 部门图表月汇总
     *
     * @param to
     * @des 部门图表月汇总
     * @version v1
     */
    @GetMapping("v1/monthDepartFigure")
    public Result monthDepartFigure(PersonTO to) throws ActException {
        try {
            OptionMakeBO bo = businessContractAPI.monthDepartFigure(to);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 部门图表季度汇总
     *
     * @param to
     * @des 部门图表季度汇总
     * @version v1
     */
    @GetMapping("v1/quarterDepartFigure")
    public Result quarterDepartFigure(PersonTO to) throws ActException {
        try {
            OptionMakeBO bo = businessContractAPI.quarterDepartFigure(to);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 部门图表年汇总
     *
     * @param to
     * @des 部门图表年汇总
     * @version v1
     */
    @GetMapping("v1/yearDepartFigure")
    public Result yearDepartFigure(PersonTO to) throws ActException {
        try {
            OptionMakeBO bo = businessContractAPI.yearDepartFigure(to);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    @Autowired
    private TaskNodeAPI taskNodeAPI;

    @GetMapping("v1/aa")
    public Result aa(PersonTO to) throws ActException {
        try {
            CollectDataTO collectDataTO = BeanTransform.copyProperties(to, CollectDataTO.class);
            CollectDataVO vo = taskNodeAPI.personProjectCollect(collectDataTO);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有员工
     *
     * @version v1
     */
    @GetMapping("v1/allStaff")
    public Result listAllStaff() throws ActException {
        try {
            List<UserBO> list = positionDetailUserAPI.findUserList();
            Set<String> sets = new HashSet<>();
            for (UserBO bo : list) {
                sets.add(bo.getUsername());
            }
            return ActResult.initialize(sets);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有年份
     *
     * @version v1
     */
    @GetMapping("v1/years")
    public Result years() throws ActException {
        try {
            List<Integer> list = new ArrayList<>();
            int year = LocalDate.now().getYear();
            for (int i = year - 5; i <= year + 5; i++) {
                list.add(i);
            }
            return ActResult.initialize(list);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }
}