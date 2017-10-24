package com.bjike.goddess.businessproject.action.businessproject;

import com.bjike.goddess.businessproject.api.BusinessContractAPI;
import com.bjike.goddess.businessproject.bo.BusinessContractProgressBO;
import com.bjike.goddess.businessproject.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 商务合同管理进度汇总
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-19 11:36 ]
 * @Description: [ 商务合同管理进度汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businesscontractprogress")
public class BusinessContractProgressAction {
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
     * 商务合同管理进度日汇总
     *
     * @param time time
     * @return class BusinessContractProgressBO
     * @des 商务合同管理进度日汇总
     * @version v1
     */
    @GetMapping("v1/daycollect")
    public Result daycollect(String time) throws ActException {
        try {
            List<BusinessContractProgressBO> bos = businessContractAPI.dayCollect(time);
            return ActResult.initialize(bos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 商务合同管理进度周汇总
     *
     * @param year
     * @param month
     * @param week
     * @return class BusinessContractProgressBO
     * @version v1
     */
    @GetMapping("v1/weekCollect")
    public Result weekCollect(Integer year, Integer month, Integer week) throws ActException {
        try {
            List<BusinessContractProgressBO> bos = BeanTransform.copyProperties(businessContractAPI.weekCollect(year, month, week), BusinessContractProgressBO.class);
            return ActResult.initialize(bos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 商务合同管理进度月汇总
     *
     * @param year
     * @param month
     * @return class BusinessContractProgressBO
     * @version v1
     */
    @GetMapping("v1/monthCollect")
    public Result monthCollect(Integer year, Integer month) throws ActException {
        try {
            List<BusinessContractProgressBO> bos = BeanTransform.copyProperties(businessContractAPI.monthCollect(year, month), BusinessContractProgressBO.class);
            return ActResult.initialize(bos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 商务合同管理进度季度汇总
     *
     * @param year
     * @param quarter
     * @return class BusinessContractProgressBO
     * @version v1
     */
    @GetMapping("v1/quarterCollect")
    public Result quarterCollect(Integer year, Integer quarter) throws ActException {
        try {
            List<BusinessContractProgressBO> bos = BeanTransform.copyProperties(businessContractAPI.quarterCollect(year, quarter), BusinessContractProgressBO.class);
            return ActResult.initialize(bos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 商务合同管理进度年汇总
     *
     * @param year
     * @return class BusinessContractProgressBO
     * @version v1
     */
    @GetMapping("v1/yearCollect")
    public Result yearCollect(Integer year) throws ActException {
        try {
            List<BusinessContractProgressBO> bos = BeanTransform.copyProperties(businessContractAPI.yearCollect(year), BusinessContractProgressBO.class);
            return ActResult.initialize(bos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 商务合同管理进度累计汇总
     *
     * @param time
     * @return class BusinessContractProgressBO
     * @version v1
     */
    @GetMapping("v1/totalCollect")
    public Result totalCollect(String time) throws ActException {
        try {
            List<BusinessContractProgressBO> bos = BeanTransform.copyProperties(businessContractAPI.totalCollect(time), BusinessContractProgressBO.class);
            return ActResult.initialize(bos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}