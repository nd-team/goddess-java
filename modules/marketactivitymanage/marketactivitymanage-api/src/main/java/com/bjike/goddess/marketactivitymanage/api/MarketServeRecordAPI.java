package com.bjike.goddess.marketactivitymanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketactivitymanage.bo.MarketServeRecordBO;
import com.bjike.goddess.marketactivitymanage.dto.MarketServeRecordDTO;
import com.bjike.goddess.marketactivitymanage.to.CustomerInfoTO;
import com.bjike.goddess.marketactivitymanage.to.GuidePermissionTO;
import com.bjike.goddess.marketactivitymanage.to.MarketServeRecordImprotTO;
import com.bjike.goddess.marketactivitymanage.to.MarketServeRecordTO;
import com.bjike.goddess.marketactivitymanage.type.AuditType;

import java.util.List;

/**
 * 市场招待记录业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-18T10:56:16.078 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MarketServeRecordAPI {

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
     * 根据id查询市场招待记录
     *
     * @param id 市场招待记录唯一标识
     * @return class MarketServeRecordBO
     * @throws SerException
     */
    MarketServeRecordBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 市场招待记录dto
     * @throws SerException
     */
    Long count(MarketServeRecordDTO dto) throws SerException;

    /**
     * 分页查询市场招待记录
     *
     * @param dto 市场招待记录dto
     * @return class MarketServeRecordBO
     * @throws SerException
     */
    List<MarketServeRecordBO> list(MarketServeRecordDTO dto) throws SerException;

    /**
     * 保存市场招待记录
     *
     * @param to 市场招待记录to
     * @return class MarketServeRecordBO
     * @throws SerException
     */
    MarketServeRecordBO save(MarketServeRecordTO to) throws SerException;

    /**
     * 根据id删除市场招待记录
     *
     * @param id 市场招待记录唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新市场招待记录
     *
     * @param to 市场招待记录to
     * @throws SerException
     */
    void update(MarketServeRecordTO to) throws SerException;

    /**
     * 资金模块意见
     *
     * @param to 市场招待记录to
     * @throws SerException
     */
    /**
     * 运营商务部资金模块审核
     *
     * @param id                市场招待记录唯一标识
     * @param fundModuleOpinion 运营商务部资金模块
     * @throws SerException
     */
    void fundModuleOpinion(String id, String fundModuleOpinion) throws SerException;

    /**
     * 决策层意见
     *
     * @param id                    市场招待记录唯一标识
     * @param executiveAuditOpinion 决策层审核意见
     * @throws SerException
     */
    void executiveOpinion(String id, AuditType executiveAuditOpinion) throws SerException;

    /**
     * 查看详情
     *
     * @param id 市场招待记录唯一标识
     * @return class MarketServeApplyBO
     * @throws SerException
     */
    MarketServeRecordBO checkDetails(String id) throws SerException;

    /**
     * 添加客户信息
     *
     * @param to 客户信息to
     * @throws SerException
     */
    void addClientInfo(CustomerInfoTO to) throws SerException;

    /**
     * 编辑客户信息
     *
     * @param to 客户信息to
     * @throws SerException
     */
    void editClientInfo(CustomerInfoTO to) throws SerException;

    /**
     * 查看所有的项目名
     *
     * @throws SerException
     */
    List<String> findAllProjectName() throws SerException;

    /**
     * 查看所有的地区
     *
     * @throws SerException
     */
    List<String> findAllAreas() throws SerException;
    /**
     * 导出Excel
     *
     * @param areas
     * @param startTime
     * @param endTime
     * @throws SerException
     */
    byte[] exportExcel(String[] areas, String startTime, String endTime) throws SerException;

    /**
     * 导出Excel
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;

    /**
     * 导入
     *
     * @param marketServeRecordImprotTOS 市场活动申请记录
     */
    void importExcel(List<MarketServeRecordImprotTO> marketServeRecordImprotTOS) throws SerException;
}