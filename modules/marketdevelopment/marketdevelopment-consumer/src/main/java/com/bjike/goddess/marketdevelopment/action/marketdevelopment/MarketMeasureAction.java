package com.bjike.goddess.marketdevelopment.action.marketdevelopment;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.api.MarketMeasureAPI;
import com.bjike.goddess.marketdevelopment.to.MarketMeasureTO;
import com.bjike.goddess.marketdevelopment.vo.MarketMeasureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 市场测算
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:19 ]
 * @Description: [ 市场测算 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("marketmeasure")
public class MarketMeasureAction {

    @Autowired
    private MarketMeasureAPI marketMeasureAPI;

    /**
     * 保存市场测算数据
     *
     * @param to 市场测算传输对象
     * @return class MarketMeasureVO
     * @version v1
     */
    @PostMapping("v1/save/{id}")
    public Result save(MarketMeasureTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(marketMeasureAPI.save(to), MarketMeasureVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改市场测算数据
     *
     * @param to 市场测算传输对象
     * @return class MarketMeasureVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(MarketMeasureTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(marketMeasureAPI.update(to), MarketMeasureVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除市场测算数据
     *
     * @param to 市场测算传输对象
     * @return class MarketMeasureVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(MarketMeasureTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(marketMeasureAPI.delete(to), MarketMeasureVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据业务类型查询市场测算数据
     *
     * @param type 业务类型
     * @return class MarketMeasureVO
     * @version v1
     */
    @GetMapping("v1/findByType")
    public Result findByType(String type) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(marketMeasureAPI.findByType(type), MarketMeasureVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据业务方向科目查询市场测算数据
     *
     * @param course 业务方向科目
     * @return class MarketMeasureVO
     * @version v1
     */
    @GetMapping("v1/findByCourse")
    public Result findByCourse(String course) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(marketMeasureAPI.findByCourse(course), MarketMeasureVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据业务类型和方向科目查询市场测算数据
     *
     * @param type   业务类型
     * @param course 业务方向科目
     * @return class MarketMeasureVO
     * @version v1
     */
    @GetMapping("v1/findByCourseType")
    public Result findByCourseType(String type, String course) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(marketMeasureAPI.findByCourseType(type, course), MarketMeasureVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}