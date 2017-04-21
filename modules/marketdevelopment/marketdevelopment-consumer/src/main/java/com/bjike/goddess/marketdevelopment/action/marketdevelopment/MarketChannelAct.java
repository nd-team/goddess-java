package com.bjike.goddess.marketdevelopment.action.marketdevelopment;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.api.MarketChannelAPI;
import com.bjike.goddess.marketdevelopment.dto.MarketChannelDTO;
import com.bjike.goddess.marketdevelopment.to.MarketChannelTO;
import com.bjike.goddess.marketdevelopment.vo.MarketChannelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 市场挖掘
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:15 ]
 * @Description: [ 市场挖掘 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("marketchannel")
public class MarketChannelAct {

    @Autowired
    private MarketChannelAPI marketChannelAPI;


    /**
     * 列表
     *
     * @param dto 市场挖掘数据传输对象
     * @return class MarketChannelVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(MarketChannelDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(marketChannelAPI.maps(dto), MarketChannelVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 保存市场挖掘数据
     *
     * @param to 市场挖掘传输对象
     * @return class MarketChannelVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) MarketChannelTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(marketChannelAPI.save(to), MarketChannelVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改市场挖掘数据
     *
     * @param to 市场挖掘传输对象
     * @return class MarketChannelVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) MarketChannelTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(marketChannelAPI.update(to), MarketChannelVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除市场挖掘数据
     *
     * @param to 市场挖掘传输对象
     * @return class MarketChannelVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(MarketChannelTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(marketChannelAPI.delete(to), MarketChannelVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据业务类型查询市场挖掘数据
     *
     * @param type 业务类型
     * @return class MarketChannelVO
     * @version v1
     */
    @GetMapping("v1/findByType")
    public Result findByType(String type) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(marketChannelAPI.findByType(type), MarketChannelVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据业务方向科目查询市场挖掘数据
     *
     * @param course 业务方向科目
     * @return class MarketChannelVO
     * @version v1
     */
    @GetMapping("v1/findByCourse")
    public Result findByCourse(String course) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(marketChannelAPI.findByCourse(course), MarketChannelVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据业务类型和方向科目查询市场挖掘数据
     *
     * @param type   业务类型
     * @param course 业务方向科目
     * @return class MarketChannelVO
     * @version v1
     */
    @GetMapping("v1/findByCourseType")
    public Result findByCourseType(String type, String course) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(marketChannelAPI.findByCourseType(type, course), MarketChannelVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取市场挖掘数据
     *
     * @param id 市场挖掘数据id
     * @return class MarketChannelVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(marketChannelAPI.getById(id), MarketChannelVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal() throws ActException {
        try {
            return ActResult.initialize(marketChannelAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}