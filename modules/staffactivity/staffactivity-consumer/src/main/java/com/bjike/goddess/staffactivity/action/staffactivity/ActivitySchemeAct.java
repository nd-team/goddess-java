package com.bjike.goddess.staffactivity.action.staffactivity;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffactivity.api.ActivitySchemeAPI;
import com.bjike.goddess.staffactivity.bo.ActivitySchemeBO;
import com.bjike.goddess.staffactivity.dto.ActivitySchemeDTO;
import com.bjike.goddess.staffactivity.to.ActivitySchemeTO;
import com.bjike.goddess.staffactivity.vo.ActivitySchemeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 活动方案
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 08:42 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("activityscheme")
public class ActivitySchemeAct {

    @Autowired
    private ActivitySchemeAPI activitySchemeAPI;

    /**
     * 根据id查询合同节点标准信息
     *
     * @param id 合同节点标准信息唯一标识
     * @return class ActivitySchemeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/activityscheme/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ActivitySchemeBO bo = activitySchemeAPI.findById(id);
            ActivitySchemeVO vo = BeanTransform.copyProperties(bo, ActivitySchemeVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 合同节点标准信息dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated ActivitySchemeDTO dto, BindingResult result) throws ActException {
        try {
            Long count = activitySchemeAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 活动方案dto
     * @return class ActivitySchemeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ActivitySchemeDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ActivitySchemeBO> boList = activitySchemeAPI.list(dto);
            List<ActivitySchemeVO> voList = BeanTransform.copyProperties(boList, ActivitySchemeVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加活动方案
     *
     * @param to 活动方案to
     * @return class ActivitySchemeVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) ActivitySchemeTO to, HttpServletRequest request) throws ActException {
        try {
            ActivitySchemeBO bo = activitySchemeAPI.save(to);
            ActivitySchemeVO vo = BeanTransform.copyProperties(bo, ActivitySchemeVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除活动方案
     *
     * @param id 活动方案唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            activitySchemeAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑活动方案
     *
     * @param to 活动方案to
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(ActivitySchemeTO to) throws ActException {
        try {
            activitySchemeAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传文件
     *
     * @param maps 文件名，文件字节
     * @param path 上传路径
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/upload")
    public Result upload(Map<String, byte[]> maps, String path) throws ActException {
        return new ActResult("upload success!");
    }

    /**
     * 运营商务部意见
     *
     * @param id        活动方案id
     * @param yYOpinion 运营商务部意见
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/yYOpinion/{id}")
    public Result yYOpinion(@PathVariable(value = "id") String id, @RequestParam(value = "yYOpinion") String yYOpinion) throws ActException {
        try {
            activitySchemeAPI.yYOpinion(id, yYOpinion);
            return new ActResult("yYOpinion success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总经办意见
     *
     * @param id           活动方案id
     * @param ifSchemePass 方案是否通过
     * @param zjbOpinion   总经办意见
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/zjbOpinion/{id}")
    public Result zjbOpinion(@PathVariable(value = "id") String id,
                             @RequestParam(value = "ifSchemePass") Boolean ifSchemePass,
                             @RequestParam(value = "zjbOpinion") String zjbOpinion) throws ActException {
        try {
            activitySchemeAPI.zjbOpinion(id, ifSchemePass, zjbOpinion);
            return new ActResult("zjbOpinion success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 是否持续开展
     *
     * @param id               活动方案唯一标识
     * @param ifNeedContinue   是否有必要持续开展
     * @param reasonAndOpinion 原因及意见
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/ifContinueLaunch/{id}")
    public Result ifContinueLaunch(@PathVariable(value = "id") String id,
                                   @RequestParam(value = "ifNeedContinue") Boolean ifNeedContinue,
                                   @RequestParam(value = "reasonAndOpinion") String reasonAndOpinion) throws ActException {
        try {
            activitySchemeAPI.ifContinueLaunch(id, ifNeedContinue, reasonAndOpinion);
            return new ActResult("ifContinueLaunch success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 运营资金评价
     *
     * @param id                    活动方案唯一标识
     * @param ifTotalOutlayRational 活动总支出是否合理
     * @param fundProposal          经费建议
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/yYFundEvaluate/{id}")
    public Result yYFundEvaluate(@PathVariable(value = "id") String id,
                                 @RequestParam(value = "ifTotalOutlayRational") Boolean ifTotalOutlayRational,
                                 @RequestParam(value = "fundProposal") String fundProposal) throws ActException {
        try {
            activitySchemeAPI.yYFundEvaluate(id, ifTotalOutlayRational, fundProposal);
            return new ActResult("yYFundEvaluate success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 监督者评价
     *
     * @param id           活动方案id
     * @param ifFlowDefect 活动流程是否存在缺陷
     * @param flowProposal 流程建议
     * @throws ActException
     */
    @LoginAuth
    @PutMapping("v1/supervisorEvaluate/{id}")
    public Result supervisorEvaluate(@PathVariable(value = "id") String id,
                                     @RequestParam(value = "ifFlowDefect") Boolean ifFlowDefect,
                                     @RequestParam(value = "flowProposal") String flowProposal) throws ActException {
        try {
            activitySchemeAPI.supervisorEvaluate(id, ifFlowDefect, flowProposal);
            return new ActResult("supervisorEvaluate success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总经办评价
     *
     * @param id             活动方案唯一标识
     * @param activityEffect 活动效应
     * @param zjbEvaluate    总经办评价及建议
     * @throws ActException
     */
    @LoginAuth
    @PutMapping("v1/zjbEvaluate/{id}")
    public Result zjbEvaluate(@PathVariable(value = "id") String id,
                              @RequestParam(value = "activityEffect") String activityEffect,
                              @RequestParam(value = "zjbEvaluate") String zjbEvaluate) throws ActException {
        try {
            activitySchemeAPI.zjbEvaluate(id, activityEffect, zjbEvaluate);
            return new ActResult("zjbEvaluate success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 公司各地区活动经费汇总
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/activityFundSummary")
    public Result activityFundSummary(String startDate, String endDate) throws ActException {
        try {
            activitySchemeAPI.activityFundSummary(startDate, endDate);
            return new ActResult("activityFundSummary success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}