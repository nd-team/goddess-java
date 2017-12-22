package com.bjike.goddess.businessabilityshow.api;

import com.bjike.goddess.businessabilityshow.bo.BACollectEchartBO;
import com.bjike.goddess.businessabilityshow.bo.BusinessAbilityBO;
import com.bjike.goddess.businessabilityshow.bo.BusinessAbilityCollectBO;
import com.bjike.goddess.businessabilityshow.dto.BusinessAbilityDTO;
import com.bjike.goddess.businessabilityshow.to.BusinessAbilityTO;
import com.bjike.goddess.businessabilityshow.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 项目合同基本信息业务接口
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-16 02:50 ]
 * @Description: [ 项目合同基本信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BusinessAbilityAPI {

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
     * 获取商务能力列表
     *
     * @param
     * @return class
     * @version v1
     */
    List<BusinessAbilityBO> list(BusinessAbilityDTO dto) throws SerException;

    /**
     * 获取商务能力总数
     *
     * @param
     * @return class
     * @version v1
     */
    Long count(BusinessAbilityDTO dto) throws SerException;

    /**
     * 获取单个商务能力
     *
     * @param
     * @return class
     * @version v1
     */
    BusinessAbilityBO getOne(String id) throws SerException;

    /**
     * 添加商务能力
     *
     * @param
     * @return class
     * @version v1
     */
    void add(BusinessAbilityTO to) throws SerException;

    /**
     * 更新商务能力
     *
     * @param
     * @return class
     * @version v1
     */
    void update(BusinessAbilityTO to) throws SerException;

    /**
     * 删除一个商务能力
     *
     * @param
     * @return class
     * @version v1
     */
    void delete(String id) throws SerException;

    /**
     * 导入商务能力
     *
     * @param
     * @return class
     * @version v1
     */
    void importExcel(List<BusinessAbilityTO> tos) throws SerException;

    /**
     * 导出商务能力
     *
     * @param
     * @return class
     * @version v1
     */
    byte[] exportExcel(BusinessAbilityDTO dto) throws SerException;

    /**
     * 商务能力汇总
     *
     * @param
     * @return class
     * @version v1
     */
    List<BusinessAbilityCollectBO> businessAbilitycollect() throws SerException;

    /**
     * 公司参与项目情况汇总图形数据
     *
     * @param
     * @return class
     * @version v1
     */
    List<BACollectEchartBO> businessAbilityEchartCollect() throws SerException;

    /**
     * 公司参与项目情况汇总图形数据
     *
     * @param
     * @return class
     * @version v1
     */
    List<BACollectEchartBO> businessProjectEchartcollect() throws SerException;


}