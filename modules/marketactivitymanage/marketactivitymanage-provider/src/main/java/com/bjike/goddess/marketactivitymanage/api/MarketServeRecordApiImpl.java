package com.bjike.goddess.marketactivitymanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketactivitymanage.bo.MarketServeRecordBO;
import com.bjike.goddess.marketactivitymanage.dto.MarketServeRecordDTO;
import com.bjike.goddess.marketactivitymanage.entity.MarketServeRecord;
import com.bjike.goddess.marketactivitymanage.service.MarketServeRecordSer;
import com.bjike.goddess.marketactivitymanage.to.CustomerInfoTO;
import com.bjike.goddess.marketactivitymanage.to.MarketServeRecordTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * 市场招待记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-18T10:56:16.099 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("marketServeRecordApiImpl")
public class MarketServeRecordApiImpl implements MarketServeRecordAPI {

    @Autowired
    private MarketServeRecordSer marketServeRecordSer;

    /**
     * 根据id查询市场招待记录
     *
     * @param id 市场招待记录唯一标识
     * @return class MarketServeRecordBO
     * @throws SerException
     */
    @Override
    public MarketServeRecordBO findById(String id) throws SerException {
        MarketServeRecord model = marketServeRecordSer.findById(id);
        return BeanTransform.copyProperties(model, MarketServeRecordBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 市场招待记录dto
     * @throws SerException
     */
    @Override
    public Long count(MarketServeRecordDTO dto) throws SerException {
        return marketServeRecordSer.count(dto);
    }

    /**
     * 分页查询市场招待记录
     *
     * @param dto 市场招待记录dto
     * @return class MarketServeRecordBO
     * @throws SerException
     */
    @Override
    public List<MarketServeRecordBO> list(MarketServeRecordDTO dto) throws SerException {
        return marketServeRecordSer.list(dto);
    }

    /**
     * 保存市场招待记录
     *
     * @param to 市场招待记录to
     * @return class MarketServeRecordBO
     * @throws SerException
     */
    @Override
    public MarketServeRecordBO save(MarketServeRecordTO to) throws SerException {
        return marketServeRecordSer.save(to);
    }

    /**
     * 根据id删除市场招待记录
     *
     * @param id 市场招待记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        marketServeRecordSer.remove(id);
    }

    /**
     * 更新市场招待记录
     *
     * @param to 市场招待记录to
     * @throws SerException
     */
    @Override
    public void update(MarketServeRecordTO to) throws SerException {
        marketServeRecordSer.update(to);
    }

    /**
     * 添加客户信息
     *
     * @param to 客户信息to
     * @throws SerException
     */
    @Override
    public void addClientInfo(CustomerInfoTO to) throws SerException {
        marketServeRecordSer.addClientInfo(to);
    }

    /**
     * 运营商务部资金模块意见
     *
     * @param to 市场招待记录to
     * @throws SerException
     */
    @Override
    public void fundModuleOpinion(MarketServeRecordTO to) throws SerException {
        marketServeRecordSer.fundModuleOpinion(to);
    }

    /**
     * 决策层意见
     *
     * @param to 市场招待记录to
     * @throws SerException
     */
    @Override
    public void executiveOpinion(MarketServeRecordTO to) throws SerException {
        marketServeRecordSer.executiveOpinion(to);
    }

    /**
     * 导入文件
     *
     * @param inputStream 目标路径
     * @param targetPath 文件输入流
     * @throws SerException
     */
    @Override
    public void importFile(InputStream inputStream, String targetPath) throws SerException {
        marketServeRecordSer.importFile(inputStream, targetPath);
    }

    /**
     * 导出文件
     *
     * @param filePath 需要导出的文件的路径
     * @return class OutputStream
     * @throws SerException
     */
    @Override
    public OutputStream exportFile(String filePath) throws SerException {
        return marketServeRecordSer.exportFile(filePath);
    }

    /**
     * 查看详情
     *
     * @param id 市场招待记录唯一标识
     * @return class MarketServeRecordBO
     * @throws SerException
     */
    @Override
    public MarketServeRecordBO checkDetails(String id) throws SerException {
        return marketServeRecordSer.checkDetails(id);
    }

}