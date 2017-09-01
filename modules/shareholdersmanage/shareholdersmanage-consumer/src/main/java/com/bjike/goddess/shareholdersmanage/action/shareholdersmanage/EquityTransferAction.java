package com.bjike.goddess.shareholdersmanage.action.shareholdersmanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.api.EquityTransferAPI;
import com.bjike.goddess.shareholdersmanage.api.NewEquityAPI;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransferBO;
import com.bjike.goddess.shareholdersmanage.dto.EquityTransferDTO;
import com.bjike.goddess.shareholdersmanage.to.EquityTransferTO;
import com.bjike.goddess.shareholdersmanage.vo.EquityTransferVO;
import com.bjike.goddess.shareholdersmanage.vo.NewEquityLinkDateVO;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 股权转让
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:32 ]
 * @Description: [ 股权转让 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("equitytransfer")
public class EquityTransferAction {
    @Autowired
    private EquityTransferAPI equityTransferAPI;
    @Autowired
    private NewEquityAPI newEquityAPI;

    /**
     * 列表总条数
     *
     * @param equityTransferDTO 股权转让dto
     * @des 获取所有股权转让总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(EquityTransferDTO equityTransferDTO) throws ActException {
        try {
            Long count = equityTransferAPI.countEquityfer(equityTransferDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个股权转让
     *
     * @param id 股权转让id
     * @return class EquityTransferVO
     * @des 根据id获取股权转让
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            EquityTransferVO equityTransferVO = BeanTransform.copyProperties(
                    equityTransferAPI.getOne(id), EquityTransferVO.class);
            return ActResult.initialize(equityTransferVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 股权转让列表
     *
     * @param equityTransferDTO 股权转让dto
     * @return class EquityTransferVO
     * @des 获取所有股权转让
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findEquityTrfer(EquityTransferDTO equityTransferDTO, HttpServletRequest request) throws ActException {
        try {
            List<EquityTransferVO> equityTransferVOS = BeanTransform.copyProperties(
                    equityTransferAPI.findList(equityTransferDTO), EquityTransferVO.class, request);
            return ActResult.initialize(equityTransferVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加股权转让
     *
     * @param equityTransferTO 股权转让to
     * @return class EquityTransferBO
     * @des 添加股权转让
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addEqurfer(@Validated({ADD.class}) EquityTransferTO equityTransferTO, BindingResult result) throws ActException {
        try {
            EquityTransferBO equityTransferBO = equityTransferAPI.save(equityTransferTO);
            return ActResult.initialize(BeanTransform.copyProperties(equityTransferBO, EquityTransferVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑股权转让
     *
     * @param equityTransferTO 股权转让数据bo
     * @return class EquityTransferVO
     * @des 编辑股权转让
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editShareOpen(@Validated({EDIT.class}) EquityTransferTO equityTransferTO, BindingResult result) throws ActException {
        try {
            EquityTransferBO equityTransferBO = equityTransferAPI.edit(equityTransferTO);
            return ActResult.initialize(BeanTransform.copyProperties(equityTransferBO, EquityTransferVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除股权转让
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteShareChange(@PathVariable String id) throws ActException {
        try {
            equityTransferAPI.delete(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据股东名链接数据
     *
     * @param shareholderName 转让人
     * @param newHoldNum      转让股数
     * @return class NewEquityLinkDateVO
     * @des 根据股东名链接数据
     * @version v1
     */
    @GetMapping("v1/getLinkDate/logoutShareName")
    public Result getLinkDate(@RequestParam Integer newHoldNum, @RequestParam String shareholderName) throws ActException {
        try {
            NewEquityLinkDateVO newEquityLinkDateVO = BeanTransform.copyProperties(
                    newEquityAPI.newEqLinkDate(newHoldNum, shareholderName), NewEquityLinkDateVO.class);
            return ActResult.initialize(newEquityLinkDateVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}