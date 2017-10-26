package com.bjike.goddess.rentutilitiespay.action.rentutilitiespay;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rentutilitiespay.api.ImageCollectAPI;
import com.bjike.goddess.rentutilitiespay.bo.OptionBO;
import com.bjike.goddess.rentutilitiespay.vo.OptionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 图形化汇总
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-24 10:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("imagecollect")
public class ImageCollectAction {
    @Autowired
    private ImageCollectAPI imageCollectAPI;

    /**
     * 图形化月汇总
     * @param year 汇总年份
     * @param month 汇总月份
     * @return class OptionVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/figureShow/month")
    public Result figureShowMonth(Integer year,Integer month) throws ActException{
        try {
            OptionBO optionBOS = imageCollectAPI.firgureShowMonth(year,month);
            OptionVO optionVO = BeanTransform.copyProperties(optionBOS,OptionVO.class);
            return ActResult.initialize(optionVO);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 图形化年汇总
     * @param year 汇总年份
     * @return class OptionVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/figureShow/year")
    public Result figureShowYear(Integer year) throws ActException{
        try {
            OptionBO optionBOS = imageCollectAPI.figrueShowYear(year);
            OptionVO optionVO = BeanTransform.copyProperties(optionBOS,OptionVO.class);
            return ActResult.initialize(optionVO);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 图形化累计汇总
     * @param endDate 截止日期
     * @return class OptionVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/figureShow/total")
    public Result figureShowTotal(String endDate) throws ActException{
        try {
            OptionBO optionBOS = imageCollectAPI.figureShowTotal(endDate);
            OptionVO optionVO = BeanTransform.copyProperties(optionBOS,OptionVO.class);
            return ActResult.initialize(optionVO);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }
}
