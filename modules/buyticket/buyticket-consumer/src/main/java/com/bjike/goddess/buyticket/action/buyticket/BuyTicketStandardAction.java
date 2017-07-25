package com.bjike.goddess.buyticket.action.buyticket;

import com.bjike.goddess.buyticket.api.BuyTicketStandardAPI;
import com.bjike.goddess.buyticket.bo.BuyTicketStandardBO;
import com.bjike.goddess.buyticket.dto.BuyTicketStandardDTO;
import com.bjike.goddess.buyticket.to.BuyTicketStandardTO;
import com.bjike.goddess.buyticket.to.BuyGuidePermissionTO;
import com.bjike.goddess.buyticket.vo.BuyTicketStandardVO;
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
 * 购票标准
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 06:40 ]
 * @Description: [ 购票标准 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("buyticketstandard")
public class BuyTicketStandardAction {
    @Autowired
    private BuyTicketStandardAPI buyTicketStandardAPI;

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(BuyGuidePermissionTO.TestAdd.class) BuyGuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = buyTicketStandardAPI.guidePermission(guidePermissionTO);
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
     * 购票标准列表总条数
     *
     * @param buyTicketStandardDTO 购票标准dto
     * @des 获取所有购票标准总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BuyTicketStandardDTO buyTicketStandardDTO) throws ActException {
        try {
            Long count = buyTicketStandardAPI.countBuyTicketStandard(buyTicketStandardDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个购票标准
     *
     * @param id
     * @return class BuyTicketStandardVO
     * @des 获取一个购票标准
     * @version v1
     */
    @GetMapping("v1/standard/{id}")
    public Result standard(@PathVariable String id) throws ActException {
        try {
            BuyTicketStandardBO buyTicketStandardBO = buyTicketStandardAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(buyTicketStandardBO, BuyTicketStandardVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 购票标准列表
     *
     * @param buyTicketStandardDTO 购票标准dto
     * @return class BuyTicketStandardVO
     * @des 获取所有购票标准
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(BuyTicketStandardDTO buyTicketStandardDTO, HttpServletRequest request) throws ActException {
        try {
            List<BuyTicketStandardVO> buyTicketStandardVOS = BeanTransform.copyProperties
                    (buyTicketStandardAPI.findListBuyTicketStandard(buyTicketStandardDTO), BuyTicketStandardVO.class, request);
            return ActResult.initialize(buyTicketStandardVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加购票标准
     *
     * @param buyTicketStandardTO 购票标准数据to
     * @return class BuyTicketStandardVO
     * @des 添加购票标准
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) BuyTicketStandardTO buyTicketStandardTO, BindingResult bindingResult) throws ActException {
        try {
            BuyTicketStandardBO buyTicketStandardBO = buyTicketStandardAPI.insertBuyTicketStandard(buyTicketStandardTO);
            return ActResult.initialize(buyTicketStandardBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑购票标准
     *
     * @param buyTicketStandardTO 购票标准数据to
     * @return class BuyTicketStandardVO
     * @des 编辑购票标准
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) BuyTicketStandardTO buyTicketStandardTO, BindingResult bindingResult) throws ActException {
        try {
            BuyTicketStandardBO buyTicketStandardBO = buyTicketStandardAPI.editBuyTicketStandard(buyTicketStandardTO);
            return ActResult.initialize(buyTicketStandardBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除购票标准
     *
     * @param id 用户id
     * @des 根据用户id删除购票标准记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            buyTicketStandardAPI.removeBuyTicketStandard(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}