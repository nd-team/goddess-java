package com.bjike.goddess.royalty.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.royalty.dto.JobsBetADTO;
import com.bjike.goddess.royalty.entity.JobsBetA;
import com.bjike.goddess.royalty.entity.JobsBetB;

/**
 * 岗位间对赌表A持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:21 ]
 * @Description: [ 岗位间对赌表A持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface JobsBetARep extends JpaRep<JobsBetA, JobsBetADTO> {

}