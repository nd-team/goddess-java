package com.bjike.goddess.attainment.api;

import com.bjike.goddess.attainment.bo.SkillAnalyseBO;
import com.bjike.goddess.attainment.to.SkillAnalyseTO;
import com.bjike.goddess.common.api.exception.SerException;

/**
 * 技能分析表业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:45 ]
 * @Description: [ 技能分析表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SkillAnalyseAPI {

    /**
     * 保存
     *
     * @param to 技能分析传输对象
     * @return
     * @throws SerException
     */
    default SkillAnalyseBO save(SkillAnalyseTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 技能分析传输对象
     * @return
     * @throws SerException
     */
    default SkillAnalyseBO update(SkillAnalyseTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 技能分析数据ID
     * @return
     * @throws SerException
     */
    default SkillAnalyseBO delete(String id) throws SerException {
        return null;
    }


}