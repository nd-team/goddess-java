package com.bjike.goddess.marketactivitymanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketactivitymanage.bo.MarketServeApplyBO;
import com.bjike.goddess.marketactivitymanage.dto.MarketServeApplyDTO;
import com.bjike.goddess.marketactivitymanage.entity.MarketServeApply;
import com.bjike.goddess.marketactivitymanage.service.MarketServeApplySer;
import com.bjike.goddess.marketactivitymanage.to.CustomerInfoTO;
import com.bjike.goddess.marketactivitymanage.to.MarketServeApplyTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * 市场招待申请业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-18T10:37:08.056 ]
 * @Description: [ 市场招待申请业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("marketServeApplyApiImpl")
public class MarketServeApplyApiImpl implements MarketServeApplyAPI {

    @Autowired
    private MarketServeApplySer marketServeApplySer;

    /**
     * 根据id查询市场招待申请
     *
     * @param id 市场招待申请唯一标识
     * @return class MarketServeApplyBO
     * @throws SerException
     */
    @Override
    public MarketServeApplyBO findById(String id) throws SerException {
        MarketServeApply model = marketServeApplySer.findById(id);
        return BeanTransform.copyProperties(model, MarketServeApplyBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 市场招待申请dto
     * @throws SerException
     */
    @Override
    public Long count(MarketServeApplyDTO dto) throws SerException {
        return marketServeApplySer.count(dto);
    }

    /**
     * 分页查询市场招待申请
     *
     * @param dto 市场招待申请dto
     * @return class MarketServeApplyBO
     * @throws SerException
     */
    @Override
    public List<MarketServeApplyBO> list(MarketServeApplyDTO dto) throws SerException {
        return marketServeApplySer.list(dto);
    }

    /**
     * 保存市场招待申请
     *
     * @param to 市场招待申请to
     * @return class MarketServeApplyBO
     * @throws SerException
     */
    @Override
    public MarketServeApplyBO save(MarketServeApplyTO to) throws SerException {
        return marketServeApplySer.save(to);
    }

    /**
     * 根据id删除市场招待申请
     *
     * @param id 市场招待申请唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        marketServeApplySer.remove(id);
    }

    /**
     * 更新市场招待申请
     *
     * @param to 市场招待申请to
     * @throws SerException
     */
    @Override
    public void update(MarketServeApplyTO to) throws SerException {
        marketServeApplySer.update(to);
    }

    /**
     * 添加客户信息
     *
     * @param to 客户信息to
     * @throws SerException
     */
    @Override
    public void addClientInfo(CustomerInfoTO to) throws SerException {
        marketServeApplySer.addClientInfo(to);
    }

    /**
     * 运营商务部资金模块意见
     *
     * @param to 市场招待申请to
     * @throws SerException
     */
    @Override
    public void fundModuleOpinion(MarketServeApplyTO to) throws SerException {
        marketServeApplySer.fundModuleOpinion(to);
    }

    /**
     * 决策层意见
     *
     * @param to 市场招待申请to
     * @throws SerException
     */
    @Override
    public void executiveOpinion(MarketServeApplyTO to) throws SerException {
        marketServeApplySer.executiveOpinion(to);
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
        marketServeApplySer.importFile(inputStream, targetPath);
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
        return marketServeApplySer.exportFile(filePath);
    }

    /**
     * 查看详情
     *
     * @param id 市场招待申请唯一标识
     * @return class MarketServeApplyBO
     * @throws SerException
     */
    @Override
    public MarketServeApplyBO checkDetails(String id) throws SerException {
        return marketServeApplySer.checkDetails(id);
    }
}