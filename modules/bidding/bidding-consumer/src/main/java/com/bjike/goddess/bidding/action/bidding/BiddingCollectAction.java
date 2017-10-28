package com.bjike.goddess.bidding.action.bidding;

import com.bjike.goddess.bidding.api.BiddingInfoAPI;
import com.bjike.goddess.bidding.to.BiddingCollectTO;
import com.bjike.goddess.bidding.to.GuidePermissionTO;
import com.bjike.goddess.bidding.vo.BiddingCollectVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 招投标流程进度管理汇总
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-16T13:48:29.980 ]
 * @Description: [ 招投标流程进度管理汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("biddingcollect")
public class BiddingCollectAction {
    @Autowired
    private BiddingInfoAPI biddingInfoAPI;
    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = biddingInfoAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 招投标流程进度管理日汇总
     *
     * @param to to
     * @return class BiddingCollectVO
     * @des 招投标流程进度管理汇总
     * @version v1
     */
    @GetMapping("v1/dayCollect")
    public Result dayCollect(BiddingCollectTO to) throws ActException {
        try {
            List<BiddingCollectVO> biddingInfoCollectVOS = BeanTransform.copyProperties(
                    biddingInfoAPI.dayCollect(to), BiddingCollectVO.class);
            return ActResult.initialize(biddingInfoCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 招投标流程进度管理周汇总
     *
     * @param to to
     * @return class BiddingCollectVO
     * @des 招投标流程进度管理周汇总
     * @version v1
     */
    @GetMapping("v1/weekCollect")
    public Result weekCollect(BiddingCollectTO to) throws ActException {
        try {
            List<BiddingCollectVO> biddingInfoCollectVOS = BeanTransform.copyProperties(
                    biddingInfoAPI.weekCollect(to), BiddingCollectVO.class);
            return ActResult.initialize(biddingInfoCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 招投标流程进度管理月汇总
     *
     * @param to to
     * @return class BiddingCollectVO
     * @des 招投标流程进度管理月汇总
     * @version v1
     */
    @GetMapping("v1/monthCollect")
    public Result monthCollect(BiddingCollectTO to) throws ActException {
        try {
            List<BiddingCollectVO> biddingInfoCollectVOS = BeanTransform.copyProperties(
                    biddingInfoAPI.monthCollect(to), BiddingCollectVO.class);
            return ActResult.initialize(biddingInfoCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 招投标流程进度管理累计汇总
     *
     * @param to to
     * @return class BiddingCollectVO
     * @des 招投标流程进度管理累计汇总
     * @version v1
     */
    @GetMapping("v1/totalCollect")
    public Result totalCollect(BiddingCollectTO to) throws ActException {
        try {
            List<BiddingCollectVO> biddingInfoCollectVOS = BeanTransform.copyProperties(
                    biddingInfoAPI.totalCollect(to), BiddingCollectVO.class);
            return ActResult.initialize(biddingInfoCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取当前月有几周
     *
     * @param year  年份
     * @param month 月份
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findWeek")
    public Result findWeek(@RequestParam Integer year, @RequestParam Integer month) throws ActException {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1);
            int weekNum = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= weekNum; i++) {
                list.add(i);
            }
            return ActResult.initialize(list);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }


}