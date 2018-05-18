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
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;

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

    /**
     * 报销借款统计指定地区参数获取
     *
     * @des 获取所有地区
     * @version v1
     */
    @GetMapping("v1/listArea")
    public Result listArea() throws ActException {
        try {
            List<String> userList = new ArrayList<>();
            List<AreaBO> areaBOList = departmentDetailAPI.findArea();
            userList = (areaBOList != null && areaBOList.size() > 0) ? areaBOList.stream().map(AreaBO::getArea).collect(Collectors.toList()) : new ArrayList<>();
            return ActResult.initialize(userList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 报销借款统计指定项目组参数获取
     *
     * @des 获取所有项目组
     * @version v1
     */
    @GetMapping("v1/listGroup")
    public Result listGroup() throws ActException {
        try {
            List<String> userList = new ArrayList<>();
            userList = positionDetailUserAPI.getAllDepartment();
            return ActResult.initialize(userList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 报销借款统计指定项目名称参数获取
     *
     * @des 获取所有项目名称
     * @version v1
     */
    @GetMapping("v1/listProject")
    public Result listProject() throws ActException {
        try {
            List<String> userList = new ArrayList<>();
            userList =  departmentDetailAPI.findAllProject();
            return ActResult.initialize(userList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}