package com.bjike.goddess.foreigntax.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.foreigntax.bo.LandTaxReportBO;
import com.bjike.goddess.foreigntax.dto.LandTaxReportDTO;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import com.bjike.goddess.foreigntax.to.LandTaxReportTO;

import java.util.List;

/**
 * 国地税报表业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-17 09:13 ]
 * @Description: [ 国地税报表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface LandTaxReportAPI {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 国地税报表列表总条数
     */
    default Long count(LandTaxReportDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个国地税报表
     *
     * @return class LandTaxReportBO
     */
    default LandTaxReportBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 国地税报表
     *
     * @param dto 国地税报表dto
     * @return class LandTaxReportBO
     * @throws SerException
     */
    default List<LandTaxReportBO> list(LandTaxReportDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加国地税报表
     *
     * @param to 国地税报表数据to
     * @return class LandTaxReportBO
     * @throws SerException
     */
    default LandTaxReportBO insert(LandTaxReportTO to) throws SerException {
        return null;
    }

    /**
     * 编辑国地税报表
     *
     * @param to 国地税报表数据to
     * @return class LandTaxReportBO
     * @throws SerException
     */
    default LandTaxReportBO edit(LandTaxReportTO to) throws SerException {
        return null;
    }

    /**
     * 根据id删除国地税报表
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }
}