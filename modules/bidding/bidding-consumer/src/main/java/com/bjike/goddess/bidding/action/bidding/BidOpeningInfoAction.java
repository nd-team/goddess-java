package com.bjike.goddess.bidding.action.bidding;

import com.bjike.goddess.bidding.api.BidOpeningInfoAPI;
import com.bjike.goddess.bidding.bo.BidOpeningInfoBO;
import com.bjike.goddess.bidding.dto.BidOpeningInfoDTO;
import com.bjike.goddess.bidding.to.BidOpeningInfoTO;
import com.bjike.goddess.bidding.vo.BidOpeningInfoVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 开标信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T14:17:14.811 ]
 * @Description: [ 开标信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("bidding/bidopeninginfo")
public class BidOpeningInfoAction {
    @Autowired
    private BidOpeningInfoAPI bidOpeningInfoAPI;

    /**
     * 开标信息
     *
     * @param bidOpeningInfoDTO 开标信息dto
     * @return class BidOpeningInfoVO
     * @des 获取所有开标信息
     * @version v1
     */
    @GetMapping("v1/listTenderInfo")
    public Result findListBidOpeningInfo(BidOpeningInfoDTO bidOpeningInfoDTO) throws ActException {
        try {
            List<BidOpeningInfoVO> bidOpeningInfoVOS = BeanTransform.copyProperties(
                    bidOpeningInfoAPI.findListBidOpeningInfo(bidOpeningInfoDTO), BidOpeningInfoVO.class, true);
            return ActResult.initialize(bidOpeningInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加开标信息
     *
     * @param bidOpeningInfoTO 开标信息数据to
     * @return class BidOpeningInfoVO
     * @des 添加开标信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addBidOpeningInfo(BidOpeningInfoTO bidOpeningInfoTO) throws ActException {
        try {
            BidOpeningInfoBO bidOpeningInfoBO = bidOpeningInfoAPI.insertBidOpeningInfo(bidOpeningInfoTO);
            return ActResult.initialize(bidOpeningInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑开标信息
     *
     * @param bidOpeningInfoTO 开标信息数据to
     * @return class BidOpeningInfoVO
     * @des 编辑开标信息
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result editBidOpeningInfo(BidOpeningInfoTO bidOpeningInfoTO) throws ActException {
        try {
            BidOpeningInfoBO bidOpeningInfoBO = bidOpeningInfoAPI.editBidOpeningInfo(bidOpeningInfoTO);
            return ActResult.initialize(bidOpeningInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除开标信息
     *
     * @param id 用户id
     * @des 根据用户id删除开标信息记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deleteBidOpeningInfo(@PathVariable String id) throws ActException {
        try {
            bidOpeningInfoAPI.removeBidOpeningInfo(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总开标信息统计
     *
     * @param bidOpeningInfoDTO 开标信息bo
     * @return class BidOpeningInfoVO
     * @des 根据竞争公司(competitive) 汇总开标信息统计
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collectBidOpeningInfo(BidOpeningInfoDTO bidOpeningInfoDTO) throws ActException {
        try {
            List<BidOpeningInfoVO> bidOpeningInfoVOS = BeanTransform.copyProperties(
                    bidOpeningInfoAPI.collectBidOpeningInfo(bidOpeningInfoDTO), BidOpeningInfoVO.class, true);
            return ActResult.initialize(bidOpeningInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 搜索
     *
     * @return class BidOpeningInfoVO
     * @des 根据竞争公司(competitive) 搜索
     * @version v1
     */
    @GetMapping("v1/search")
    public Result searchBidOpeningInfo(@PathVariable String competitive) throws ActException {
        try {
            BidOpeningInfoBO bidOpeningInfoBO = bidOpeningInfoAPI.search(competitive);
            BidOpeningInfoVO bidOpeningInfoVO = BeanTransform.copyProperties(bidOpeningInfoBO, BidOpeningInfoVO.class);
            return ActResult.initialize(bidOpeningInfoVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    //TODO: xiazhili 2017-03-10 未做导出
    //TODO: xiazhili 2017-03-10 未做发送邮件


}