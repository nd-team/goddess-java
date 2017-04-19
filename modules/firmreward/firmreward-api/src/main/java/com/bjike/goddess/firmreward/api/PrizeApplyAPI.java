package com.bjike.goddess.firmreward.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.firmreward.bo.*;
import com.bjike.goddess.firmreward.dto.PrizeApplyDTO;
import com.bjike.goddess.firmreward.entity.PrizeDetail;
import com.bjike.goddess.firmreward.to.PrizeApplyTO;

import java.util.List;

/**
 * 奖品申请业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-13 09:04 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PrizeApplyAPI {

    /**
     * 分页查询奖品申请
     *
     * @return class PrizeApplyBO
     * @throws SerException
     */
    List<PrizeApplyBO> list(PrizeApplyDTO dto) throws SerException;

    /**
     * 保存奖品申请
     *
     * @param to 奖品申请to
     * @return class PrizeApplyBO
     * @throws SerException
     */
    PrizeApplyBO save(PrizeApplyTO to) throws SerException;

    /**
     * 根据id删除奖品申请
     *
     * @param id 讲评申请唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新奖品申请
     *
     * @param to 奖品申请to
     * @throws SerException
     */
    void update(PrizeApplyTO to) throws SerException;

    /**
     * 添加奖品明细
     *
     * @param to 奖品申请to
     * @throws SerException
     */
    void addPrizeDetails(PrizeApplyTO to) throws SerException;

    /**
     * 更新奖品明细
     *
     * @param to 奖品申请to
     * @throws SerException
     */
    void updatePrizeDetails(PrizeApplyTO to) throws SerException;

    /**
     * 查看奖品明细
     *
     * @param applyId 奖品申请id
     * @return class PrizeDetailBO
     * @throws SerException
     */
    List<PrizeDetailBO> checkPrizeDetails(String applyId) throws SerException;

    /**
     * 员工奖励汇总
     *
     * @return class StaffRewardCollectBO
     * @throws SerException
     */
    List<StaffRewardCollectBO> staffRewardCollect() throws SerException;

    /**
     * 项目组奖励汇总
     *
     * @return class ProjectGroupRewardCollectBO
     * @throws SerException
     */
    List<ProjectGroupRewardCollectBO> projectGroupRewardCollect() throws SerException;

    /**
     * 地区奖励汇总
     *
     * @return class AreaRewardCollectBO
     * @throws SerException
     */
    List<AreaRewardCollectBO> areaRewardCollect() throws SerException;

}