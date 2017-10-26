package com.bjike.goddess.dispatchcar.action.dispatchcar;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dispatchcar.api.ImageColletcAPI;
import com.bjike.goddess.dispatchcar.bo.OptionBO;
import com.bjike.goddess.dispatchcar.to.GuidePermissionTO;
import com.bjike.goddess.dispatchcar.vo.OptionVO;
import com.bjike.goddess.organize.vo.OpinionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 图形化
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-19 15:53]
 * @Description: [图形化]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("imagecollect")
public class ImageCollectAction {


    @Autowired
    private ImageColletcAPI imageColletcAPI;


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

            Boolean isHasPermission = imageColletcAPI.guidePermission(guidePermissionTO);
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
     * 图形化日汇总
     * @param day 日汇总时间
     * @return class OptionVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/dayCollect")
    public Result dayCollect(String day) throws ActException{
        try {
            OptionBO optionBO = imageColletcAPI.figureShowDay(day);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO,OptionVO.class);
            return ActResult.initialize(optionVO);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 图形化周汇总
     * @param year 年份
     * @param month 月份
     * @param week 周数
     * @return class OptionVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/weekCollect")
    public Result weekCollect(Integer year,Integer month,Integer week) throws ActException{
        try {
            OptionBO optionBO = imageColletcAPI.figureShowWeek(year,month,week);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO,OptionVO.class);
            return ActResult.initialize(optionVO);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 图形化月汇总
     * @param year 年份
     * @param month 月份
     * @return class OptionVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/monthCollect")
    public Result monthCollect(Integer year, Integer month) throws ActException{
        try {
            OptionBO optionBO = imageColletcAPI.figureShowMonth(year,month);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO,OptionVO.class);
            return ActResult.initialize(optionVO);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 图形化累计汇总
     * @param day 截止日期
     * @return class OptionVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allCollect")
    public Result allCollect(String day) throws ActException{
        try {
            OptionBO optionBO = imageColletcAPI.figureShowTotal(day);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO,OptionVO.class);
            return ActResult.initialize(optionVO);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

}
