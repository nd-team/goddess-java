package com.bjike.goddess.royalty.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.royalty.dto.JobsBetBDTO;
import com.bjike.goddess.royalty.dto.JobsBetDDTO;
import com.bjike.goddess.royalty.entity.JobsBetC;
import com.bjike.goddess.royalty.entity.JobsBetD;

/**
 * 岗位间对赌表D持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:33 ]
 * @Description: [ 岗位间对赌表D持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface JobsBetDRep extends JpaRep<JobsBetD, JobsBetDDTO> {

}