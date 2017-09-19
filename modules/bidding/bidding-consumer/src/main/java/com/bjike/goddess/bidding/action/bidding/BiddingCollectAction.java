package com.bjike.goddess.bidding.action.bidding;

import com.bjike.goddess.bidding.api.BiddingInfoAPI;
import com.bjike.goddess.bidding.to.BiddingCollectTO;
import com.bjike.goddess.bidding.vo.BiddingCollectVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}