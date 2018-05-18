package com.bjike.goddess.socialinsurance.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.socialinsurance.bo.SICollectEchartBO;
import com.bjike.goddess.socialinsurance.bo.SocialInsuranceCollectBO;
import com.bjike.goddess.socialinsurance.dto.SocialInsuranceCollectDTO;
import com.bjike.goddess.socialinsurance.entity.SocialInsurance;
import com.bjike.goddess.socialinsurance.excel.SonPermissionObject;
import com.bjike.goddess.socialinsurance.to.GuidePermissionTO;

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
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 工能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

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
     * 记账凭证汇总
     *
     * @param dto
     * @return class
     * @version v1
     */
    List<SocialInsuranceCollectBO> voucherCollect(SocialInsuranceCollectDTO dto) throws SerException;


    /**
     * 地区汇总
     *
     * @param dto
     * @return class
     * @version v1
     */
    List<SocialInsuranceCollectBO> areaCollect(SocialInsuranceCollectDTO dto) throws SerException;

    /**
     * 个人分析柱状图图表展示
     *
     * @param dto
     * @return class
     * @version v1
     */
    SICollectEchartBO personalCollectEchart(SocialInsuranceCollectDTO dto) throws SerException;

    /**
     * 部门分析柱状图图表展示
     *
     * @param dto
     * @return class
     * @version v1
     */
    SICollectEchartBO departmentCollectEchart(SocialInsuranceCollectDTO dto) throws SerException;

    /**
     * 地区分析柱状图图表展示
     *
     * @param dto
     * @return class
     * @version v1
     */
    SICollectEchartBO areaCollectEchart(SocialInsuranceCollectDTO dto) throws SerException;

    /**
     * 个人分析饼型图图表展示
     *
     * @param dto
     * @return class
     * @version v1
     */
    SICollectEchartBO personalCollectPieEchart(SocialInsuranceCollectDTO dto) throws SerException;

    /**
     * 部门分析饼型图图表展示
     *
     * @param dto
     * @return class
     * @version v1
     */
    SICollectEchartBO departmentCollectPieEchart(SocialInsuranceCollectDTO dto) throws SerException;

    /**
     * 地区分析饼型图图表展示
     *
     * @param dto
     * @return class
     * @version v1
     */
    SICollectEchartBO areaCollectPieEchart(SocialInsuranceCollectDTO dto) throws SerException;

    /**
     * 修改社保缴费状态
     *
     * @param
     * @return class
     * @version v1
     */
    void updateSocialInsurancePayStatus(String department, String startDate, String endDate) throws SerException;

}
