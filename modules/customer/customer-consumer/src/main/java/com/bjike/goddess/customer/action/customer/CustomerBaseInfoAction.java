package com.bjike.goddess.customer.action.customer;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.common.api.constant.RpcCommon;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.customer.api.CusPermissionAPI;
import com.bjike.goddess.customer.api.CustomerBaseInfoAPI;
import com.bjike.goddess.customer.api.CustomerDetailAPI;
import com.bjike.goddess.customer.bo.CustomerBaseInfoBO;
import com.bjike.goddess.customer.bo.OptionBO;
import com.bjike.goddess.customer.bo.PieOptionBO;
import com.bjike.goddess.customer.bo.SummationBO;
import com.bjike.goddess.customer.dto.CustomerBaseInfoDTO;
import com.bjike.goddess.customer.excel.CustomerBaseInfoExcel;
import com.bjike.goddess.customer.to.*;
import com.bjike.goddess.customer.vo.*;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 客户基本信息
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-15T16:23:28.059 ]
 * @Description: [ 客户基本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("customerbaseinfo")
public class CustomerBaseInfoAction extends BaseFileAction {

    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private CustomerBaseInfoAPI customerBaseInfoAPI;
    @Autowired
    private CustomerDetailAPI customerDetailAPI;
    @Autowired
    private CusPermissionAPI cusPermissionAPI;


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

            Boolean isHasPermission = customerBaseInfoAPI.guidePermission(guidePermissionTO);
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
     * 客户基本列表总条数
     *
     * @param customerBaseInfoDTO 客户基本信息dto
     * @des 获取所有客户基本信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CustomerBaseInfoDTO customerBaseInfoDTO) throws ActException {
        try {
            Long count = customerBaseInfoAPI.countCustomerBaseInfo(customerBaseInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 客户基本列表
     *
     * @param customerBaseInfoDTO 客户基本信息dto
     * @return class CustomerBaseInfoVO
     * @des 获取所有客户基本信息
     * @version v1
     */
    @GetMapping("v1/listCustomerBaseInfo")
    public Result findListCustomerBaseInfo(CustomerBaseInfoDTO customerBaseInfoDTO) throws ActException {
        try {

            List<CustomerBaseInfoBO> customerBaseInfoBOList = customerBaseInfoAPI.listCustomerBaseInfo(customerBaseInfoDTO);
            List<CustomerBaseInfoVO> customerBaseInfoVOList = new ArrayList<>();
            customerBaseInfoBOList.stream().forEach(str -> {
                CustomerLevelVO customerLevelVO = BeanTransform.copyProperties(str.getCustomerLevelBO(), CustomerLevelVO.class, true);
                CustomerBaseInfoVO customerBaseInfoVO = BeanTransform.copyProperties(str, CustomerBaseInfoVO.class);
                customerBaseInfoVO.setCustomerLevelVO(customerLevelVO);
                customerBaseInfoVOList.add(customerBaseInfoVO);
            });
            return ActResult.initialize(customerBaseInfoVOList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 自动编号
     *
     * @return class CustomerBaseInfoVO
     * @des 自动生成客户编号
     * @version v1
     */
    @GetMapping("v1/generateNumber")
    public Result generateNumber() throws ActException {
        try {
            CustomerBaseInfoBO customerBaseInfoBO1 = customerBaseInfoAPI.generateCustomerNum();
            return ActResult.initialize(BeanTransform.copyProperties(customerBaseInfoBO1, CustomerBaseInfoVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加客户基本信息
     *
     * @param customerBaseInfoTO 客户基本信息数据to
     * @return class CustomerBaseInfoVO
     * @des 添加客户基本信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addCustomerBaseInfo(@Validated CustomerBaseInfoTO customerBaseInfoTO, BindingResult bindingResult) throws ActException {
        try {

            CustomerBaseInfoBO customerBaseInfoBO1 = customerBaseInfoAPI.addCustomerBaseInfo(customerBaseInfoTO);
            return ActResult.initialize(BeanTransform.copyProperties(customerBaseInfoBO1, CustomerBaseInfoVO.class, true));

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑客户基本
     *
     * @param customerBaseInfoTO 客户基本基本信息数据bo
     * @return class CustomerBaseInfoVO
     * @des 添加客户基本
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editCustomerBaseInfo(@Validated CustomerBaseInfoTO customerBaseInfoTO) throws ActException {
        try {

            CustomerBaseInfoBO customerBaseInfoBO1 = customerBaseInfoAPI.editCustomerBaseInfo(customerBaseInfoTO);
            return ActResult.initialize(BeanTransform.copyProperties(customerBaseInfoBO1, CustomerBaseInfoVO.class, true));

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除客户基本信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteCustomerBaseInfo(@PathVariable String id) throws ActException {
        try {

            customerBaseInfoAPI.deleteCustomerBaseInfo(id);
            return new ActResult("delete success!");

        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }


    /**
     * 冻结
     *
     * @param id id
     * @des 根据id冻结客户基本信息记录
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {

            customerBaseInfoAPI.congealCustomerBaseInfo(id);
            return new ActResult("congeal success!");

        } catch (SerException e) {
            throw new ActException("冻结失败：" + e.getMessage());
        }
    }


    /**
     * 解冻
     *
     * @param id id
     * @des 根据id解冻客户基本信息记录
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id) throws ActException {
        try {

            customerBaseInfoAPI.thawCustomerBaseInfo(id);
            return new ActResult("thaw success!");

        } catch (SerException e) {
            throw new ActException("解冻失败：" + e.getMessage());
        }
    }

    /**
     * 获取客户编号
     *
     * @return {name:'List<string>',type:'List<string>',defaultValue:'',description:'返回地区数组'}
     * @des 获取客户编号集合
     * @version v1
     */
    @GetMapping("v1/getCusNum")
    public Result getCusNum() throws ActException {
        try {
            List<String> areaList = customerBaseInfoAPI.getCustomerBaseInfoCusNum();
            return ActResult.initialize(areaList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取客户地区
     *
     * @return {name:'List<string>',type:'List<string>',defaultValue:'',description:'返回地区数组'}
     * @des 获取客户地区集合
     * @version v1
     */
    @GetMapping("v1/getArea")
    public Result getCusArea() throws ActException {
        try {
            List<String> areaList = customerBaseInfoAPI.getCustomerBaseInfoArea();
            return ActResult.initialize(areaList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取客户名
     *
     * @return {name:'List<string>',type:'List<string>',defaultValue:'',description:'获取客户名集合'}
     * @des 获取客户名集合
     * @version v1
     */
    @GetMapping("v1/getName")
    public Result getCusName() throws ActException {
        try {
            List<String> nameList = customerBaseInfoAPI.getCustomerBaseInfoName();
            return ActResult.initialize(nameList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取单个客户
     *
     * @param customerNum customerNum
     * @return class CustomerBaseInfoVO
     * @des 根据客户编号查询客户基本信息
     * @version v1
     */
    @GetMapping("v1/getCustomer")
    public Result getCustomer(String customerNum) throws ActException {
        try {
            CustomerBaseInfoBO bo = customerBaseInfoAPI.getCustomerInfoByNum(customerNum);
            CustomerLevelVO customerLevelVO = BeanTransform.copyProperties(bo.getCustomerLevelBO(), CustomerLevelVO.class);
            CustomerBaseInfoVO customerBaseInfoVO = BeanTransform.copyProperties(bo, CustomerBaseInfoVO.class);
            customerBaseInfoVO.setCustomerLevelVO(customerLevelVO);
            return ActResult.initialize(customerBaseInfoVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取行业数组
     *
     * @return {name:'List<string>',type:'List<string>',defaultValue:'',description:'返回行业数组'}
     * @des 获取客户编号集合
     * @version v1
     */
    @GetMapping("v1/getWorks")
    public Result getWorks() throws ActException {
        try {
            List<String> workList = customerBaseInfoAPI.getCustomerBaseInfoWorks();
            return ActResult.initialize(workList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出excel
     *
     * @des 导出客户信息记录
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "客户信息.xlsx";
            super.writeOutFile(response, customerBaseInfoAPI.exportExcel(), fileName);
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
     * @des 下载模板客户信息
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "客户信息模板.xlsx";
            super.writeOutFile(response, customerBaseInfoAPI.templateExport(), fileName);
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
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<CustomerBaseInfoExcel> tos = ExcelUtil.mergeExcelToClazz(is, CustomerBaseInfoExcel.class, excel);
//            List<IndividualResumeTO> tocs = new ArrayList<>();
            Set<String> customerNums = new HashSet<>();
            for (CustomerBaseInfoExcel customerBaseInfoExcel : tos) {
                customerNums.add(customerBaseInfoExcel.getCustomerNum());
            }
            for (String customerNum : customerNums) {
                List<CustomerBaseInfoExcel> customerBaseInfoExcels = new ArrayList<>();
                List<CusFamilyMemberTO> cusFamilyMemberTOS = new ArrayList<>();
                for (CustomerBaseInfoExcel str : tos) {
                    if (str.getCustomerNum().equals(customerNum)) {
                        customerBaseInfoExcels.add(str);
                        CusFamilyMemberTO cusFamilyMemberTO = BeanTransform.copyProperties(str, CusFamilyMemberTO.class);
                        cusFamilyMemberTOS.add(cusFamilyMemberTO);
                    }
                }
                CustomerDetailTO customerDetailTO = BeanTransform.copyProperties(customerBaseInfoExcels.get(0), CustomerDetailTO.class,"birthday");
                customerDetailTO.setBirthday(customerBaseInfoExcels.get(0).getBirthday().toString());
                CustomerBaseInfoTO customerBaseInfoTO = BeanTransform.copyProperties(customerBaseInfoExcels.get(0), CustomerBaseInfoTO.class,"proceedMarketTreat","marketReceptTime");
                customerBaseInfoTO.setProceedMarketTreat(stringToBool(customerBaseInfoExcels.get(0).getProceedMarketTreat()));
                customerBaseInfoTO.setMarketReceptTime(DateUtil.dateToString(customerBaseInfoExcels.get(0).getMarketReceptTime()));
                customerDetailTO.setCustomerBaseInfoTO(customerBaseInfoTO);
                customerDetailTO.setCusFamilyMemberTOList(cusFamilyMemberTOS);
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
                customerBaseInfoAPI.addCustomerBaseInfo(customerBaseInfoTO);
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
                customerDetailAPI.editCustomerDetail(customerDetailTO);
            }

            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    public Boolean stringToBool(String value) throws ActException {
        Boolean bool = null;
        if (value != null) {
            switch (value) {
                case "是":
                    bool = true;
                    break;
                case "否":
                    bool = false;
                    break;
                default:
                    throw new ActException("是否需进行市场招待格式输入错误,正确格式为(是/否)");
            }
        }
        return bool;
    }

    /**
     * 市场信息日汇总
     *
     * @param date 日期
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/day")
    public Result summarizeDay(String date, HttpServletRequest request) throws ActException {
        try {
            List<SummationBO> boList = customerBaseInfoAPI.summaDay(date);
            List<SummationVO> voList = BeanTransform.copyProperties(boList, SummationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 市场信息周汇总
     *
     * @param year  年份
     * @param month 月份
     * @param week  周期
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/week")
    public Result summarizeWeek(Integer year, Integer month, Integer week, HttpServletRequest request) throws ActException {
        try {
            List<SummationBO> boList = customerBaseInfoAPI.summaWeek(year, month, week);
            List<SummationVO> voList = BeanTransform.copyProperties(boList, SummationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 市场信息月汇总
     *
     * @param year  年份
     * @param month 月份
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/month")
    public Result summarizeMonth(Integer year, Integer month, HttpServletRequest request) throws ActException {
        try {
            List<SummationBO> boList = customerBaseInfoAPI.summaMonth(year, month);
            List<SummationVO> voList = BeanTransform.copyProperties(boList, SummationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 市场信息季度汇总
     *
     * @param year  年份
     * @param quarter 季度
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/quarter")
    public Result summarizeQuarter(Integer year, Integer quarter, HttpServletRequest request) throws ActException {
        try {
            List<SummationBO> boList = customerBaseInfoAPI.summaQuarter(year, quarter);
            List<SummationVO> voList = BeanTransform.copyProperties(boList, SummationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 市场信息年度汇总
     *
     * @param year  年份
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/year")
    public Result summarizeYear(Integer year, HttpServletRequest request) throws ActException {
        try {
            List<SummationBO> boList = customerBaseInfoAPI.summaYear(year);
            List<SummationVO> voList = BeanTransform.copyProperties(boList, SummationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 市场信息累计汇总
     *
     * @param date 截止日期
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/total")
    public Result summarizeTotal(String date, HttpServletRequest request) throws ActException {
        try {
            List<SummationBO> boList = customerBaseInfoAPI.summaTotal(date);
            List<SummationVO> voList = BeanTransform.copyProperties(boList, SummationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 客户信息图形展示日汇总
     *
     * @param date 日期
     * @return class OptionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/day")
    public Result figureShowDay(String date, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = customerBaseInfoAPI.figureShowDay(date);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);

            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 客户信息图形展示周汇总
     *
     * @param year  年份
     * @param month 月份
     * @param week  周期
     * @return class OptionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/week")
    public Result figureShowWeek(Integer year, Integer month, Integer week, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = customerBaseInfoAPI.figureShowWeek(year, month, week);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 客户信息图形展示月汇总
     *
     * @param year  年份
     * @param month 月份
     * @return class OptionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/month")
    public Result figureShowMonth(Integer year, Integer month, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = customerBaseInfoAPI.figureShowMonth(year, month);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 客户信息图形展示季度汇总
     *
     * @param year  年份
     * @param quarter 季度
     * @return class OptionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/quarter")
    public Result figureShowQuarter(Integer year, Integer quarter, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = customerBaseInfoAPI.figureShowQuarter(year, quarter);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 客户信息图形展示年度汇总
     *
     * @param year  年份
     * @return class OptionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/year")
    public Result figureShowYear(Integer year, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = customerBaseInfoAPI.figureShowYear(year);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 客户信息图形展示累计汇总
     * @return class OptionVO
     * @param date 截止日期
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/total")
    public Result figureShowTotal(String date, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = customerBaseInfoAPI.figureShowTotal(date);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 客户地区分布情况饼状图
     * @return class PieOptionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/pieAreaShow")
    public Result pieAreaShow( HttpServletRequest request) throws ActException {
        try {
            PieOptionBO pieOptionBO = customerBaseInfoAPI.areaPieShow();
            PieOptionVO pieOptionVO = BeanTransform.copyProperties(pieOptionBO, PieOptionVO.class);
            return ActResult.initialize(pieOptionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取所有的地区
     *
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/findAllArea")
    public Result findAllArea( HttpServletRequest request) throws ActException {
        try {
            List<String> areas = customerBaseInfoAPI.findArea();
            return ActResult.initialize(areas);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取所有的业务类型
     *
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/findBussType")
    public Result findBussType( HttpServletRequest request) throws ActException {
        try {
            List<String> bussType = customerBaseInfoAPI.findBussType();
            return ActResult.initialize(bussType);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 客户地区分布情况饼状图
     * @param area 地区
     * @return class PieOptionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/pieBussTypeShow/area")
    public Result pieAreaBussTypeShow(String area, HttpServletRequest request) throws ActException {
        try {
            PieOptionBO pieOptionBO = customerBaseInfoAPI.areaBussTypePieShow(area);
            PieOptionVO pieOptionVO = BeanTransform.copyProperties(pieOptionBO, PieOptionVO.class);
            return ActResult.initialize(pieOptionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 各业务类型客户地区分布情况
     * @return class OptionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/bussTypeAreaBaiShow")
    public Result bussTypeAreaBaiShow( HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = customerBaseInfoAPI.bussTypeAreaBaiShow();
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 根据业务类型客户来源分析
     * @return class PieOptionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/resoucePieShowBybussType")
    public Result resoucePieShowByArea( String bussType, HttpServletRequest request) throws ActException {
        try {
            PieOptionBO pieOptionBO = customerBaseInfoAPI.resoucePieShowBybussType(bussType);
            PieOptionVO pieOptionVO = BeanTransform.copyProperties(pieOptionBO, PieOptionVO.class);
            return ActResult.initialize(pieOptionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    } /**
     * 客户来源分析
     * @return class OptionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/resouceBaiShow")
    public Result resouceBaiShow( HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = customerBaseInfoAPI.resouceBaiShow();
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
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
}