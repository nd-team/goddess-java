package com.bjike.goddess.marketactivitymanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.marketactivitymanage.bo.MarketServeApplyBO;
import com.bjike.goddess.marketactivitymanage.dto.MarketServeApplyDTO;
import com.bjike.goddess.marketactivitymanage.entity.MarketServeApply;
import com.bjike.goddess.marketactivitymanage.to.CustomerInfoTO;
import com.bjike.goddess.marketactivitymanage.to.MarketServeApplyTO;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * 市场招待申请业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-18T10:37:08.047 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MarketServeApplySer extends Ser<MarketServeApply, MarketServeApplyDTO> {

    /**
     * 查询市场招待申请
     *
     * @param dto 市场招待申请dto
     * @return class MarketServeApplyBO
     * @throws SerException
     */
    List<MarketServeApplyBO> list(MarketServeApplyDTO dto) throws SerException;

    /**
     * 保存市场招待申请
     *
     * @param to 市场招待申请to
     * @return class MarketServeApplyBO
     * @throws SerException
     */
    MarketServeApplyBO save(MarketServeApplyTO to) throws SerException;

    /**
     * 根据id删除市场招待申请
     *
     * @param id 市场招待申请id
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新市场招待申请
     *
     * @param to 市场招待申请to
     * @throws SerException
     */
    void update(MarketServeApplyTO to) throws SerException;

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
     * @param to 市场招待申请to
     * @throws SerException
     */
    void fundModuleOpinion(MarketServeApplyTO to) throws SerException;

    /**
     * 决策层意见
     *
     * @param to 市场招待申请to
     * @throws SerException
     */
    void executiveOpinion(MarketServeApplyTO to) throws SerException;

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
     * @param id 市场招待申请唯一标识
     * @return class MarketServeApplyBO
     * @throws SerException
     */
    MarketServeApplyBO checkDetails(String id) throws SerException;
}