package com.bjike.goddess.shareholdersmanage.action.shareholdersmanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.api.NewEquityAPI;
import com.bjike.goddess.shareholdersmanage.api.PledgeEquityAPI;
import com.bjike.goddess.shareholdersmanage.bo.PledgeEquityBO;
import com.bjike.goddess.shareholdersmanage.dto.PledgeEquityDTO;
import com.bjike.goddess.shareholdersmanage.entity.PledgeEquity;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import com.bjike.goddess.shareholdersmanage.to.PledgeEquityTO;
import com.bjike.goddess.shareholdersmanage.vo.NewEquityLinkDateVO;
import com.bjike.goddess.shareholdersmanage.vo.PledgeEquityVO;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 质押股权
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:32 ]
 * @Description: [ 质押股权 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("pledgeequity")
public class PledgeEquityAction {
    @Autowired
    private PledgeEquityAPI pledgeEquityAPI;
    @Autowired
    private NewEquityAPI newEquityAPI;

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, javax.servlet.http.HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = pledgeEquityAPI.guidePermission(guidePermissionTO);
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
     * 列表总条数
     *
     * @param pledgeEquityDTO 质押股权dto
     * @des 获取所有质押股权总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(PledgeEquityDTO pledgeEquityDTO) throws ActException {
        try {
            Long count = pledgeEquityAPI.countPledge(pledgeEquityDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个质押股权
     *
     * @param id 质押股权id
     * @return class PledgeEquityVO
     * @des 根据id获取质押股权
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            PledgeEquityVO pledgeEquityVO = BeanTransform.copyProperties(
                    pledgeEquityAPI.getOne(id), PledgeEquityVO.class);
            return ActResult.initialize(pledgeEquityVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 质押股权列表
     *
     * @param pledgeEquityDTO 质押股权dto
     * @return class PledgeEquityVO
     * @des 获取所有质押股权
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findEquityTrfer(PledgeEquityDTO pledgeEquityDTO, HttpServletRequest request) throws ActException {
        try {
            List<PledgeEquityVO> pledgeEquityVOS = BeanTransform.copyProperties(
                    pledgeEquityAPI.findList(pledgeEquityDTO), PledgeEquityVO.class, request);
            return ActResult.initialize(pledgeEquityVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加质押股权
     *
     * @param pledgeEquityTO 质押股权to
     * @return class PledgeEquityVO
     * @des 添加质押股权
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addEqurfer(@Validated({ADD.class}) PledgeEquityTO pledgeEquityTO, BindingResult result) throws ActException {
        try {
            PledgeEquityBO pledgeEquityBO = pledgeEquityAPI.save(pledgeEquityTO);
            return ActResult.initialize(BeanTransform.copyProperties(pledgeEquityBO, PledgeEquityVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑质押股权
     *
     * @param pledgeEquityTO 质押股权数据bo
     * @return class PledgeEquityVO
     * @des 编辑质押股权
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editShareOpen(@Validated({EDIT.class}) PledgeEquityTO pledgeEquityTO, BindingResult result) throws ActException {
        try {
            PledgeEquityBO pledgeEquityBO = pledgeEquityAPI.edit(pledgeEquityTO);
            return ActResult.initialize(BeanTransform.copyProperties(pledgeEquityBO, PledgeEquityVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除质押股权
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteShareChange(@PathVariable String id) throws ActException {
        try {
            pledgeEquityAPI.delete(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据股东名链接数据
     *
     * @param shareholderName 出质人
     * @param holdNum      股权数量
     * @return class NewEquityLinkDateVO
     * @des 根据股东名链接数据
     * @version v1
     */
    @GetMapping("v1/getLinkDate/logoutShareName")
    public Result getLinkDate(@RequestParam Integer holdNum, @RequestParam String shareholderName) throws ActException {
        try {
            NewEquityLinkDateVO newEquityLinkDateVO = BeanTransform.copyProperties(
                    newEquityAPI.newEqLinkDate(holdNum, shareholderName), NewEquityLinkDateVO.class);
            return ActResult.initialize(newEquityLinkDateVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}