package com.bjike.goddess.bidding.action.bidding;

import com.bjike.goddess.bidding.api.BidOpeningInfoAPI;
import com.bjike.goddess.bidding.bo.BidOpeningInfoBO;
import com.bjike.goddess.bidding.bo.BiddingInfoBO;
import com.bjike.goddess.bidding.dto.BidOpeningInfoDTO;
import com.bjike.goddess.bidding.dto.BiddingInfoDTO;
import com.bjike.goddess.bidding.to.BidOpeningInfoTO;
import com.bjike.goddess.bidding.to.BiddingInfoTO;
import com.bjike.goddess.bidding.vo.BidOpeningCollectVO;
import com.bjike.goddess.bidding.vo.BidOpeningInfoVO;
import com.bjike.goddess.bidding.vo.BiddingInfoCollectVO;
import com.bjike.goddess.bidding.vo.BiddingInfoVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("bidopeninginfo")
public class BidOpeningInfoAction {
    @Autowired
    private BidOpeningInfoAPI bidOpeningInfoAPI;
    /**
     * 开标信息列表总条数
     *
     * @param bidOpeningInfoDTO 开标信息dto
     * @des 获取所有开标信息
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BidOpeningInfoDTO bidOpeningInfoDTO) throws ActException {
        try {
            Long count = bidOpeningInfoAPI.countBidOpeningInfo(bidOpeningInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 一个开标信息
     *
     * @param id
     * @return class BidOpeningInfoVO
     * @des 获取一个开标信息
     * @version v1
     */
    @GetMapping("v1/info/{id}")
    public Result info(@PathVariable String id) throws ActException {
        try {
            BidOpeningInfoBO bidOpeningInfoBO = bidOpeningInfoAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(bidOpeningInfoBO, BidOpeningInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 开标信息列表
     *
     * @param bidOpeningInfoDTO 开标信息dto
     * @return class BidOpeningInfoVO
     * @des 获取所有开标信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(BidOpeningInfoDTO bidOpeningInfoDTO,HttpServletRequest request) throws ActException {
        try {
            List<BidOpeningInfoVO> bidOpeningInfoVOS = BeanTransform.copyProperties(
                    bidOpeningInfoAPI.findListBidOpeningInfo(bidOpeningInfoDTO), BidOpeningInfoVO.class,request);
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
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) BidOpeningInfoTO bidOpeningInfoTO, BindingResult bindingResult) throws ActException {
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
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) BidOpeningInfoTO bidOpeningInfoTO, BindingResult bindingResult) throws ActException {
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
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            bidOpeningInfoAPI.removeBidOpeningInfo(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 搜索
     *
     * @param bidOpeningInfoDTO 开标信息dto
     * @return class BidOpeningInfoVO
     * @des 搜索获取所有开标信息
     * @version v1
     */
    @GetMapping("v1/search")
    public Result search(BidOpeningInfoDTO bidOpeningInfoDTO,HttpServletRequest request) throws ActException {
        try {
            List<BidOpeningInfoVO> bidOpeningInfoVOS = BeanTransform.copyProperties(
                    bidOpeningInfoAPI.searchBidOpeningInfo(bidOpeningInfoDTO),BidOpeningInfoVO.class);
            return ActResult.initialize(bidOpeningInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 开标信息导出
     *
     * @param projectName 项目名称
     * @version v1
     */
    @PostMapping("v1/exportExcel")
    public Result exportExcel(String projectName) throws ActException {
        String excel = null;
        try {
            excel = bidOpeningInfoAPI.exportExcel(projectName);
            return new ActResult(excel);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }
    /**
     * 汇总开标信息
     *
     * @param cities 地市
     * @return class BidOpeningInfoVO
     * @des 汇总招标信息
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(@RequestParam String[] cities) throws ActException {
        try {
            List<BidOpeningInfoVO> bidOpeningInfoVOS = BeanTransform.copyProperties(
                    bidOpeningInfoAPI.collectBidOpening(cities), BidOpeningCollectVO.class);
            return ActResult.initialize(bidOpeningInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取地市
     *
     * @des 获取地市集合
     * @version v1
     */
    @GetMapping("v1/cities")
    public Result cities() throws ActException {
        try {
            List<String> citiesList = bidOpeningInfoAPI.getBidOpeningInfoCities();
            return ActResult.initialize(citiesList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 发送邮件
     *
     * @version v1
     */
    @PostMapping("v1/send")
    public Result sendBidOpeningInfo(BidOpeningInfoTO bidOpeningInfoTO) throws ActException {
        try {
            BidOpeningInfoBO bidOpeningInfoBO = bidOpeningInfoAPI.sendBidOpeningInfo(bidOpeningInfoTO);
            return ActResult.initialize(bidOpeningInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

}