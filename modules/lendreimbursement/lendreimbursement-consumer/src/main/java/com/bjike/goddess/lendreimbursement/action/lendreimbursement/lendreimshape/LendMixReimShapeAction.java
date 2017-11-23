package com.bjike.goddess.lendreimbursement.action.lendreimbursement.lendreimshape;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.lendreimbursement.api.ApplyLendAPI;
import com.bjike.goddess.lendreimbursement.dto.reimshape.LendMixCompanyShapeDTO;
import com.bjike.goddess.lendreimbursement.dto.reimshape.LendMixReimSelfShapeDTO;
import com.bjike.goddess.lendreimbursement.vo.lendreimshape.LendMixReimShapeVO;
import com.bjike.goddess.lendreimbursement.vo.lendreimshape.ReimShapeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 借款和报销混搭记录图形
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:42 ]
 * @Description: [ 借款和报销混搭记录图形 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("lendmixreimshape")
public class LendMixReimShapeAction extends BaseFileAction {

    @Autowired
    private ApplyLendAPI applyLendAPI;


    /**
     * 汇总（个人等）年和月和周数据
     *
     * @param lendMixReimShapeDTO 借款报销汇总条件
     * @return class LendMixReimShapeVO
     * @des 汇总（个人或项目组或项目名称或地区）年和月和周的（申报报销/已报销/申请借款/已还款核对和帐务核对的借款）四种数据
     * @version v1
     */
    @PostMapping("v1/mixed/collectSelf")
    public Result collectMixMonAndWeek(LendMixReimSelfShapeDTO lendMixReimShapeDTO, BindingResult bindingResult) throws ActException {
        //周/月/年每个人的报销的情况
        try {
            LendMixReimShapeVO reimShapeVO = applyLendAPI.collectMixMonAndWeek(lendMixReimShapeDTO);
            return ActResult.initialize(reimShapeVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总公司年和月和周混合数据
     *
     * @param lendMixCompanyShapeDTO 借款报销汇总条件
     * @return class LendMixReimShapeVO
     * @des 汇总（项目组或项目名称或地区）年和月和周的（申报报销/已报销/申请借款/已还款核对和帐务核对的借款）四种数据
     * @version v1
     */
    @PostMapping("v1/mixed/collectCompany")
    public Result collectMixCompany(LendMixCompanyShapeDTO lendMixCompanyShapeDTO, BindingResult bindingResult) throws ActException {
        //周/月/年每个人的报销的情况
        try {
            LendMixReimShapeVO reimShapeVO = applyLendAPI.collectMixCompany(lendMixCompanyShapeDTO);
            return ActResult.initialize(reimShapeVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总详细公司年和月和周混合数据
     *
     * @param mixCompanyDetailShapeDTO 借款报销汇总条件
     * @return class ReimShapeVO
     * @des 汇总（项目组或项目名称或地区）年和月和周的（申报报销/已报销/申请借款/已还款核对和帐务核对的借款）四种数据
     * @version v1
     */
    @PostMapping("v1/mixed/detail/collectCompany")
    public Result collectDetailMixCompany(LendMixCompanyShapeDTO mixCompanyDetailShapeDTO, BindingResult bindingResult) throws ActException {
        //周/月/年每个人的报销的情况
        try {
            ReimShapeVO reimShapeVO = applyLendAPI.collectDetailMixCompany(mixCompanyDetailShapeDTO);
            return ActResult.initialize(reimShapeVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}