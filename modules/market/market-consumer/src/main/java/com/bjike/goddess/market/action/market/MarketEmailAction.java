package com.bjike.goddess.market.action.market;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.auth.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.market.api.MarketEmailAPI;
import com.bjike.goddess.market.bo.MarketEmailBO;
import com.bjike.goddess.market.dto.MarketEmailDTO;
import com.bjike.goddess.market.to.MarketEmailTO;
import com.bjike.goddess.market.vo.MarketCollectVO;
import com.bjike.goddess.market.vo.MarketEmailVO;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 发送市场信息管理
 * @Author: [xiazhili]
 * @Date: [17-3-27]
 * @Description: [发送市场信息管理]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
@RestController
@RequestMapping("marketemail")
public class MarketEmailAction {
    @Autowired
    private MarketEmailAPI marketEmailAPI;
    /**
     * 总条数
     *
     * @param marketEmailDTO
     * @des 获取总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(MarketEmailDTO marketEmailDTO) throws ActException {
        try {
            Long count = marketEmailAPI.counts(marketEmailDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个邮件
     *
     * @param id
     * @des 获取一个邮件
     * @return  class MarketEmailVO
     * @version v1
     */
    @GetMapping("v1/email/{id}")
    public Result email(@PathVariable String id) throws ActException {
        try {
            MarketEmailBO marketEmailBO = marketEmailAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(marketEmailBO , MarketEmailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 市场信息管理邮件汇总列表
     *
     * @param marketEmailDTO 市场信息管理邮件汇总信息dto
     * @des 获取所有市场信息管理邮件汇总信息
     * @return  class MarketEmailVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(MarketEmailDTO marketEmailDTO, HttpServletRequest request) throws ActException {
        try {
            List<MarketEmailVO> marketEmailVOS = BeanTransform.copyProperties(
                    marketEmailAPI.listMarketEmail(marketEmailDTO),MarketEmailVO.class,request);
            return ActResult.initialize(marketEmailVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加市场信息管理邮件汇总
     *
     * @param marketEmailTO 市场信息管理邮件汇总基本信息数据to
     * @des 添加市场信息管理邮件汇总
     * @return  class MarketEmailVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated MarketEmailTO marketEmailTO,BindingResult bindingResult) throws ActException {
        try {
            MarketEmailBO marketEmailBO = marketEmailAPI.addMarketEmail(marketEmailTO);
            return ActResult.initialize(BeanTransform.copyProperties(marketEmailBO,MarketEmailVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑市场信息管理邮件汇总
     *
     * @param marketEmailTO 市场信息管理邮件汇总基本信息数据bo
     * @des 编辑市场信息管理邮件汇总
     * @return  class MarketEmailVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated  MarketEmailTO marketEmailTO, BindingResult bindingResult) throws ActException {
        try {
            MarketEmailBO marketEmailBO = marketEmailAPI.editMarketEmail(marketEmailTO);
            return ActResult.initialize(BeanTransform.copyProperties(marketEmailBO,MarketEmailVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除市场信息管理邮件汇总信息记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            marketEmailAPI.deleteMarketEmail(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总市场信息管理
     *
     * @param areas 地区
     * @des 项目市场信息管理
     * @return  class MarketEmailVO
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect ( @RequestParam String[] areas ) throws ActException {
        try {
            List<MarketCollectVO> marketEmailVOS = BeanTransform.copyProperties(
                    marketEmailAPI.marketCollect(areas),MarketCollectVO.class);
            return ActResult.initialize(marketEmailVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取地区
     *
     * @des 获取地区集合
     * @version v1
     */
    @GetMapping("v1/area")
    public Result area() throws ActException {
        try {
            List<String> areaList = marketEmailAPI.getArea();
            return ActResult.initialize(areaList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
