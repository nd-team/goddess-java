package com.bjike.goddess.regularization.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.regularization.bo.ManagementScoreBO;
import com.bjike.goddess.regularization.bo.RegularizationBO;
import com.bjike.goddess.regularization.dto.RegularizationDTO;
import com.bjike.goddess.regularization.to.RegularizationTO;

import java.util.List;

/**
 * 员工转正业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 05:43 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RegularizationAPI {

    /**
     * 分页查询员工转正
     *
     * @return class RegularizationBO
     * @throws SerException
     */
    List<RegularizationBO> list(RegularizationDTO dto) throws SerException;

    /**
     * 保存员工转正
     *
     * @param to 员工转正to
     * @return class RegularizationBO
     * @throws SerException
     */
    RegularizationBO save(RegularizationTO to) throws SerException;

    /**
     * 根据id删除员工转正
     *
     * @param id 员工转正唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新员工转正
     *
     * @param to 员工转正to
     * @throws SerException
     */
    void update(RegularizationTO to) throws SerException;


    /**
     * 管理层评分
     *
     * @param to 员工转正to
     * @throws SerException
     */
    void manageScore(RegularizationTO to) throws SerException;

    /**
     * 查看管理层评分
     *
     * @param to 员工转正to
     * @return class ManagementScoreBO
     * @throws SerException
     */
    List<ManagementScoreBO> checkManageScore(RegularizationTO to) throws SerException;

    /**
     * 决策层评价
     *
     * @param to 员工转正to
     * @throws SerException
     */
    void decisionLevelEvaluate(RegularizationTO to) throws SerException;

    /**
     * 规划模块补充
     *
     * @param to 员工转正to
     * @throws SerException
     */
    void planModuleSupply(RegularizationTO to) throws SerException;

    /**
     * 预算模块补充
     *
     * @param to 员工转正to
     * @throws SerException
     */
    void budgetModuleSupply(RegularizationTO to) throws SerException;

    /**
     * 总经办审批
     *
     * @param to 员工转正to
     * @throws SerException
     */
    void zjbApproval(RegularizationTO to) throws SerException;
}