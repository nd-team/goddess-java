package com.bjike.goddess.headcount.action.headcount;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.headcount.api.IntervalInfoAPI;
import com.bjike.goddess.headcount.bo.IntervalInfoBO;
import com.bjike.goddess.headcount.to.IntervalInfoTO;
import com.bjike.goddess.headcount.vo.IntervalInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: [yewenbo]
 * @Date: [2017-03-14 10:03]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@RestController
@RequestMapping("headcount/valinfo")
public class ValInfoAct {
    @Autowired
    private IntervalInfoAPI intervalInfoAPI;

    /**
     * 添加薪资区间信息
     *
     * @param intervalInfoTO 薪资区间信息
     * @Des 返回薪资区间信息
     * @throws ActException
     */
    @PostMapping("v1/add")
    public Result save(@Validated({ADD.class}) IntervalInfoTO intervalInfoTO)throws ActException{
        try{
            return ActResult.initialize(intervalInfoAPI.saveByTO(null,intervalInfoTO));
        }catch(SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取薪资区间信息
     * @version v1
     * @throws ActException
     */
    @GetMapping("v1/list")
    public Result list()throws ActException{
        try{
            List<IntervalInfoBO> intervalInfoBOs = intervalInfoAPI.list();
            List<IntervalInfoVO> intervalInfoVO = BeanTransform.copyProperties(intervalInfoBOs,IntervalInfoVO.class);
            return ActResult.initialize(intervalInfoVO);
        }catch(SerException e){
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 更新薪资区间信息
     *
     * @param intervalInfoTO 更新薪资区间信息
     * @version v1
     * @throws ActException
     */
    @PostMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) IntervalInfoTO intervalInfoTO)throws ActException{
        try{
            intervalInfoAPI.update(intervalInfoTO);
            return ActResult.initialize("edit success");
        }catch(SerException e){
            throw new ActException(e.getMessage());
        }
    }


}
