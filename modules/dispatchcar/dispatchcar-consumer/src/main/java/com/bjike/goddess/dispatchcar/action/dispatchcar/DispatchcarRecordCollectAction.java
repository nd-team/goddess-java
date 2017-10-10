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
import com.bjike.goddess.dispatchcar.vo.AreaCollectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
     * @param day 周汇总时间
     * @return class AreaCollectVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/weekCollect")
    public Result weekCollect(@RequestParam String day) throws ActException{
        try {
            List<AreaCollectBO> boList = dispatchcarRecordCollectAPI.weekCollect(day);
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
    @GetMapping("v1/weekCollect")
    public Result monthCollect(@RequestParam String year,@RequestParam String month) throws ActException{
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