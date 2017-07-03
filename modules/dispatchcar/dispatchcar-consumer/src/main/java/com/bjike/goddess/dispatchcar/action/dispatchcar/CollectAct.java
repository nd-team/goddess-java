package com.bjike.goddess.dispatchcar.action.dispatchcar;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dispatchcar.api.DispatchCarInfoAPI;
import com.bjike.goddess.dispatchcar.enums.CollectType;
import com.bjike.goddess.dispatchcar.to.CollectIntervalTypeTO;
import com.bjike.goddess.dispatchcar.vo.DispatchAreaCollectVO;
import com.bjike.goddess.dispatchcar.vo.DispatchDriverCollectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 汇总
 *
 * @Author: [Jason]
 * @Date: [17-4-14 下午2:50]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("collect")
public class CollectAct {

    @Autowired
    private DispatchCarInfoAPI dispatchCarInfoAPI;

    /**
     * 出车情况汇总
     *
     * @param to 汇总日期间隔
     * @return class DispatchAreaCollectVO
     * @version v1
     */
    @GetMapping("v1/dispatchCollect")
    public Result dispatchCollect(@Validated({ADD.class}) CollectIntervalTypeTO to, HttpServletRequest request) throws ActException {
        try {
            List<DispatchAreaCollectVO> voList = BeanTransform.copyProperties(dispatchCarInfoAPI.dispatchCollect(to.getType(), CollectType.AREA), DispatchAreaCollectVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 司机出车情况汇总
     *
     * @param to 汇总日期间隔
     * @return class DispatchDriverCollectVO
     * @version v1
     */
    @GetMapping("v1/driverCollect")
    public Result driverCollect(@Validated({ADD.class}) CollectIntervalTypeTO to, HttpServletRequest request) throws ActException {
        try {
            List<DispatchDriverCollectVO> voList = BeanTransform.copyProperties(dispatchCarInfoAPI.dispatchCollect(to.getType(), CollectType.DRIVER), DispatchDriverCollectVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
