package com.bjike.goddess.royalty.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.royalty.dto.JobsBetDTO;
import com.bjike.goddess.royalty.entity.JobsBet;

/**
 * 岗位间对赌表持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:34 ]
 * @Description: [ 岗位间对赌表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface JobsBetRep extends JpaRep<JobsBet, JobsBetDTO> {

}