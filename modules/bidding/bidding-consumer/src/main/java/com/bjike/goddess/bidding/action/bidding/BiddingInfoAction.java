package com.bjike.goddess.bidding.action.bidding;

import com.bjike.goddess.bidding.api.BiddingInfoAPI;
import com.bjike.goddess.bidding.bo.BiddingInfoBO;
import com.bjike.goddess.bidding.dto.BiddingInfoDTO;
import com.bjike.goddess.bidding.to.BiddingInfoTO;
import com.bjike.goddess.bidding.vo.BiddingInfoVO;
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
import java.util.List;

/**
 * 招标信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:48:29.980 ]
 * @Description: [ 招标信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("bidding/biddinginfo")
public class BiddingInfoAction {
    @Autowired
    private BiddingInfoAPI biddingInfoAPI;
    /**
     * 招标信息列表总条数
     *
     * @param biddingInfoDTO 招标信息dto
     * @des 获取所有招标信息
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BiddingInfoDTO biddingInfoDTO) throws ActException {
        try {
            Long count = biddingInfoAPI.countBiddingInfo(biddingInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 招标信息
     *
     * @param biddingInfoDTO 招标信息dto
     * @return class BiddingInfoVO
     * @des 获取所有招标信息
     * @version v1
     */
    @GetMapping("v1/listBiddingInfo")
    public Result findListBiddingInfo(BiddingInfoDTO biddingInfoDTO, BindingResult bindingResult,HttpServletRequest request) throws ActException {
        try {
            List<BiddingInfoVO> biddingInfoVOS = BeanTransform.copyProperties(
                    biddingInfoAPI.findListBiddingInfo(biddingInfoDTO), BiddingInfoVO.class);
            return ActResult.initialize(biddingInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加招标信息
     *
     * @param biddingInfoTO 招标信息to
     * @return class BiddingInfoVO
     * @des 添加招标信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addBiddingInfo(@Validated BiddingInfoTO biddingInfoTO, BindingResult bindingResult,HttpServletRequest request) throws ActException {
        try {
            BiddingInfoBO biddingInfoBO = biddingInfoAPI.insertBiddingInfo(biddingInfoTO);
            return ActResult.initialize(biddingInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑招标信息
     *
     * @param biddingInfoTO 招标信息数据to
     * @return class BiddingInfoVO
     * @des 编辑招标信息
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result editBiddingInfo(@Validated BiddingInfoTO biddingInfoTO,HttpServletRequest request) throws ActException {
        try {
            BiddingInfoBO biddingInfoBO = biddingInfoAPI.editBiddingInfo(biddingInfoTO);
            return ActResult.initialize(biddingInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除招标信息
     *
     * @param id 用户id
     * @des 根据用户id删除招标信息记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deleteBiddingInfo(@PathVariable String id) throws ActException {
        try {
            biddingInfoAPI.removeBiddingInfo(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 搜索
     *
     * @param biddingInfoDTO 招标信息dto
     * @return class BiddingInfoVO
     * @des 搜索获取所有招标信息
     * @version v1
     */
    @GetMapping("v1/search")
    public Result searchBiddingInfo(BiddingInfoDTO biddingInfoDTO,HttpServletRequest request) throws ActException {
        try {
            List<BiddingInfoVO> biddingInfoVOS = BeanTransform.copyProperties(
                    biddingInfoAPI.searchBiddingInfo(biddingInfoDTO),BiddingInfoVO.class);
            return ActResult.initialize(biddingInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总
     *
     * @return class BiddingInfoVO
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collectBiddingInfo(String[] cities) throws ActException {
        try {
            List<BiddingInfoVO> biddingInfoVOS = BeanTransform.copyProperties(
                    biddingInfoAPI.collectBiddingInfo(cities), BiddingInfoVO.class, true);
            return ActResult.initialize(biddingInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 招标信息导出
     *
     * @param projectName 项目名称
     * @version v1
     */
    @PostMapping("v1/exportExcel")
    public Result exportExcel(String projectName) throws ActException {
        String excel = null;
        try {
            excel = biddingInfoAPI.exportExcel(projectName);
            return new ActResult(excel);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 上传
     *
     * @version v1
     */
    @PostMapping("v1/upload")
    public Result upload() throws ActException {
        try {
            biddingInfoAPI.upload();
            return new ActResult("upload success!");
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
    public Result sendBiddingInfo(BiddingInfoTO biddingInfoTO) throws ActException {
        try {
            BiddingInfoBO biddingInfoBO = biddingInfoAPI.sendBiddingInfo(biddingInfoTO);
            return ActResult.initialize(biddingInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

}