package com.bjike.goddess.projectmeasure.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.projectmeasure.dto.MultipleProjectMultipleUIBDTO;
import com.bjike.goddess.projectmeasure.dto.MultipleProjectMultipleUIDTO;
import com.bjike.goddess.projectmeasure.entity.MultipleProjectMultipleUI;
import com.bjike.goddess.projectmeasure.entity.MultipleProjectMultipleUIB;

/**
 * 多项目多个界面持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 11:04 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MultipleProjectMultipleUIBRep extends JpaRep<MultipleProjectMultipleUIB, MultipleProjectMultipleUIBDTO> {

}