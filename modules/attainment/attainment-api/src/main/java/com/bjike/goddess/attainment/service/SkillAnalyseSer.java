package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.SkillAnalyseBO;
import com.bjike.goddess.attainment.dto.SkillAnalyseDTO;
import com.bjike.goddess.attainment.entity.SkillAnalyse;
import com.bjike.goddess.attainment.to.SkillAnalyseTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 技能分析表业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:45 ]
 * @Description: [ 技能分析表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SkillAnalyseSer extends Ser<SkillAnalyse, SkillAnalyseDTO> {

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

    /**
     * 列表
     *
     * @param dto 技能分析数据传输对象
     * @return
     * @throws SerException
     */
    default List<SkillAnalyseBO> maps(SkillAnalyseDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取技能分析数据
     *
     * @param id 技能分析数据id
     * @return
     * @throws SerException
     */
    default SkillAnalyseBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }

}