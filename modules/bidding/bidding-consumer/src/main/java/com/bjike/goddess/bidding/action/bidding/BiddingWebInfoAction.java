package com.bjike.goddess.bidding.action.bidding;

import com.bjike.goddess.bidding.api.BiddingWebInfoAPI;
import com.bjike.goddess.bidding.bo.BiddingWebInfoBO;
import com.bjike.goddess.bidding.dto.BiddingWebInfoDTO;
import com.bjike.goddess.bidding.to.BiddingWebInfoTO;
import com.bjike.goddess.bidding.vo.BiddingWebInfoVO;
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
 * 招投标网站信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T10:15:43.324 ]
 * @Description: [ 招投标网站信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("biddingwebinfo")
public class BiddingWebInfoAction {
    @Autowired
    private BiddingWebInfoAPI biddingWebInfoAPI;

    /**
     * 招投标网站信息列表总条数
     *
     * @param biddingWebInfoDTO 招投标网站信息dto
     * @des 获取所有招投标网站信息
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BiddingWebInfoDTO biddingWebInfoDTO) throws ActException {
        try {
            Long count = biddingWebInfoAPI.countBiddingWebInfo(biddingWebInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个招投标网站信息
     *
     * @param id
     * @return class BiddingWebInfoVO
     * @des 获取一个招投标网站信息
     * @version v1
     */
    @GetMapping("v1/web/{id}")
    public Result web(@PathVariable String id) throws ActException {
        try {
            BiddingWebInfoBO biddingWebInfoBO = biddingWebInfoAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(biddingWebInfoBO, BiddingWebInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 招投标网站信息
     *
     * @param biddingWebInfoDTO 招投标网站信息dto
     * @return class BiddingWebInfoVO
     * @des 获取所有招投标网站信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(BiddingWebInfoDTO biddingWebInfoDTO, HttpServletRequest request) throws ActException {
        try {
            List<BiddingWebInfoVO> biddingWebInfoVOS = BeanTransform.copyProperties(
                    biddingWebInfoAPI.findListBiddingWebInfo(biddingWebInfoDTO), BiddingWebInfoVO.class, request);
            return ActResult.initialize(biddingWebInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加招投标网站信息
     *
     * @param biddingWebInfoTO 招投标网站信息to
     * @return class BiddingWebInfoVO
     * @des 添加招投标网站信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) BiddingWebInfoTO biddingWebInfoTO, BindingResult bindingResult) throws ActException {
        try {
            BiddingWebInfoBO biddingWebInfoBO = biddingWebInfoAPI.insertBiddingWebInfo(biddingWebInfoTO);
            return ActResult.initialize(biddingWebInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑招投标网站信息
     *
     * @param biddingWebInfoTO 招投标网站信息to
     * @return class BiddingWebInfoVO
     * @des 编辑招投标网站信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) BiddingWebInfoTO biddingWebInfoTO, BindingResult bindingResult) throws ActException {
        try {
            BiddingWebInfoBO biddingWebInfoBO = biddingWebInfoAPI.editBiddingWebInfo(biddingWebInfoTO);
            return ActResult.initialize(biddingWebInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除招投标网站信息
     *
     * @param id 用户id
     * @des 根据用户id删除招投标网站信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            biddingWebInfoAPI.removeBiddingWebInfo(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}