package com.bjike.goddess.bidding.action.bidding;

import com.bjike.goddess.bidding.api.BiddingTypeAPI;
import com.bjike.goddess.bidding.bo.BiddingAcceptBO;
import com.bjike.goddess.bidding.bo.BiddingTypeBO;
import com.bjike.goddess.bidding.dto.BiddingAcceptDTO;
import com.bjike.goddess.bidding.dto.BiddingTypeDTO;
import com.bjike.goddess.bidding.entity.BiddingType;
import com.bjike.goddess.bidding.to.BiddingAcceptTO;
import com.bjike.goddess.bidding.to.BiddingTypeTO;
import com.bjike.goddess.bidding.to.GuidePermissionTO;
import com.bjike.goddess.bidding.vo.BiddingAcceptVO;
import com.bjike.goddess.bidding.vo.BiddingTypeVO;
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
 * 招投标类型
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-14 04:24 ]
 * @Description: [ 招投标类型 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("biddingtype")
public class BiddingTypeAction {
    @Autowired
    private BiddingTypeAPI biddingTypeAPI;
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

            Boolean isHasPermission = biddingTypeAPI.guidePermission(guidePermissionTO);
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
     * 招投标类型列表总条数
     *
     * @param dto 招投标类型dto
     * @des 获取所有招投标类型
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BiddingTypeDTO dto) throws ActException {
        try {
            Long count = biddingTypeAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个招投标类型
     *
     * @param id
     * @return class BiddingAcceptVO
     * @des 获取一个招投标类型
     * @version v1
     */
    @GetMapping("v1/type/{id}")
    public Result type(@PathVariable String id) throws ActException {
        try {
            BiddingTypeBO bo = biddingTypeAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BiddingTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 招投标类型列表
     *
     * @param dto 招投标类型dto
     * @return class BiddingTypeVO
     * @des 获取所有招投标类型
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(BiddingTypeDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<BiddingTypeVO> biddingAcceptVOS = BeanTransform.copyProperties(
                    biddingTypeAPI.list(dto), BiddingTypeVO.class, request);
            return ActResult.initialize(biddingAcceptVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加招投标类型
     *
     * @param to 招投标类型to
     * @return class BiddingTypeVO
     * @des 添加招投标类型
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) BiddingTypeTO to, BindingResult bindingResult) throws ActException {
        try {
            BiddingTypeBO bo = biddingTypeAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BiddingTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编招投标类型
     *
     * @param to 招投标类型数据to
     * @return class BiddingTypeVO
     * @des 添加招投标类型
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) BiddingTypeTO to, BindingResult bindingResult) throws ActException {
        try {
            BiddingTypeBO bo = biddingTypeAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BiddingTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除招投标类型
     *
     * @param id 用户id
     * @des 根据用户id删除招投标类型记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            biddingTypeAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取招投标类型
     *
     * @des 获取招投标类型
     * @version v1
     */
    @GetMapping("v1/getType")
    public Result getType() throws ActException {
        try {
            List<String> typeList = biddingTypeAPI.getType();
            return ActResult.initialize(typeList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}