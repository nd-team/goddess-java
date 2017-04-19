package com.bjike.goddess.staffactivity.action.staffactivity;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffactivity.api.ActivitySchemeAPI;
import com.bjike.goddess.staffactivity.bo.ActivitySchemeBO;
import com.bjike.goddess.staffactivity.dto.ActivitySchemeDTO;
import com.bjike.goddess.staffactivity.to.ActivitySchemeTO;
import com.bjike.goddess.staffactivity.vo.ActivitySchemeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
     * 获取列表
     *
     * @param dto 活动方案dto
     * @return class ActivitySchemeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ActivitySchemeDTO dto) throws ActException {
        try {
            List<ActivitySchemeBO> boList = activitySchemeAPI.list(dto);
            List<ActivitySchemeVO> voList = BeanTransform.copyProperties(boList, ActivitySchemeVO.class);
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
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) ActivitySchemeTO to) throws ActException {
        try {
            ActivitySchemeBO bo = activitySchemeAPI.save(to);
            ActivitySchemeVO vo = BeanTransform.copyProperties(bo, ActivitySchemeVO.class);
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
    @PostMapping("v1/upload")
    public Result upload(Map<String, byte[]> maps, String path) throws ActException {
        try {
            activitySchemeAPI.upload(maps, path);
            return new ActResult("upload success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 运营商务部意见
     *
     * @param to 活动方案to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/yYOpinion")
    public Result yYOpinion(ActivitySchemeTO to) throws ActException {
        try {
            activitySchemeAPI.yYOpinion(to);
            return new ActResult("yYOpinion success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总经办意见
     *
     * @param to 活动方案to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/zjbOpinion")
    public Result zjbOpinion(ActivitySchemeTO to) throws ActException {
        try {
            activitySchemeAPI.zjbOpinion(to);
            return new ActResult("zjbOpinion success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 是否持续开展
     *
     * @param to 活动方案to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/ifContinueLaunch")
    public Result ifContinueLaunch(ActivitySchemeTO to) throws ActException {
        try {
            activitySchemeAPI.ifContinueLaunch(to);
            return new ActResult("ifContinueLaunch success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 运营资金评价
     *
     * @param to 活动方案to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/yYFundEvaluate")
    public Result yYFundEvaluate(ActivitySchemeTO to) throws ActException {
        try {
            activitySchemeAPI.yYFundEvaluate(to);
            return new ActResult("yYFundEvaluate success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 监督者评价
     *
     * @param to 活动方案to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/supervisorEvaluate")
    public Result supervisorEvaluate(ActivitySchemeTO to) throws ActException {
        try {
            activitySchemeAPI.supervisorEvaluate(to);
            return new ActResult("supervisorEvaluate success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总经办评价
     *
     * @param to 活动方案to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/zjbEvaluate")
    public Result zjbEvaluate(ActivitySchemeTO to) throws ActException {
        try {
            activitySchemeAPI.zjbEvaluate(to);
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