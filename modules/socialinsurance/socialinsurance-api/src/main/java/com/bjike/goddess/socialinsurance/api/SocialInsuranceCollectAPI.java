package com.bjike.goddess.socialinsurance.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.socialinsurance.bo.SocialInsuranceCollectBO;
import com.bjike.goddess.socialinsurance.dto.SocialInsuranceCollectDTO;

import java.util.List;

/**
 * 社会保险汇总
 * @Author: [caiwenxian]
 * @Date: [2017-12-21 18:03]
 * @Description: [ 社会保险汇总 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface SocialInsuranceCollectAPI {

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
