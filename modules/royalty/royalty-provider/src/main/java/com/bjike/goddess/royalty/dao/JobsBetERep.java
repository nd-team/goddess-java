package com.bjike.goddess.royalty.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.royalty.dto.JobsBetBDTO;
import com.bjike.goddess.royalty.dto.JobsBetEDTO;
import com.bjike.goddess.royalty.entity.JobsBetB;
import com.bjike.goddess.royalty.entity.JobsBetE;

/**
 * 岗位间对赌表E持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:33 ]
 * @Description: [ 岗位间对赌表E持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface JobsBetERep extends JpaRep<JobsBetE, JobsBetEDTO> {

}