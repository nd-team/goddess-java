package com.bjike.goddess.contractcommunicat.action.contractcommunicat;

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
import com.bjike.goddess.contractcommunicat.api.BusinessNegotiationAPI;
import com.bjike.goddess.contractcommunicat.dto.BusinessNegotiationDTO;
import com.bjike.goddess.contractcommunicat.excel.BusinessNegotiationExcel;
import com.bjike.goddess.contractcommunicat.excel.SonPermissionObject;
import com.bjike.goddess.contractcommunicat.to.BusinessNegotiationTO;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;
import com.bjike.goddess.contractcommunicat.vo.BusinessNegotiationVO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.dto.DepartmentDetailDTO;
import com.bjike.goddess.organize.vo.DepartmentDetailVO;
//import com.bjike.goddess.problemhandle.api.ProjectProblemAccAPI;
//import com.bjike.goddess.problemhandle.bo.ProjectProblemAccBO;
//import com.bjike.goddess.problemhandle.dto.ProjectProblemAccDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 商务洽谈
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-28 11:24 ]
 * @Description: [ 商务洽谈 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businessnegotiation")
public class BusinessNegotiationAction extends BaseFileAction{
    @Autowired
    private BusinessNegotiationAPI businessNegotiationAPI;

    @Autowired
    UserSetPermissionAPI userSetPermissionAPI;
//    @Autowired
//    ProjectProblemAccAPI projectProblemAccAPI;
    @Autowired
    DepartmentDetailAPI departmentDetailAPI;


    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result setButtonPermission() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        SonPermissionObject obj = new SonPermissionObject();
        obj.setName("cuspermission");
        obj.setDescribesion("设置");
//            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
        Boolean isHasPermission = true;
        if (!isHasPermission) {
            //int code, String msg
            obj.setFlag(false);
        } else {
            obj.setFlag(true);
        }
        list.add(obj);
        return new ActResult(0, "设置权限", list);
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

            Boolean isHasPermission = businessNegotiationAPI.guidePermission(guidePermissionTO);
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
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = businessNegotiationAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BusinessNegotiationDTO dto) throws ActException {
        try {
            Long count = businessNegotiationAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询商务洽谈
     *
     * @param id 商务洽谈id
     * @return class BusinessNegotiationVO
     * @version v1
     */
    @GetMapping("v1/business/{id}")
    public Result business(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            BusinessNegotiationVO vo = BeanTransform.copyProperties(businessNegotiationAPI.getOne(id), BusinessNegotiationVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 商务洽谈列表
     *
     * @return class BusinessNegotiationVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(BusinessNegotiationDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<BusinessNegotiationVO> vo = BeanTransform.copyProperties(businessNegotiationAPI.list(dto), BusinessNegotiationVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增项目承包洽谈
     *
     * @param to 项目承包洽谈信息
     * @return class BusinessNegotiationVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) BusinessNegotiationTO to, BindingResult bindingResult) throws ActException {
        try {
            BusinessNegotiationVO voList = BeanTransform.copyProperties(businessNegotiationAPI.insert(to), BusinessNegotiationVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目承包洽谈
     *
     * @param to 项目承包洽谈信息
     * @return class ProjectOutsourcingVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) BusinessNegotiationTO to, BindingResult bindingResult) throws ActException {
        try {
            BusinessNegotiationVO vo = BeanTransform.copyProperties(businessNegotiationAPI.edit(to), BusinessNegotiationVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除项目承包洽谈
     *
     * @param id id
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/remove/{id}")
    public Result remove(@PathVariable String id) throws ActException {
        try {
            businessNegotiationAPI.remove(id);
            return new ActResult("delete success");
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
            List<BusinessNegotiationExcel> tos = ExcelUtil.excelToClazz(is, BusinessNegotiationExcel.class, excel);
            List<BusinessNegotiationTO> tocs = new ArrayList<>();
            for (BusinessNegotiationExcel str : tos) {
                BusinessNegotiationTO negotiationTO = BeanTransform.copyProperties(str, BusinessNegotiationTO.class,
                        "discussPrepare","discuss","attainDiscussIdea","discussProblem","soundRecord",
                        "hasProject","marketCost","marketFor","continueFollowUp","closedLoop",
                        "needAssist","assistLetter","produceTrip");
                //是否有洽谈准备
                negotiationTO.setDiscussPrepare(stringToBool(str.getDiscussPrepare(),"是否有洽谈准备"));
                //是否洽谈
                negotiationTO.setDiscuss(stringToBool(str.getDiscuss(),"是否洽谈"));
                //是否达到洽谈目的
                negotiationTO.setAttainDiscussIdea(stringToBool(str.getAttainDiscussIdea(),"是否达到洽谈目的"));
                //是否有录音
                negotiationTO.setDiscussProblem(stringToBool(str.getDiscussProblem(),"是否有录音"));
                //是否有洽谈到其他问题
                negotiationTO.setSoundRecord(stringToBool(str.getSoundRecord(),"是否有洽谈到其他问题"));
                //是否转入合同管理-已立项
                negotiationTO.setHasProject(stringToBool(str.getHasProject(),"是否转入合同管理-已立项"));
                //是否转入合同管理-市场费用
                negotiationTO.setMarketCost(stringToBool(str.getMarketCost(),"是否转入合同管理-市场费用"));
                //是否转换市场招待
                negotiationTO.setMarketFor(stringToBool(str.getMarketFor(),"是否转换市场招待"));
                //是否持续跟进
                negotiationTO.setContinueFollowUp(stringToBool(str.getContinueFollowUp(),"是否持续跟进"));
                //是否闭环
                negotiationTO.setClosedLoop(stringToBool(str.getClosedLoop(),"是否闭环"));
                //是否需要协助
                negotiationTO.setNeedAssist(stringToBool(str.getNeedAssist(),"是否需要协助"));
                //是否已发协助函
                negotiationTO.setAssistLetter(stringToBool(str.getAssistLetter(),"是否已发协助函"));
                //是否产生路费
                negotiationTO.setProduceTrip(stringToBool(str.getProduceTrip(),"是否产生路费"));
                tocs.add(negotiationTO);
            }
            //注意序列化
            businessNegotiationAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出excel
     *
     * @param dto dto 商务洽谈
     * @des 导出商务洽谈
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/exportExcel")
    public Result exportReport(@Validated() BusinessNegotiationDTO dto, HttpServletResponse response, BindingResult
            result) throws ActException {
        try {
            String fileName = "商务洽谈.xlsx";
            super.writeOutFile(response, businessNegotiationAPI.exportExcel(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 模板下载
     *
     * @des 下载模板商务洽谈
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "商务洽谈导入模板.xlsx";
            super.writeOutFile(response, businessNegotiationAPI.templateExcel(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    private Boolean stringToBool(String str,String fileName) throws ActException{
        Boolean bool = null;
        if(str != null){
            switch (str){
                case "是":
                    bool = true;
                    break;
                case "否":
                    bool = false;
                    break;
                default:
                    throw new ActException(fileName + "格式输入错误,正确格式为(是/否)");
            }

        }
        return bool;
    }

    /**
     * 洽谈准备
     *
     * @param id 商务洽谈id
     * @param skillLibraryId  谈判技巧库id
     * @version v1
     */
    @PostMapping("v1/prepareNegotiation")
    public Result prepareNegotiation(String id, String skillLibraryId) throws ActException {
        try {
            businessNegotiationAPI.addPrepareNegotiation(id, skillLibraryId);
            return new ActResult("添加成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 重新洽谈洽谈
     *
     * @param id 商务洽谈id
     * @param skillLibraryId  谈判技巧库id
     * @version v1
     */
    @PostMapping("v1/reNegotiation")
    public Result reNegotiation(String id, String skillLibraryId) throws ActException {
        try {
            businessNegotiationAPI.addPrepareNegotiation(id, skillLibraryId);
            return new ActResult("添加成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取全部部门
     *
     * @return class DepartmentDetailBO
     * @version v1
     */
    @GetMapping("v1/allDepartments")
    public Result allDepartment() throws ActException {
        try {
            List<DepartmentDetailBO> bos = departmentDetailAPI.view(new DepartmentDetailDTO());

            return ActResult.initialize(BeanTransform.copyProperties(bos, DepartmentDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取业务类型
     *
     * @version v1
     */
    @GetMapping("v1/businessType")
    public Result listBusinessType() throws ActException {
//        try {
//            List<ProjectProblemAccBO> bos = projectProblemAccAPI.findListProjectProblem(new ProjectProblemAccDTO());
        //todo 无法从“项目中的问题处理和受理”中获取
        Set<String> set = new HashSet<>();
        return ActResult.initialize(set);

//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
    }

    /**
     * 获取地区
     *
     * @version v1
     */
    @GetMapping("v1/area")
    public Result listArea() throws ActException {
        try {
            /*List<ProjectProblemAccBO> bos = projectProblemAccAPI.findListProjectProblem(new ProjectProblemAccDTO());
            if (null == bos) {
                return ActResult.initialize(null);
            }
            Set<String> set = new HashSet<>();
            for (ProjectProblemAccBO bo : bos) {
                set.add(bo.get());
            }*/
            Set<String> set = new HashSet<>();
            List<AreaBO> list = departmentDetailAPI.findArea();
            for (AreaBO bo : list) {
                set.add(bo.getArea());
            }
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取项目组部门
     *
     * @version v1
     */
    @GetMapping("v1/department")
    public Result listDepartment() throws ActException {
        try {
            /*List<ProblemAcceptBO> bos = projectProblemAccAPI.findListProblemAccept(new ProblemAcceptDTO());
            if (null == bos) {
                return ActResult.initialize(null);
            }
            Set<String> set = new HashSet<>();
            for (ProblemAcceptBO bo : bos) {
//                set.add(bo.getAffectedDepartment());
            }*/
            List<String> list = departmentDetailAPI.findAllProject();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取问题受理编号（对内）
     *
     * @version v1
     */
//    @GetMapping("v1/problemNum")
//    public Result problemNum() throws ActException {
//        try {
//            List<ProjectProblemAccBO> bos = projectProblemAccAPI.findListProjectProblem(new ProjectProblemAccDTO());
//            if (null == bos) {
//                return ActResult.initialize(null);
//            }
//            Set<String> set = new HashSet<>();
//            for (ProjectProblemAccBO bo : bos) {
//                set.add(bo.getProblemAcceptanceNum());
//            }
//            return ActResult.initialize(set);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
}