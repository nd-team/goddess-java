package com.bjike.goddess.regularization.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.regularization.bo.ManagementScoreBO;
import com.bjike.goddess.regularization.bo.RegularizationBO;
import com.bjike.goddess.regularization.dto.ManagementScoreDTO;
import com.bjike.goddess.regularization.dto.RegularizationDTO;
import com.bjike.goddess.regularization.entity.ManagementScore;
import com.bjike.goddess.regularization.entity.Regularization;
import com.bjike.goddess.regularization.to.RegularizationTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
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
     * @param to 员工转正to
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
    public void manageScore(RegularizationTO to) throws SerException {
        String regularizationId = to.getId();
        String username = getCurUsername();
        String opinion = to.getOpinion();//获取管理层意见
        String scoreGrade = to.getScoreGrade();//获取评分等级
        ManagementScore model = new ManagementScore();
        model.setManagement(username);
        model.setOpinion(opinion);
        model.setScoreGrade(scoreGrade);
        model.setRegularizationId(regularizationId);
        managementScoreSer.save(model);
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
     * @param to 员工转正to
     * @return class ManagementScoreBO
     * @throws SerException
     */
    @Override
    public List<ManagementScoreBO> checkManageScore(RegularizationTO to) throws SerException {
        String regularizationId = to.getId();
        if (StringUtils.isNotBlank(regularizationId)) {
            ManagementScoreDTO dto = new ManagementScoreDTO();
            dto.getConditions().add(Restrict.eq("regularizationId", regularizationId));
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
     * @param to 员工转正to
     * @throws SerException
     */
    @Override
    public void decisionLevelEvaluate(RegularizationTO to) throws SerException {
        String curUsername = getCurUsername();//获取当前用户名
        to.setDecisionLevel(curUsername);//设置决策层
        update(to);
    }

    /**
     * 规划模块补充
     *
     * @param to 员工转正to
     * @throws SerException
     */
    @Override
    public void planModuleSupply(RegularizationTO to) throws SerException {
        String curUsername = getCurUsername();
        to.setPlanModule(curUsername);//设置规划莫模块负责人
        update(to);
    }

    /**
     * 预算模块补充
     *
     * @param to 员工转正to
     * @throws SerException
     */
    @Override
    public void budgetModuleSupply(RegularizationTO to) throws SerException {
        String curUsername = getCurUsername();
        to.setBudgetModule(curUsername);//设置预算模块负责人
        update(to);
    }

    /**
     * 总经办审批
     *
     * @param to 员工转正to
     * @throws SerException
     */
    @Override
    public void zjbApproval(RegularizationTO to) throws SerException {
        String curUsername = getCurUsername();
        to.setGmOffice(curUsername);
        update(to);
    }
}