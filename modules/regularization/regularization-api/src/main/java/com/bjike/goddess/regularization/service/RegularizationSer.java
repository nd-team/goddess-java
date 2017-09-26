package com.bjike.goddess.regularization.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.regularization.bo.ManagementScoreBO;
import com.bjike.goddess.regularization.bo.RegularizationBO;
import com.bjike.goddess.regularization.dto.RegularizationDTO;
import com.bjike.goddess.regularization.entity.Regularization;
import com.bjike.goddess.regularization.excel.SonPermissionObject;
import com.bjike.goddess.regularization.to.*;

import java.util.List;
import java.util.Set;

/**
 * 员工转正业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 05:43 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RegularizationSer extends Ser<Regularization, RegularizationDTO> {

    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {

        return null;
    }

    /**
     * 功能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

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
     * @param id 员工转正唯一标识
     * @param to 管理层评分to
     * @throws SerException
     */
    void manageScore(String id, ManagementScoreTO to) throws SerException;

    /**
     * 查看管理层评分
     *
     * @param id 员工转正唯一标识
     * @return class ManagementScoreBO
     * @throws SerException
     */
    List<ManagementScoreBO> checkManageScore(String id) throws SerException;

    /**
     * 决策层评价
     *
     * @param id                    员工转正唯一标识
     * @param decisionLevelEvaluate 决策层评价
     * @param decisionLevelRank     决策层评分等级
     * @param decisionLevelScore    决策层具体评分
     * @throws SerException
     */
    void decisionLevelEvaluate(String id, String decisionLevelEvaluate, String decisionLevelRank, Integer decisionLevelScore) throws SerException;

    /**
     * 规划模块补充
     *
     * @param to 规划模块补充to
     * @throws SerException
     */
    void planModuleSupply(PlanModuleSupplyTO to) throws SerException;

    /**
     * 预算模块补充
     *
     * @param id                    员工转正唯一标识
     * @param budgetPositiveComment 预算模块转正意见
     * @throws SerException
     */
    void budgetModuleSupply(String id, String budgetPositiveComment) throws SerException;

    /**
     * 总经办审批
     *
     * @param to 总经办审批to
     * @throws SerException
     */
    void zjbApproval(ZjbApprovalTO to) throws SerException;

    /**
     * 获取所有组织结构中的部门
     *
     * @return
     * @throws SerException
     */
    default List<String> findAddAllDetails() throws SerException {
        return null;
    }

    /**
     * 获取所有用户
     *
     * @return
     * @throws SerException
     */
    default List<String> findallMonUser() throws SerException {
        return null;
    }

    /**
     * 链接入职信息
     *
     * @return
     * @throws SerException
     */
    default RegularizationBO findAddRusult(String empNumer) throws SerException {
        return null;
    }

    /**
     * 根据用户名查看信息
     *
     * @return
     * @throws SerException
     */
    default List<RegularizationBO> findByName() throws SerException {
        return null;
    }

    /**
     * chenjunhao
     * 根据员工编号查找转正时间
     *
     * @param empNo 员工编号
     * @return
     * @throws SerException
     */
    String time(String empNo) throws SerException;

    /**
     * chenjunhao
     * 获取所有员工编号
     *
     * @return
     * @throws SerException
     */
    Set<String> allNum() throws SerException;

    /**
     * 根据员工姓名查找转正时间
     * zhuangkaiqin
     */
    default String getTime(String userName) throws SerException {
        return null;
    }
    /**
     * 根据员工姓名判断是否转正
     * jiangzaixuan
     */
    default Boolean checkTran(String userName) throws SerException {
        return null;
    }
}