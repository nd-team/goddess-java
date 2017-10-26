package com.bjike.goddess.rentcar.action.rentcar;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rentcar.api.CarSendEmailAPI;
import com.bjike.goddess.rentcar.api.CollectDriverInfoAPI;
import com.bjike.goddess.rentcar.bo.AreaBO;
import com.bjike.goddess.rentcar.bo.CollectDriverInfoBO;
import com.bjike.goddess.rentcar.bo.OptionBO;
import com.bjike.goddess.rentcar.to.GuidePermissionTO;
import com.bjike.goddess.rentcar.vo.AreaVO;
import com.bjike.goddess.rentcar.vo.CollectDriverInfoVO;
import com.bjike.goddess.rentcar.vo.OptionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
* 租车协议管理汇总
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-07 11:56 ]
* @Description:	[ 租车协议管理汇总 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@RestController
@RequestMapping("collectdriverinfo")
public class CollectDriverInfoAction {
    @Autowired
    private CollectDriverInfoAPI collectDriverInfoAPI;

    @Autowired
    private CarSendEmailAPI carSendEmailAPI;

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

            Boolean isHasPermission = carSendEmailAPI.guidePermission(guidePermissionTO);
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
     * 月租车管理汇总
     * @param year
     * @param month
     * @return class AreaVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/monthCollect")
    public Result monthCollect(Integer year,Integer month,HttpServletRequest request) throws ActException{
        try {
            List<AreaBO> boList = collectDriverInfoAPI.monthCollect(year,month);
            List<AreaVO> voList = BeanTransform.copyProperties(boList,AreaVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 累计租车协议管理汇总
     * @param endDate
     * @return class AreaVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allCollect")
    public Result allCollect(String endDate,HttpServletRequest request) throws ActException{
        try {
            List<AreaBO> boList = collectDriverInfoAPI.allCollect(endDate);
            List<AreaVO> voList = BeanTransform.copyProperties(boList,AreaVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 图形化月租车协议管理汇总
     * @param year
     * @param month
     * @return class OptionVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/figureShow/month")
    public Result figureShowDay(Integer year,Integer month) throws ActException{
        try {
            OptionBO optionBO = collectDriverInfoAPI.figureShowMonth(year,month);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO,OptionVO.class);
            return ActResult.initialize(optionVO);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 图形化累计租车协议管理汇总
     * @param endDate
     * @return class OptionVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/figureShow/total")
    public Result figureShowTotal(String endDate) throws ActException{
        try {
            OptionBO optionBO = collectDriverInfoAPI.figureShowTotal(endDate);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO,OptionVO.class);
            return ActResult.initialize(optionVO);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

 }