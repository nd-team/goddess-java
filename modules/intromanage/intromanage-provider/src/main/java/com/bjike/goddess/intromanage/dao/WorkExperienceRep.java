package com.bjike.goddess.intromanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.intromanage.dto.WorkExperienceDTO;
import com.bjike.goddess.intromanage.entity.WorkExperience;

/**
 * 工作经历持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:47 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WorkExperienceRep extends JpaRep<WorkExperience, WorkExperienceDTO> {

}