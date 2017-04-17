package com.bjike.goddess.regularization.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.regularization.bo.ManagementScoreBO;
import com.bjike.goddess.regularization.bo.RegularizationBO;
import com.bjike.goddess.regularization.dto.RegularizationDTO;
import com.bjike.goddess.regularization.entity.Regularization;
import com.bjike.goddess.regularization.to.RegularizationTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

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

    /**
     * 分页查询员工转正
     *
     * @return class RegularizationBO
     * @throws SerException
     */
    @Override
    public List<RegularizationBO> list(RegularizationDTO dto) throws SerException {
        return null;
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
        return null;
    }

    /**
     * 更新员工转正
     *
     * @param to 员工转正to
     * @throws SerException
     */
    @Override
    public void update(RegularizationTO to) throws SerException {

    }

    /**
     * 根据id删除员工转正
     *
     * @param id 员工转正唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 管理层评分
     *
     * @param to 员工转正to
     * @throws SerException
     */
    @Override
    public void manageScore(RegularizationTO to) throws SerException {

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
        return null;
    }

    /**
     * 决策层评价
     *
     * @param to 员工转正to
     * @throws SerException
     */
    @Override
    public void decisionLevelEvaluate(RegularizationTO to) throws SerException {

    }

    /**
     * 规划模块补充
     *
     * @param to 员工转正to
     * @throws SerException
     */
    @Override
    public void planModuleSupply(RegularizationTO to) throws SerException {

    }

    /**
     * 预算模块补充
     *
     * @param to 员工转正to
     * @throws SerException
     */
    @Override
    public void budgetModuleSupply(RegularizationTO to) throws SerException {

    }

    /**
     * 总经办审批
     *
     * @param to 员工转正to
     * @throws SerException
     */
    @Override
    public void zjbApproval(RegularizationTO to) throws SerException {

    }
}