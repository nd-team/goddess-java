package com.bjike.goddess.carinfo.action.carinfo;

import com.bjike.goddess.carinfo.api.CollectCarinfoAPI;
import com.bjike.goddess.carinfo.bo.AreaBO;
import com.bjike.goddess.carinfo.bo.OptionBO;
import com.bjike.goddess.carinfo.vo.AreaVO;
import com.bjike.goddess.carinfo.vo.CollectCarinfoVO;
import com.bjike.goddess.carinfo.vo.OptionVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.vo.OperateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
* 司机信息管理汇总
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-10 05:56 ]
* @Description:	[ 司机信息管理汇总 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@RestController
@RequestMapping("collectcarinfo")
public class CollectCarinfoAction {
    @Autowired
    private CollectCarinfoAPI collectCarinfoAPI;

    /**
     * 司机信息日汇总
     * @param day 汇总日期
     * @return class AreaVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/dayCollect")
    public Result dayCollect(String day) throws ActException{
        try {
            List<AreaBO> boList = collectCarinfoAPI.dayCollect(day);
            List<AreaVO> voList = BeanTransform.copyProperties(boList, AreaVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 司机信息周汇总
     * @param year 年份
     * @param month 月份
     * @param week 周数
     * @return class AreaVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/weekCollect")
    public Result weekCollect(Integer year,Integer month,Integer week) throws ActException{
        try {
            List<AreaBO> boList = collectCarinfoAPI.weekCollect(year,month,week);
            List<AreaVO> voList = BeanTransform.copyProperties(boList, AreaVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 司机信息月汇总
     * @param year 年份
     * @param month 月份
     * @return class AreaVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/monthCollect")
    public Result monthCollect(Integer year,Integer month) throws ActException{
        try {
            List<AreaBO> boList = collectCarinfoAPI.monthCollect(year,month);
            List<AreaVO> voList = BeanTransform.copyProperties(boList, AreaVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 司机信息累计汇总
     * @param endDate 截止日期
     * @return class AreaVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allCollect")
    public Result allCollect(String endDate) throws ActException{
        try {
            List<AreaBO> boList = collectCarinfoAPI.allCollect(endDate);
            List<AreaVO> voList = BeanTransform.copyProperties(boList, AreaVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 图形化司机信息日汇总
     * @param day 汇总日期
     * @return class OptionVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/figureShow/day")
    public Result figureShowDay(String day) throws ActException{
        try {
            OptionBO optionBO = collectCarinfoAPI.figureShowDay(day);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO,OptionVO.class);
            return ActResult.initialize(optionVO);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 图形化司机信息周汇总
     * @param year 年份
     * @param month 月份
     * @param week 周数
     * @return class OptionVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/figureShow/week")
    public Result figureShowWeek(Integer year,Integer month,Integer week) throws ActException{
        try {
            OptionBO optionBO = collectCarinfoAPI.figureShowWeek(year,month,week);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO,OptionVO.class);
            return ActResult.initialize(optionVO);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 图形化司机信息月汇总
     * @param year 年份
     * @param month 月份
     * @return class OptionVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/figureShow/month")
    public Result figureShowMonth(Integer year,Integer month) throws ActException{
        try {
            OptionBO optionBO = collectCarinfoAPI.figureShowMonth(year,month);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO,OptionVO.class);
            return ActResult.initialize(optionVO);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 图形化累计司机信息汇总
     * @param endDate 截止日期
     * @return class OptionVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/figureShow/total")
    public Result figureShowTotal(String endDate) throws ActException{
        try {
            OptionBO optionBO = collectCarinfoAPI.figrureShowTotal(endDate);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO,OptionVO.class);
            return ActResult.initialize(optionVO);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }



}