package com.bjike.goddess.attainment.action.attainment;

import com.bjike.goddess.attainment.api.SkillAnalyseAPI;
import com.bjike.goddess.attainment.to.SkillAnalyseTO;
import com.bjike.goddess.attainment.vo.SkillAnalyseVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 技能分析表
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:45 ]
 * @Description: [ 技能分析表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("skillanalyse")
public class SkillAnalyseAct {

    @Autowired
    private SkillAnalyseAPI skillAnalyseAPI;

    /**
     * 保存
     *
     * @param to 技能分析传输对象
     * @return class SkillAnalyseVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) SkillAnalyseTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(skillAnalyseAPI.save(to), SkillAnalyseVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 技能分析传输对象
     * @return class SkillAnalyseVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) SkillAnalyseTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(skillAnalyseAPI.update(to), SkillAnalyseVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 技能分析数据ID
     * @return class SkillAnalyseVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(skillAnalyseAPI.delete(id), SkillAnalyseVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}