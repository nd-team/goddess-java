package com.bjike.goddess.marketdevelopment.action.marketdevelopment;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.api.MarketResearchAPI;
import com.bjike.goddess.marketdevelopment.to.MarketResearchTO;
import com.bjike.goddess.marketdevelopment.vo.MarketResearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 市场调研
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:16 ]
 * @Description: [ 市场调研 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("marketresearch")
public class MarketResearchAct {

    @Autowired
    private MarketResearchAPI marketResearchAPI;

    /**
     * 保存市场调研数据
     *
     * @param to 市场调研传输对象
     * @return class MarketResearchVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) MarketResearchTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(marketResearchAPI.save(to), MarketResearchVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改市场调研数据
     *
     * @param to 市场调研传输对象
     * @return class MarketResearchVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) MarketResearchTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(marketResearchAPI.update(to), MarketResearchVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除市场调研数据
     *
     * @param to 市场调研传输对象
     * @return class MarketResearchVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(MarketResearchTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(marketResearchAPI.delete(to), MarketResearchVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据业务类型查询市场调研数据
     *
     * @param type 业务类型
     * @return class MarketResearchVO
     * @version v1
     */
    @GetMapping("v1/findByType")
    public Result findByType(String type) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(marketResearchAPI.findByType(type), MarketResearchVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据业务方向科目查询市场调研数据
     *
     * @param course 业务方向科目
     * @return class MarketResearchVO
     * @version v1
     */
    @GetMapping("v1/findByCourse")
    public Result findByCourse(String course) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(marketResearchAPI.findByCourse(course), MarketResearchVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据业务类型和方向科目查询市场调研数据
     *
     * @param type   业务类型
     * @param course 业务方向科目
     * @return class MarketResearchVO
     * @version v1
     */
    @GetMapping("v1/findByCourseType")
    public Result findByCourseType(String type, String course) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(marketResearchAPI.findByCourseType(type, course), MarketResearchVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取市场调研数据
     *
     * @param id 市场调研数据id
     * @return class MarketResearchVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(marketResearchAPI.getById(id), MarketResearchVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}