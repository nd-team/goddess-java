package com.bjike.goddess.regularization.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.regularization.bo.ManagementScoreBO;
import com.bjike.goddess.regularization.bo.RegularizationBO;
import com.bjike.goddess.regularization.dto.RegularizationDTO;
import com.bjike.goddess.regularization.entity.Regularization;
import com.bjike.goddess.regularization.excel.SonPermissionObject;
import com.bjike.goddess.regularization.service.RegularizationSer;
import com.bjike.goddess.regularization.to.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 员工转正业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 05:43 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("regularizationApiImpl")
public class RegularizationApiImpl implements RegularizationAPI {


    @Autowired
    private RegularizationSer regularizationSer;


    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return regularizationSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return regularizationSer.guidePermission(guidePermissionTO);
    }
    /**
     * 根据id查询员工转正
     *
     * @param id 员工转正唯一标识
     * @return class RegularizationBO
     * @throws SerException
     */
    @Override
    public RegularizationBO findById(String id) throws SerException {
        Regularization model = regularizationSer.findById(id);
        return BeanTransform.copyProperties(model, RegularizationBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 员工转正dto
     * @throws SerException
     */
    @Override
    public Long count(RegularizationDTO dto) throws SerException {
        return regularizationSer.count(dto);
    }

    /**
     * 分页查询员工转正
     *
     * @return class RegularizationBO
     * @throws SerException
     */
    @Override
    public List<RegularizationBO> list(RegularizationDTO dto) throws SerException {
        return regularizationSer.list(dto);
    }

    /**
     * 保存员工转正
     *
     * @param to 员工转正to
     * @return class RegularizationBO
     * @throws SerException
     */
    @Override
    public RegularizationBO save(RegularizationTO to) throws SerException {
        return regularizationSer.save(to);
    }

    /**
     * 根据id删除员工转正
     *
     * @param id 员工转正唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        regularizationSer.remove(id);
    }

    /**
     * 更新员工转正
     *
     * @param to 员工转正to
     * @throws SerException
     */
    @Override
    public void update(RegularizationTO to) throws SerException {
        regularizationSer.update(to);
    }

    /**
     * 管理层评分
     *
     * @param id 员工转正唯一标识
     * @param to 管理层评分to
     * @throws SerException
     */
    @Override
    public void manageScore(String id, ManagementScoreTO to) throws SerException {
        regularizationSer.manageScore(id, to);
    }

    /**
     * 查看管理层评分
     *
     * @param id 员工转正唯一标识
     * @return class ManagementScoreBO
     * @throws SerException
     */
    @Override
    public List<ManagementScoreBO> checkManageScore(String id) throws SerException {
        return regularizationSer.checkManageScore(id);
    }

    /**
     * 决策层评价
     *
     * @param id 员工转正唯一标识
     * @param decisionLevelEvaluate 决策层评价
     * @param decisionLevelRank 决策层评分等级
     * @param decisionLevelScore 决策层具体评分
     * @throws SerException
     */
    @Override
    public void decisionLevelEvaluate(String id, String decisionLevelEvaluate, String decisionLevelRank, Integer decisionLevelScore) throws SerException {
        regularizationSer.decisionLevelEvaluate(id, decisionLevelEvaluate, decisionLevelRank, decisionLevelScore);
    }

    /**
     * 规划模块补充
     *
     * @param to 规划模块补充to
     * @throws SerException
     */
    @Override
    public void planModuleSupply(PlanModuleSupplyTO to) throws SerException {
        regularizationSer.planModuleSupply(to);
    }

    /**
     * 预算模块转正意见
     *
     * @param id 员工转正唯一标识
     * @param budgetPositiveComment 预算模块转正意见
     * @throws SerException
     */
    @Override
    public void budgetModuleSupply(String id, String budgetPositiveComment) throws SerException {
        regularizationSer.budgetModuleSupply(id, budgetPositiveComment);
    }

    /**
     * 总经办审批
     *
     * @param to 总经办审批to
     * @throws SerException
     */
    @Override
    public void zjbApproval(ZjbApprovalTO to) throws SerException {
        regularizationSer.zjbApproval(to);
    }

    @Override
    public List<String> findAddAllDetails() throws SerException {
        return regularizationSer.findAddAllDetails();
    }

    @Override
    public List<String> findallMonUser() throws SerException {
        return regularizationSer.findallMonUser();
    }

    @Override
    public RegularizationBO findAddRusult(String name, String empNumer) throws SerException {
        return regularizationSer.findAddRusult(name,empNumer);
    }

    @Override
    public String time(String empNo) throws SerException {
        return regularizationSer.time(empNo);
    }

    @Override
    public Set<String> allNum() throws SerException {
        return regularizationSer.allNum();
    }

    @Override
    public String getTime(String userName) throws SerException {
        return regularizationSer.getTime(userName);
    }
}