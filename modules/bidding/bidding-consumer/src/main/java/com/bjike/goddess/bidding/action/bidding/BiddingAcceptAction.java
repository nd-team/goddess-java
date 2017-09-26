package com.bjike.goddess.bidding.action.bidding;

import com.bjike.goddess.bidding.api.BiddingAcceptAPI;
import com.bjike.goddess.bidding.bo.BiddingAcceptBO;
import com.bjike.goddess.bidding.dto.BiddingAcceptDTO;
import com.bjike.goddess.bidding.to.BiddingAcceptTO;
import com.bjike.goddess.bidding.to.GuidePermissionTO;
import com.bjike.goddess.bidding.vo.BiddingAcceptVO;
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
 * 招标问题受理和处理
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-14 04:41 ]
 * @Description: [ 招标问题受理和处理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("biddingaccept")
public class BiddingAcceptAction {
    @Autowired
    private BiddingAcceptAPI biddingAcceptAPI;

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

            Boolean isHasPermission = biddingAcceptAPI.guidePermission(guidePermissionTO);
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
     * 招标问题受理和处理列表总条数
     *
     * @param dto 招标问题受理和处理dto
     * @des 获取所有招标问题受理和处理
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BiddingAcceptDTO dto) throws ActException {
        try {
            Long count = biddingAcceptAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个招标问题受理和处理
     *
     * @param id
     * @return class BiddingAcceptVO
     * @des 获取一个招标问题受理和处理
     * @version v1
     */
    @GetMapping("v1/answer/{id}")
    public Result answer(@PathVariable String id) throws ActException {
        try {
            BiddingAcceptBO biddingAcceptBO = biddingAcceptAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(biddingAcceptBO, BiddingAcceptVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 招标问题受理和处理列表
     *
     * @param dto 招标问题受理和处理dto
     * @return class BiddingAcceptVO
     * @des 获取所有招标问题受理和处理
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(BiddingAcceptDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<BiddingAcceptVO> biddingAcceptVOS = BeanTransform.copyProperties(
                    biddingAcceptAPI.list(dto), BiddingAcceptVO.class, request);
            return ActResult.initialize(biddingAcceptVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加招标问题受理和处理
     *
     * @param to 招标问题受理和处理to
     * @return class BiddingAcceptVO
     * @des 添加招标问题受理和处理
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) BiddingAcceptTO to, BindingResult bindingResult) throws ActException {
        try {
            BiddingAcceptBO biddingAcceptBO = biddingAcceptAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(biddingAcceptBO, BiddingAcceptVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑招标问题受理和处理
     *
     * @param to 招标问题受理和处理数据to
     * @return class BiddingAcceptVO
     * @des 添加招标问题受理和处理
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) BiddingAcceptTO to, BindingResult bindingResult) throws ActException {
        try {
            BiddingAcceptBO biddingAcceptBO = biddingAcceptAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(biddingAcceptBO, BiddingAcceptVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除招标问题受理和处理
     *
     * @param id 用户id
     * @des 根据用户id删除招标问题受理和处理记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            biddingAcceptAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 是否通报
     *
     * @param to 招标问题受理和处理数据to
     * @return class BiddingAcceptVO
     * @des 是否通报
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/notification")
    public Result notification(@Validated(EDIT.class) BiddingAcceptTO to, BindingResult bindingResult) throws ActException {
        try {
            BiddingAcceptBO biddingAcceptBO = biddingAcceptAPI.notification(to);
            return ActResult.initialize(BeanTransform.copyProperties(biddingAcceptBO, BiddingAcceptVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}