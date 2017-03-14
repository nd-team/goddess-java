package com.bjike.goddess.staffentry.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffentry.dto.StudyExperienceDTO;
import com.bjike.goddess.staffentry.entity.StudyExperience;

/**
 * 学习经历持久化接口, 继承基类可使用ｊｐａ命名查询
 * @Author: [tanghaixiang]
 * @Date: [2017-03-10 12:01]
 * @Description: [学习经历持久化接口, 继承基类可使用ｊｐａ命名查询]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface StudyExperienceRep extends JpaRep<StudyExperience ,StudyExperienceDTO> {
}
