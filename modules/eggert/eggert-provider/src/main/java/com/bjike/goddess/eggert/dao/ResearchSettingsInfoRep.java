package com.bjike.goddess.eggert.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.eggert.dto.ResearchSettingsInfoDTO;
import com.bjike.goddess.eggert.entity.ResearchSettingsInfo;

/**
 * 调研设置信息持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-31 05:22 ]
 * @Description: [ 调研设置信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ResearchSettingsInfoRep extends JpaRep<ResearchSettingsInfo, ResearchSettingsInfoDTO> {

}