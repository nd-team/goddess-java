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
import com.bjike.goddess.contractcommunicat.to.BusinessNegotiationTO;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;
import com.bjike.goddess.contractcommunicat.vo.BusinessNegotiationVO;
import com.bjike.goddess.contractcommunicat.vo.InProjectsVO;
import com.bjike.goddess.contractcommunicat.vo.ProjectOutsourcingVO;
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
public class BusinessNegotiationAction extends BaseFileAction {
    @Autowired
    private BusinessNegotiationAPI businessNegotiationAPI;
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
            List<BusinessNegotiationVO> vo = BeanTransform.copyProperties(businessNegotiationAPI.list(dto), InProjectsVO.class);
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
            BusinessNegotiationVO voList = BeanTransform.copyProperties(businessNegotiationAPI.insert(to), ProjectOutsourcingVO.class);
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
    @PutMapping("v1/remove")
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
    @PostMapping("v1/importExcel")
    public Result importExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<BusinessNegotiationExcel> tos = ExcelUtil.excelToClazz(is, BusinessNegotiationExcel.class, excel);
            List<BusinessNegotiationTO> tocs = new ArrayList<> ();
            for (BusinessNegotiationExcel str : tos) {
                BusinessNegotiationTO businessNegotiationTO = BeanTransform.copyProperties(str, BusinessNegotiationTO.class,
                        "discussPrepare", "discuss", "attainDiscussIdea", "discussProblem",
                        "soundRecord", "hasProject", "marketCost", "marketFor",
                        "continueFollowUp", "closedLoop", "needAssist", "assistLetter", "produceTrip");
                //是否有洽谈准备
                if (null != str.getDiscussPrepare()) {
                    if (str.getDiscussPrepare().equals("是")) {
                        businessNegotiationTO.setDiscussPrepare(true);
                    } else {
                        businessNegotiationTO.setDiscussPrepare(false);
                    }
                }
                //是否洽谈
                if(null != str.getDiscuss()){
                    if (str.getDiscuss().equals("是")) {
                        businessNegotiationTO.setDiscuss(true);
                    } else {
                        businessNegotiationTO.setDiscuss(false);
                    }
                }
                //是否达到洽谈目的
                if(null != str.getAttainDiscussIdea()){
                    if (str.getAttainDiscussIdea().equals("是")) {
                        businessNegotiationTO.setAttainDiscussIdea(true);
                    } else {
                        businessNegotiationTO.setAttainDiscussIdea(false);
                    }
                }
                //是否有洽谈到其他问题
                if(null != str.getDiscussProblem()){
                    if (str.getDiscussProblem().equals("是")) {
                        businessNegotiationTO.setDiscussProblem(true);
                    } else {
                        businessNegotiationTO.setDiscussProblem(false);
                    }
                }

                //是否有录音
                if (null != str.getSoundRecord()) {
                    if (str.getSoundRecord().equals("是")) {
                        businessNegotiationTO.setSoundRecord(true);
                    } else {
                        businessNegotiationTO.setSoundRecord(false);
                    }
                }
                //是否转入合同管理-已立项
                if(null != str.getHasProject()){
                    if (str.getHasProject().equals("是")) {
                        businessNegotiationTO.setHasProject(true);
                    } else {
                        businessNegotiationTO.setHasProject(false);
                    }
                }
                //是否转入合同管理-市场费用
                if(null != str.getMarketCost()){
                    if (str.getMarketCost().equals("是")) {
                        businessNegotiationTO.setMarketCost(true);
                    } else {
                        businessNegotiationTO.setMarketCost(false);
                    }
                }
                //是否转换市场招待
                if(null != str.getMarketFor()){
                    if (str.getMarketFor().equals("是")) {
                        businessNegotiationTO.setMarketFor(true);
                    } else {
                        businessNegotiationTO.setMarketFor(false);
                    }
                }
                //是否持续跟进
                if(null != str.getContinueFollowUp()){
                    if (str.getContinueFollowUp().equals("是")) {
                        businessNegotiationTO.setContinueFollowUp(true);
                    } else {
                        businessNegotiationTO.setContinueFollowUp(false);
                    }
                }
                //是否闭环
                if(null != str.getClosedLoop()){
                    if (str.getClosedLoop().equals("是")) {
                        businessNegotiationTO.setClosedLoop(true);
                    } else {
                        businessNegotiationTO.setClosedLoop(false);
                    }
                }
                //是否需要协助
                if(null != str.getNeedAssist()){
                    if (str.getNeedAssist().equals("是")) {
                        businessNegotiationTO.setNeedAssist(true);
                    } else {
                        businessNegotiationTO.setNeedAssist(false);
                    }
                }
                //是否已发协助函
                if(null != str.getAssistLetter()){
                    if (str.getAssistLetter().equals("是")) {
                        businessNegotiationTO.setAssistLetter(true);
                    } else {
                        businessNegotiationTO.setAssistLetter(false);
                    }
                }
                //是否产生路费
                if(null != str.getProduceTrip()){
                    if (str.getProduceTrip().equals("是")) {
                        businessNegotiationTO.setProduceTrip(true);
                    } else {
                        businessNegotiationTO.setProduceTrip(false);
                    }
                }
                tocs.add(businessNegotiationTO);
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
     * @param dto 商务洽谈
     * @des 商务洽谈
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/export")
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
}