package com.bjike.goddess.marketactivitymanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketactivitymanage.bo.MarketServeSummaryBO;
import com.bjike.goddess.marketactivitymanage.bo.ServeSummaryBO;
import com.bjike.goddess.marketactivitymanage.dto.MarketServeSummaryDTO;
import com.bjike.goddess.marketactivitymanage.excel.SonPermissionObject;
import com.bjike.goddess.marketactivitymanage.to.GuidePermissionTO;
import com.bjike.goddess.marketactivitymanage.to.MarketServeSummaryTO;

import java.util.List;

/**
 * 市场招待汇总邮件发送业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-21 02:19 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MarketServeSummaryAPI {


    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {

        return null;
    }

    /**
     * 工能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 根据id查询市场招待汇总邮件发送
     *
     * @param id 市场招待汇总邮件发送唯一标识
     * @return class MarketServeSummaryBO
     * @throws SerException
     */
    MarketServeSummaryBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 市场招待汇总邮件发送dto
     * @throws SerException
     */
    Long count(MarketServeSummaryDTO dto) throws SerException;

    /**
     * 分页查询市场招待汇总邮件发送
     *
     * @param dto 市场招待汇总dto
     * @return class MarketServeSummaryBO
     * @throws SerException
     */
    List<MarketServeSummaryBO> list(MarketServeSummaryDTO dto) throws SerException;

    /**
     * 保存市场招待汇总
     *
     * @param to 市场招待汇总to
     * @return class MarketServeSummaryBO
     * @throws SerException
     */
    MarketServeSummaryBO save(MarketServeSummaryTO to) throws SerException;

    /**
     * 根据id删除市场招待汇总
     *
     * @param id 市场招待汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 编辑市场招待汇总邮件发送
     *
     * @param to 市场招待汇总to
     * @throws SerException
     */
    void update(MarketServeSummaryTO to) throws SerException;

    /**
     * 解冻市场招待汇总邮件发送
     *
     * @param id 市场招待汇总邮件发送唯一标识
     * @throws SerException
     */
    void thaw(String id) throws SerException;

    /**
     * 冻结市场招待汇总邮件发送
     *
     * @param id 市场招待汇总邮件发送唯一标识
     * @throws SerException
     */
    void congeal(String id) throws SerException;

    /**
     * 市场招待汇总
     *
     * @param type 汇总类型
     * @param projectGroups 部门/项目组
     * @param startTimeString 起始时间
     * @param endTimeString 结束时间
     * @return class MarketServeSummaryVO
     * @throws SerException
     */
    default List<ServeSummaryBO> summarize(Boolean type, String[] projectGroups, String startTimeString, String endTimeString) throws SerException{return  null;};
    /**
     * 定时器检测要发送的邮件
     */
    default void checkSendEmail() throws SerException {
        return;
    }
}