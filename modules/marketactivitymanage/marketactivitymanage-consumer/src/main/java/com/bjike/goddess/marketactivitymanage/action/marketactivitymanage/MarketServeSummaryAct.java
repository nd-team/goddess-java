package com.bjike.goddess.marketactivitymanage.action.marketactivitymanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketactivitymanage.api.MarketServeSummaryAPI;
import com.bjike.goddess.marketactivitymanage.bo.MarketServeSummaryBO;
import com.bjike.goddess.marketactivitymanage.bo.ServeSummaryBO;
import com.bjike.goddess.marketactivitymanage.dto.MarketServeSummaryDTO;
import com.bjike.goddess.marketactivitymanage.to.MarketServeSummaryTO;
import com.bjike.goddess.marketactivitymanage.vo.MarketServeSummaryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 市场招待汇总及邮件发送
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-21 02:19 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("marketservesummary")
public class MarketServeSummaryAct {

    @Autowired
    private MarketServeSummaryAPI marketServeSummaryAPI;

    /**
     * 根据id查询市场招待汇总邮件发送
     *
     * @param id 市场招待汇总邮件发送唯一标识
     * @return class MarketServeSummaryVO
     * @version v1
     */
    @GetMapping("v1/marketservesummary/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            MarketServeSummaryBO bo = marketServeSummaryAPI.findById(id);
            MarketServeSummaryVO vo = BeanTransform.copyProperties(bo, MarketServeSummaryVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 市场招待汇总邮件发送dto
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated MarketServeSummaryDTO dto, BindingResult result) throws ActException {
        try {
            Long count = marketServeSummaryAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询市场招待邮件
     *
     * @param dto 市场招待邮箱发送传输对象
     * @return class MarketServeSummaryVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated MarketServeSummaryDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<MarketServeSummaryBO> boList = marketServeSummaryAPI.list(dto);
            List<MarketServeSummaryVO> voList = BeanTransform.copyProperties(boList, MarketServeSummaryVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加市场招待邮件发送
     *
     * @param to 市场招待邮件发送to信息
     * @return class MarketServeSummaryVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) MarketServeSummaryTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            MarketServeSummaryBO bo = marketServeSummaryAPI.save(to);
            MarketServeSummaryVO vo = BeanTransform.copyProperties(bo, MarketServeSummaryVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除市场招待邮件发送
     *
     * @param id 市场招待邮件发送唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            marketServeSummaryAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑市场招待邮件发送
     *
     * @param to 市场招待邮件发送to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) MarketServeSummaryTO to, BindingResult result) throws ActException {
        try {
            marketServeSummaryAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻市场招待邮件发送
     *
     * @param to 市场招待邮件发送to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PatchMapping("v1/thaw")
    public Result thaw(@Validated MarketServeSummaryTO to, BindingResult result) throws ActException {
        try {
            marketServeSummaryAPI.thaw(to);
            return new ActResult("thaw success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结市场招待邮件发送
     *
     * @param to 市场招待邮件发送to
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PatchMapping("v1/congeal")
    public Result congeal(@Validated MarketServeSummaryTO to, BindingResult result) throws ActException {
        try {
            marketServeSummaryAPI.congeal(to);
            return new ActResult("congeal success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 市场招待汇总
     *
     * @param to 市场招待邮件发送to
     * @return class MarketServeSummaryVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize")
    public Result summarize(@Validated MarketServeSummaryTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<ServeSummaryBO> boList = marketServeSummaryAPI.summarize(to);
            List<MarketServeSummaryVO> voList = BeanTransform.copyProperties(boList, MarketServeSummaryVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}