package com.bjike.goddess.regularization.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.regularization.bo.ManagementScoreBO;
import com.bjike.goddess.regularization.bo.RegularizationBO;
import com.bjike.goddess.regularization.dto.ManagementScoreDTO;
import com.bjike.goddess.regularization.dto.RegularizationDTO;
import com.bjike.goddess.regularization.entity.ManagementScore;
import com.bjike.goddess.regularization.entity.Regularization;
import com.bjike.goddess.regularization.to.ManagementScoreTO;
import com.bjike.goddess.regularization.to.PlanModuleSupplyTO;
import com.bjike.goddess.regularization.to.RegularizationTO;
import com.bjike.goddess.regularization.to.ZjbApprovalTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工转正业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 05:43 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "regularizationSerCache")
@Service
public class RegularizationSerImpl extends ServiceImpl<Regularization, RegularizationDTO> implements RegularizationSer {

    @Autowired
    private ManagementScoreSer managementScoreSer;

    @Autowired
    private UserAPI userAPI;

    /**
     * 分页查询员工转正
     *
     * @return class RegularizationBO
     * @throws SerException
     */
    @Override
    public List<RegularizationBO> list(RegularizationDTO dto) throws SerException {
        List<Regularization> list = super.findByPage(dto);
        List<RegularizationBO> listBO = BeanTransform.copyProperties(list, RegularizationBO.class);
        return listBO;
    }

    /**
     * 保存员工转正
     *
     * @param to 员工转正to
     * @return class RegularizationBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public RegularizationBO save(RegularizationTO to) throws SerException {
        Regularization entity = BeanTransform.copyProperties(to, Regularization.class, true);
        entity = super.save(entity);
        RegularizationBO bo = BeanTransform.copyProperties(entity, RegularizationBO.class);
        return bo;
    }

    /**
     * 更新员工转正
     *
     * @param to 员工转正to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void update(RegularizationTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            Regularization model = super.findById(to.getId());
            if (model != null) {
                updateRegularization(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新员工转正
     *
     * @param to    员工转正to
     * @param model 员工转正
     * @throws SerException
     */
    private void updateRegularization(RegularizationTO to, Regularization model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除员工转正
     *
     * @param id 员工转正唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void remove(String id) throws SerException {
        removeManageScore(id);
        super.remove(id);
    }

    /**
     * 删除管理层评分
     *
     * @param id 员工转正id
     * @throws SerException
     */
    private void removeManageScore(String id) throws SerException {
        ManagementScoreDTO dto = new ManagementScoreDTO();
        dto.getConditions().add(Restrict.eq("regularizationId", id));
        List<ManagementScore> list = managementScoreSer.findByCis(dto);
        managementScoreSer.remove(list);//删除管理层评分
    }

    /**
     * 管理层评分
     *
     * @param to 员工转正to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void manageScore(String id, ManagementScoreTO to) throws SerException {
        String username = getCurUsername();
        ManagementScore model = setManagementScore(id, to, username);
        managementScoreSer.save(model);
    }

    /**
     * 设置管理层评分
     *
     * @param id
     * @param to
     * @param username
     * @return
     */
    private ManagementScore setManagementScore(String id, ManagementScoreTO to, String username) {
        String opinion = to.getOpinion();//获取管理层意见
        String scoreGrade = to.getScoreGrade();//获取评分等级
        Integer specificScore = to.getSpecificScore();//获取具体评价分数
        ManagementScore model = new ManagementScore();
        model.setManagement(username);
        model.setOpinion(opinion);
        model.setScoreGrade(scoreGrade);
        model.setSpecificScore(specificScore);
        model.setRegularizationId(id);
        return model;
    }

    /**
     * 获取当前用户名
     *
     * @return
     * @throws SerException
     */
    private String getCurUsername() throws SerException {
        UserBO user = userAPI.currentUser();
        return user.getUsername();
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
        if (StringUtils.isNotBlank(id)) {
            ManagementScoreDTO dto = new ManagementScoreDTO();
            dto.getConditions().add(Restrict.eq("regularizationId", id));
            List<ManagementScore> list = managementScoreSer.findByCis(dto);
            List<ManagementScoreBO> listBO = BeanTransform.copyProperties(list, ManagementScoreBO.class);
            return listBO;
        } else {
            throw new SerException("员工转正id不能为空!");
        }

    }

    /**
     * 决策层评价
     *
     * @param id                    员工转正唯一标识
     * @param decisionLevelEvaluate 决策层评价
     * @param decisionLevelRank     决策层评分等级
     * @param decisionLevelScore    决策层具体评分
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void decisionLevelEvaluate(String id, String decisionLevelEvaluate, String decisionLevelRank, Integer decisionLevelScore) throws SerException {
        Regularization model = super.findById(id);
        model.setDecisionLevelEvaluate(decisionLevelEvaluate);
        model.setDecisionLevelRank(decisionLevelRank);
        model.setDecisionLevelScore(decisionLevelScore);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 规划模块补充
     *
     * @param to 员工转正to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void planModuleSupply(PlanModuleSupplyTO to) throws SerException {
        Regularization model = super.findById(to.getId());
        model.setAfterPost(to.getAfterPost());//转正后岗位
        model.setAfterSkillRank(to.getAfterSkillRank());//转正技能定级
        model.setPlanPositiveComment(to.getPlanPositiveComment());//规划模块转正意见
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 预算模块补充
     *
     * @param id                    员工转正唯一标识
     * @param budgetPositiveComment 预算模块转正意见
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void budgetModuleSupply(String id, String budgetPositiveComment) throws SerException {
        Regularization model = super.findById(id);
        model.setBudgetPositiveComment(budgetPositiveComment);//预算模块转正意见
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 总经办审批
     *
     * @param to 总经办审批to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void zjbApproval(ZjbApprovalTO to) throws SerException {
        Regularization model = super.findById(to.getId());
        model.setModifyTime(LocalDateTime.now());
        model.setPositiveType(to.getPositiveType());//转正类型
        model.setZjbAppraise(to.getZjbAppraise());//总经办评价
        model.setPositiveDate(DateUtil.parseDate(to.getPositiveDate()));
        super.update(model);
    }

}