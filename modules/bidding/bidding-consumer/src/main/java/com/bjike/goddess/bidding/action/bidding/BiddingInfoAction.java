package com.bjike.goddess.bidding.action.bidding;

import com.bjike.goddess.bidding.api.BiddingInfoAPI;
import com.bjike.goddess.bidding.bo.BiddingInfoBO;
import com.bjike.goddess.bidding.dto.BiddingInfoDTO;
import com.bjike.goddess.bidding.to.BiddingInfoTO;
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
 * 招标信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:48:29.980 ]
 * @Description: [ 招标信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("biddinginfo")
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
     * 一个招标信息
     *
     * @param id
     * @return class BiddingInfoVO
     * @des 获取一个招标信息
     * @version v1
     */
    @GetMapping("v1/info/{id}")
    public Result info(@PathVariable String id) throws ActException {
        try {
            BiddingInfoBO biddingInfoBO = biddingInfoAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(biddingInfoBO, BiddingInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 招标信息列表
     *
     * @param biddingInfoDTO 招标信息dto
     * @return class BiddingInfoVO
     * @des 获取所有招标信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(BiddingInfoDTO biddingInfoDTO, HttpServletRequest request) throws ActException {
        try {
            List<BiddingInfoVO> biddingInfoVOS = BeanTransform.copyProperties(
                    biddingInfoAPI.findListBiddingInfo(biddingInfoDTO), BiddingInfoVO.class, request);
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
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) BiddingInfoTO biddingInfoTO, BindingResult bindingResult) throws ActException {
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
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) BiddingInfoTO biddingInfoTO, BindingResult bindingResult) throws ActException {
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
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
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
    public Result search(BiddingInfoDTO biddingInfoDTO, HttpServletRequest request) throws ActException {
        try {
            List<BiddingInfoVO> biddingInfoVOS = BeanTransform.copyProperties(
                    biddingInfoAPI.searchBiddingInfo(biddingInfoDTO), BiddingInfoVO.class);
            return ActResult.initialize(biddingInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总招标信息
     *
     * @param cities 地市
     * @return class BiddingInfoCollectVO
     * @des 汇总招标信息
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(@RequestParam String[] cities) throws ActException {
        try {
            List<BiddingInfoCollectVO> biddingInfoCollectVOS = BeanTransform.copyProperties(
                    biddingInfoAPI.collectBiddingInfo(cities), BiddingInfoCollectVO.class);
            return ActResult.initialize(biddingInfoCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取地区
     *
     * @des 获取地区集合
     * @version v1
     */
    @GetMapping("v1/cities")
    public Result cities() throws ActException {
        try {
            List<String> citiesList = biddingInfoAPI.getBiddingInfoCities();
            return ActResult.initialize(citiesList);
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