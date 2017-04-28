package com.bjike.goddess.marketactivitymanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.marketactivitymanage.bo.MarketServeRecordBO;
import com.bjike.goddess.marketactivitymanage.dto.MarketServeRecordDTO;
import com.bjike.goddess.marketactivitymanage.entity.MarketServeRecord;
import com.bjike.goddess.marketactivitymanage.to.CustomerInfoTO;
import com.bjike.goddess.marketactivitymanage.to.MarketServeRecordTO;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * 市场招待记录业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-18T10:56:16.081 ]
 * @Description: [ 市场招待记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MarketServeRecordSer extends Ser<MarketServeRecord, MarketServeRecordDTO> {

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
     * @param to
     * @return
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
     * @param to
     * @throws SerException
     */
    void update(MarketServeRecordTO to) throws SerException;

    /**
     * 添加客户信息
     *
     * @param to 客户信息to
     * @throws SerException
     */
    void addClientInfo(CustomerInfoTO to) throws SerException;

    /**
     * 资金模块意见
     *
     * @param to 市场招待记录to
     * @throws SerException
     */
    void fundModuleOpinion(MarketServeRecordTO to) throws SerException;

    /**
     * 决策层意见
     *
     * @param to 市场招待记录to
     * @throws SerException
     */
    void executiveOpinion(MarketServeRecordTO to) throws SerException;

    /**
     * 导入
     *
     * @param inputStream 目标路径
     * @param targetPath  文件输入流
     * @throws SerException
     */
    void importFile(InputStream inputStream, String targetPath) throws SerException;

    /**
     * 导出文件
     *
     * @param filePath 需要导出的文件的路径
     * @return class OutputStream
     * @throws SerException
     */
    OutputStream exportFile(String filePath) throws SerException;

    /**
     * 查看详情
     *
     * @param id 市场招待记录唯一标识
     * @return class MarketServeApplyBO
     * @throws SerException
     */
    MarketServeRecordBO checkDetails(String id) throws SerException;
}