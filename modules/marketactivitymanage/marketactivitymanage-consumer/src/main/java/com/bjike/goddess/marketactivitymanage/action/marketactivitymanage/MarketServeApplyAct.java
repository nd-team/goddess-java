package com.bjike.goddess.marketactivitymanage.action.marketactivitymanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketactivitymanage.api.MarketServeApplyAPI;
import com.bjike.goddess.marketactivitymanage.bo.MarketServeApplyBO;
import com.bjike.goddess.marketactivitymanage.dto.MarketServeApplyDTO;
import com.bjike.goddess.marketactivitymanage.to.MarketServeApplyTO;
import com.bjike.goddess.marketactivitymanage.vo.MarketServeApplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 市场招待申请
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-18T10:37:08.048 ]
 * @Description: [ 市场招待申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("marketactivitymanage/marketserveapply")
public class MarketServeApplyAct {

    @Autowired
    private MarketServeApplyAPI marketServeApplyAPI;

    /**
     * 获取列表
     *
     * @param dto 市场招待申请dto
     * @return class MarketServeApplyVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(MarketServeApplyDTO dto) throws ActException {
        try {
            List<MarketServeApplyBO> boList = marketServeApplyAPI.list(dto);
            List<MarketServeApplyVO> voList = BeanTransform.copyProperties(boList, MarketServeApplyVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加市场招待申请
     *
     * @param to 市场招待申请to信息
     * @return class FailPhoneReasonVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) MarketServeApplyTO to) throws ActException {
        try {
            MarketServeApplyBO bo = marketServeApplyAPI.save(to);
            MarketServeApplyVO vo = BeanTransform.copyProperties(bo, MarketServeApplyVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除市场招待申请
     *
     * @param id 市场招待申请唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            marketServeApplyAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑市场招待申请
     *
     * @param to 市场招待申请to信息
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(MarketServeApplyTO to) throws ActException {
        try {
            marketServeApplyAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 运营商务部资金模块意见
     *
     * @param to 市场招待申请to信息
     * @throws ActException
     */
    @PutMapping("v1/fundModuleOpinion")
    public Result fundModuleOpinion(MarketServeApplyTO to) throws ActException {
        try {
            marketServeApplyAPI.fundModuleOpinion(to);
            return new ActResult("fundModuleOpinion success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 决策层意见
     *
     * @param to 市场招待申请to信息
     * @return
     * @throws ActException
     */
    @PutMapping("v1/executiveOpinion")
    public Result executiveOpinion(MarketServeApplyTO to) throws ActException {
        try {
            marketServeApplyAPI.executiveOpinion(to);
            return new ActResult("executiveOpinion success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    // TODO: 17-3-20
    //上传,下载,导入,导出

}