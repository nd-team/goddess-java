package com.bjike.goddess.costdetail.action.costdetail;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.costdetail.api.CostDetailsAPI;
import com.bjike.goddess.costdetail.bo.*;
import com.bjike.goddess.costdetail.dto.CostDetailsDTO;
import com.bjike.goddess.costdetail.entity.CostDetails;
import com.bjike.goddess.costdetail.to.CostDetailsAddEditTO;
import com.bjike.goddess.costdetail.to.GuidePermissionTO;
import com.bjike.goddess.costdetail.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 成本明细
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 05:45 ]
 * @Description: [ 成本明细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("costdetails")
public class CostDetailsAction {

    @Autowired
    private CostDetailsAPI costDetailsAPI;

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

            Boolean isHasPermission = costDetailsAPI.guidePermission(guidePermissionTO);
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
     * @param costDetailsDTO 成本明细
     * @des 获取所有成本明细总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CostDetailsDTO costDetailsDTO) throws ActException {
        try {
            Long count = costDetailsAPI.count(costDetailsDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个成本明细
     *
     * @param id 成本明细id
     * @return class CostDetailsVO
     * @des 根据id成本明细
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            CostDetailsVO costDetailsVO = BeanTransform.copyProperties(
                    costDetailsAPI.getOneById(id), CostDetailsVO.class);
            return ActResult.initialize(costDetailsVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 成本明细及其所有明细
     *
     * @param id 成本明细id
     * @return class CostDetailsVO
     * @des 根据id成本明细
     * @version v1
     */
    @GetMapping("v1/findAllById/{id}")
    public Result findAllById(@PathVariable String id) throws ActException {
        try {
            CostDetailsAddEditVO costDetailsAddEditVO = BeanTransform.copyProperties(
                    costDetailsAPI.getAllById(id), CostDetailsAddEditVO.class);
            return ActResult.initialize(costDetailsAddEditVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 成本明细列表
     *
     * @param costDetailsDTO 成本明细dto
     * @return class CostDetailsVO
     * @des 获取所有成本明细
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findList(CostDetailsDTO costDetailsDTO,HttpServletRequest request) throws ActException {
        try {
            List<CostDetails> costDetailsList = BeanTransform.copyProperties(
                    costDetailsAPI.list(costDetailsDTO), CostDetailsVO.class, request);
            return ActResult.initialize(costDetailsList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加成本明细
     *
     * @param costDetailsAddEditTO 成本明细to
     * @return class CostDetailsVO
     * @des 添加成本明细
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addCost(@Validated({ADD.class}) CostDetailsAddEditTO costDetailsAddEditTO, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            CostDetailsBO costDetailsBO = costDetailsAPI.add(costDetailsAddEditTO);
            return ActResult.initialize(BeanTransform.copyProperties(costDetailsBO, CostDetailsVO.class,request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑成本明细
     *
     * @param costDetailsAddEditTO 成本明细bo
     * @return class CostDetailsVO
     * @des 编辑成本明细
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editCost(@Validated({EDIT.class}) CostDetailsAddEditTO costDetailsAddEditTO, BindingResult result,HttpServletRequest request) throws ActException {
        try {
            CostDetailsBO costDetailsBO = costDetailsAPI.edit(costDetailsAddEditTO);
            return ActResult.initialize(BeanTransform.copyProperties(costDetailsBO, CostDetailsVO.class,request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表详情
     *
     * @param costDetailsDTO 成本明细dto
     * @return class CostDetailsAddEditVO
     * @des 根据id成本明细
     * @version v1
     */
    @GetMapping("v1/listDetail")
    public Result seeDetail(CostDetailsDTO costDetailsDTO) throws ActException {
        try {
            CostDetailsAddEditBO costDetailsAddEditBO = costDetailsAPI.listDetail(costDetailsDTO);
            CostDetailsAddEditVO costDetailsAddEditVO = new CostDetailsAddEditVO();
            List<LaborCostDetailBO> laborCostDetailBOList = costDetailsAddEditBO.getLaborCostDetailList();
            List<CompanyBorrowedDetailBO> companyBorrowedDetailBOList = costDetailsAddEditBO.getCompanyBorrowedDetailList();
            List<PaidCapitalDetailBO> paidCapitalDetailBOList = costDetailsAddEditBO.getPaidCapitalDetailList();
            List<CompanyLendDetailBO> companyLendDetailBOList = costDetailsAddEditBO.getCompanyLendDetailList();
            List<BusinessIncomeDetailBO> businessIncomeDetailBOList = costDetailsAddEditBO.getBusinessIncomeDetailList();

            costDetailsAddEditVO = BeanTransform.copyProperties(costDetailsAddEditBO, CostDetailsAddEditVO.class, "laborCostDetailList", "CompanyBorrowedDetailList", "PaidCapitalDetailList", "CompanyLendDetailList", "BusinessIncomeDetailList");
            costDetailsAddEditVO.setLaborCostDetailList(BeanTransform.copyProperties(laborCostDetailBOList, LaborCostDetailVO.class));
            costDetailsAddEditVO.setCompanyBorrowedDetailList(BeanTransform.copyProperties(companyBorrowedDetailBOList, CompanyBorrowedDetailVO.class));
            costDetailsAddEditVO.setPaidCapitalDetailList(BeanTransform.copyProperties(paidCapitalDetailBOList, PaidCapitalDetailVO.class));
            costDetailsAddEditVO.setCompanyLendDetailList(BeanTransform.copyProperties(companyLendDetailBOList, CompanyLendDetailVO.class));
            costDetailsAddEditVO.setBusinessIncomeDetailList(BeanTransform.copyProperties(businessIncomeDetailBOList, BusinessIncomeDetailVO.class));

            return ActResult.initialize(costDetailsAddEditVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id
     * @des 根据id删除成本明细
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            costDetailsAPI.delete(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 月汇总
     *
     * @param projectGroup 部门
     * @param costTime     时间
     * @return class CostDetailsAddEditVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/monthCompare")
    public Result proCompare(@RequestParam String costTime, @RequestParam String[] projectGroup, HttpServletRequest request) throws ActException {
        try {
            CostDetailsAddEditBO costDetailsAddEditBO = costDetailsAPI.monthCollect(costTime, projectGroup);
            CostDetailsAddEditVO costDetailsAddEditVO = new CostDetailsAddEditVO();
            List<LaborCostDetailBO> laborCostDetailBOList = costDetailsAddEditBO.getLaborCostDetailList();
            List<CompanyBorrowedDetailBO> companyBorrowedDetailBOList = costDetailsAddEditBO.getCompanyBorrowedDetailList();
            List<PaidCapitalDetailBO> paidCapitalDetailBOList = costDetailsAddEditBO.getPaidCapitalDetailList();
            List<CompanyLendDetailBO> companyLendDetailBOList = costDetailsAddEditBO.getCompanyLendDetailList();
            List<BusinessIncomeDetailBO> businessIncomeDetailBOList = costDetailsAddEditBO.getBusinessIncomeDetailList();

            costDetailsAddEditVO = BeanTransform.copyProperties(costDetailsAddEditBO, CostDetailsAddEditVO.class, "laborCostDetailList", "CompanyBorrowedDetailList", "PaidCapitalDetailList", "CompanyLendDetailList", "BusinessIncomeDetailList");
            costDetailsAddEditVO.setLaborCostDetailList(BeanTransform.copyProperties(laborCostDetailBOList, LaborCostDetailVO.class));
            costDetailsAddEditVO.setCompanyBorrowedDetailList(BeanTransform.copyProperties(companyBorrowedDetailBOList, CompanyBorrowedDetailVO.class));
            costDetailsAddEditVO.setPaidCapitalDetailList(BeanTransform.copyProperties(paidCapitalDetailBOList, PaidCapitalDetailVO.class));
            costDetailsAddEditVO.setCompanyLendDetailList(BeanTransform.copyProperties(companyLendDetailBOList, CompanyLendDetailVO.class));
            costDetailsAddEditVO.setBusinessIncomeDetailList(BeanTransform.copyProperties(businessIncomeDetailBOList, BusinessIncomeDetailVO.class));

            return ActResult.initialize(costDetailsAddEditVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询所有本表中的部门
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findDetail")
    public Result findDetail() throws ActException {
        try {
            List<String> detail = new ArrayList<>();
            detail = costDetailsAPI.findAllDetails();
            return ActResult.initialize(detail);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 年汇总
     *
     * @param years 年份
     * @return class CostDetailsYeCollVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/yeCompare")
    public Result yeCompare(@RequestParam Integer years, HttpServletRequest request) throws ActException {
        try {
            List<CostDetailsYeCollBO> boList = costDetailsAPI.yearCollect(years);
            List<CostDetailsYeCollVO> voList = BeanTransform.copyProperties(boList, CostDetailsYeCollVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 返回添加数据
     *
     * @return class CostDetailsAddReturnVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/returnAddResult")
    public Result returnAddResult(HttpServletRequest request) throws ActException {
        try {
            List<CostDetailsAddReturnBO> boList = costDetailsAPI.returnAddResult();
            List<CostDetailsAddReturnVO> voList = BeanTransform.copyProperties(boList, CostDetailsAddReturnVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加中所有的部门
     *
     * @version v1
     */
    @GetMapping("v1/allOrageDepartment")
    public Result allOrageDepartment() throws ActException {
        try {
            List<String> detail = new ArrayList<>();
            detail = costDetailsAPI.findAddAllDetails();
            return ActResult.initialize(detail);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 所有时间接口
     *
     * @version v1
     */
    @GetMapping("v1/allDate")
    public Result allDate() throws ActException {
        try {
            List<String> date = new ArrayList<>();
            date = costDetailsAPI.findDate();
            return ActResult.initialize(date);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}