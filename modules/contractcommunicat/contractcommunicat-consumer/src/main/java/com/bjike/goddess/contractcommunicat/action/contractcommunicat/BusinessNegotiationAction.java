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
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.contractcommunicat.api.BusinessNegotiationAPI;
import com.bjike.goddess.contractcommunicat.dto.BusinessNegotiationDTO;
import com.bjike.goddess.contractcommunicat.excel.BusinessNegotiationExcel;
import com.bjike.goddess.contractcommunicat.to.BusinessNegotiationTO;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;
import com.bjike.goddess.contractcommunicat.vo.BusinessNegotiationVO;
import com.bjike.goddess.contractcommunicat.vo.InProjectsVO;
import com.bjike.goddess.contractcommunicat.vo.ProjectOutsourcingVO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.dto.DepartmentDetailDTO;
import com.bjike.goddess.organize.vo.DepartmentDetailVO;
import com.bjike.goddess.projectissuehandle.api.ProjectProblemAccAPI;
import com.bjike.goddess.projectissuehandle.bo.ProjectProblemAccBO;
import com.bjike.goddess.projectissuehandle.dto.ProjectProblemAccDTO;
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
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    ProjectProblemAccAPI projectProblemAccAPI;




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
            ProjectOutsourcingVO vo = BeanTransform.copyProperties(businessNegotiationAPI.edit(to), ProjectOutsourcingVO.class);
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
    @PutMapping("v1/remove/{id}")
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
//    @LoginAuth
    @PostMapping("v1/import")
    public Result leadExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<BusinessNegotiationExcel> tos = ExcelUtil.excelToClazz(is, BusinessNegotiationExcel.class, excel);
            List<BusinessNegotiationTO> toList = new ArrayList<>();
            for (BusinessNegotiationExcel excel1 : tos){
                BusinessNegotiationTO to = BeanTransform.copyProperties(excel1, BusinessNegotiationTO.class, "discussPrepare", "discuss", "attainDiscussIdea", "discussProblem", "soundRecord", "hasProject", "marketCost", "marketFor", "continueFollowUp", "closedLoop", "needAssist", "assistLetter", "produceTrip");

                if ("是".equals(excel1.getDiscussPrepare())) {
                    to.setDiscussPrepare(true);
                } else {
                    to.setDiscussPrepare(false);
                }
                if ("是".equals(excel1.getDiscuss())) {
                    to.setDiscuss(true);
                } else {
                    to.setDiscuss(false);
                }

                if ("是".equals(excel1.getAttainDiscussIdea())) {
                    to.setAttainDiscussIdea(true);
                } else {
                    to.setAttainDiscussIdea(false);
                }

                if ("是".equals(excel1.getDiscussProblem())) {
                    to.setDiscussProblem(true);
                } else {
                    to.setDiscussProblem(false);
                }

                if ("是".equals(excel1.getSoundRecord())) {
                    to.setSoundRecord(true);
                } else {
                    to.setSoundRecord(false);
                }
                if ("是".equals(excel1.getHasProject())) {
                    to.setHasProject(true);
                } else {
                    to.setHasProject(false);
                }
                if ("是".equals(excel1.getMarketCost())) {
                    to.setMarketCost(true);
                } else {
                    to.setMarketCost(false);
                }
                if ("是".equals(excel1.getMarketFor())) {
                    to.setMarketFor(true);
                } else {
                    to.setMarketFor(false);
                }
                if ("是".equals(excel1.getContinueFollowUp())) {
                    to.setContinueFollowUp(true);
                } else {
                    to.setContinueFollowUp(false);
                }
                if ("是".equals(excel1.getClosedLoop())) {
                    to.setClosedLoop(true);
                } else {
                    to.setClosedLoop(false);
                }
                if ("是".equals(excel1.getNeedAssist())) {
                    to.setNeedAssist(true);
                } else {
                    to.setNeedAssist(false);
                }
                if ("是".equals(excel1.getAssistLetter())) {
                    to.setAssistLetter(true);
                } else {
                    to.setAssistLetter(false);
                }
                if ("是".equals(excel1.getProduceTrip())) {
                    to.setProduceTrip(true);
                } else {
                    to.setProduceTrip(false);
                }
                toList.add(to);
            }

            businessNegotiationAPI.importExcel(toList);

            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导出Excel
     *
     * @param dto dto
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/export")
    public Result exportExcel(BusinessNegotiationDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "商务洽谈.xlsx";
            super.writeOutFile(response, businessNegotiationAPI.exportExcel(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出Excel模板
     *
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/exportTemplate")
    public Result exportExcelTemplate(HttpServletResponse response) throws ActException {
        try {
            String fileName = "商务洽谈模板.xlsx";
            super.writeOutFile(response, businessNegotiationAPI.templateExcel(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 洽谈准备
     *
     * @param id 商务洽谈id
     * @param skillLibraryId  谈判技巧库id
     * @return class
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
     * @return class
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
     * @return class
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
     * @return class
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
     * @return class
     * @version v1
     */
    @GetMapping("v1/area")
    public Result listArea() throws ActException {
        try {
            List<ProjectProblemAccBO> bos = projectProblemAccAPI.findListProjectProblem(new ProjectProblemAccDTO());
            Set<String> set = new HashSet<>();
            for (ProjectProblemAccBO bo : bos) {
                set.add(bo.getEachDistrict());
            }
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取项目组/ 部门
     *
     * @return class
     * @version v1
     */
    @GetMapping("v1/department")
    public Result listDepartment() throws ActException {
        try {
            List<ProjectProblemAccBO> bos = projectProblemAccAPI.findListProjectProblem(new ProjectProblemAccDTO());
            Set<String> set = new HashSet<>();
            for (ProjectProblemAccBO bo : bos) {
                set.add(bo.getSubordinateDepartment());
            }

            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取问题受理编号（对内）
     *
     * @return class
     * @version v1
     */
    @GetMapping("v1/problemNum")
    public Result problemNum() throws ActException {
        try {
            List<ProjectProblemAccBO> bos = projectProblemAccAPI.findListProjectProblem(new ProjectProblemAccDTO());
            Set<String> set = new HashSet<>();
            for (ProjectProblemAccBO bo : bos) {
                set.add(bo.getProblemAcceptanceNum());
            }
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}