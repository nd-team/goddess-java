package com.bjike.goddess.marketactivitymanage.action.marketactivitymanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketactivitymanage.api.MarketServeRecordAPI;
import com.bjike.goddess.marketactivitymanage.bo.MarketServeRecordBO;
import com.bjike.goddess.marketactivitymanage.dto.MarketServeRecordDTO;
import com.bjike.goddess.marketactivitymanage.to.MarketServeRecordTO;
import com.bjike.goddess.marketactivitymanage.vo.MarketServeRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 市场招待记录
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-18T10:56:16.087 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("marketserverecord")
public class MarketServeRecordAct {

    @Autowired
    private MarketServeRecordAPI marketServeRecordAPI;

    /**
     * 根据id查询市场招待记录
     *
     * @param id      市场招待记录唯一标识
     * @return class MarketServeRecordVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/marketserverecord/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            MarketServeRecordBO bo = marketServeRecordAPI.findById(id);
            MarketServeRecordVO vo = BeanTransform.copyProperties(bo, MarketServeRecordVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 市场招待记录dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated MarketServeRecordDTO dto, BindingResult result) throws ActException {
        try {
            Long count = marketServeRecordAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 市场招待记录dto
     * @return class MarketServeRecordVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated MarketServeRecordDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<MarketServeRecordBO> boList = marketServeRecordAPI.list(dto);
            List<MarketServeRecordVO> voList = BeanTransform.copyProperties(boList, MarketServeRecordVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加市场招待记录
     *
     * @param to 市场招待记录to信息
     * @return class MarketServeRecordVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) MarketServeRecordTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            MarketServeRecordBO bo = marketServeRecordAPI.save(to);
            MarketServeRecordVO vo = BeanTransform.copyProperties(bo, MarketServeRecordVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除市场招待记录
     *
     * @param id 市场招待记录唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            marketServeRecordAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑市场招待记录
     *
     * @param to 市场招待记录to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) MarketServeRecordTO to, BindingResult result) throws ActException {
        try {
            marketServeRecordAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 运营商务部资金模块意见
     *
     * @param to 市场招待记录to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/fundmodule")
    public Result fundModuleOpinion(@Validated(MarketServeRecordTO.FUNDMODULE.class) MarketServeRecordTO to, BindingResult result) throws ActException {
        try {
            marketServeRecordAPI.fundModuleOpinion(to);
            return new ActResult("fundModuleOpinion success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 决策层意见
     *
     * @param to 市场招待记录to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/executive")
    public Result executiveOpinion(@Validated(MarketServeRecordTO.EXECUTIVE.class) MarketServeRecordTO to, BindingResult result) throws ActException {
        try {
            marketServeRecordAPI.executiveOpinion(to);
            return new ActResult("executiveOpinion success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看详情
     *
     * @param id 市场招待记录唯一标识
     * @return class MarketServeRecordVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/checkdetail/{id}")
    public Result checkDetails(@PathVariable String id) throws ActException {
        try {
            MarketServeRecordBO bo =  marketServeRecordAPI.checkDetails(id);
            MarketServeRecordVO vo = BeanTransform.copyProperties(bo, MarketServeRecordVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}