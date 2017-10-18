package com.bjike.goddess.dispatchcar.action.dispatchcar;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dispatchcar.api.DispatchCarInfoAPI;
import com.bjike.goddess.dispatchcar.api.DispatchcarRecordCollectAPI;
import com.bjike.goddess.dispatchcar.bo.AreaCollectBO;
import com.bjike.goddess.dispatchcar.bo.DispatchcarRecordCollectBO;
import com.bjike.goddess.dispatchcar.to.GuidePermissionTO;
import com.bjike.goddess.dispatchcar.vo.AreaCollectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
* 出车记录管理汇总
* @Author: [ Jason ]
* @Date: [  2017-09-27 05:16 ]
* @Description:	[ 出车记录管理汇总 ]
* @Version:	[ v1.0.0 ]
* @Copy: [ com.bjike ]
*/
@RestController
@RequestMapping("dispatchcarrecordcollect")
public class DispatchcarRecordCollectAction {
    @Autowired
    private DispatchcarRecordCollectAPI dispatchcarRecordCollectAPI;

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

            Boolean isHasPermission = dispatchcarRecordCollectAPI.guidePermission(guidePermissionTO);
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
     * 日汇总
     * @param day 日汇总时间
     * @return class AreaCollectVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/dayCollcet")
    public Result dayCollect(@RequestParam String day) throws ActException{
        try {
            List<AreaCollectBO> boList = dispatchcarRecordCollectAPI.dayCollect(day);
            List<AreaCollectVO> voList = BeanTransform.copyProperties(boList,AreaCollectVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 周汇总
     * @param year 年份
     * @param month 月份
     * @param week 周数
     * @return class AreaCollectVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/weekCollect")
    public Result weekCollect(Integer year,Integer month,Integer week) throws ActException{
        try {
            List<AreaCollectBO> boList = dispatchcarRecordCollectAPI.weekCollect(year,month,week);
            List<AreaCollectVO> voList = BeanTransform.copyProperties(boList,AreaCollectVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 月汇总
     * @param year 年份
     * @param month 月份
     * @return class AreaCollectVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/monthCollect")
    public Result monthCollect(@RequestParam Integer year,@RequestParam Integer month) throws ActException{
        try {
            List<AreaCollectBO> boList = dispatchcarRecordCollectAPI.monthCollect(year,month);
            List<AreaCollectVO> voList = BeanTransform.copyProperties(boList,AreaCollectVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 累计汇总
     * @param day 截止日期
     * @return class AreaCollectVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allCollect")
    public Result allCollect(@RequestParam String day) throws ActException{
        try {
            List<AreaCollectBO> boList = dispatchcarRecordCollectAPI.allCollect(day);
            List<AreaCollectVO> voList = BeanTransform.copyProperties(boList,AreaCollectVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

 }