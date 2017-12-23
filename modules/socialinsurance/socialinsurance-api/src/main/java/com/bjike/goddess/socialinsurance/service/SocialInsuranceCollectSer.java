package com.bjike.goddess.socialinsurance.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.socialinsurance.bo.SocialInsuranceCollectBO;
import com.bjike.goddess.socialinsurance.dto.SocialInsuranceCollectDTO;
import com.bjike.goddess.socialinsurance.entity.SocialInsurance;

import java.util.List;

/**
 * 社会保险汇总业务接口
 * @Author: [caiwenxian]
 * @Date: [2017-12-21 17:56]
 * @Description: [ 社会保险汇总业务接口　]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface SocialInsuranceCollectSer extends Ser<SocialInsurance, SocialInsuranceCollectDTO> {


    /**
     * 个人汇总
     *
     * @param dto
     * @return class
     * @version v1
     */
    List<SocialInsuranceCollectBO> personalCollect(SocialInsuranceCollectDTO dto) throws SerException;

    /**
     * 部门汇总
     *
     * @param dto
     * @return class
     * @version v1
     */
    List<SocialInsuranceCollectBO> departmentCollect(SocialInsuranceCollectDTO dto) throws SerException;

    /**
     * 地区汇总
     *
     * @param dto
     * @return class
     * @version v1
     */
    List<SocialInsuranceCollectBO> areaCollect(SocialInsuranceCollectDTO dto) throws SerException;


}
