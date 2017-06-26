package com.bjike.goddess.moneyside.action.moneyside;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.api.FundEntryAPI;
import com.bjike.goddess.moneyside.bo.FundEntryBO;
import com.bjike.goddess.moneyside.dto.FundEntryDTO;
import com.bjike.goddess.moneyside.to.*;
import com.bjike.goddess.moneyside.vo.CollectVO;
import com.bjike.goddess.moneyside.vo.FundEntryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 资金进入申请
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 11:05 ]
 * @Description: [ 资金进入申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("fundentry")
public class FundEntryAction {
    @Autowired
    private FundEntryAPI fundEntryAPI;

    /**
     * 资金进入申请列表总条数
     *
     * @param fundEntryDTO 资金进入申请dto
     * @des 获取所有资金进入申请总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(FundEntryDTO fundEntryDTO) throws ActException {
        try {
            Long count = fundEntryAPI.countFundEntry(fundEntryDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个资金进入申请
     *
     * @param id
     * @return class FundEntryVO
     * @des 获取一个资金进入申请
     * @version v1
     */
    @GetMapping("v1/fund/{id}")
    public Result fund(@PathVariable String id) throws ActException {
        try {
            FundEntryBO fundEntryBO = fundEntryAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(fundEntryBO, FundEntryVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 资金进入申请列表
     *
     * @param fundEntryDTO 资金进入申请dto
     * @return class FundEntryVO
     * @des 获取所有资金进入申请
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(FundEntryDTO fundEntryDTO, HttpServletRequest request) throws ActException {
        try {
            List<FundEntryVO> fundEntryVOS = BeanTransform.copyProperties
                    (fundEntryAPI.findListFundEntry(fundEntryDTO), FundEntryVO.class, request);
            return ActResult.initialize(fundEntryVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加资金进入申请
     *
     * @param fundEntryTO 资金进入申请数据to
     * @return class FundEntryVO
     * @des 添加资金进入申请
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) FundEntryTO fundEntryTO, BindingResult bindingResult) throws ActException {
        try {
            FundEntryBO fundEntryBO = fundEntryAPI.insertFundEntry(fundEntryTO);
            return ActResult.initialize(fundEntryBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑资金进入申请
     *
     * @param fundEntryTO 资金进入申请数据to
     * @return class FundEntryVO
     * @des 编辑资金进入申请
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) FundEntryTO fundEntryTO, BindingResult bindingResult) throws ActException {
        try {
            FundEntryBO fundEntryBO = fundEntryAPI.editFundEntry(fundEntryTO);
            return ActResult.initialize(fundEntryBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除资金进入申请
     *
     * @param id 用户id
     * @des 根据用户id删除资金进入申请记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            fundEntryAPI.removeFundEntry(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取投资人
     *
     * @des 获取投资人
     * @version v1
     */
    @GetMapping("v1/getInvestor")
    public Result getInvestor() throws ActException {
        try {
            List<String> list = fundEntryAPI.getInvestor();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核
     *
     * @param fundEntryTO 审核数据to
     * @return class FundEntryVO
     * @des 审核
     * @version v1
     */
    @PostMapping("v1/audit")
    public Result audit(@Validated FundEntryTO fundEntryTO, BindingResult bindingResult) throws ActException {
        try {
            FundEntryBO fundEntryBO = fundEntryAPI.audit(fundEntryTO);
            return ActResult.initialize(fundEntryBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 申请人汇总
     *
     * @param to 汇总条件
     * @return class CollectVO
     * @version v1
     */
    @GetMapping("v1/investor")
    public Result investor(@Validated({ApplyPeopleTO.Collect.class}) ApplyPeopleTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            CollectTO collectTO = BeanTransform.copyProperties(to, CollectTO.class);
            List<CollectVO> voList = BeanTransform.copyProperties(fundEntryAPI.collect(collectTO), CollectVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 资金进入方式汇总
     *
     * @param to 汇总条件
     * @return class CollectVO
     * @version v1
     */
    @GetMapping("v1/accessCollect")
    public Result accessCollect(@Validated({AccessToFundTO.Collect.class}) AccessToFundTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            CollectTO collectTO = BeanTransform.copyProperties(to, CollectTO.class);
            List<CollectVO> voList = BeanTransform.copyProperties(fundEntryAPI.collect(collectTO), CollectVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 资金进入时间汇总
     *
     * @param to 汇总条件
     * @return class CollectVO
     * @version v1
     */
    @GetMapping("v1/timeCollect")
    public Result timeCollect(@Validated({DateTO.Collect.class}) DateTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            CollectTO collectTO = BeanTransform.copyProperties(to, CollectTO.class);
            List<CollectVO> voList = BeanTransform.copyProperties(fundEntryAPI.collect(collectTO), CollectVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}