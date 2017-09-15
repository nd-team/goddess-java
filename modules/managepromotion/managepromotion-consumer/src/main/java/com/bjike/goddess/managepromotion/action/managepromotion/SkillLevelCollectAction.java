package com.bjike.goddess.managepromotion.action.managepromotion;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managepromotion.api.SkillPromotionApplyAPI;
import com.bjike.goddess.managepromotion.to.SkillLevelCollectTO;
import com.bjike.goddess.managepromotion.vo.SkillLevelCollectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 技能等级晋升管理汇总
 *
 * @Author: [xiazhili]
 * @Date: [2017-09-12 15:26]
 * @Description: [技能等级晋升管理汇总 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("skilllevelcollect")
public class SkillLevelCollectAction {
    @Autowired
    private SkillPromotionApplyAPI skillPromotionApplyAPI;

    /**
     * 技能等级晋升管理日汇总
     *
     * @param to to
     * @return class SkillLevelCollectVO
     * @des 技能等级晋升管理日汇总
     * @version v1
     */
    @GetMapping("v1/dayCollect")
    public Result dayCollect(SkillLevelCollectTO to) throws ActException {
        try {
            List<SkillLevelCollectVO> skillLevelCollectVOS =
                    BeanTransform.copyProperties(skillPromotionApplyAPI.dayLevelCollect(to), SkillLevelCollectVO.class);
            return ActResult.initialize(skillLevelCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 技能等级晋升管理周汇总
     *
     * @param to to
     * @return class SkillLevelCollectVO
     * @des 技能等级晋升管理周汇总
     * @version v1
     */
    @GetMapping("v1/weekCollect")
    public Result weekCollect(SkillLevelCollectTO to) throws ActException {
        try {
            List<SkillLevelCollectVO> skillLevelCollectVOS =
                    BeanTransform.copyProperties(skillPromotionApplyAPI.weekLevelCollect(to), SkillLevelCollectVO.class);
            return ActResult.initialize(skillLevelCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 技能等级晋升管理月汇总
     *
     * @param to to
     * @return class SkillLevelCollectVO
     * @des 技能等级晋升管理月汇总
     * @version v1
     */
    @GetMapping("v1/monthCollect")
    public Result monthCollect(SkillLevelCollectTO to) throws ActException {
        try {
            List<SkillLevelCollectVO> skillLevelCollectVOS =
                    BeanTransform.copyProperties(skillPromotionApplyAPI.monthLevelCollect(to), SkillLevelCollectVO.class);
            return ActResult.initialize(skillLevelCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 技能等级晋升管理累计汇总
     *
     * @param to to
     * @return class SkillLevelCollectVO
     * @des 技能等级晋升管理累计汇总
     * @version v1
     */
    @GetMapping("v1/totalCollect")
    public Result totalCollect(SkillLevelCollectTO to) throws ActException {
        try {
            List<SkillLevelCollectVO> skillLevelCollectVOS =
                    BeanTransform.copyProperties(skillPromotionApplyAPI.totalLevelCollect(to), SkillLevelCollectVO.class);
            return ActResult.initialize(skillLevelCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
