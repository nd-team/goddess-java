package com.bjike.goddess.managepromotion.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.managepromotion.bo.SkillLevelCollectBO;
import com.bjike.goddess.managepromotion.bo.SkillPromotionApplyBO;
import com.bjike.goddess.managepromotion.dto.SkillPromotionApplyDTO;
import com.bjike.goddess.managepromotion.to.GuidePermissionTO;
import com.bjike.goddess.managepromotion.to.SkillLevelCollectTO;
import com.bjike.goddess.managepromotion.to.SkillPromotionApplyTO;

import java.util.List;

/**
 * 技能晋升申请业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 09:03 ]
 * @Description: [ 技能晋升申请业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SkillPromotionApplyAPI {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }
    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 技能晋升申请列表总条数
     */
    default Long countSkillPromotionApply(SkillPromotionApplyDTO skillPromotionApplyDTO) throws SerException {
        return null;
    }
    /**
     * 一个技能晋升申请
     * @return class SkillPromotionApplyBO
     */
    default SkillPromotionApplyBO getOne(String id) throws SerException {return null;}

    /**
     * 技能晋升申请
     *
     * @param skillPromotionApplyDTO 技能晋升申请dto
     * @return class SkillPromotionApplyBO
     * @throws SerException
     */
    default List<SkillPromotionApplyBO> findListSkillPromotionApply(SkillPromotionApplyDTO skillPromotionApplyDTO) throws SerException {
        return null;
    }

    /**
     * 申请晋升
     *
     * @param skillPromotionApplyTO 申请晋升数据to
     * @return class SkillPromotionApplyBO
     * @throws SerException
     */
    default SkillPromotionApplyBO insertSkillPromotionApply(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        return null;
    }

    /**
     * 综合素养补充
     *
     * @param skillPromotionApplyTO 综合素养补充数据to
     * @return class SkillPromotionApplyBO
     * @throws SerException
     */
    default SkillPromotionApplyBO editSkillPromotionApply(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        return null;
    }

    /**
     * 负责人审核
     *
     * @param skillPromotionApplyTO 负责人审核数据to
     * @return class SkillPromotionApplyBO
     * @throws SerException
     */
    default SkillPromotionApplyBO headAudit(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        return null;
    }

    /**
     * 预算审核
     *
     * @param skillPromotionApplyTO 预算审核数据to
     * @return class SkillPromotionApplyBO
     * @throws SerException
     */
    default SkillPromotionApplyBO budgetAudit(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        return null;
    }

    /**
     * 项目经理审核
     *
     * @param skillPromotionApplyTO 项目经理审核数据to
     * @return class SkillPromotionApplyBO
     * @throws SerException
     */
    default SkillPromotionApplyBO projectManagerAudit(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        return null;
    }

    /**
     * 规划模块审核
     *
     * @param skillPromotionApplyTO 规划模块审核数据to
     * @return class SkillPromotionApplyBO
     * @throws SerException
     */
    default SkillPromotionApplyBO planAudit(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        return null;
    }
    /**
     * 总经办审核
     *
     * @param skillPromotionApplyTO 总经办审核数据to
     * @return class SkillPromotionApplyBO
     * @throws SerException
     */
    default SkillPromotionApplyBO generalManagerAudit(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        return  null;
    }
    /**
     * 技能等级晋升管理日汇总
     *
     * @param to to
     * @return class SkillLevelCollectBO
     * @throws SerException
     */
    default List<SkillLevelCollectBO> dayLevelCollect(SkillLevelCollectTO to) throws SerException {
        return  null;
    }
    /**
     * 技能等级晋升管理周汇总
     *
     * @param to to
     * @return class SkillLevelCollectBO
     * @throws SerException
     */
    default List<SkillLevelCollectBO> weekLevelCollect(SkillLevelCollectTO to) throws SerException {
        return  null;
    }
    /**
     * 技能等级晋升管理月汇总
     *
     * @param to to
     * @return class SkillLevelCollectBO
     * @throws SerException
     */
    default List<SkillLevelCollectBO> monthLevelCollect(SkillLevelCollectTO to) throws SerException {
        return  null;
    }
    /**
     * 技能等级晋升管理累计汇总
     *
     * @param to to
     * @return class SkillLevelCollectBO
     * @throws SerException
     */
    default List<SkillLevelCollectBO> totalLevelCollect(SkillLevelCollectTO to) throws SerException {
        return  null;
    }
    /**
     * 获取已晋升次数月汇总
     *
     * @param to
     * @return
     * @throws SerException
     */
    Integer monthPromotedNum(SkillLevelCollectTO to) throws SerException;
    /**
     * 获取已晋升次数季度汇总
     *
     * @param to
     * @return
     * @throws SerException
     */
    Integer quartPromotedNum(SkillLevelCollectTO to) throws SerException;
    /**
     * 获取已晋升次数年汇总
     *
     * @param to
     * @return
     * @throws SerException
     */
    Integer yearPromotedNum(SkillLevelCollectTO to) throws SerException;
}