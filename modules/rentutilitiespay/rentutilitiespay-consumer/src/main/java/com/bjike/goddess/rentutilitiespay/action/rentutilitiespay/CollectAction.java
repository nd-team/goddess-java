package com.bjike.goddess.rentutilitiespay.action.rentutilitiespay;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rentutilitiespay.api.CollectAPI;
import com.bjike.goddess.rentutilitiespay.bo.AreaCollectBO;
import com.bjike.goddess.rentutilitiespay.vo.AreaCollectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
* 汇总
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-20 03:59 ]
* @Description:	[ 汇总 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@RestController
@RequestMapping("collect")
public class CollectAction {
    @Autowired
    private CollectAPI collectAPI;


    /**
     * 月汇总
     * @param year 月份
     * @param month 年份
     * @return class AreaCollectVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/monthColect")
    public Result dayCollect(Integer year,Integer month) throws ActException{
        try {
            List<AreaCollectBO> areaCollectBOS = collectAPI.monthCollect(year,month);
            List<AreaCollectVO> areaCollectVOS = BeanTransform.copyProperties(areaCollectBOS,AreaCollectVO.class);
            return ActResult.initialize(areaCollectVOS);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 年汇总
     * @param year　年份
     * @return class AreaCollectVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/yearCollect")
    public Result yearCollect(Integer year) throws ActException{
        try {
            List<AreaCollectBO> areaCollectBOS = collectAPI.yeareCollect(year);
            List<AreaCollectVO> areaCollectVOS = BeanTransform.copyProperties(areaCollectBOS,AreaCollectVO.class);
            return ActResult.initialize(areaCollectVOS);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 累计汇总
     * @param endDate 截止日期
     * @return class AreaCollectVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allCollect")
    public Result allCollect(String endDate) throws ActException{
        try {
            List<AreaCollectBO> areaCollectBOS = collectAPI.allCollect(endDate);
            List<AreaCollectVO> areaCollectVOS = BeanTransform.copyProperties(areaCollectBOS,AreaCollectVO.class);
            return ActResult.initialize(areaCollectVOS);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

 }